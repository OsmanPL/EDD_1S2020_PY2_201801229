/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;
import Clases.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author l4kz4
 */
public class MetodosArbolB {
    private int ordenarbol;
    private ArrayList<Libro> ArbolB;
    private BNode nodo;
    private int contadornodos = 1;
    
     public MetodosArbolB(int orden){
        setOrdenarbol(orden);//Me indica en que orden es el arbolB.
        setNodo(new BNode(orden,true));//Instancia el primer nodo como raiz.
        ArbolB = new ArrayList<Libro>();//Se crea lista para almacenar los valores que se ingresan al arbolB
    }

     public void Insertar(Libro Valor){
        if (ArbolB.contains(Valor)) {
            
        }else{
             ArbolB.add(Valor);//Se ingresa el valor a la lista con los valores del ArbolB
            SubNodo sn = nodo.insertar(Valor);//Se ejecuta el metodo insertar del objeto NODO para retornar un subnodo.
            if(sn != null){
                BNode nuevonodo = new BNode(ordenarbol,sn);
                this.nodo = nuevonodo;
            }
        }
    }
     
    public String imprimirarbol(MetodosArbolB arbol){
        String arbolstring = "";
        ArrayList<BNode> niv = arbol.recorrer();
        int saltolinea = 0;
        for(BNode nod : niv){

            if(nod.getNivel() != saltolinea){
                arbolstring += "\n ";
                saltolinea =  nod.getNivel();
            }
            arbolstring+="{ ";
            for (Libro mostrar : nod.getValoresNodo()) {
                if (mostrar!=null) {
                    arbolstring += "[ISBN: " + mostrar.getISBN()
                            +", Titulo: "+ mostrar.getTitulo()
                            +", Autor: "+mostrar.getAutor()
                            +", Editorial: "+mostrar.getEditorial()
                            +", Año: "+mostrar.getAño()
                            +", Edicion: "+mostrar.getEdicion()
                            +", Categoria: "+mostrar.getCategoria()
                            +", Idioma: "+mostrar.getIdioma()
                            +", Carnet: "+mostrar.getCarnetUsuario()+"]";
                }
            }
            arbolstring+=" }";
        }
        return arbolstring;
    }
    
    public String imprimirGrafico(MetodosArbolB arbol){
        String arbolstring = "digraph ArbolB{\n";
        ArrayList<BNode> niv = arbol.recorrer();
        int saltolinea = 0;
        int numero = 0;
        for(BNode nod : niv){

            if(nod.getNivel() != saltolinea){
                arbolstring += "\n ";
                saltolinea =  nod.getNivel();
            }
            
            arbolstring += "node"+numero+"[label=\""+nod.getValoresNodo().toString()+"\"];\n";
            numero++;
        }
        return arbolstring;
    }
     
    public ArrayList<BNode> recorrer(){
        Queue<BNode> cola = new LinkedList<BNode>();
        ArrayList <BNode> sale = new ArrayList<BNode>();
        sale.add(nodo);
        cola.add(nodo);
        while (!cola.isEmpty()){
            BNode nod = cola.poll();
            for (BNode hijo: nod.getSubNodos()){
                hijo.setNivel(nod.getNivel() + 1);
                sale.add(hijo);
                cola.add(hijo);
            }
        }
        return sale;
    }
    public ArrayList<Libro> devolver(int carnet){
        Queue<BNode> cola = new LinkedList<BNode>();
        ArrayList <BNode> sale = new ArrayList<BNode>();
        sale.add(nodo);
        cola.add(nodo);
        while (!cola.isEmpty()){
            BNode nod = cola.poll();
            for (BNode hijo: nod.getSubNodos()){
                hijo.setNivel(nod.getNivel() + 1);
                sale.add(hijo);
                cola.add(hijo);
            }
        }
        ArrayList <Libro> libros = new ArrayList<Libro>();
        for (BNode node: sale) {
            if (node!=null) {
                for (Libro libro: node.getValoresNodo()) {
                    if (libro!=null) {
                        if (libro.getCarnetUsuario()==carnet) {
                            libros.add(libro);
                        }
                    }
                }
            }
        }
        return libros;
    }

    protected String eliminar(int ISBN){
        String retornar = "Elemento no registrado.";
        for (Libro valor:ArbolB ) {
            if (valor!=null) {
                if (valor.getISBN()==ISBN) {
                    if(ArbolB.contains(valor)){
                        BNode nuevonodo = new BNode(ordenarbol,true);
                        SubNodo Subn;
                        Libro eliminado = new Libro();
                        eliminado = valor;
                        ArbolB.remove(eliminado);
                        for (Libro integer : ArbolB) {
                            Subn = nuevonodo.insertar(integer);
                            if(Subn != null){
                                nuevonodo = Subn.toRaiz();
                            }
                        }
                        nodo = nuevonodo;
                        return "El #"+valor+" esta eliminado.";
                    }else{
                        return "Elemento no registrado.";
                    }
                }
            }
        }
        return retornar;
    }

    public BNode buscar(int ISBN){
        for (Libro valor:ArbolB) {
            if (valor!=null) {
                if (valor.getISBN()==ISBN) {
                    return nodo.buscarnodo(valor);
                }
            }
        }
        return null;
    }
    /**
     * @return the ordenarbol
     */
    public int getOrdenarbol() {
        return ordenarbol;
    }

    /**
     * @param ordenarbol the ordenarbol to set
     */
    public void setOrdenarbol(int ordenarbol) {
        this.ordenarbol = ordenarbol;
    }

    /**
     * @return the ArbolB
     */
    public ArrayList<Libro> getArbolB() {
        return ArbolB;
    }

    /**
     * @param ArbolB the ArbolB to set
     */
    public void setArbolB(ArrayList<Libro> ArbolB) {
        this.ArbolB = ArbolB;
    }

    /**
     * @return the nodo
     */
    public BNode getNodo() {
        return nodo;
    }

    /**
     * @param nodo the nodo to set
     */
    public void setNodo(BNode nodo) {
        this.nodo = nodo;
    }

    /**
     * @return the contadornodos
     */
    public int getContadornodos() {
        return contadornodos;
    }

    /**
     * @param contadornodos the contadornodos to set
     */
    public void setContadornodos(int contadornodos) {
        this.contadornodos = contadornodos;
    }
}
