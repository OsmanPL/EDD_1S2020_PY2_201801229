/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2_201801229;

import java.util.Scanner;

import Clases.*;
import Estructuras.*;
/**
 *
 * @author l4kz4
 */
public class EDDProyecto2_201801229 {

    /**
     * @param args the command line arguments
     */
    
    MetodosArbolB mab = new MetodosArbolB(3);
    MetodosAVL mavl = new MetodosAVL();
    MetodosTablaHash tablaHash = new MetodosTablaHash(45);
    public static void main(String[] args) {
        EDDProyecto2_201801229 edd = new EDDProyecto2_201801229();
        edd.menu();
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
            }
        }while(opcion!=9);
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
        int ISBN=0, carnetUsuario=0, año=2020;
        String autor="", titulo="", editorial="", edicion="", categoria="", idioma="";
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
        edicion = scan.next();
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
                System.out.println("Error al Insertar: categoria no existe");
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
            System.out.println("Error: " + ex);
        }
    }
}
