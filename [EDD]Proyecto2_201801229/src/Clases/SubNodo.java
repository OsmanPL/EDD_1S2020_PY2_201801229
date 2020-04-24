/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author l4kz4
 */
public class SubNodo {
    private Libro valor;
    private BNode hi;
    private BNode hd;

    public SubNodo(Libro val, BNode hi, BNode hd){
        this.setValor(val);
        this.setHi(hi);
        this.setHd(hd);
    }
    /**
     * @return the valor
     */
    public Libro getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Libro valor) {
        this.valor = valor;
    }

    /**
     * @return the hi
     */
    public BNode getHi() {
        return hi;
    }

    /**
     * @param hi the hi to set
     */
    public void setHi(BNode hi) {
        this.hi = hi;
    }

    /**
     * @return the hd
     */
    public BNode getHd() {
        return hd;
    }

    /**
     * @param hd the hd to set
     */
    public void setHd(BNode hd) {
        this.hd = hd;
    }
    
    public BNode toRaiz(){
        BNode nodo = new BNode(getHd().getOrden(), this);
        return nodo;
    }

   
}
