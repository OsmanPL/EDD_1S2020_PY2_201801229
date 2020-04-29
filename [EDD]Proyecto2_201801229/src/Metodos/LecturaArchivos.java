/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import Clases.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import static edd.proyecto2_201801229.EDDProyecto2_201801229.tablaHash;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.mavl;
/**
 *
 * @author l4kz4
 */
public class LecturaArchivos {

    public LecturaArchivos() {
    }
    
    
    public void leerUsuarios(String ruta){
        try{
            JSONParser parser = new JSONParser();
            JSONObject archivoUsers =  (JSONObject) parser.parse(new FileReader(ruta));
            JSONArray usuarios = (JSONArray) archivoUsers.get("Usuarios");
            for (Object obj: usuarios) {
                if (obj!=null) {
                    JSONObject usuario = (JSONObject) obj;
                    Long car = new Long((long) usuario.get("Carnet"));
                    int carnet = car.intValue();
                    String nombre = (String) usuario.get("Nombre");
                    String apellido = (String) usuario.get("Apellido");
                    String carrera = (String) usuario.get("Carrera");
                    String password = (String) usuario.get("Password");
                    
                    Usuario nuevoUsuario = new Usuario(carnet,nombre,apellido,carrera,password);
                    tablaHash.insertar(nuevoUsuario);
                 }
            }
        }catch(Exception ex){
            
            System.out.println("Error Lectura de archivo usuarios: "+ex.toString());
        }
    }
    
    public void leerLibros(String ruta){
       // try{
            int i = 1;
            JSONParser parser = new JSONParser();
            JSONObject archivoUsers = new JSONObject();
            try{
                
                archivoUsers =  (JSONObject) parser.parse(new FileReader(ruta));
                
                
            }catch(Exception ex){
                
            }
            JSONArray librosArchivo = (JSONArray) archivoUsers.get("libros");
            for (Object obj: librosArchivo) {
                if (obj!=null) {
                    JSONObject libro = (JSONObject) obj;
                    Long isbn = new Long((long)libro.get("ISBN"));
                    int ISBN = isbn.intValue();
                    Long anio = new Long((long)libro.get("Año"));
                    int año = anio.intValue();
                    String idioma = (String) libro.get("Idioma");
                    String titulo = (String) libro.get("Titulo");
                    String editorial = (String) libro.get("Editorial");
                    String autor = (String) libro.get("Autor");
                    Long edic = new Long((long)libro.get("Edicion"));
                    int edicion = edic.intValue();
                    String categoria = (String) libro.get("Categoria");
                    
                    
                    Libro nuevo = new Libro(ISBN, 0,  año,  autor,  titulo,  editorial,  edicion, categoria, idioma);
                    
                    AVLNode libros = mavl.buscar(categoria);
                    if (libros != null) {
                        libros.getArbolB().Insertar(nuevo);
                    }else{
                        mavl.insert(categoria);
                        libros = mavl.buscar(categoria);
                        libros.getArbolB().Insertar(nuevo);
                    }
                 }
            }
      /* }catch(Exception ex){
            
            System.out.println("Error lectura archivo libros: "+ex.toString());
        }*/
    }
}
