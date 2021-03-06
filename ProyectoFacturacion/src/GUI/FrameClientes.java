/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CLASES.Cliente;
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
public class FrameClientes extends javax.swing.JInternalFrame {
    private Logica logica;
    
    private ArrayList <Cliente> clientes;    
    
    private TextAutoCompleter autoNombre;
    private TextAutoCompleter autoApellidos;
    private TextAutoCompleter autoNIT;
    
    
    /**
     * Creates new form FrameClientes
     */
    public FrameClientes(Logica logica) {
        initComponents();
        
        this.logica=logica;
        
        actualizar();
    }
    
    private void actualizar(){
        DefaultTableModel modelo = logica.getModelClientes(null);
        TableRowSorter sorter = new TableRowSorter(modelo);
        tableClientes.setModel(modelo);
        tableClientes.setRowSorter(sorter);
        
        txNombre.setText("");
        txApellidos.setText("");
        txNIT.setText("");
        
        clientes=logica.getListClientes();
        
        autoNombre = new TextAutoCompleter(txNombre);
        autoApellidos = new TextAutoCompleter(txApellidos);
        autoNIT = new TextAutoCompleter(txNIT);
        
        autoNombre.setCaseSensitive(false);
        autoApellidos.setCaseSensitive(false);
        autoNIT.setCaseSensitive(false);
        
        autoNombre.setMode(0);
        autoApellidos.setMode(0);
        autoNIT.setMode(0);
        
        for (int i = 0; i < clientes.size(); i++) {
            autoNombre.addItem(clientes.get(i).getNombre());
            autoApellidos.addItem(clientes.get(i).getApellidos());
            autoNIT.addItem(clientes.get(i).getNit());
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

        jLabel29 = new javax.swing.JLabel();
        btNuevo = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();
        btActualizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txNombre = new javax.swing.JTextField();
        jlabel11 = new javax.swing.JLabel();
        txApellidos = new javax.swing.JTextField();
        btBuscar = new javax.swing.JButton();
        jlabel12 = new javax.swing.JLabel();
        txNIT = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Clientes");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setText("CLIENTES");

        btNuevo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btNuevo.setText("Nuevo Cliente");
        btNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoActionPerformed(evt);
            }
        });

        tableClientes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tableClientes);

        btActualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btActualizar.setText("Actualizar");
        btActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActualizarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nombre:");

        txNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jlabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlabel11.setText("Apellidos:");

        txApellidos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btBuscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btBuscar.setText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        jlabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlabel12.setText("NIT:");

        txNIT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txNIT, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btBuscar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txNIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoActionPerformed
        FrameNuevoCliente frame = new FrameNuevoCliente(logica);       
        Descktop.descktopPane.add(frame);
        frame.toFront(); 
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = frame.getSize();
        frame.setLocation((pantalla.width - ventana.width)/ 2, (pantalla.height - ventana.height)/ 2);
        frame.show();
    }//GEN-LAST:event_btNuevoActionPerformed

    private void btActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActualizarActionPerformed
        actualizar();
    }//GEN-LAST:event_btActualizarActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        String nombre="";
        String apellidos="";
        String nit="";
        nombre+=txNombre.getText().toUpperCase();
        apellidos+=txApellidos.getText().toUpperCase();
        nit+=txNIT.getText().toUpperCase();

        String condiciones ="";
        boolean and=false;
        String mensaje="Se realizo una busqueda por ";
        if(!nombre.equals("")){
            and=true;
            condiciones+="NOMBRE LIKE \'%"+nombre.toUpperCase()+"%\' ";
            mensaje+="NOMBRE";
        }
        if(!apellidos.equals("")){
            if(and){
                condiciones+=" AND ";
            }
            and=true;
            condiciones+="APELLIDOS LIKE \'%"+apellidos.toUpperCase()+"%\' ";
            mensaje+=" APELIIDOS";
        }
        if(!nit.equals("")){
            if(and){
                condiciones+=" AND ";
            }
            condiciones+="NIT LIKE \'%"+nit.toUpperCase()+"%\' ";
            mensaje+=" NIT";
        }
        mensaje+=".";
        
        if(condiciones.equals("")){
            actualizar();
            return;
        }

        JOptionPane.showMessageDialog(this, mensaje, "Buscado", JOptionPane.PLAIN_MESSAGE);
        DefaultTableModel modelo = logica.getModelClientes(condiciones);
        TableRowSorter sorter = new TableRowSorter(modelo);
        tableClientes.setModel(modelo);
        tableClientes.setRowSorter(sorter);
    }//GEN-LAST:event_btBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btActualizar;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btNuevo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jlabel11;
    private javax.swing.JLabel jlabel12;
    private javax.swing.JTable tableClientes;
    private javax.swing.JTextField txApellidos;
    private javax.swing.JTextField txNIT;
    private javax.swing.JTextField txNombre;
    // End of variables declaration//GEN-END:variables
}
