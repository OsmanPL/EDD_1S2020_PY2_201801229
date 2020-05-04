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

import static edd.proyecto2_201801229.EDDProyecto2_201801229.tablaHash;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.mavl;
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
    LecturaArchivos ArchivoJson = new LecturaArchivos();
    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
    }
}
