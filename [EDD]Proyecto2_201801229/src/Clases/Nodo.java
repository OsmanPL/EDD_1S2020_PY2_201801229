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

import static edd.proyecto2_201801229.EDDProyecto2_201801229.tablaHash;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.mavl;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.bloques;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.red;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.cb;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.nodoRed;

/**
 *
 * @author l4kz4
 */
public class Nodo extends Thread {

    private InetAddress direccion;
    private DatagramSocket socketUDP;

    public Nodo() {
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

        while (true) {
            try {
                byte[] buffer = new byte[1024];
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                String mensaje = new String(peticion.getData());
                if (mensaje.contains("Conectar")) {
                    NodoRed nodo = new NodoRed();
                    nodo.setPuerto(peticion.getPort());
                    NodoRed inicio = red.getInicio();
                    red.insertar(nodo);
                    String agregar = "Agregar:" + nodo.getPuerto();
                    byte[] buff = new byte[1024];
                    buff = agregar.getBytes();
                    while (inicio != null) {
                        if (inicio.getPuerto() != nodo.getPuerto() && inicio.getPuerto() != nodoRed.getSocketUDP().getLocalPort()) {
                            DatagramPacket agreguen = new DatagramPacket(buff, buff.length, direccion, inicio.getPuerto());
                            socketUDP.send(agreguen);
                        }
                        inicio = inicio.getSiguiente();
                    }
                    inicio = red.getInicio();
                    while (inicio != null) {
                        if (inicio.getPuerto() != nodo.getPuerto()) {
                            String lista = "Agregar:" + inicio.getPuerto();
                            byte[] list = new byte[1024];
                            list = lista.getBytes();
                            DatagramPacket agreguen = new DatagramPacket(list, list.length, direccion, nodo.getPuerto());
                            socketUDP.send(agreguen);
                        }
                        inicio = inicio.getSiguiente();
                    }
                } else if (mensaje.contains("Agregar:")) {
                    String[] puerto = mensaje.trim().split(":");
                    try {
                        Long isbn = new Long(puerto[1].trim());
                        int puert = isbn.intValue();
                        NodoRed nodo = new NodoRed();
                        nodo.setPuerto(puert);
                        red.insertar(nodo);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error NumberFormatException value: " + puerto[1]);
                    }
                }
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

}
