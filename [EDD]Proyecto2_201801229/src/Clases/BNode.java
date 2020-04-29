/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author l4kz4
 */
public class BNode {
    private int orden;
    private boolean hoja;
    private ArrayList<Libro> valoresNodo;
    private ArrayList<BNode>  SubNodos;
    private int nivel;

    
    public BNode(int orden, boolean hoja){
        this.setOrden(orden);//Indica el orden del arbolB
        this.setHoja(hoja);//Indica si el nodo es hoja. Es decir que no tiene hijos.
        setValoresNodo(new ArrayList<Libro>());//Se crea lista para almacenar los valores del nodo.
        setSubNodos(new ArrayList<BNode>());//Se crea lista para almacenar los subnodos del nodo principal.
        setNivel(0);
    }

    public BNode(int orden, SubNodo snodo){
        this.setOrden(orden);
        this.setHoja(false);
        setValoresNodo(new ArrayList<Libro>());
        setSubNodos(new ArrayList<BNode>());
        nivel = 0;
        valoresNodo.add(snodo.getValor());
        SubNodos.add(snodo.getHi());
        SubNodos.add(snodo.getHd());

    }

    //Nodo que se crea al momento de dividir el ArbolB.
    public BNode(int orden, boolean hoja, ArrayList<Libro> valores, ArrayList<BNode> hijos){
        this.setOrden(orden);
        this.setHoja(hoja);
        this.setValoresNodo(valores);
        this.setSubNodos(hijos);
    }

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * @return the hoja
     */
    public boolean isHoja() {
        return hoja;
    }

    /**
     * @param hoja the hoja to set
     */
    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }

    

    /**
     * @return the SubNodos
     */
    public ArrayList<BNode> getSubNodos() {
        return SubNodos;
    }

    /**
     * @param SubNodos the SubNodos to set
     */
    public void setSubNodos(ArrayList<BNode> SubNodos) {
        this.SubNodos = SubNodos;
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public BNode buscarnodo(Libro valor){
        if(valoresNodo.contains(valor)){
            return this;
        }else{
            if(hoja){
                return null;
            }else{
                int i = 0;
                while (i < valoresNodo.size() && valor.getISBN() > valoresNodo.get(i).getISBN()) {
                    i++;
                }
                return SubNodos.get(i).buscarnodo(valor);
            }
        }
    }
    private SubNodo dividir(){
        ArrayList<Libro> Vi = new ArrayList<Libro>();//Se crea un arreglo para almacenar los valores a la izquierda del valor central
        ArrayList<BNode> Hi = new ArrayList<BNode>();//Se crea un arreglo para almacenar los hijos a la izquierda del valor central
        ArrayList<Libro> Vd = new ArrayList<Libro>();//Se crea un arreglo para almacenar los valores a la derecha del valor central
        ArrayList<BNode> Hd = new ArrayList<BNode>();//Se crea un arreglo para almacenar los hijos a la derecha del valor central

        int i = 0;
        while (i < valoresNodo.size()/2){
            Vi.add(valoresNodo.get(i));
            i++;//Este ciclo ingresa los valores a la izquierda del nodo a la lista.
        }

        Libro val = valoresNodo.get(i);
        i++;

        while (i < valoresNodo.size()){
            Vd.add(valoresNodo.get(i));
            i++;//Este ciclo ingresa los valores a la derecha del nodo a la lista.
        }

        if(!hoja){
            for(int k = 0; k <= orden; k++){
                if (k < orden-2){
                    Hi.add(SubNodos.get(k));
                }else{
                    Hd.add(SubNodos.get(k));
                }
            }
        }

        BNode iz = new BNode(orden,hoja,Vi,Hi);
        BNode de = new BNode(orden,hoja,Vd,Hd);
        SubNodo sn = new SubNodo(val,iz,de);
        return sn;
    }
    private SubNodo tamanonodo(){
        SubNodo sn;
        if(valoresNodo.size() > (orden-1)){
            sn = dividir();//Se valida si el tamano del nodo es mayor al permitido y se divide.
            return sn;
        }
        return null;
    }
    public SubNodo insertar(Libro valor){
        SubNodo sn;
        if(hoja){
            int i = 0;
            while (i < valoresNodo.size() && valor.getISBN() > valoresNodo.get(i).getISBN()){
                i++;//Ciclo que define la posición a guardar el valor.
            }
            if (valoresNodo.contains(valor)) {
            }else{
                valoresNodo.add(i,valor);
            }
            sn = tamanonodo();//Se verifica el tamaño del nodo.
            return sn;
        }else{
            int i = 0;
            while ((i< valoresNodo.size()) && (valor.getISBN() > valoresNodo.get(i).getISBN())){
                i++;
            }
            sn = SubNodos.get(i).insertar(valor);
            if(sn != null){
                SubNodos.remove(i);
                Libro val = sn.getValor();
                BNode iz = sn.getHi();
                BNode de = sn.getHd();

                valoresNodo.add(i,val);
                SubNodos.add(i,de);
                SubNodos.add(i,iz);
                sn = tamanonodo();


                return sn;
            }else{
                return null;
            }
        }
    }

    /**
     * @return the valoresNodo
     */
    public ArrayList<Libro> getValoresNodo() {
        return valoresNodo;
    }

    /**
     * @param valoresNodo the valoresNodo to set
     */
    public void setValoresNodo(ArrayList<Libro> valoresNodo) {
        this.valoresNodo = valoresNodo;
    }
}
