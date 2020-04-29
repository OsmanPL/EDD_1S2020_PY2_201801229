/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2_201801229;

import java.util.Scanner;

import Clases.*;
import Estructuras.*;
import Interfaz.*;
import Metodos.*;

import static edd.proyecto2_201801229.EDDProyecto2_201801229.tablaHash;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.mavl;
/**
 *
 * @author l4kz4
 */
public class EDDProyecto2_201801229 {

    /**
     * @param args the command line arguments
     */
    public static MetodosAVL mavl = new MetodosAVL();
    public static MetodosTablaHash tablaHash = new MetodosTablaHash(45);
    LecturaArchivos ArchivoJson = new LecturaArchivos();
    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
       /* EDDProyecto2_201801229 pr = new EDDProyecto2_201801229();
        pr.menu();*/
    }
    public void menu(){
        int opcion=0;
        Scanner scan = new Scanner(System.in);
        do{
            System.out.println("1. Insertar Arbol B");
            System.out.println("2. Mostrar Arbol B");
            System.out.println("3. Insertar Arbol AVL");
            System.out.println("4. Mostrar Arbol AVL");
            System.out.println("5. Agregar Usuario");
            System.out.println("6. Modificar Usuario");
            System.out.println("7. Borrar Usuario");
            System.out.println("8. Buscar Usuario");
            System.out.println("9. Leer Archivo Usuarios");
            System.out.println("10. Leer Archivo Libros");
            System.out.print("Su Opcion es: ");
            opcion = scan.nextInt();
            switch(opcion){
                case 1:
                    insertarLibro();
                    break;
                case 2:
                    mostrar();
                    break;
                case 3:
                    insertarAVL();
                    break;
                case 4:
                    mostrarAVL();
                    break;
                case 5:
                    insertarUsuario();
                    break;
                case 6:
                    modificar();
                    break;
                case 7:
                    eliminarUsuario();
                    break;
                case 8:
                    buscarUsuario();
                    break;
                case 9:
                    leerArchivo(true);
                    break;
                case 10:
                    leerArchivo(false);
                    break;
            }
        }while(opcion!=11);
    }
    public void mostrarAVL(){
        mavl.imprimir();
    }
    public void mostrar(){
        Scanner scan = new Scanner(System.in);
        String categoria="";
        System.out.print("Categoria: ");
        categoria = scan.next();
        AVLNode libros = mavl.buscar(categoria);
            if (libros != null) {
                System.out.println(libros.getArbolB().imprimirarbol(libros.getArbolB()));
                
            }else{
                System.out.println("Error: categoria no existe");
            }
        
    }
    public void insertarAVL(){
        try{
            Scanner scan = new Scanner(System.in);
            String categoria="";
            System.out.print("Categoria: ");
            categoria = scan.next();
            mavl.insert(categoria);
        }catch(Exception ex){
            System.out.println("Error al Insertar Categoria: "+ex.toString());
        }
    }
    public void insertarLibro(){
        try{
        Scanner scan = new Scanner(System.in);
        int ISBN=0, carnetUsuario=0, año=2020, edicion=0;
        String autor="", titulo="", editorial="", categoria="", idioma="";
        System.out.print("ISBN: ");
        ISBN = scan.nextInt();
        System.out.print("Titulo: ");
        titulo = scan.next();
        System.out.print("Autor: ");
        autor = scan.next();
        System.out.print("Editorial: ");
        editorial = scan.next();
        System.out.print("Año: ");
        año = scan.nextInt();
        System.out.print("Edicion: ");
        edicion = scan.nextInt();
        System.out.print("Categoria: ");
        categoria = scan.next();
        System.out.print("Idioma: ");
        idioma = scan.next();
        System.out.print("Carnet: ");
        carnetUsuario = scan.nextInt();
        Libro nuevo = new Libro(ISBN, carnetUsuario,  año,  autor,  titulo,  editorial,  edicion, categoria, idioma);
        AVLNode libros = mavl.buscar(categoria);
        Usuario buscarusuario = tablaHash.Buscar(carnetUsuario);
            if (libros != null) {
                if (buscarusuario!=null) {
                    libros.getArbolB().Insertar(nuevo);
                }else{
                    System.out.println("El Usuario No Existe");
                }
            }else{
                if (buscarusuario!=null) {
                    mavl.insert(categoria);
                    libros = mavl.buscar(categoria);
                    libros.getArbolB().Insertar(nuevo);
                }else{
                    System.out.println("El Usuario No Existe");
                }
            }
        }catch(Exception ex){
            System.out.println("Error al Insertar Libro: "+ex.toString());
        }
    }
    public void insertarUsuario(){
        try{
            int carnet=0;
            String nombre = "", apellido = "", carrera = "", password = "";
            Scanner scan = new Scanner(System.in);
            System.out.print("Carnet: ");
            carnet = scan.nextInt();
            System.out.print("Nombre: ");
            nombre = scan.next();
            System.out.print("Apellido: ");
            apellido = scan.next();
            System.out.print("Carrera: ");
            carrera = scan.next();
            System.out.print("Password: ");
            password = scan.next();
            
            Usuario nuevoUsurio = new Usuario(carnet, nombre, apellido, carrera, password);
            tablaHash.insertar(nuevoUsurio);
        }catch(Exception ex){
            System.out.println("Error: " + ex.toString());
        }
    }
    public void buscarUsuario(){
        try{
            int carnet=0;
           
            Scanner scan = new Scanner(System.in);
            System.out.print("Bscar Usuario\nCarnet: ");
            carnet = scan.nextInt();
            Usuario buscar = tablaHash.Buscar(carnet);
            if (buscar!=null) {
                System.out.println("Carnet: "+buscar.getCarnet()
                +"\nNombre: "+buscar.getNombre()
                +"\nApellido: "+buscar.getApellido()
                +"\nCarrera: "+buscar.getCarrera()
                +"\nPassword: "+buscar.getPassword());
            }else{
                System.out.println("Usurio no encontrado");
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex.toString());
        }
    }
    
    public void eliminarUsuario(){
        try{
            int carnet=0;
           
            Scanner scan = new Scanner(System.in);
            System.out.print("Bscar Usuario\nCarnet: ");
            carnet = scan.nextInt();
            boolean borrar = tablaHash.Borrar(carnet);
            if (borrar) {
                System.out.println("Usuario Eliminado");
            }else{
                System.out.println("Usuario no encontrado");
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex.toString());
        }
    }
    public void modificar(){
        try{
            int carnet=0;
            String nombre = "", apellido = "", carrera = "", password = "";
            Scanner scan = new Scanner(System.in);
            System.out.print("Buscar Carnet: ");
            carnet = scan.nextInt();
            System.out.print("Nombre: ");
            nombre = scan.next();
            System.out.print("Apellido: ");
            apellido = scan.next();
            System.out.print("Carrera: ");
            carrera = scan.next();
            System.out.print("Password: ");
            password = scan.next();
            
            tablaHash.modificar(carnet, nombre, apellido, carrera, password);
            
        }catch(Exception ex){
            System.out.println("Error: " + ex.toString());
        }
    }
    
    public void leerArchivo(boolean json){
        Scanner scan = new Scanner(System.in);
        System.out.print("Ruta del Archivo: ");
        String ruta = scan.next();
        if (json) {
            ArchivoJson.leerUsuarios(ruta);
        }else{
            ArchivoJson.leerLibros(ruta);
        }
    }
}
