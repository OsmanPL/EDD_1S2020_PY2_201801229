/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Clases.*;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.cb;

/**
 *
 * @author l4kz4
 */
public class MetodosAVL {

    AVLNode raiz;
    ArrayList<Integer> isbn = new ArrayList<Integer>();
    boolean s = true;

    public void insert(String categoria, int carnet) {
        raiz = insertar(raiz, categoria, carnet);
    }

    public void insertarLibro(AVLNode nodo, Libro libro) {
        if (isbn.contains(libro.getISBN())) {
            JOptionPane.showMessageDialog(null,
                    "El codigo ISBN ya esta asignado a otro libro",
                    "Libro", JOptionPane.ERROR_MESSAGE);
        } else {
            isbn.add(libro.getISBN());
            nodo.getArbolB().Insertar(libro);
        }

    }

    public AVLNode insertar(AVLNode root, String categoria, int carnet) {
        if (root == null) {
            root = new AVLNode(categoria, null, null, carnet);
            cb.crearCategoria(root);
        } else if (categoria.compareTo(root.getCategoria()) < 0) {
            root.setHi(insertar(root.getHi(), categoria, carnet));
            if (altura(root.getHi()) - altura(root.getHd()) == 2) {
                if (categoria.compareTo(root.getHi().getCategoria()) < 0) {
                    root = rotacionIzquierda(root);
                } else {
                    root = rotacionDobleIzquierda(root);
                }
            }
        } else if (categoria.compareTo(root.getCategoria()) > 0) {
            root.setHd(insertar(root.getHd(), categoria, carnet));
            if (altura(root.getHd()) - altura(root.getHi()) == 2) {
                if (categoria.compareTo(root.getHd().getCategoria()) > 0) {
                    root = rotacionDerecha(root);
                } else {
                    root = rotacionDobleDerecha(root);
                }
            }
        } else;
        root.setAltura(Max(altura(root.getHi()), altura(root.getHd())) + 1);
        return root;
    }

    public Libro iniciarEliminar(int ISBN, int carnet) {
        return eliminar(raiz, ISBN, carnet);
    }

    public Libro eliminar(AVLNode actual, int ISBN, int carnet) {
        if (actual != null) {
            Libro retornar = actual.getArbolB().eliminar(ISBN, carnet);
            if (retornar != null) {
                isbn.remove((Object) retornar.getISBN());
                return retornar;
            } else {
                if (actual.getHi() != null && retornar == null) {
                    retornar = eliminar(actual.getHi(), ISBN, carnet);
                }
                if (actual.getHd() != null && retornar == null) {
                    retornar = eliminar(actual.getHd(), ISBN, carnet);
                }
                if (retornar != null) {
                    isbn.remove((Object) retornar.getISBN());
                }
                return retornar;
            }
        }
        return null;
    }

    public int Max(int alturaIzquierda, int alturaDerecha) {
        return alturaIzquierda > alturaDerecha ? alturaIzquierda : alturaDerecha;
    }

    public AVLNode rotacionIzquierda(AVLNode nodo) {
        AVLNode aux = nodo.getHi();
        nodo.setHi(aux.getHd());
        aux.setHd(nodo);
        nodo.setAltura(Max(altura(nodo.getHi()), altura(nodo.getHd())) + 1);
        aux.setAltura(Max(altura(aux.getHi()), nodo.getAltura()) + 1);
        return aux;
    }

    public int altura(AVLNode nodo) {
        return nodo == null ? -1 : nodo.getAltura();
    }

    public AVLNode rotacionDerecha(AVLNode nodo) {
        AVLNode aux = nodo.getHd();
        nodo.setHd(aux.getHi());
        aux.setHi(nodo);
        nodo.setAltura(Max(altura(nodo.getHi()), altura(nodo.getHd())) + 1);
        aux.setAltura(Max(altura(aux.getHd()), nodo.getAltura()) + 1);
        return aux;
    }

    public ArrayList<Libro> lista(int carnet) {
        return listaLibros(raiz, carnet);
    }

    public ArrayList<Libro> listaLibros(AVLNode actual, int carnet) {
        ArrayList<Libro> lista = new ArrayList<Libro>();
        if (actual != null) {
            lista.addAll(actual.getArbolB().devolver(carnet));
            if (actual.getHi() != null) {
                lista.addAll(listaLibros(actual.getHi(), carnet));
            }
            if (actual.getHd() != null) {
                lista.addAll(listaLibros(actual.getHd(), carnet));
            }
        }
        return lista;
    }

    public ArrayList<Libro> busquedaTitutlo(String titulo) {
        return listaTitulo(raiz, titulo);
    }

    public ArrayList<Libro> listaTitulo(AVLNode actual, String titulo) {
        ArrayList<Libro> lista = new ArrayList<Libro>();
        if (actual != null) {
            lista.addAll(actual.getArbolB().buscarTitulo(titulo));
            if (actual.getHi() != null) {
                lista.addAll(listaTitulo(actual.getHi(), titulo));
            }
            if (actual.getHd() != null) {
                lista.addAll(listaTitulo(actual.getHd(), titulo));
            }
        }
        return lista;
    }

