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
public class AVLNode {
    private String categoria;
    private MetodosArbolB arbolB;
    private AVLNode hi, hd;
    private int altura;
    private int carnetUsuario;
    
    public AVLNode() {
    }

    public AVLNode(String categoria) {
        this.categoria = categoria;
        this.arbolB = new MetodosArbolB(5);
        this.altura = 0;
    }

    public AVLNode(String categoria, AVLNode hi, AVLNode hd, int carnet) {
        this.categoria = categoria;
        this.hi = hi;
        this.hd = hd;
        this.arbolB = new MetodosArbolB(5);
        this.altura = 0;
        this.carnetUsuario = carnet;
    }
    
    

    
    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the arbolB
     */
    public MetodosArbolB getArbolB() {
        return arbolB;
    }

    /**
     * @param arbolB the arbolB to set
     */
    public void setArbolB(MetodosArbolB arbolB) {
        this.arbolB = arbolB;
    }

    /**
     * @return the hi
     */
    public AVLNode getHi() {
        return hi;
    }

    /**
     * @param hi the hi to set
     */
    public void setHi(AVLNode hi) {
        this.hi = hi;
    }

    /**
     * @return the hd
     */
    public AVLNode getHd() {
        return hd;
    }

    /**
     * @param hd the hd to set
     */
    public void setHd(AVLNode hd) {
        this.hd = hd;
    }

    /**
     * @return the altura
     */
    public int getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * @return the carnetUsuario
     */
    public int getCarnetUsuario() {
        return carnetUsuario;
    }

    /**
     * @param carnetUsuario the carnetUsuario to set
     */
    public void setCarnetUsuario(int carnetUsuario) {
        this.carnetUsuario = carnetUsuario;
    }
}
