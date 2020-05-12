/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Metodos.LecturaArchivos;
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
import static edd.proyecto2_201801229.EDDProyecto2_201801229.indexBloque;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.nodoRed;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.red;
import javax.swing.JOptionPane;

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

                System.out.println("Estoy escuchando...");
                byte[] buffer = new byte[1024];
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                System.out.println("Encontre peticion");
                String mensaje = new String(peticion.getData());
                System.out.println(mensaje);
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
                } else if (mensaje.contains("Eliminar:")) {
                    String[] puerto = mensaje.trim().split(":");
                    try {
                        Long isbn = new Long(puerto[1].trim());
                        int puert = isbn.intValue();
                        NodoRed nodo = red.buscar(puert);
                        System.out.println(mensaje + " " + puert);
                        int puertonodo = nodo.getPuerto();
                        red.eliminar(nodo);
                        NodoRed inicio = red.getInicio();
                        String agregar = "Eliminen:" + nodo.getPuerto();
                        byte[] buff = new byte[1024];
                        buff = agregar.getBytes();
                        while (inicio != null) {
                            if (inicio.getPuerto() != puertonodo && inicio.getPuerto() != nodoRed.getSocketUDP().getLocalPort()) {

                                DatagramPacket agreguen = new DatagramPacket(buff, buff.length, direccion, inicio.getPuerto());
                                socketUDP.send(agreguen);
                            }
                            inicio = inicio.getSiguiente();
                        }
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error NumberFormatException value: " + puerto[1]);
                    }
                } else if (mensaje.contains("Bloques")) {
                    System.out.print(mensaje);
                    LecturaArchivos la = new LecturaArchivos();
                    la.leerBloque(mensaje.trim());
                    indexBloque++;
                    System.out.println(indexBloque);
                } else if (mensaje.contains("Eliminen:")) {
                    String[] puerto = mensaje.trim().split(":");
                    try {
                        Long isbn = new Long(puerto[1].trim());
                        int puert = isbn.intValue();
                        NodoRed nodo = red.buscar(puert);
                        System.out.println(mensaje + " " + puert);
                        red.eliminar(nodo);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error NumberFormatException value: " + puerto[1]);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(NodoRed.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error");
            }
        }
    }

    public void enviar(NodoRed inicio, String ruta) {
        try {
            NodoRed aux = red.getInicio();
            while (aux != null) {
                if (aux.getPuerto() != nodoRed.getSocketUDP().getLocalPort()) {

                    byte[] buffer = new byte[1024];
                    buffer = ruta.getBytes();
                    DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, aux.getPuerto());

                    socketUDP.send(respuesta);

                }
                aux = aux.getSiguiente();
            }
        } catch (IOException ex) {
            Logger.getLogger(NodoRed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salir() {

        try {

            NodoRed aux = red.getInicio();
            String lista = "Eliminar:" + nodoRed.getSocketUDP().getLocalPort();
            byte[] list = new byte[1024];
            list = lista.getBytes();

            DatagramPacket respuesta = new DatagramPacket(list, list.length, direccion, aux.getPuerto());

            socketUDP.send(respuesta);

        } catch (IOException ex) {
            Logger.getLogger(NodoRed.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void conectar(int puerto) {
        String conectar = "Conectar";
        byte[] buffer = new byte[1024];
        try {
            buffer = conectar.getBytes();
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puerto);

            socketUDP.send(respuesta);
             JOptionPane.showMessageDialog(null,
                    "Conexion Exitosa",
                    "Conexion", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(NodoRed.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                    "Error en la conexion: "+ ex.toString(),
                    "Conexion", JOptionPane.ERROR_MESSAGE);
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
