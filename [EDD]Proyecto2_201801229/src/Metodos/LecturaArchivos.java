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
import static edd.proyecto2_201801229.EDDProyecto2_201801229.bloques;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.red;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.cb;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.nodoRed;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.indexBloque;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            // try{
            int i = 1;
            JSONParser parser = new JSONParser();
            JSONObject archivoUsers = new JSONObject();
            archivoUsers = (JSONObject) parser.parse(new FileReader(ruta));
            
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
            
        } catch (IOException ex) {
            Logger.getLogger(LecturaArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(LecturaArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void leerBloque(String ruta) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject archivo = (JSONObject) parser.parse(new FileReader(ruta));
            Long indexLong = new Long((long) archivo.get("INDEX"));
            int index = indexLong.intValue();
            Timestamp time = new Timestamp(System.currentTimeMillis());
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date parsedDate = dateFormat.parse((String) archivo.get("TIMESTAMP"));
                time = new java.sql.Timestamp(parsedDate.getTime());
            } catch (Exception e) { //this generic but you can control another types of exception
                // look the origin of excption 
            }
            Long nonceLong = new Long((long) archivo.get("NONCE"));
            long nonce = (long) nonceLong;

            JSONArray operaciones = (JSONArray) archivo.get("DATA");
            for (Object obj : operaciones) {
                if (obj != null) {
                    JSONObject operacion = (JSONObject) obj;
                    try {
                        JSONArray crearUsuario = (JSONArray) operacion.get("CREAR_USUARIO");
                        for (Object object : crearUsuario) {
                            if (object != null) {
                                JSONObject usuario = (JSONObject) object;
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

                    }

                    try {
                        JSONArray editarUsuario = (JSONArray) operacion.get("EDITAR_USUARIO");
                        for (Object object : editarUsuario) {
                            if (object != null) {
                                JSONObject usuario = (JSONObject) object;

                                Long car = new Long((long) usuario.get("Carnet"));
                                int carnet = car.intValue();
                                String nombre = (String) usuario.get("Nombre");
                                String apellido = (String) usuario.get("Apellido");
                                String carrera = (String) usuario.get("Carrera");
                                String password = (String) usuario.get("Password");

                                tablaHash.modificar(carnet, nombre, apellido, carrera, password);
                            }
                        }
                    } catch (Exception ex) {

                    }

                    try {
                        JSONArray eliminarUsuario = (JSONArray) operacion.get("ELIMINAR_USUARIO");
                        for (Object object : eliminarUsuario) {
                            if (object != null) {
                                JSONObject usuario = (JSONObject) object;

                                Long car = new Long((long) usuario.get("Carnet"));
                                int carnet = car.intValue();

                                tablaHash.Borrar(carnet);
                            }
                        }
                    } catch (Exception ex) {

                    }

                    try {
                        JSONArray crearCategoria = (JSONArray) operacion.get("CREAR_CATEGORIA");
                        for (Object object : crearCategoria) {
                            if (object != null) {
                                JSONObject categoria = (JSONObject) object;

                                String nombre = (String) categoria.get("Nombre");
                                Long car = new Long((long) categoria.get("Carnet"));
                                int carnet = car.intValue();

                                mavl.insert(nombre, carnet);
                            }
                        }
                    } catch (Exception ex) {

                    }

                    try {
                        JSONArray crearLibro = (JSONArray) operacion.get("CREAR_LIBRO");
                        for (Object object : crearLibro) {
                            if (object != null) {
                                JSONObject libro = (JSONObject) object;
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
                                Long car = new Long((long) libro.get("Carnet"));
                                int carnet = car.intValue();

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
                    } catch (Exception ex) {

                    }
                    try{
                        JSONArray eliminarLibro = (JSONArray) operacion.get("ELIMINAR_LIBRO");
                        for (Object object : eliminarLibro) {
                            if (object != null) {
                                JSONObject libro = (JSONObject) object;
                                Long isbn = new Long((long) libro.get("ISBN"));
                                int ISBN = isbn.intValue();
                                String titulo = (String) libro.get("Titulo");
                                String categoria = (String) libro.get("Categoria");
                                Long car = new Long((long) libro.get("Carnet"));
                                int carnet = car.intValue();
                                
                                mavl.iniciarEliminar(ISBN, carnet);
                            }
                        }
                    }catch(Exception ex){
                        
                    }
                    
                    try {
                        JSONArray eliminarCategoria = (JSONArray) operacion.get("ELIMINAR_CATEGORIA");
                        for (Object object : eliminarCategoria) {
                            if (object != null) {
                                JSONObject categoria = (JSONObject) object;

                                String nombre = (String) categoria.get("Nombre");
                                Long car = new Long((long) categoria.get("Carnet"));
                                int carnet = car.intValue();

                                mavl.eliminar(nombre, carnet);
                            }
                        }
                    } catch (Exception ex) {

                    }

                    String previoushash = (String) archivo.get("PREVIOUSHASH");
                    String hash = (String) archivo.get("HASH");

                    String data = "\"DATA\": [\n";
                    data += "{\n\"CREAR_USUARIO\": [\n" + cb.getCrearUsuario() + "]\n}\n";
                    data += "{\n\"EDITAR_USUARIO\": [\n" + cb.getEditarUsuario() + "]\n}\n";
                    data += "{\n\"ELIMINAR_USUARIO\": [\n" + cb.getEliminarUsuario() + "]\n}\n";
                    data += "{\n\"CREAR_CATEGORIA\": [\n" + cb.getCrearCategoria() + "]\n}\n";
                    data += "{\n\"CREAR_LIBRO\": [\n" + cb.getCrearLibro() + "]\n}\n";
                    data += "{\n\"ELIMINAR_CATEGORIA\": [\n" + cb.getEliminarCategoria() + "]\n}\n";
                    data += "{\n\"ELIMINAR_LIBRO\": [\n" + cb.getEliminarLibro() + "]\n}\n";
                    data += "],\n";

                    Bloque nuevo = new Bloque(index);

                    nuevo.setIndex(index);
                    nuevo.setNONCE(nonce);
                    nuevo.setData(data);
                    nuevo.setTimestamp(time);
                    nuevo.setPrevioushash(previoushash);
                    nuevo.setHash(hash);

                    bloques.insertar(nuevo);
                    cb = new CrearBloque();
                }
            }
        } catch (Exception ex) {

            System.out.println("Error Lectura de archivo bloque: "+ruta+" " + ex.toString());
        }
    }

    public void escribirBloque() {
        Bloque nuevoBloque = new Bloque(indexBloque);
        nuevoBloque.setTimestamp(new Timestamp(System.currentTimeMillis()));
        String data = "\"DATA\": [\n";
        data += "{\n\"CREAR_USUARIO\": [\n" + cb.getCrearUsuario() + "]\n}\n";
        data += "{\n\"EDITAR_USUARIO\": [\n" + cb.getEditarUsuario() + "]\n}\n";
        data += "{\n\"ELIMINAR_USUARIO\": [\n" + cb.getEliminarUsuario() + "]\n}\n";
        data += "{\n\"CREAR_CATEGORIA\": [\n" + cb.getCrearCategoria() + "]\n}\n";
        data += "{\n\"CREAR_LIBRO\": [\n" + cb.getCrearLibro() + "]\n}\n";
        data += "{\n\"ELIMINAR_CATEGORIA\": [\n" + cb.getEliminarCategoria() + "]\n}\n";
        data += "{\n\"ELIMINAR_LIBRO\": [\n" + cb.getEliminarLibro() + "]\n}\n";
        data += "],\n";

        nuevoBloque.setData(data);
        bloques.insertar(nuevoBloque);
        FileWriter flwriter = null;
        try {
            //crea el flujo para escribir en el archivo
            flwriter = new FileWriter("Bloques/Bloque" + indexBloque + ".json");
            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            String grafica = "{\n" + bloque(bloques.getPrimero()) + "}\n";
            bfwriter.write(grafica);
            //cierra el buffer intermedio
            bfwriter.close();
            flwriter.close();
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
        nodoRed.enviar(red.getInicio(), "Bloques/Bloque" + indexBloque + ".json");
        cb = new CrearBloque();
        indexBloque++;
    }

    public String bloque(Bloque nuevo) {
        String bloque = "";
        if (nuevo != null) {
            bloque += "\"INDEX\": " + nuevo.getIndex() + ",\n";
            bloque += "\"TIMESTAMP\": \"" + nuevo.getTimestamp() + "\",\n";
            bloque += "\"NONCE\": " + nuevo.getNONCE() + ",\n";
            bloque += nuevo.getData() + "\n";
            bloque += "\"PREVIOUSHASH\": \"" + nuevo.getPrevioushash() + "\",\n";
            bloque += "\"HASH\": \"" + nuevo.getHash() + "\",\n";
        }
        return bloque;
    }
}