    public ArrayList<String> iniciarCatalogo() {
        return catalogo(raiz);
    }

    public ArrayList<String> catalogo(AVLNode actual) {
        ArrayList<String> catalogo = new ArrayList<String>();
        if (actual != null) {
            catalogo.add(actual.getCategoria());
            if (actual.getHi() != null) {
                catalogo.addAll(catalogo(actual.getHi()));
            }
            if (actual.getHd() != null) {
                catalogo.addAll(catalogo(actual.getHd()));
            }
        }
        return catalogo;
    }

    public ArrayList<Libro> todosLosLibrosCategoria(String categoria) {
        return listaTodosCategoria(raiz, categoria);
    }

    public ArrayList<Libro> listaTodosCategoria(AVLNode actual, String categoria) {
        ArrayList<Libro> lista = new ArrayList<Libro>();
        if (actual != null) {
            if (actual.getCategoria().compareTo(categoria) == 0) {
                lista = actual.getArbolB().todosLosLibros();
            } else {
                if (actual.getCategoria().compareTo(categoria) < 0) {
                    lista = listaTodosCategoria(actual.getHd(), categoria);
                } else if (actual.getCategoria().compareTo(categoria) > 0) {
                    lista = listaTodosCategoria(actual.getHi(), categoria);
                }
            }
        }
        return lista;
    }

    public ArrayList<Libro> todosLosLibros() {
        return listaTodos(raiz);
    }

    public ArrayList<Libro> listaTodos(AVLNode actual) {
        ArrayList<Libro> lista = new ArrayList<Libro>();
        if (actual != null) {
            lista.addAll(actual.getArbolB().todosLosLibros());
            if (actual.getHi() != null) {
                lista.addAll(listaTodos(actual.getHi()));
            }
            if (actual.getHd() != null) {
                lista.addAll(listaTodos(actual.getHd()));
            }
        }
        return lista;
    }

    public AVLNode rotacionDobleIzquierda(AVLNode nodo) {
        nodo.setHi(rotacionDerecha(nodo.getHi()));
        return rotacionIzquierda(nodo);
    }

    public AVLNode buscar(String categoria) {
        return buscar(raiz, categoria);
    }

    public AVLNode buscar(AVLNode nodo, String categoria) {
        if (nodo == null) {
            return nodo;
        } else {
            if (categoria.compareTo(nodo.getCategoria()) == 0) {
                return nodo;
            } else if (categoria.compareTo(nodo.getCategoria()) > 0) {
                return buscar(nodo.getHd(), categoria);
            } else if (categoria.compareTo(nodo.getCategoria()) < 0) {
                return buscar(nodo.getHi(), categoria);
            }
            return nodo;
        }
    }

    public AVLNode rotacionDobleDerecha(AVLNode nodo) {
        nodo.setHd(rotacionIzquierda(nodo.getHd()));
        return rotacionDerecha(nodo);
    }

    public void imprimir() {
        imprimir(raiz);
    }

    public void imprimir(AVLNode nodo) {
        if (nodo != null) {
            imprimir(nodo.getHd());
            System.out.println("[" + nodo.getCategoria() + ", " + nodo.getArbolB().getArbolB().size() + "]");
            imprimir(nodo.getHi());
        }
    }

    public ArrayList<AVLNode> devolverArbol() {
        return devolverCategorias(raiz);
    }

    public ArrayList<AVLNode> devolverCategorias(AVLNode nodo) {
        ArrayList<AVLNode> devolver = new ArrayList<AVLNode>();
        if (nodo != null) {
            devolver.add(nodo);
            if (nodo.getHi() != null) {
                devolver.addAll(devolverCategorias(nodo.getHi()));
            }
            if (nodo.getHd() != null) {
                devolver.addAll(devolverCategorias(nodo.getHd()));
            }
        }
        return devolver;
    }

    public void eliminar(String categoria, int carnet) {
        s = true;
        raiz = delete(raiz, categoria, carnet);
    }

