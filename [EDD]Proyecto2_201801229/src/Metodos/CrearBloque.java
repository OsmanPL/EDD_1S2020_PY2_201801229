/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import Clases.*;

import static edd.proyecto2_201801229.EDDProyecto2_201801229.indexBloque;

/**
 *
 * @author l4kz4
 */
public class CrearBloque {

    private String crearUsuario;
    private String editarUsuario;
    private String eliminarUsuario;
    private String crearLibro;
    private String eliminarLibro;
    private String crearCategoria;
    private String eliminarCategoria;

    public CrearBloque() {
        crearUsuario="";
        editarUsuario="";
        eliminarUsuario = "";
        crearLibro = "";
        eliminarLibro = "";
        crearCategoria = "";
        eliminarCategoria = "";
    }
    
    public void crearUsuario(Usuario nuevo){
        crearUsuario += "{\n";
        crearUsuario += "\"Carnet\": " +nuevo.getCarnet() +",\n";
        crearUsuario += "\"Nombre\": \"" +nuevo.getNombre() +"\",\n";
        crearUsuario += "\"Apellido\": \"" +nuevo.getApellido() +"\",\n";
        crearUsuario += "\"Carrera\": \"" +nuevo.getCarrera() +"\",\n";
        crearUsuario += "\"Password\": \"" +nuevo.getPassword() +"\",\n";
        crearUsuario += "},\n";
    }
    public void EditarUsuario(Usuario nuevo){
        editarUsuario += "{\n";
        editarUsuario += "\"Carnet\": " +nuevo.getCarnet() +",\n";
        editarUsuario += "\"Nombre\": \"" +nuevo.getNombre() +"\",\n";
        editarUsuario += "\"Apellido\": \"" +nuevo.getApellido() +"\",\n";
        editarUsuario += "\"Carrera\": \"" +nuevo.getCarrera() +"\",\n";
        editarUsuario += "\"Password\": \"" +nuevo.getPassword() +"\",\n";
        editarUsuario += "},\n";
    }
    public void eliminarUsuario(Usuario nuevo){
        eliminarUsuario += "{\n";
        eliminarUsuario += "\"Carnet\": " +nuevo.getCarnet() +",\n";
        eliminarUsuario += "\"Password\": \"" +nuevo.getPassword() +"\",\n";
        eliminarUsuario += "},\n";
    }
    public void crearLibro(Libro nuevo){
        crearLibro += "{\n";
        crearLibro += "\"ISBN\": "+nuevo.getISBN()+",\n";
        crearLibro += "\"Año\": "+nuevo.getAño()+",\n";
        crearLibro += "\"Idioma\": \""+nuevo.getIdioma()+"\",\n";
        crearLibro += "\"Titulo\": \""+nuevo.getTitulo()+"\",\n";
        crearLibro += "\"Editorial\": \""+nuevo.getEditorial()+"\",\n";
        crearLibro += "\"Autor\": \""+nuevo.getAutor()+"\",\n";
        crearLibro += "\"Edicion\": "+nuevo.getEdicion()+",\n";
        crearLibro += "\"Categoria\": \""+nuevo.getCategoria()+"\",\n";
        crearLibro += "\"Carnet\": " +nuevo.getCarnetUsuario()+",\n";
        crearLibro += "},\n";
    }
    public void eliminarLibro(Libro nuevo){
        eliminarLibro += "{\n";
        eliminarLibro += "\"ISBN\": "+nuevo.getISBN()+",\n";
        eliminarLibro += "\"Titulo\": \""+nuevo.getTitulo()+"\",\n";
        eliminarLibro += "\"Categoria\": \""+nuevo.getCategoria()+"\",\n";
        eliminarLibro += "\"Carnet\": " +nuevo.getCarnetUsuario()+",\n";
        eliminarLibro += "},\n";
    }
    public void crearCategoria(AVLNode nuevo){
        crearCategoria += "{\n";
        crearCategoria += "\"Nombre\": \""+nuevo.getCategoria()+"\",\n";
        crearCategoria += "\"Carnet:\": "+nuevo.getCarnetUsuario()+",\n";
        crearCategoria += "},\n";
    }
    public void eliminarCategoria(AVLNode nuevo){
        eliminarCategoria += "{\n";
        eliminarCategoria += "\"Nombre\": \""+nuevo.getCategoria()+"\",\n";
        eliminarCategoria += "\"Carnet:\": "+nuevo.getCarnetUsuario()+",\n";
        eliminarCategoria += "},\n";
    }
    /**
     * @return the crearUsuario
     */
    public String getCrearUsuario() {
        return crearUsuario;
    }

    /**
     * @param crearUsuario the crearUsuario to set
     */
    public void setCrearUsuario(String crearUsuario) {
        this.crearUsuario = crearUsuario;
    }

    /**
     * @return the editarUsuario
     */
    public String getEditarUsuario() {
        return editarUsuario;
    }

    /**
     * @param editarUsuario the editarUsuario to set
     */
    public void setEditarUsuario(String editarUsuario) {
        this.editarUsuario = editarUsuario;
    }

    /**
     * @return the crearLibro
     */
    public String getCrearLibro() {
        return crearLibro;
    }

    /**
     * @param crearLibro the crearLibro to set
     */
    public void setCrearLibro(String crearLibro) {
        this.crearLibro = crearLibro;
    }

    /**
     * @return the eliminarLibro
     */
    public String getEliminarLibro() {
        return eliminarLibro;
    }

    /**
     * @param eliminarLibro the eliminarLibro to set
     */
    public void setEliminarLibro(String eliminarLibro) {
        this.eliminarLibro = eliminarLibro;
    }

    /**
     * @return the crearCategoria
     */
    public String getCrearCategoria() {
        return crearCategoria;
    }

    /**
     * @param crearCategoria the crearCategoria to set
     */
    public void setCrearCategoria(String crearCategoria) {
        this.crearCategoria = crearCategoria;
    }

    /**
     * @return the eliminarCategoria
     */
    public String getEliminarCategoria() {
        return eliminarCategoria;
    }

    /**
     * @param eliminarCategoria the eliminarCategoria to set
     */
    public void setEliminarCategoria(String eliminarCategoria) {
        this.eliminarCategoria = eliminarCategoria;
    }

    /**
     * @return the eliminarUsuario
     */
    public String getEliminarUsuario() {
        return eliminarUsuario;
    }

    /**
     * @param eliminarUsuario the eliminarUsuario to set
     */
    public void setEliminarUsuario(String eliminarUsuario) {
        this.eliminarUsuario = eliminarUsuario;
    }

}
