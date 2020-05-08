/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author l4kz4
 */
public class NodoRed extends Thread {

    private InetAddress direccion;
    private DatagramSocket socketUDP;
    private NodoRed siguiente;

    public NodoRed() {
        try {
            direccion = InetAddress.getByName("localhost");
            socketUDP = new DatagramSocket();
        } catch (UnknownHostException ex) {
            Logger.getLogger(NodoRed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(NodoRed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        byte[] buffer = new byte[1024];
        while (true) {
            try {
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                
            } catch (IOException ex) {
                Logger.getLogger(NodoRed.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the direccion
     */
    public InetAddress getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(InetAddress direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the socketUDP
     */
    public DatagramSocket getSocketUDP() {
        return socketUDP;
    }

    /**
     * @param socketUDP the socketUDP to set
     */
    public void setSocketUDP(DatagramSocket socketUDP) {
        this.socketUDP = socketUDP;
    }

    /**
     * @return the siguiente
     */
    public NodoRed getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(NodoRed siguiente) {
        this.siguiente = siguiente;
    }

}