    public AVLNode delete(AVLNode root, String categoria, int carnet) {
        if (root == null) {
            return root;
        } else if (categoria.compareTo(root.getCategoria()) < 0) {
            root.setHi(delete(root.getHi(), categoria, carnet));
        } else if (categoria.compareTo(root.getCategoria()) > 0) {
            root.setHd(delete(root.getHd(), categoria, carnet));
        } else if (carnet == root.getCarnetUsuario()) {
            if (root.getHi() == null || root.getHd() == null) {
                AVLNode temp = null;
                if (temp == root.getHi()) {
                    temp = root.getHd();
                } else if (temp == root.getHd()) {
                    temp = root.getHi();
                } else {
                    temp = null;
                }
                if (temp == null) {
                    temp = root;
                    ArrayList<Integer> eliminados = new ArrayList<Integer>();
                    for (Libro libro : root.getArbolB().getArbolB()) {
                        if (libro != null) {
                            eliminados.add(libro.getISBN());
                            isbn.remove((Object) libro.getISBN());
                        }
                    }
                    isbn.removeAll(eliminados);
                    if (s) {
                        cb.eliminarCategoria(root);
                        s = false;
                    }

                    root = null;
                } else {
                    ArrayList<Integer> eliminados = new ArrayList<Integer>();
                    for (Libro libro : root.getArbolB().getArbolB()) {
                        if (libro != null) {
                            eliminados.add(libro.getISBN());
                            isbn.remove((Object) libro.getISBN());
                        }
                    }
                    isbn.removeAll(eliminados);
                    if (s) {
                        cb.eliminarCategoria(root);
                        s = false;
                    }
                    root = temp;
                }
            } else {
                AVLNode temp = minVal(root.getHd());
                ArrayList<Integer> eliminados = new ArrayList<Integer>();
                for (Libro libro : root.getArbolB().getArbolB()) {
                    if (libro != null) {
                        eliminados.add(libro.getISBN());
                        isbn.remove((Object) libro.getISBN());
                    }
                }
                isbn.removeAll(eliminados);
                if (s) {
                    cb.eliminarCategoria(root);
                    s = false;
                }
                root.setCategoria(temp.getCategoria());
                root.setArbolB(temp.getArbolB());
                root.setCarnetUsuario(temp.getCarnetUsuario());
                root.setHd(delete(root.getHd(), temp.getCategoria(), temp.getCarnetUsuario()));
            }
        }
        if (root == null) {
            return root;
        }
        root.setAltura(Max(altura(root.getHi()), altura(root.getHd())) + 1);
        if (altura(root.getHi()) - altura(root.getHd()) == 2) {
            if (categoria.compareTo(root.getHi().getCategoria()) < 0) {
                return rotacionDobleIzquierda(root);
            } else {
                try {
                    System.out.println("lo hace?");
                    return rotacionDobleIzquierda(root);
                } catch (Exception ex) {
                    System.out.println("valio gaver");
                    return rotacionIzquierda(root);
                }
            }
        } else if (altura(root.getHd()) - altura(root.getHi()) == 2) {
            if (categoria.compareTo(root.getHd().getCategoria()) > 0) {
                return rotacionDobleDerecha(root);
            } else {
                try {
                    System.out.println("lo hace?");
                    return rotacionDobleDerecha(root);
                } catch (Exception ex) {
                    System.out.println("valio gaver");
                    return rotacionDerecha(root);
                }
            }
        } else;
        return root;
    }

    private AVLNode minVal(AVLNode nodo) {
        AVLNode actual = nodo;
        while (actual.getHi() != null) {
            actual = actual.getHi();
        }
        return actual;
    }

    public void graficarArbol() {
        String grafica = "digraph ArbolAVL{\n";
        grafica += graficar(raiz);
        grafica += "}";
        FileWriter flwriter = null;
        try {
            //crea el flujo para escribir en el archivo
            flwriter = new FileWriter("ArbolAVL.txt");
            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
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
        try {
            ProcessBuilder pbuilder;
            String direccionPng = "ArbolAVL.png";
            String direccionDot = "ArbolAVL.txt";
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", direccionPng, direccionDot);
            pbuilder.redirectErrorStream(true);
            //Ejecuta el proceso
            pbuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String direccionPng = "ArbolAVL.png";
            File objetofile = new File(direccionPng);
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            System.out.println(ex);

        }

    }

    public String graficar(AVLNode root) {
        String grafica = "";
        if (root != null) {
            grafica += root.getCategoria() + " [label=\"" + root.getCategoria() + "\nLibros: " + root.getArbolB().getArbolB().size() + "\nCarnet: " + root.getCarnetUsuario() + "  \"];\n";
            if (root.getHi() != null) {
                grafica += root.getCategoria() + "->" + root.getHi().getCategoria() + ";\n";
                grafica += graficar(root.getHi());
            }
            if (root.getHd() != null) {
                grafica += root.getCategoria() + "->" + root.getHd().getCategoria() + ";\n";
                grafica += graficar(root.getHd());
            }
        }
        return grafica;
    }

    public void graficarArbolB(AVLNode actual) {
        String grafica = "digraph ArbolB{\n node[shape=rect];\n";
        grafica += actual.getArbolB().imprimirGrafico(actual.getArbolB());
        grafica += "}";
        FileWriter flwriter = null;
        try {
            //crea el flujo para escribir en el archivo
            flwriter = new FileWriter("ArbolB.txt");
            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
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
        try {
            ProcessBuilder pbuilder;
            String direccionPng = "ArbolB.png";
            String direccionDot = "ArbolB.txt";
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", direccionPng, direccionDot);
            pbuilder.redirectErrorStream(true);
            //Ejecuta el proceso
            pbuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String direccionPng = "ArbolB.png";
            File objetofile = new File(direccionPng);
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            System.out.println(ex);

        }

    }
}
