/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CLASES.Cliente;
import CLASES.Empleado;
import CLASES.Inventario;
import CLASES.Logica;
import CLASES.Proveedor;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jonathan
 */
public class FrameCompra extends javax.swing.JInternalFrame {
    private Logica logica;
    private Empleado empleado;
    private double total;
    private TextAutoCompleter autoProducto;
    private ArrayList<Inventario> inventario;
    private DefaultTableModel modelo;
    private int index;
    /**
     * Creates new form FrameVenta
     */
    public FrameCompra(Logica logica, Empleado empleado) {
        initComponents();
        
        this.logica=logica;
        this.empleado=empleado; 
        
        total=0.00;
        
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("PRODUCTO");
        modelo.addColumn("MARCA");
        modelo.addColumn("PRESENTACION");
        modelo.addColumn("UNIDAD");
        modelo.addColumn("PRECIO");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("SUB TOTAL");
        
        tableVentas.setModel(modelo);
        
        index=-1;
        
        inventario=logica.getListInventario();
        autoProducto = new TextAutoCompleter(txProducto, new AutoCompleterCallback() {
            @Override
            public void callback(Object selectedItem) {
                for(int i=0; i<inventario.size(); i++){
                    String valor=inventario.get(i).getProducto()+" "+inventario.get(i).getMarca()+" "+inventario.get(i).getPresentacion()+" "+inventario.get(i).getUnidad();
                    if(valor.equals(selectedItem)){
                        index=i;
                        txCantidad.setText("1");
                        txPrecio.setText(inventario.get(i).getPrecio()+"");
                    }
                }
            }
        });
        autoProducto.setCaseSensitive(false);
        autoProducto.setMode(0);               
        for(int i=0; i<inventario.size(); i++){
            String valor=inventario.get(i).getProducto()+" "+inventario.get(i).getMarca()+" "+inventario.get(i).getPresentacion()+" "+inventario.get(i).getUnidad();
            autoProducto.addItem(valor);
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

        jLabel1 = new javax.swing.JLabel();
        btGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVentas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txNIT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btAgregar = new javax.swing.JButton();
        txProducto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txCantidad = new javax.swing.JTextField();
        txPrecio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btCodigo = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txNombre = new javax.swing.JTextField();
        btBorrar = new javax.swing.JButton();

        setClosable(true);
        setTitle("VENTA");
        setNextFocusableComponent(txProducto);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("VENTA");

        btGuardar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btGuardar.setText("GUARDAR");
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });

