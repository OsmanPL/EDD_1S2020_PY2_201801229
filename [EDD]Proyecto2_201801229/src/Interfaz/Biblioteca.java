/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Clases.*;
import Metodos.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import static edd.proyecto2_201801229.EDDProyecto2_201801229.tablaHash;
import static edd.proyecto2_201801229.EDDProyecto2_201801229.mavl;
/**
 *
 * @author l4kz4
 */
public class Biblioteca extends javax.swing.JFrame {

    /**
     * Creates new form Biblioteca
     */
    Usuario usuarioActual = new Usuario();
    public Biblioteca(Usuario actual) {
        initComponents();
        usuarioActual = actual;
        user();
    }

    public void user(){
        jLabel2.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jLabel2.setText("Carnet: "+usuarioActual.getCarnet());
        jTextField2.setText(usuarioActual.getNombre());
        jTextField3.setText(usuarioActual.getApellido());
        jTextField4.setText(usuarioActual.getCarrera());
        jPasswordField1.setText("");
        jPasswordField2.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Cerrar Sesion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 120, -1));

        jButton2.setText("Cargar Archivo Libros");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Carnet:");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 270, 20));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nombre:");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 90, 20));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 350, -1));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 350, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Apellido:");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 90, 20));
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 350, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Carrera:");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 90, 20));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Password Viejo:");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 100, 20));
        jPanel1.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 350, -1));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Password Nuevo:");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 100, 20));
        jPanel1.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 350, -1));

        jButton3.setText("Eliminar Usuario");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 310, 180, 40));

        jButton4.setText("Modificar Informacion");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, 180, 40));

        jButton5.setText("Reportes");
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 180, 40));

        jButton6.setText("Nuevo Libro");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 180, 40));

        jButton7.setText("Dar De Baja");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 180, 40));

        jButton8.setText("Ver Mis Libros");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 180, 40));

        jButton9.setText("Ver Catalogo");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 180, 40));

        jButton10.setText("Configuración");
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 350, 180, 40));

        jButton11.setText("Crear Categoria");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 180, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String aux="";
        String texto="";
        try
        {
            /**llamamos el metodo que permite cargar la ventana*/
            JFileChooser file=new JFileChooser();
            FileNameExtensionFilter filtro=new FileNameExtensionFilter("JSON","json");
            file.setFileFilter(filtro);
            file.showOpenDialog(this);
            File abre=file.getSelectedFile();

            System.out.println(abre.getAbsolutePath());

            LecturaArchivos users = new LecturaArchivos();
            users.leerLibros(abre.getAbsolutePath(), usuarioActual.getCarnet());
            if(abre!=null)
            {
                FileReader archivos=new FileReader(abre);
                BufferedReader lee=new BufferedReader(archivos);
                while((aux=lee.readLine())!=null)
                {
                    texto+= aux+ "\n";
                }
                lee.close();
            }
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null,ex+"" +
                "\nNo se ha encontrado el archivo",
                "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.hide();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int carnet = usuarioActual.getCarnet();
        String nombre = jTextField2.getText();
        String apellido = jTextField3.getText();
        String carrera = jTextField4.getText();
        if (nombre.equals("") || apellido.equals("") || carrera.equals("")) {
            JOptionPane.showMessageDialog(null,"Los Campos Nombre, Apellido y Carrera deben ser llenados",
                    "Error",JOptionPane.ERROR_MESSAGE);
        }else{
            if (jPasswordField2.getText().equals("")) {
                tablaHash.modificar(carnet, nombre, apellido, carrera, usuarioActual.getPassword());
                JOptionPane.showMessageDialog(null,"El Usuario Ha sido Modificado",
                "Usuario",JOptionPane.INFORMATION_MESSAGE);
            }else{
                if (jPasswordField1.getText().equals(usuarioActual.getPassword())) {
                    tablaHash.modificar(carnet, nombre, apellido, carrera, jPasswordField2.getText());
                    JOptionPane.showMessageDialog(null,"El Usuario Ha sido Modificado",
                    "Usuario",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"El password no concuerda con el del usuario",
                    "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        user();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        NuevoLibro nl = new NuevoLibro(usuarioActual);
        nl.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        DarBaja db = new DarBaja(usuarioActual);
        db.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        MisLibros ml = new MisLibros(usuarioActual);
        ml.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        Catologo cat = new Catologo(usuarioActual);
        cat.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton11ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
