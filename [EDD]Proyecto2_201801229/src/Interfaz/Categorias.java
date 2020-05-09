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
import static edd.proyecto2_201801229.EDDProyecto2_201801229.bloques;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.red;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.cb;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.nodoRed;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author l4kz4
 */
public class Categorias extends javax.swing.JFrame {

    /**
     * Creates new form Categorias
     */
    DefaultTableModel model = new DefaultTableModel();
    Usuario usuarioActual = new Usuario();
    ArrayList<AVLNode> libros = new ArrayList<AVLNode>();
    public Categorias(Usuario actual) {
        initComponents();
        usuarioActual = actual;
        libros = mavl.devolverArbol();
        cargaDeDatos();
        cargaItem();
    }
public void cargaDeDatos(){
        model = new DefaultTableModel();
        model.addColumn("Categoria");
        model.addColumn("Libros");
        
        for (AVLNode libro:libros) {
            if (libro!=null) {
                Object[] dato = new Object[]{libro.getCategoria(),libro.getArbolB().getArbolB().size()};
                model.addRow(dato);
            }
        }
        
        jTable1.setModel(model);
    }
    
    public void cargaItem(){
        jComboBox1.removeAllItems();
        ArrayList<String> catalogo = mavl.iniciarCatalogo();
        for (String categoria:catalogo) {
            if (categoria!=null) {
                jComboBox1.addItem(categoria);
            }
        }
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
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 240, -1));

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 110, -1));

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 240, -1));

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 110, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 710, 350));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Eliminar Categoria:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 110, 20));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Categoria Nueva:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 110, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Biblioteca bi  = new Biblioteca(usuarioActual);
        bi.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
            String categoria = jTextField1.getText();
            mavl.insert(categoria, usuarioActual.getCarnet());
            if (categoria.equals("")) {
                
                JOptionPane.showMessageDialog(null,
                "Lllenar el campo de categoria nueva",
                "Caegoria",JOptionPane.ERROR_MESSAGE);
            }else{
                
                JOptionPane.showMessageDialog(null,
                "Categoria creada exitosamente",
                "Caegoria Nueva",JOptionPane.INFORMATION_MESSAGE);
                libros = mavl.devolverArbol();
            
                cargaDeDatos();
                cargaItem();
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,
                "Error: "+ex,
                "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String categoria = jComboBox1.getSelectedItem().toString();
             mavl.eliminar(categoria, usuarioActual.getCarnet());
             JOptionPane.showMessageDialog(null,
                "Se elimino la categoria",
                "Eliminar Categoria",JOptionPane.INFORMATION_MESSAGE);
             libros = mavl.devolverArbol();
            
                cargaDeDatos();
                cargaItem();
        /*try{
             
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,
                "Error: "+ex,
                "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
        }*/
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
