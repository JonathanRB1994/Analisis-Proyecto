/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CLASES.Logica;
import CLASES.Marca;
import CLASES.Producto;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jonathan
 */
public class FrameInventario extends javax.swing.JInternalFrame {
    private Logica logica;
    
    private ArrayList <Producto> productos;
    private ArrayList <Marca> marcas;
    
    private TextAutoCompleter autoProducto;
    private TextAutoCompleter autoMarca;
    
    private boolean habilitar=false;
    /**
     * Creates new form FrameInventario
     */
    public FrameInventario(Logica logica) {
        initComponents();
        
        this.logica=logica;
         
        actualizar();
               
    }
    
    private void actualizar(){
        String condiciones="";
        if(rbSI.isSelected()){
            condiciones+=" AND I.ESTADO=1 ";
        }else{
            condiciones+=" AND I.ESTADO=0 ";
        }
        DefaultTableModel modelo = logica.getModelInventario(condiciones);
        TableRowSorter sorter = new TableRowSorter(modelo);
        tableInventario.setModel(modelo);
        tableInventario.setRowSorter(sorter);
        
        txProducto.setText("");
        txMarca.setText("");
        
        productos=logica.getListProducto();        
        autoProducto = new TextAutoCompleter(txProducto);
        autoProducto.setCaseSensitive(false);
        autoProducto.setMode(0);
        for (int i = 0; i < productos.size(); i++) {
            autoProducto.addItem(productos.get(i).getNombre());
        }
        
        marcas=logica.getListMarca();        
        autoMarca = new TextAutoCompleter(txMarca);
        autoMarca.setCaseSensitive(false);
        autoMarca.setMode(0);
        for (int i = 0; i < marcas.size(); i++) {
            autoMarca.addItem(marcas.get(i).getNombre());
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

        Activo = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableInventario = new javax.swing.JTable();
        btBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txProducto = new javax.swing.JTextField();
        jlabel11 = new javax.swing.JLabel();
        txMarca = new javax.swing.JTextField();
        btNuevo = new javax.swing.JButton();
        btActualizar1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        rbSI = new javax.swing.JRadioButton();
        rbNO = new javax.swing.JRadioButton();
        btEstado = new javax.swing.JButton();

        setClosable(true);
        setTitle("Inventario");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("INVENTARIO");

        tableInventario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableInventario);

        btBuscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btBuscar.setText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Producto:");

        txProducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jlabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlabel11.setText("Marca:");

        txMarca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btNuevo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btNuevo.setText("Nuevo Articulo");
        btNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoActionPerformed(evt);
            }
        });

        btActualizar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btActualizar1.setText("Actualizar");
        btActualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActualizar1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Activo:");

        Activo.add(rbSI);
        rbSI.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rbSI.setSelected(true);
        rbSI.setText("SI");
        rbSI.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbSIItemStateChanged(evt);
            }
        });

        Activo.add(rbNO);
        rbNO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rbNO.setText("NO");

        btEstado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btEstado.setText("Deshabilitar");
        btEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                                .addComponent(btActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 155, Short.MAX_VALUE))
                            .addComponent(txProducto))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(rbSI))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbNO)
                        .addGap(37, 37, 37)
                        .addComponent(btBuscar)
                        .addGap(112, 112, 112)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlabel11)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbSI)
                            .addComponent(rbNO))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        String producto="";
        String marca="";
        producto+=autoProducto.findItem(txProducto.getText());
        marca+=autoMarca.findItem(txMarca.getText());
         
        boolean t=true;
        String condiciones="";
        if(rbSI.isSelected()){
            condiciones+=" AND I.ESTADO=1 ";
        }else{
            condiciones+=" AND I.ESTADO=0 ";
        }
        String mensaje="Se realizo una busqueda por ";
        if(!producto.equals("null")){
            condiciones+="AND P.NOMBRE=\'"+producto.toUpperCase()+"\' ";
            mensaje+="PRUDCTO";
            t=false;
        }
        if(!marca.equals("null")){
            condiciones+="AND M.NOMBRE=\'"+marca.toUpperCase()+"\' ";
            mensaje+=" MARCA";
            t=false;
        }
        mensaje+=".";
        
        if(true){
            actualizar();
            return;
        }
        
        JOptionPane.showMessageDialog(this, mensaje, "Buscado", JOptionPane.PLAIN_MESSAGE);
        DefaultTableModel modelo = logica.getModelInventario(condiciones);
        TableRowSorter sorter = new TableRowSorter(modelo);
        tableInventario.setModel(modelo);
        tableInventario.setRowSorter(sorter);

    }//GEN-LAST:event_btBuscarActionPerformed

    private void btNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoActionPerformed
        FrameNuevoArticulo frame = new FrameNuevoArticulo(logica);       
        Descktop.descktopPane.add(frame);
        frame.toFront();   
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = frame.getSize();
        frame.setLocation((pantalla.width - ventana.width)/ 2, (pantalla.height - ventana.height)/ 2);
        frame.show();
    }//GEN-LAST:event_btNuevoActionPerformed

    private void btActualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActualizar1ActionPerformed
        actualizar();
        
        txMarca.setText("");
        txProducto.setText("");
    }//GEN-LAST:event_btActualizar1ActionPerformed

    private void btEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEstadoActionPerformed
        try {
            DefaultTableModel tm = (DefaultTableModel) tableInventario.getModel();            
            String id=String.valueOf(tm.getValueAt(tableInventario.getSelectedRow(),0));

            if(habilitar){
                if(logica.changeStateInventario(id, "1")){
                    JOptionPane.showMessageDialog(this, "Articulo "+id+" habilitado.", "Correcto", JOptionPane.PLAIN_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this, "Articulo "+id+" no se puedo habilitar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                if(logica.changeStateInventario(id, "0")){
                    JOptionPane.showMessageDialog(this, "Articulo "+id+" deshabilitado.", "Correcto", JOptionPane.PLAIN_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this, "Articulo "+id+" no se puedo deshabilitado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            actualizar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Selecciones una fila de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
        }                
        
    }//GEN-LAST:event_btEstadoActionPerformed

    private void rbSIItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbSIItemStateChanged
        
        if(rbSI.isSelected()){
            habilitar=false;
            btEstado.setText("Deshabilitar");
        }else{
            habilitar=true;
            btEstado.setText("Habilitar");
        }
        actualizar();
        
    }//GEN-LAST:event_rbSIItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Activo;
    private javax.swing.JButton btActualizar1;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btEstado;
    private javax.swing.JButton btNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabel11;
    private javax.swing.JRadioButton rbNO;
    private javax.swing.JRadioButton rbSI;
    private javax.swing.JTable tableInventario;
    private javax.swing.JTextField txMarca;
    private javax.swing.JTextField txProducto;
    // End of variables declaration//GEN-END:variables
}
