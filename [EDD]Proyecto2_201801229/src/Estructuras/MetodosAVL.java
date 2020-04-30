/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;
import Clases.*;
import java.util.ArrayList;
/**
 *
 * @author l4kz4
 */
public class MetodosAVL {
    AVLNode raiz;
    
    public void insert(String categoria){
        raiz = insertar(raiz, categoria);
    }
    public AVLNode insertar(AVLNode root, String categoria){
        if (root==null) {
            root = new AVLNode(categoria, null,null);
        }else if (categoria.compareTo(root.getCategoria())<0) {
            root.setHi(insertar(root.getHi(),categoria));
            if (altura(root.getHi())-altura(root.getHd())==2) {
                if (categoria.compareTo(root.getHi().getCategoria())<0) {
                    root = rotacionIzquierda(root);
                }else{
                    root = rotacionDobleIzquierda(root);
                }
            }
        }else if (categoria.compareTo(root.getCategoria())>0) {
            root.setHd(insertar(root.getHd(),categoria));
            if (altura(root.getHd())-altura(root.getHi())==2) {
                if (categoria.compareTo(root.getHd().getCategoria())>0) {
                    root = rotacionDerecha(root);
                }else{
                    root = rotacionDobleDerecha(root);
                }
            }
        }else;
        root.setAltura(Max(altura(root.getHi()), altura(root.getHd())) + 1);
        return root;
    }
    
    public int Max(int alturaIzquierda, int alturaDerecha){
        return alturaIzquierda>alturaDerecha?alturaIzquierda:alturaDerecha;
    }
    
    public AVLNode rotacionIzquierda(AVLNode nodo){
        AVLNode aux = nodo.getHi();
        nodo.setHi(aux.getHd());
        aux.setHd(nodo);
        nodo.setAltura(Max(altura(nodo.getHi()), altura(nodo.getHd()))+1);
        aux.setAltura(Max(altura(aux.getHi()), nodo.getAltura())+1);
        return aux;
    }
    
    public int altura(AVLNode nodo){
        return nodo==null?-1:nodo.getAltura();
    }
    
    public AVLNode rotacionDerecha(AVLNode nodo){
        AVLNode aux = nodo.getHd();
        nodo.setHd(aux.getHi());
        aux.setHi(nodo);
        nodo.setAltura(Max(altura(nodo.getHi()), altura(nodo.getHd()))+1);
        aux.setAltura(Max(altura(aux.getHd()), nodo.getAltura())+1);
        return aux;
    }
    public ArrayList<Libro> lista(int carnet){
        return listaLibros(raiz, carnet);
    }
    public ArrayList<Libro> listaLibros(AVLNode actual, int carnet){
        ArrayList<Libro> lista = new ArrayList<Libro>();
        if (actual!=null) {
            lista.addAll(actual.getArbolB().devolver(carnet));
            if (actual.getHi()!=null) {
                lista.addAll(listaLibros(actual.getHi(),carnet));
            }
            if (actual.getHd()!=null) {
                lista.addAll(listaLibros(actual.getHd(),carnet));
            }
        }
        return lista;
    }
    public AVLNode rotacionDobleIzquierda(AVLNode nodo){
        nodo.setHi(rotacionDerecha(nodo.getHi()));
        return rotacionIzquierda(nodo);
    }
    public AVLNode buscar(String categoria){
        return buscar(raiz, categoria);
    }
    public AVLNode buscar(AVLNode nodo, String categoria){
        if (nodo==null) {
            return nodo;
        }else{
            if (categoria.compareTo(nodo.getCategoria())== 0) {
                return nodo;
            }else if (categoria.compareTo(nodo.getCategoria())>0) {
                return buscar(nodo.getHd(),categoria);
            }else if (categoria.compareTo(nodo.getCategoria())<0) {
                return buscar(nodo.getHi(),categoria);
            }
            return nodo;
        }
    }
    public AVLNode rotacionDobleDerecha(AVLNode nodo){
        nodo.setHd(rotacionIzquierda(nodo.getHd()));
        return rotacionDerecha(nodo);
    }
    
    public void imprimir(){
        imprimir(raiz);
    }
    public void imprimir(AVLNode nodo){
        if (nodo!=null) {
            imprimir(nodo.getHd());
            System.out.println("["+nodo.getCategoria()+", "+nodo.getArbolB().getArbolB().size()+"]");
            imprimir(nodo.getHi());
        }
    }
}
