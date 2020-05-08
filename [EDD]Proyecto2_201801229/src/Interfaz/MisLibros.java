/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Clases.*;
import Estructuras.*;

import static edd.proyecto2_201801229.EDDProyecto2_201801229.tablaHash;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.mavl;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author l4kz4
 */
public class MisLibros extends javax.swing.JFrame {

    /**
     * Creates new form MisLibros
     */
    DefaultTableModel model = new DefaultTableModel();
    Usuario usuarioActual = new Usuario();
    ArrayList<Libro> libros = new ArrayList<Libro>();
    public MisLibros(Usuario actual) {
        initComponents();
        usuarioActual = actual;
        libros = mavl.lista(usuarioActual.getCarnet());
        cargaDeDatos();
    }

    public void cargaDeDatos(){
        model.addColumn("ISBN");
        model.addColumn("Titulo");
        model.addColumn("Autor");
        model.addColumn("Editorail");
        model.addColumn("Año");
        model.addColumn("Edicion");
        model.addColumn("Categoria");
        model.addColumn("Idioma");
        model.addColumn("Carnet Usuario");
        
        for (Libro libro:libros) {
            if (libro!=null) {
                Object[] dato = new Object[]{libro.getISBN(),libro.getTitulo(),
                libro.getAutor(),libro.getEditorial(),libro.getAño(),
                libro.getEdicion(),libro.getCategoria(),libro.getIdioma(),
                libro.getCarnetUsuario()};
                model.addRow(dato);
            }
        }
        
        jTable1.setModel(model);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Titulo", "Autor", "Editorial", "Año", "Edicion", "Categoria", "Idioma", "Carnet Usuario"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 720, 410));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Biblioteca bi  = new Biblioteca(usuarioActual);
        bi.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
