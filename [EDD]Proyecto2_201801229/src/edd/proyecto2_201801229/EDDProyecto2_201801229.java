/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2_201801229;

import java.util.Scanner;

import Clases.*;
import Estructuras.*;
import Interfaz.*;
import Metodos.*;
import java.io.File;
import java.sql.Timestamp;

/**
 *
 * @author l4kz4
 */
public class EDDProyecto2_201801229 {

    /**
     * @param args the command line arguments
     */
    public static MetodosAVL mavl = new MetodosAVL();
    public static MetodosTablaHash tablaHash = new MetodosTablaHash(45);
    public static ListaSimpleNodos red = new ListaSimpleNodos();
    public static ListaDobleBloques bloques = new ListaDobleBloques();
    public static Nodo nodoRed;
    public static int indexBloque = 0;
    public static CrearBloque cb = new CrearBloque();
    public static void main(String[] args) {
        calularIndex();
        nodoRed = new Nodo();
        nodoRed.start();
        NodoRed nodo = new NodoRed();
        nodo.setPuerto(nodoRed.getSocketUDP().getLocalPort());
        red.insertar(nodo);
        Login login = new Login();
        login.setVisible(true);
    }

    public static void calularIndex() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        File carpeta = new File("Bloques");
        if (carpeta.exists()) {
            System.out.println("Existe");
            String[] listado = carpeta.list();
            if (listado == null || listado.length == 0) {
                System.out.println("No hay elementos dentro de la carpeta actual");
            } else {
                for (int i = 0; i < listado.length; i++) {
                    System.out.println(listado[i]);
                    LecturaArchivos la = new LecturaArchivos();
                    la.leerBloque("Bloques/"+listado[i]);
                    indexBloque = i+1;
                }
                
                System.out.println("Siguiente Bloque: "+ indexBloque);
            }
        } else {
            System.out.println("No Existe");
            carpeta.mkdir();
        }
    }
}
