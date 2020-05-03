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
        if (getInicio()!=null) {
            return false;
        }else{
            return true;
        }
    }
    
    public void insertar(Usuario nuevoUsuario){
        if (listaVacia()) {
            setInicio(nuevoUsuario);
        }else{
            nuevoUsuario.setSiguiente(getInicio());
            setInicio(nuevoUsuario);
        }
    }
    
    public boolean borrar(int carnetBorrar){
        Usuario aux = getInicio();
        Usuario aux2 = getInicio();
        boolean borro = false;
        while(aux!=null){
            if (carnetBorrar == aux.getCarnet() && aux == getInicio()) {
                setInicio(aux.getSiguiente());
                borro = true;
                break;
            }
            else if (carnetBorrar == aux.getCarnet() && aux != getInicio()) {
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
        Usuario aux = getInicio();
        while(aux!=null){
            if (carnetBorrar == aux.getCarnet()) {
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }
    public Usuario ingresar(int carnetBorrar, String password){
        Usuario aux = getInicio();
        while(aux!=null){
            if (carnetBorrar == aux.getCarnet() && password.equals(aux.getPassword())) {
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }
    
    public String grafica(int i){
        String grafica = "subgraph Usuario"+i+"{\nrankdir=\"LR\";\n node[shape=rect];\n";
        Usuario aux = getInicio();
        while(aux!=null){
            grafica += aux.getCarnet() +"[label=\"Carnet: "+aux.getCarnet()+"\nNombre: "+aux.getNombre() +" "+aux.getApellido()+"\"];\n";
            Usuario aux2 = aux.getSiguiente();
            if (aux2!=null) {
                grafica+= aux.getCarnet() +" -> "+aux2.getCarnet()+";\n";
            }
            aux = aux.getSiguiente();
        }
        grafica += "}\n";
        return grafica;
    }

    /**
     * @return the inicio
     */
    public Usuario getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Usuario inicio) {
        this.inicio = inicio;
    }
}
