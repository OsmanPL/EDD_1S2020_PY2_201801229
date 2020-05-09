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
import static edd.proyecto2_201801229.EDDProyecto2_201801229.cb;
/**
 *
 * @author l4kz4
 */
public class MetodosArbolB {
    private int ordenarbol;
    private ArrayList<Libro> ArbolB;
    private BNode nodo;
    private int contadornodos = 1;
    private int i = 0;
     public MetodosArbolB(int orden){
        setOrdenarbol(orden);//Me indica en que orden es el arbolB.
        setNodo(new BNode(orden,true));//Instancia el primer nodo como raiz.
        ArbolB = new ArrayList<Libro>();//Se crea lista para almacenar los valores que se ingresan al arbolB
    }

     public void Insertar(Libro Valor){
        if (ArbolB.contains(Valor)) {
            
        }else{
             ArbolB.add(Valor);//Se ingresa el valor a la lista con los valores del ArbolB
             cb.crearLibro(Valor);
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
        String arbolstring = "";
        arbolstring += graicar(nodo);
        return arbolstring;
    }
    
    public String graicar(BNode actual){
        String grafica = "";
         int k = i;
        if (actual!=null) {
            grafica += "parent"+i+"[label=<\n<table border='1' cellborder='1'>\n";
            int j = 0;
            k=i;
            grafica+= "<tr>";
            grafica+= "<td port='port_"+k+""+j+"'>H"+j+"</td>";
            j++;
            for (Libro libro:actual.getValoresNodo()) {
                if (libro!=null) {
                    grafica+= "<td ><table cellspacing='0'>" +
                            "<tr><td>ISBN: "+libro.getISBN()+"</td></tr>" +
                            "<tr><td>Titulo: "+libro.getTitulo().replaceAll(";", "").replaceAll("&", "&#38;")+"</td></tr>" +
                            "<tr><td>Autor: "+libro.getAutor().replaceAll(";", "").replaceAll("&", "&#38;")+"</td></tr>" +
                            "<tr><td>Editorail: "+libro.getEditorial().replaceAll(";", "").replaceAll("&", "&#38;")+"</td></tr>" +
                            "<tr><td>Año: "+libro.getAño()+"</td></tr>" +
                            "<tr><td>Edicion: "+libro.getEdicion()+"</td></tr>" +
                            "<tr><td>Categoria: "+libro.getCategoria().replaceAll(";", "").replaceAll("&", "&#38;")+"</td></tr>" +
                            "<tr><td>Idioma: "+libro.getIdioma().replaceAll(";", "").replaceAll("&", "&#38;")+"</td></tr>" +
                            "<tr><td>Carnet Usuario: "+libro.getCarnetUsuario()+"</td></tr>" +
                            "</table></td>";
                    grafica+= "<td port='port_"+k+""+j+"'>H"+j+"</td>";
                    j++;
                }
            }
            grafica+= "</tr>";
            grafica+= "</table>\n>];\n";
            j=0;
            i++;
            for (BNode sub:actual.getSubNodos()) {
                if (sub!=null) {
                    grafica += "parent"+k+":port_"+k+""+j+" -> parent"+i+";\n";
                    grafica += graicar(sub);
                    j++;
                }
            }
        }
        return grafica;
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
    
    public ArrayList<Libro> buscarTitulo(String titulo){
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
                        if (libro.getTitulo().contains(titulo)) {
                            libros.add(libro);
                        }
                    }
                }
            }
        }
        return libros;
    }
    public ArrayList<Libro> todosLosLibros(){
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
                        libros.add(libro);
                    }
                }
            }
        }
        return libros;
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

    protected Libro eliminar(int ISBN, int carnet){
        for (Libro valor:ArbolB ) {
            if (valor!=null) {
                if (valor.getISBN()==ISBN && valor.getCarnetUsuario()==carnet) {
                    if(ArbolB.contains(valor)){
                        BNode nuevonodo = new BNode(ordenarbol,true);
                        SubNodo Subn;
                        Libro eliminado = new Libro();
                        eliminado = valor;
                        cb.eliminarLibro(valor);
                        ArbolB.remove(eliminado);
                        for (Libro integer : ArbolB) {
                            Subn = nuevonodo.insertar(integer);
                            if(Subn != null){
                                nuevonodo = Subn.toRaiz();
                            }
                        }
                        nodo = nuevonodo;
                        return valor;
                    }else{
                        return null;
                    }
                }
            }
        }
        return null;
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
