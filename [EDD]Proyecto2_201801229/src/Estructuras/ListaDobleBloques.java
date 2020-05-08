/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Clases.*;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.indexBloque;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author l4kz4
 */
public class ListaDobleBloques {

    private Bloque primero;
    private Bloque ultimo;

    public ListaDobleBloques() {
    }

    public boolean listaVacia() {
        return getPrimero() == null || getUltimo() == null ? true : false;
    }

    public void insertar(Bloque nuevo) {
        if (listaVacia()) {
            nuevo.setPrevioushash("0000");
            nuevo.setNONCE(0);
            nuevo.setHash(sha256("" + nuevo.getIndex() + nuevo.getTimestamp() + nuevo.getPrevioushash() + nuevo.getData() + nuevo.getNONCE()));
            System.out.println(nuevo.getHash());
            while (!nuevo.getHash().startsWith("0000")) {
                nuevo.setNONCE(nuevo.getNONCE() + 1);
                nuevo.setHash(sha256("" + nuevo.getIndex() + nuevo.getTimestamp() + nuevo.getPrevioushash() + nuevo.getData() + nuevo.getNONCE()));
                System.out.println(nuevo.getHash());
            }
            setPrimero(nuevo);
            setUltimo(nuevo);
            indexBloque++;
        } else {
            nuevo.setPrevioushash(getPrimero().getHash());
            nuevo.setNONCE(0);
            nuevo.setHash(sha256("" + nuevo.getIndex() + nuevo.getTimestamp() + nuevo.getPrevioushash() + nuevo.getData() + nuevo.getNONCE()));
            System.out.println(nuevo.getHash());
            while (!nuevo.getHash().startsWith("0000")) {
                nuevo.setNONCE(nuevo.getNONCE() + 1);
                nuevo.setHash(sha256("" + nuevo.getIndex() + nuevo.getTimestamp() + nuevo.getPrevioushash() + nuevo.getData() + nuevo.getNONCE()));
                System.out.println(nuevo.getHash());
            }
            nuevo.setSiguiente(getPrimero());
            getPrimero().setAnterior(nuevo);
            setPrimero(nuevo);
            indexBloque++;
        }
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));

        return hexString.toString();
    }

    /**
     * @return the primero
     */
    public Bloque getPrimero() {
        return primero;
    }

    /**
     * @param primero the primero to set
     */
    public void setPrimero(Bloque primero) {
        this.primero = primero;
    }

    /**
     * @return the ultimo
     */
    public Bloque getUltimo() {
        return ultimo;
    }

    /**
     * @param ultimo the ultimo to set
     */
    public void setUltimo(Bloque ultimo) {
        this.ultimo = ultimo;
    }
}