        tableVentas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PRODUCTO", "MARCA", "PRESENTACION", "UNIDAD", "PRECIO", "CANTIDAD", "SUB TOTAL"
            }
        ));
        jScrollPane1.setViewportView(tableVentas);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("PROVEEDOR:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("No. NIT:");

        txNIT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txNIT.setText("C/F");
        txNIT.setNextFocusableComponent(btGuardar);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Producto:");

        btAgregar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btAgregar.setText("+");
        btAgregar.setNextFocusableComponent(btCodigo);
        btAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarActionPerformed(evt);
            }
        });
        btAgregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btAgregarKeyPressed(evt);
            }
        });

        txProducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txProducto.setNextFocusableComponent(txCantidad);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Cantidad:");

        txCantidad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txCantidad.setNextFocusableComponent(txPrecio);

        txPrecio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txPrecio.setNextFocusableComponent(btAgregar);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Precio:");

        btCodigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCodigo.setText("=");
        btCodigo.setNextFocusableComponent(txProducto);
        btCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCodigoActionPerformed(evt);
            }
        });
        btCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btCodigoKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("TOTAL:");

        lbTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotal.setText("0.00");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Nombre:");

        txNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txNombre.setText("C/F");

        btBorrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btBorrar.setText("-");
        btBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 2, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txNIT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(txProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txNIT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarActionPerformed
        if(txCantidad.getText().equals("") || txProducto.getText().equals("") || txPrecio.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Todos los campos del producto son obligatorios.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }        
        if(Integer.valueOf(txCantidad.getText()) <= 0){
            JOptionPane.showMessageDialog(this, "No hay productos disponibles.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txProducto.setText("");
            txPrecio.setText("");
            txCantidad.setText("");
            return;
        }        
        if(index== -1){
            JOptionPane.showMessageDialog(this, "Seleccione un producto valido.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }        
        if(inventario.get(index).getCantidad() < Integer.valueOf(txCantidad.getText())){
            JOptionPane.showMessageDialog(this, "La cantidad maxima disponible es "+inventario.get(index).getCantidad(), "ERROR", JOptionPane.ERROR_MESSAGE);
            txCantidad.setText(inventario.get(index).getCantidad()+"");
            return;
        }
        
        Object [] fila = new Object[8];
        fila[0]=inventario.get(index).getId();
        fila[1]=inventario.get(index).getProducto();
        fila[2]=inventario.get(index).getMarca();
        fila[3]=inventario.get(index).getPresentacion();
        fila[4]=inventario.get(index).getUnidad();
        fila[5]=Double.valueOf(txPrecio.getText());
        fila[6]=Integer.valueOf(txCantidad.getText());
        fila[7]=Double.valueOf(txCantidad.getText()) * Double.valueOf(txPrecio.getText());
        
        
        total+=Double.valueOf(txCantidad.getText()) * Double.valueOf(txPrecio.getText());
        lbTotal.setText(total+"");
        modelo.addRow(fila);
        
        inventario.get(index).setCantidad( inventario.get(index).getCantidad() - Integer.valueOf(txCantidad.getText()) );        
        
        txProducto.setText("");
        txCantidad.setText("");
        txPrecio.setText("");
        index=-1;
    }//GEN-LAST:event_btAgregarActionPerformed

    private void btBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBorrarActionPerformed
        int row = tableVentas.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Seleccione una fila a eliminar.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for(int i =0; i<inventario.size(); i++){
            if(inventario.get(i).getId() == (int) modelo.getValueAt(row, 0)){
                inventario.get(i).setCantidad( inventario.get(i).getCantidad() + (int) modelo.getValueAt(row, 6) );
                total-=(double) modelo.getValueAt(row, 7);
                lbTotal.setText(total+"");
                break;
            }
        }
        
        modelo.removeRow(row);
        
    }//GEN-LAST:event_btBorrarActionPerformed

    private void btAgregarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btAgregarKeyPressed
        btAgregar.doClick();
    }//GEN-LAST:event_btAgregarKeyPressed

    private void btCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btCodigoKeyPressed
        btCodigo.doClick();
    }//GEN-LAST:event_btCodigoKeyPressed

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
     
        if (modelo.getRowCount() <= 0){
            JOptionPane.showMessageDialog(this, "Agregue articulos a la lista de venta.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(logica.compra(empleado, modelo, total, new Proveedor(txNIT.getText().toUpperCase(), txNombre.getText().toUpperCase()))){
            total=0.00;
            lbTotal.setText(total+"");
            
            modelo = new DefaultTableModel();
            modelo.addColumn("ID");
            modelo.addColumn("PRODUCTO");
            modelo.addColumn("MARCA");
            modelo.addColumn("PRESENTACION");
            modelo.addColumn("UNIDAD");
            modelo.addColumn("PRECIO");
            modelo.addColumn("CANTIDAD");
            modelo.addColumn("SUB TOTAL");

            tableVentas.setModel(modelo);
            txNIT.setText("C/F");
            txNombre.setText("C/F");
            JOptionPane.showMessageDialog(this, "Compra exitosa.", "CORRECTO", JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Ocurrio un error en la compra.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
    }//GEN-LAST:event_btGuardarActionPerformed

    private void btCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCodigoActionPerformed
        // Con caja de texto
        int codigo = Integer.valueOf(JOptionPane.showInputDialog(this,"Código:", "CODIGO",JOptionPane.QUESTION_MESSAGE));
        
        for (int i = 0; i < inventario.size(); i++) {
            if(inventario.get(i).getId()==codigo){
                String valor=inventario.get(i).getProducto()+" "+inventario.get(i).getMarca()+" "+inventario.get(i).getPresentacion()+" "+inventario.get(i).getUnidad();
                txProducto.setText(valor);
                txCantidad.setText(1+"");
                txPrecio.setText(inventario.get(i).getPrecio()+"");
                index=i;
                break;
            }
        }

        
    }//GEN-LAST:event_btCodigoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAgregar;
    private javax.swing.JButton btBorrar;
    private javax.swing.JButton btCodigo;
    private javax.swing.JButton btGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tableVentas;
    private javax.swing.JTextField txCantidad;
    private javax.swing.JTextField txNIT;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextField txPrecio;
    private javax.swing.JTextField txProducto;
    // End of variables declaration//GEN-END:variables
}
