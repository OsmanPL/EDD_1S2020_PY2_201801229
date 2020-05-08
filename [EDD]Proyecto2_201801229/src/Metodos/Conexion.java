/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import Clases.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author l4kz4
 */
public class Conexion {

    public void enviar(NodoRed inicio, String ruta, NodoRed actual) {
        NodoRed aux = inicio;

        byte[] buffer = new byte[1024];
        while (aux != null) {
            if (aux != actual) {
                try {
                    buffer = ruta.getBytes();
                    DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, aux.getDireccion(), aux.getSocketUDP().getLocalPort());

                    actual.getSocketUDP().send(respuesta);
                } catch (IOException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            aux = aux.getSiguiente();
        }

    }
}
