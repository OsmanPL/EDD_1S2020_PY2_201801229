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
public class ListaSimpleUsuarios {
    private Usuario inicio;
    
    public boolean listaVacia(){
        if (inicio!=null) {
            return false;
        }else{
            return true;
        }
    }
    
    public void insertar(Usuario nuevoUsuario){
        if (listaVacia()) {
            inicio = nuevoUsuario;
        }else{
            nuevoUsuario.setSiguiente(inicio);
            inicio = nuevoUsuario;
        }
    }
    
    public boolean borrar(int carnetBorrar){
        Usuario aux = inicio;
        Usuario aux2 = inicio;
        boolean borro = false;
        while(aux!=null){
            if (carnetBorrar == aux.getCarnet() && aux == inicio) {
                inicio = aux.getSiguiente();
                borro = true;
                break;
            }
            else if (carnetBorrar == aux.getCarnet() && aux != inicio) {
                aux2.setSiguiente(aux.getSiguiente());
                aux = null;
                borro = true;
                break;
            }
            aux2 = aux;
            aux = aux.getSiguiente();
        }
        return borro;
    }
    
    public Usuario buscar(int carnetBorrar){
        Usuario aux = inicio;
        while(aux!=null){
            if (carnetBorrar == aux.getCarnet()) {
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }
    public Usuario ingresar(int carnetBorrar, String password){
        Usuario aux = inicio;
        while(aux!=null){
            if (carnetBorrar == aux.getCarnet() && password.equals(aux.getPassword())) {
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }
}
