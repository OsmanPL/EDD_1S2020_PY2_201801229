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
import static edd.proyecto2_201801229.EDDProyecto2_201801229.bloques;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.tablaHash;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.mavl;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.indexBloque;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.cb;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author l4kz4
 */
public class LecturaArchivos {

    public LecturaArchivos() {
    }

    public void leerUsuarios(String ruta) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject archivoUsers = (JSONObject) parser.parse(new FileReader(ruta));
            JSONArray usuarios = (JSONArray) archivoUsers.get("Usuarios");
            for (Object obj : usuarios) {
                if (obj != null) {
                    JSONObject usuario = (JSONObject) obj;
                    Long car = new Long((long) usuario.get("Carnet"));
                    int carnet = car.intValue();
                    String nombre = (String) usuario.get("Nombre");
                    String apellido = (String) usuario.get("Apellido");
                    String carrera = (String) usuario.get("Carrera");
                    String password = (String) usuario.get("Password");

                    Usuario nuevoUsuario = new Usuario(carnet, nombre, apellido, carrera, password);
                    tablaHash.insertar(nuevoUsuario);
                }
            }
        } catch (Exception ex) {

            System.out.println("Error Lectura de archivo usuarios: " + ex.toString());
        }
    }

    public void leerLibros(String ruta, int carnet) {
        // try{
        int i = 1;
        JSONParser parser = new JSONParser();
        JSONObject archivoUsers = new JSONObject();
        try {

            archivoUsers = (JSONObject) parser.parse(new FileReader(ruta));

        } catch (Exception ex) {

        }
        JSONArray librosArchivo = (JSONArray) archivoUsers.get("libros");
        for (Object obj : librosArchivo) {
            if (obj != null) {
                JSONObject libro = (JSONObject) obj;
                Long isbn = new Long((long) libro.get("ISBN"));
                int ISBN = isbn.intValue();
                Long anio = new Long((long) libro.get("Año"));
                int año = anio.intValue();
                String idioma = (String) libro.get("Idioma");
                String titulo = (String) libro.get("Titulo");
                String editorial = (String) libro.get("Editorial");
                String autor = (String) libro.get("Autor");
                Long edic = new Long((long) libro.get("Edicion"));
                int edicion = edic.intValue();
                String categoria = (String) libro.get("Categoria");

                Libro nuevo = new Libro(ISBN, carnet, año, autor, titulo, editorial, edicion, categoria, idioma);

                AVLNode libros = mavl.buscar(categoria);
                if (libros != null) {
                    mavl.insertarLibro(libros, nuevo);
                } else {
                    mavl.insert(categoria, carnet);
                    libros = mavl.buscar(categoria);
                    mavl.insertarLibro(libros, nuevo);
                }
            }
        }
        /* }catch(Exception ex){
            
         System.out.println("Error lectura archivo libros: "+ex.toString());
         }*/
    }

    public void escribirBloque() {
        Bloque nuevoBloque = new Bloque(indexBloque);
        nuevoBloque.setTimestamp(new Timestamp(System.currentTimeMillis()));
        String data = "\"DATA\": [\n";
        data += "{\n\"CREAR_USUARIO\": [\n" + cb.getCrearUsuario() + "]\n}\n";
        data += "],\n";
        nuevoBloque.setData(data);
        bloques.insertar(nuevoBloque);
        FileWriter flwriter = null;
        try {
            //crea el flujo para escribir en el archivo
            flwriter = new FileWriter("Bloques/Bloque" + indexBloque + ".json");
            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            String grafica = "{\n"+bloque(bloques.getPrimero())+"}\n";
            bfwriter.write(grafica);
            //cierra el buffer intermedio
            bfwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {//cierra el flujo principal
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        cb = new CrearBloque();
    }

    public String bloque(Bloque nuevo) {
        String bloque = "";
        if (nuevo != null) {
            bloque += "\"INDEX\": " + nuevo.getIndex() + ",\n";
            bloque += "\"TIMESTAMP\": \"" + nuevo.getTimestamp() + "\",\n";
            bloque += "\"NONCE\": "+nuevo.getNONCE()+",\n";
            bloque += nuevo.getData() + "\n";
            bloque += "\"PREVIOUSHASH\": \""+nuevo.getPrevioushash()+"\",\n";
            bloque += "\"HASH\": \""+nuevo.getHash()+"\",\n";
        }
        return bloque;
    }
}
