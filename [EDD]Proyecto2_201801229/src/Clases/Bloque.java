/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author l4kz4
 */
public class Bloque {

    private int index;
    private Timestamp timestamp;
    private String data;
    private long NONCE;
    private String previoushash, hash;
    private Bloque siguiente, anterior;

    public Bloque(int index) {
        this.index = index;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the NONCE
     */
    public long getNONCE() {
        return NONCE;
    }

    /**
     * @param NONCE the NONCE to set
     */
    public void setNONCE(long NONCE) {
        this.NONCE = NONCE;
    }

    /**
     * @return the previoushash
     */
    public String getPrevioushash() {
        return previoushash;
    }

    /**
     * @param previoushash the previoushash to set
     */
    public void setPrevioushash(String previoushash) {
        this.previoushash = previoushash;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * @return the siguiente
     */
    public Bloque getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Bloque siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * @return the anterior
     */
    public Bloque getAnterior() {
        return anterior;
    }

    /**
     * @param anterior the anterior to set
     */
    public void setAnterior(Bloque anterior) {
        this.anterior = anterior;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

}
