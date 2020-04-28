/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import Estructuras.*;
/**
 *
 * @author l4kz4
 */
public class TablaHashNode {
    private ListaSimpleUsuarios listaUsuarios;

    public TablaHashNode() {
        listaUsuarios = new ListaSimpleUsuarios();
    }

    public void insertar(Usuario nuevo) {
        listaUsuarios.insertar(nuevo);
    }
    
    public boolean borrar(int carnet){
        return listaUsuarios.borrar(carnet);
    }
    
    public Usuario buscar(int carnet){
        return listaUsuarios.buscar(carnet);
    }

    /**
     * @return the listaUsuarios
     */
    public ListaSimpleUsuarios getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(ListaSimpleUsuarios listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
}
