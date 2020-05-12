/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Clases.*;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.indexBloque;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
            if (nuevo.getPrevioushash() == null) {
                nuevo.setPrevioushash("0000");
                nuevo.setNONCE(0);
                try {
                    nuevo.setHash(toHexString(getSHA("" + nuevo.getIndex() + nuevo.getTimestamp() + nuevo.getPrevioushash() + nuevo.getData() + nuevo.getNONCE())));
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(ListaDobleBloques.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(nuevo.getHash());
                while (!nuevo.getHash().startsWith("0000")) {
                    nuevo.setNONCE(nuevo.getNONCE() + 1);
                    try {
                        nuevo.setHash(toHexString(getSHA("" + nuevo.getIndex() + nuevo.getTimestamp() + nuevo.getPrevioushash() + nuevo.getData() + nuevo.getNONCE())));
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(ListaDobleBloques.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(nuevo.getHash());
                }
            }
            setPrimero(nuevo);
            setUltimo(nuevo);
        } else {
            if (nuevo.getPrevioushash() == null) {
                nuevo.setPrevioushash(getPrimero().getHash());
                nuevo.setNONCE(0);
                nuevo.setHash(sha256("" + nuevo.getIndex() + nuevo.getTimestamp() + nuevo.getPrevioushash() + nuevo.getData() + nuevo.getNONCE()));
                System.out.println(nuevo.getHash());
                while (!nuevo.getHash().startsWith("0000")) {
                    nuevo.setNONCE(nuevo.getNONCE() + 1);
                    nuevo.setHash(sha256("" + nuevo.getIndex() + nuevo.getTimestamp() + nuevo.getPrevioushash() + nuevo.getData() + nuevo.getNONCE()));
                    System.out.println(nuevo.getHash());
                }
            }
            nuevo.setSiguiente(getPrimero());
            getPrimero().setAnterior(nuevo);
            setPrimero(nuevo);
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
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String HexAux = Integer.toHexString(0xff & hash[i]);
            if (HexAux.length() == 1) {
                hexString.append('0');
            }
            hexString.append(HexAux);
        }

        return hexString.toString();
    }

    public void iniciarGrafica() {
        String grafica = grafica();
        FileWriter flwriter = null;
        try {
            //crea el flujo para escribir en el archivo
            flwriter = new FileWriter("Bloques.txt");
            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            bfwriter.write(grafica);
            //cierra el buffer intermedio
            bfwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {//cierra el flujo principal
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            ProcessBuilder pbuilder;
            String direccionPng = "Bloques.png";
            String direccionDot = "Bloques.txt";
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", direccionPng, direccionDot);
            pbuilder.redirectErrorStream(true);
            //Ejecuta el proceso
            pbuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String direccionPng = "Bloques.png";
            File objetofile = new File(direccionPng);
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            System.out.println(ex);

        }
    }

    public String grafica() {
        String grafica = "digraph ListaBloques{\nrankdir=\"LR\";\nnode[shape=rect];\n";
        Bloque aux = ultimo;
        while (aux != null) {
            grafica += "node" + aux.getIndex() + "[label=\"Index: " + aux.getIndex() + "\nTimestamp: " + aux.getTimestamp()
                    + "\nNONCE: " + aux.getNONCE() + "\nPreoviousHash: " + aux.getPrevioushash() + "\nHash: " + aux.getHash() + "\"];\n";
            if (aux.getAnterior() != null && aux.getAnterior().getIndex()!= aux.getIndex()) {
                grafica += "node" + aux.getIndex() + " -> node" + aux.getAnterior().getIndex() + ";\n";
                grafica += "node" + aux.getAnterior().getIndex() + " -> node" + aux.getIndex() + ";\n";
            }
            aux = aux.getAnterior();
        }
        grafica += "}\n";
        return grafica;
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
