/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;
import Clases.*;
/**
 *
 * @author l4kz4
 */
public class MetodosTablaHash {
    private TablaHashNode[] tablaHash ; 
    private int tamaño;

    public MetodosTablaHash(int tamaño) {
        this.tamaño = tamaño;
        this.tablaHash = new TablaHashNode[this.tamaño];
    }
    
    public int FuncionHash(int carnet){
        return carnet % tamaño;
    }
    public void insertar(Usuario nuevoUsuario){
        int valorHash = FuncionHash(nuevoUsuario.getCarnet());
        if (tablaHash[valorHash]!=null) {
            tablaHash[valorHash].insertar(nuevoUsuario);
        }else{
            TablaHashNode nuevoNodo = new TablaHashNode();
            tablaHash[valorHash] = nuevoNodo;
            tablaHash[valorHash].insertar(nuevoUsuario);
        }
    }
    
    public Usuario ingresar(int carnet, String password){
        int valorHash = FuncionHash(carnet);
        if (tablaHash[valorHash]!=null) {
            Usuario buscarUsuario = tablaHash[valorHash].ingresar(carnet,password);
            if (buscarUsuario!=null) {
                return buscarUsuario;
            }
        }
        return null;
    }
    
    public Usuario Buscar(int carnet){
        int valorHash = FuncionHash(carnet);
        if (tablaHash[valorHash]!=null) {
            Usuario buscarUsuario = tablaHash[valorHash].buscar(carnet);
            if (buscarUsuario!=null) {
                return buscarUsuario;
            }
        }
        return null;
    }
    
    public boolean Borrar(int carnet){
        int valorHash = FuncionHash(carnet);
        if (tablaHash[valorHash]!=null) {
            return tablaHash[valorHash].borrar(carnet);
        }
        return false;
    }
    
    public void modificar(int carnet, String nombre, String apellido, String carrera, String password){
        Usuario modificarUsuario = Buscar(carnet);
        if (modificarUsuario!=null) {
            modificarUsuario.setApellido(apellido);
            modificarUsuario.setNombre(nombre);
            modificarUsuario.setPassword(password);
            modificarUsuario.setCarrera(carrera);
        }else{
            System.out.println("El Uuario No Existe");
        }
    }
}
