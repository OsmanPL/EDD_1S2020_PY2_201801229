/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Clases.*;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.cb;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author l4kz4
 */
public class MetodosTablaHash {

    private TablaHashNode[] tablaHash;
    private int tamaño;
    private ArrayList<Integer> carnets = new ArrayList<Integer>();

    public MetodosTablaHash(int tamaño) {
        this.tamaño = tamaño;
        this.tablaHash = new TablaHashNode[this.tamaño];
    }

    public int FuncionHash(int carnet) {
        return carnet % tamaño;
    }

    public void insertar(Usuario nuevoUsuario) {
        int valorHash = FuncionHash(nuevoUsuario.getCarnet());
        if (carnets.contains(nuevoUsuario.getCarnet())) {
            JOptionPane.showMessageDialog(null,
                    "El carnet esta repetido",
                    "Usuario", JOptionPane.ERROR_MESSAGE);
        } else {
            carnets.add(nuevoUsuario.getCarnet());
            if (tablaHash[valorHash] != null) {
                tablaHash[valorHash].insertar(nuevoUsuario);
                cb.crearUsuario(nuevoUsuario);
            } else {
                TablaHashNode nuevoNodo = new TablaHashNode();
                tablaHash[valorHash] = nuevoNodo;
                tablaHash[valorHash].insertar(nuevoUsuario);
                cb.crearUsuario(nuevoUsuario);
            }
        }

    }

    public Usuario ingresar(int carnet, String password) {
        int valorHash = FuncionHash(carnet);
        if (tablaHash[valorHash] != null) {
            Usuario buscarUsuario = tablaHash[valorHash].ingresar(carnet, password);
            if (buscarUsuario != null) {
                return buscarUsuario;
            }
        }
        return null;
    }

    public Usuario Buscar(int carnet) {
        int valorHash = FuncionHash(carnet);
        if (tablaHash[valorHash] != null) {
            Usuario buscarUsuario = tablaHash[valorHash].buscar(carnet);
            if (buscarUsuario != null) {
                return buscarUsuario;
            }
        }
        return null;
    }

    public boolean Borrar(int carnet) {
        int valorHash = FuncionHash(carnet);
        if (tablaHash[valorHash] != null) {
            return tablaHash[valorHash].borrar(carnet);
        }
        return false;
    }

    public void modificar(int carnet, String nombre, String apellido, String carrera, String password) {
        Usuario modificarUsuario = Buscar(carnet);
        if (modificarUsuario != null) {
            modificarUsuario.setApellido(apellido);
            modificarUsuario.setNombre(nombre);
            modificarUsuario.setPassword(password);
            modificarUsuario.setCarrera(carrera);
            cb.EditarUsuario(modificarUsuario);
        } else {
            System.out.println("El Uuario No Existe");
        }
    }

    public void graficar() {
        String grafica = "digraph TablaHash{\nrankdir=\"LR\";\n node[shape=rect];\n";
        grafica += "parent[label=<\n<table border='1' cellborder='1'>\n";
        int i = 0;
        for (TablaHashNode tn : tablaHash) {
            grafica += "<tr><td port='port_" + i + "' HEIGHT=\"100\">" + i + "</td></tr>";
            i++;
        }
        i = 0;
        grafica += "</table>\n>];";
        for (TablaHashNode tn : tablaHash) {
            if (tn != null) {
                if (tn.getListaUsuarios() != null) {
                    grafica += tn.getListaUsuarios().grafica(i);
                    grafica += "parent:port_" + i + " -> " + tn.getListaUsuarios().getInicio().getCarnet() + " [lhead=Usuario" + i + "];\n";
                }

            }
            i++;
        }
        grafica += "}";
        FileWriter flwriter = null;
        try {
            //crea el flujo para escribir en el archivo
            flwriter = new FileWriter("TablaHash.txt");
            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter bfwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("TablaHash.txt"), "utf-8"));
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
            String direccionPng = "TablaHash.png";
            String direccionDot = "TablaHash.txt";
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", direccionPng, direccionDot);
            pbuilder.redirectErrorStream(true);
            //Ejecuta el proceso
            pbuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
         try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(ListaDobleBloques.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
