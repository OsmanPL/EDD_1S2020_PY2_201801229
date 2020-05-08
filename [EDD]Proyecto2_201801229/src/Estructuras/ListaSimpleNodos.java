/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Clases.*;
import java.net.DatagramSocket;

/**
 *
 * @author l4kz4
 */
public class ListaSimpleNodos {

    private NodoRed inicio;

    public ListaSimpleNodos() {
    }

    public boolean listaVacia() {
        return inicio == null ? true : false;
    }

    public void insertar(NodoRed nuevo) {
        if (listaVacia()) {
            setInicio(nuevo);
        } else {
            nuevo.setSiguiente(getInicio());
            setInicio(nuevo);
        }
    }
    
    public void eliminar(NodoRed borrar){
        NodoRed aux1 = inicio;
        NodoRed aux2 = inicio;
        if (aux1!=null) {
            if (aux1 == borrar && aux1!=inicio) {
                aux2.setSiguiente(aux1.getSiguiente());
                aux1 = null;
            }else if (aux1==borrar && aux1==inicio) {
                inicio = null;
            }
            
            aux2 = aux1;
            aux1 = aux1.getSiguiente();
        }
    }
    
    public NodoRed buscar(DatagramSocket socketUDP){
        NodoRed aux = inicio;
        while(aux!=null){
            if (aux.getSocketUDP() == socketUDP) {
                return aux;
            }
        }
        return null;
    }
    
    public String graficar(){
        String grafica =  "digraph ListaNodos{\nrankdir=\"LR\";\nnode[shape=rect];\n";
        NodoRed aux = inicio;
        while(aux!=null){
            grafica += "node"+aux.getSocketUDP().getLocalPort()+"[label=\"IP: "+aux.getDireccion()
                    +"\nSocket: "+aux.getSocketUDP().getLocalPort()+ "\"];\n";
            if (aux.getSiguiente()!=null) {
                grafica += "node"+aux.getSocketUDP().getLocalPort()+" -> node"+aux.getSiguiente().getSocketUDP().getLocalPort()+";\n";
            }
            aux = aux.getSiguiente();
        }
        grafica += "}";
        return grafica;
    }
    
    /**
     * @return the inicio
     */
    public NodoRed getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(NodoRed inicio) {
        this.inicio = inicio;
    }
    
    
}
