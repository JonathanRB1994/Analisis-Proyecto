package CLASES;


import GUI.Descktop;
import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import com.mysql.jdbc.Connection;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Jonathan
 */
public class Logica {
    /*Controlador*/
    
    /* Variables para la Conexion*/
    private String host="localhost";
    private String port="3306";
    private String database="FACTURACION_ANALISIS";
    private String user="root";
    private String password="";
    
    public String cortadorNombreProducto(String s) {

        int cuantoLeDeboRellenar = 0;
        String nuevoString = "";
        String espacio = " ";
        if (s.length() > 20) {
            return s.substring(0, 19);
        } else {
            cuantoLeDeboRellenar = 19 - s.length();
            nuevoString = s;
            for (int i = 0; i <= cuantoLeDeboRellenar; i++) {
                nuevoString = nuevoString.concat(espacio);
            }
            return nuevoString;
        }
    }

    public int centrarLabels(int longitudLabel) {

        int posicionCentral = 40 - longitudLabel;
        if (posicionCentral == 0) {
            return 0;
        } else {
            int division = posicionCentral / 2;
            return division;
        }
    }

    public void imprimirFactura(DefaultTableModel modelo, Resolucion res, Venta venta, Transaccion t, Cliente c) throws IOException {

        PrinterMatrix printer = new PrinterMatrix();
        int numeroLineas = (modelo.getRowCount() * 2) + 52;
        int espaciosRecorridosDetalleVenta = 0;

        printer.setOutSize(numeroLineas, 40);

        printer.printTextLinCol(3, 4 + centrarLabels((venta.getNombreComercial()).length()), venta.getNombreComercial());
        printer.printTextLinCol(5, 12, venta.getRazonSocial());
        printer.printTextLinCol(7, 2 + centrarLabels((venta.getDireccion()).length()), venta.getDireccion());

        printer.printTextLinCol(11, 4 + centrarLabels((venta.getNit()).length() + 5), "Nit: " + venta.getNit());
        printer.printTextLinCol(13, 6 + centrarLabels((res.getNoResolucion()).length() + (res.getFechaAutorizacion()+"").length() + 11), "Resolucion " + res.getNoResolucion() + " del " + res.getFechaAutorizacion());
        printer.printTextLinCol(15, 7 + centrarLabels((res.getNoSerie()).length() + (res.getNoInicial()+"").length() + (res.getNoFinal()+"").length() + 14), "Serie " + res.getNoSerie() + " de " + res.getNoInicial() + " al " + res.getNoFinal());
        printer.printTextLinCol(17, 4 + centrarLabels(18), "Resolucion Sistema");
        printer.printTextLinCol(19, 8 + centrarLabels((venta.getResolucionSistema()).length() + (venta.getFechaResolucion()+"").length() + 9), "Res. " +venta.getResolucionSistema()+ " de " + venta.getFechaResolucion());

        printer.printTextLinCol(23, 4, "FACTURA SERIE " + res.getNoSerie());
        printer.printTextLinCol(23, 23, "No. ");
        printer.printTextLinCol(23, 35, res.getNoActual()+"");

        printer.printTextLinCol(25, 4, "FECHA DE EMISION:");
        printer.printTextLinCol(25, 21, t.getFecha()+"");
        printer.printTextLinCol(27, 4, "COMPUTADORA No.   " + venta.getNoMaquina());
        printer.printTextLinCol(29, 4, "TRANSACCION:    " + t.getId());

        printer.printTextLinCol(32, 4, "Nombre:     " + c.getNombre());
        printer.printTextLinCol(34, 4, "Nit:               " + c.getNit());
        printer.printTextLinCol(36, 4, "Direccion:  " + c.getDireccion());

        printer.printTextLinCol(40, 8, "Producto                               Cant.          Precio          Subtotal");

        for (int i = 0; i < modelo.getRowCount(); i++) {
            espaciosRecorridosDetalleVenta = espaciosRecorridosDetalleVenta + 2;
            printer.printTextLinCol(40 + espaciosRecorridosDetalleVenta, 35, modelo.getValueAt(i, 1).toString());
            printer.printTextLinCol(40 + espaciosRecorridosDetalleVenta, 29, modelo.getValueAt(i, 6).toString());
            printer.printTextLinCol(40 + espaciosRecorridosDetalleVenta, 23, modelo.getValueAt(i, 5).toString());
            printer.printTextLinCol(40 + espaciosRecorridosDetalleVenta, 4, cortadorNombreProducto(modelo.getValueAt(i, 7).toString()));
        }

        printer.printTextLinCol(44 + espaciosRecorridosDetalleVenta, 10, "TOTAL                                                Q." + t.getTotal());
        printer.printTextLinCol(48 + espaciosRecorridosDetalleVenta, 6, "                           Sujeto a pago trimestrales");
        printer.printTextLinCol(50 + espaciosRecorridosDetalleVenta, 6, "                        GRACIAS POR PREFERIRNOS ");

        printer.toImageFile("factura.jpg");
        String path2 = System.getProperty("user.dir");
        File fileToPrint = new File(path2 + "\\factura.jpg");
        
        Desktop.getDesktop().print(fileToPrint);
    }
    
    public boolean checkResolution(String tipo){
        boolean resolucion=false;
        ResultSet res =consultarDB("SELECT * FROM RESOLUCION WHERE ESTADO=1 AND TIPO=\'"+tipo+"\'");  
        if (res != null) {
            try {  
                while(res.next()){
                    resolucion = true;
                    if(new Date().compareTo(res.getDate("FECHA_VENCIMIENTO"))>0){
                        updateDownResolucion(res.getInt("ID"));                        
                        return false;
                    }
                    if(res.getInt("NO_ACTUAL")>res.getInt("NO_FINAL")){
                        updateDownResolucion(res.getInt("ID"));
                        return false;
                    }
                }                       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }                
        return resolucion;
    }
    
    public boolean venta(Empleado empleado, Cliente cliente, DefaultTableModel modelo, String tipo, double total){
        try {
            Resolucion resolucion=null;
            
            java.text.SimpleDateFormat formatoFecha = new java.text.SimpleDateFormat("yyyy-MM-dd");
            
            Connection conn = getConexion();
            conn.setAutoCommit(false);
            Statement sentencia = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                        
            //OBTENER RESOLUCION Y AUMENTAR CONTADOR
            ResultSet res = sentencia.executeQuery("SELECT * FROM RESOLUCION WHERE ESTADO=1 AND TIPO=\'"+tipo+"\'");            
            if (res != null){
                while(res.next()){
                    resolucion=new Resolucion(res.getInt("ID"), res.getString("TIPO"), res.getString("NO_RESOLUCION"), res.getString("NO_SERIE"), res.getInt("NO_INICIAL"),
                            res.getInt("NO_FINAL"), res.getInt("NO_ACTUAL"), res.getBoolean("ESTADO"), res.getBoolean("CONTRIBUYENTE_CHICO"), res.getDate("FECHA_AUTORIZACION"), 
                            res.getDate("FECHA_VENCIMIENTO"), res.getDate("FECHA_INGRESO"));
                }
                res.close();
            }
            if(resolucion==null){
                return false;
            }

            //AUMENTAR CONTADOR RESOLUCION
            sentencia.executeUpdate("UPDATE RESOLUCION SET NO_ACTUAL="+ (resolucion.getNoActual()+1) +" WHERE ID="+resolucion.getId() );

            //GENERAR LA TRANASACCION                 
            sentencia.executeUpdate("INSERT INTO TRANSACCION (RESOLUCION, CORRELATIVO, TOTAL, FECHA, ESTADO) VALUES"
                    + "( \'"+resolucion.getId()+"\', "
                    + "\'"+resolucion.getNoActual()+"\', "
                    + "\'"+total+"\', "
                    + "\'"+formatoFecha.format( new Date() )+"\', "
                    + "\'EMITIDO\' )",
                    Statement.RETURN_GENERATED_KEYS);
            
            ResultSet res5=null;
            res5=sentencia.getGeneratedKeys();
            res5.next();
            int transaccionID=res5.getInt(1);
            res5.close();
            
            //OBTENER CONFIGURACION
            ResultSet res2 = sentencia.executeQuery("SELECT * FROM CONFIGURACION WHERE ID=1");  
            Configuracion configuracion=null;
            if (res2 != null){
                while(res2.next()){
                    configuracion = new Configuracion( res2.getInt("ID"), res2.getString("NOMBRE_COMERCIAL"), res2.getString("RAZON_SOCIAL"), res2.getString("DIRECCION"), res2.getString("NIT") );  
                }
                res2.close();
            }            
            if(configuracion==null){
                return false;
            }

            //OBTENER RESOLUCION SISTEMA
            ResultSet res3 = sentencia.executeQuery("SELECT * FROM RESOLUCION_SISTEMA WHERE ID=1");        
            ResolucionSistema resolucionSistema=null;
            if (res3 != null){
                while(res3.next()){
                    resolucionSistema=new ResolucionSistema( res3.getInt("ID"), res3.getString("NO_RESOLUCION"), res3.getDate("FECHA_AUTORIZACION"), res3.getInt("NO_MAQUINA") );
                }
                res3.close();
            }                    
            if(resolucionSistema==null){
                return false;
            }
            
            //GENERAR LA VENTA               
            sentencia.executeUpdate("INSERT INTO VENTA (TRANSACCION, CLIENTE, NOMBRE_COMERCIAL, RAZON_SOCIAL, DIRECCION, NIT, EMPLEADO, RESOLUCION_SISTEMA) VALUES"
                    + "( "+transaccionID+", "
                    + cliente.getId()+", "
                    + "\'"+configuracion.getNombreComercial()+"\', "
                    + "\'"+configuracion.getRazonSocial()+"\', "
                    + "\'"+configuracion.getDireccion()+"\', "
                    + "\'"+configuracion.getNIT()+"\', "
                    + empleado.getId()+", "
                    + "\'"+resolucionSistema.getNoResolucion()+"\' )",
                    Statement.RETURN_GENERATED_KEYS);
            
            ResultSet res6=null;
            res6=sentencia.getGeneratedKeys();
            res6.next();
            int ventaID=res6.getInt(1);
            res6.close();
            
            //GENERAR DETALLE VENTA        
            for(int i=0; i<modelo.getRowCount(); i++){
                sentencia.executeUpdate("INSERT INTO DETALLE_VENTA (VENTA, INVENTARIO, CANTIDAD, PRECIO, TOTAL) VALUES"
                    + "( "+ventaID+", "
                    + modelo.getValueAt(i, 0)+", "
                    + modelo.getValueAt(i, 6)+", "
                    + modelo.getValueAt(i, 5)+", "
                    + modelo.getValueAt(i, 7)+" )");
                
                ResultSet res8 = sentencia.executeQuery("SELECT CANTIDAD FROM INVENTARIO WHERE ID="+modelo.getValueAt(i, 0));        
                int cantidad =-1;
                if (res8 != null){
                    while(res8.next()){
                        cantidad = res8.getInt("CANTIDAD");
                    }
                    res8.close();
                }                    
                if(cantidad==-1){
                    return false;
                }
                cantidad-=(int) modelo.getValueAt(i, 6);
                sentencia.executeUpdate("UPDATE INVENTARIO SET CANTIDAD="+cantidad+"  WHERE ID="+modelo.getValueAt(i, 0)+" ");
            }
                
            sentencia.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }        
        return true;
    }
    
    public boolean compra(Empleado empleado, DefaultTableModel modelo, double total, Proveedor proveedor){
        try {
            Resolucion resolucion=null;
            
            java.text.SimpleDateFormat formatoFecha = new java.text.SimpleDateFormat("yyyy-MM-dd");
            
            Connection conn = getConexion();
            conn.setAutoCommit(false);
            Statement sentencia = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);                                    
            
            //GENERAR LA COMPRA              
            sentencia.executeUpdate("INSERT INTO COMPRA (P_NOMBRE, P_NIT, TOTAL, EMPLEADO, FECHA) VALUES"
                    + "( \'"+proveedor.getNombre()+"\', "
                    + "\'"+proveedor.getNIT()+"\', "
                    + total+", "
                    + empleado.getId()+", "
                    + "\'"+formatoFecha.format( new Date() )+"\') ",
                    Statement.RETURN_GENERATED_KEYS);
            
            ResultSet res6=null;
            res6=sentencia.getGeneratedKeys();
            res6.next();
            int compraID=res6.getInt(1);
            res6.close();
            
            //GENERAR DETALLE COMPRA        
            for(int i=0; i<modelo.getRowCount(); i++){
                sentencia.executeUpdate("INSERT INTO DETALLE_COMPRA (COMPRA, INVENTARIO, CANTIDAD, PRECIO, TOTAL) VALUES"
                    + "( "+compraID+", "
                    + modelo.getValueAt(i, 0)+", "
                    + modelo.getValueAt(i, 6)+", "
                    + modelo.getValueAt(i, 5)+", "
                    + modelo.getValueAt(i, 7)+" )");
                
                ResultSet res8 = sentencia.executeQuery("SELECT CANTIDAD FROM INVENTARIO WHERE ID="+modelo.getValueAt(i, 0));        
                int cantidad =-1;
                if (res8 != null){
                    while(res8.next()){
                        cantidad = res8.getInt("CANTIDAD");
                    }
                    res8.close();
                }                    
                if(cantidad==-1){
                    return false;
                }
                cantidad+=(int) modelo.getValueAt(i, 6);
                sentencia.executeUpdate("UPDATE INVENTARIO SET CANTIDAD="+cantidad+"  WHERE ID="+modelo.getValueAt(i, 0)+" ");
            }
                
            sentencia.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }        
        return true;
    }
    
    public ArrayList<Cliente> getListClientes(){    
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();               
        ResultSet res =consultarDB("SELECT * FROM CLIENTE");                
        if (res != null) {
            try {  
                while(res.next()){
                    clientes.add(new Cliente(res.getInt("ID"), res.getString("NOMBRE"), res.getString("APELLIDOS"), res.getString("TELEFONO"), res.getString("DIRECCION"), res.getString("EMAIL"), res.getString("NIT")));
                }                       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientes;
    }
    
    public ArrayList<Empleado> getListEmpleados(){    
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();               
        ResultSet res =consultarDB("SELECT * FROM Empleado");                
        if (res != null) {
            try {  
                while(res.next()){
                    empleados.add(new Empleado(res.getInt("ID"), res.getString("NOMBRE"), res.getString("APELLIDOS"), res.getBoolean("ESTADO"), res.getString("CLAVE"), res.getString("EMAIL"), res.getString("TELEFONO"), res.getInt("ROL")));
                }                       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return empleados;
    }
    
    public ArrayList<Marca> getListMarca(){    
        ArrayList<Marca> marcas = new ArrayList<Marca>();               
        ResultSet res =consultarDB("SELECT * FROM MARCA");
                
        if (res != null) {
            try {  
                while(res.next()){
                    marcas.add(new Marca(res.getInt("ID"), res.getString("NOMBRE")));
                }                       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return marcas;
    }
    
    public ArrayList<Unidad> getListUnidad(){    
        ArrayList<Unidad> unidades = new ArrayList<Unidad>();               
        ResultSet res =consultarDB("SELECT * FROM UNIDAD");
                
        if (res != null) {
            try {  
                while(res.next()){
                    unidades.add(new Unidad(res.getInt("ID"), res.getString("NOMBRE")));
                }                       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return unidades;
    }
    
    public ArrayList<Presentacion> getListPresentacion(){    
        ArrayList<Presentacion> presentaciones = new ArrayList<Presentacion>();               
        ResultSet res =consultarDB("SELECT * FROM PRESENTACION");
                
        if (res != null) {
            try {  
                while(res.next()){
                    presentaciones.add(new Presentacion(res.getInt("ID"), res.getString("NOMBRE")));
                }                       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return presentaciones;
    }
    
    public ArrayList<Producto> getListProducto(){    
        ArrayList<Producto> productos = new ArrayList<Producto>();               
        ResultSet res =consultarDB("SELECT * FROM PRODUCTO");
                
        if (res != null) {
            try {  
                while(res.next()){
                    productos.add(new Producto(res.getInt("ID"), res.getString("NOMBRE")));
                }                       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productos;
    }
    
    public ArrayList<Inventario> getListInventario(){    
        ArrayList<Inventario> inventario = new ArrayList<Inventario>();        
                
        ResultSet res= consultarDB("SELECT I.ID, P.NOMBRE, M.NOMBRE, PE.NOMBRE, U.NOMBRE, I.PRECIO, I.CANTIDAD FROM INVENTARIO I, PRODUCTO P, MARCA M, PRESENTACION PE, UNIDAD U WHERE I.PRODUCTO=P.ID AND I.MARCA=M.ID AND I.PRESENTACION=PE.ID AND I.UNIDAD=U.ID AND ESTADO=1");     

                
        if (res != null) {
            try {  
                while(res.next()){
                    inventario.add(new Inventario(res.getInt("I.ID"), res.getString("P.NOMBRE"), res.getString("M.NOMBRE"), res.getString("PE.NOMBRE"), res.getString("U.NOMBRE"), res.getInt("I.PRECIO"), res.getInt("I.CANTIDAD")));
                }                       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return inventario;
    }
    
    public DefaultTableModel getModelInventario(String condiciones){    
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("PRODUCTO");
        modelo.addColumn("MARCA");
        modelo.addColumn("PRESENTACION");
        modelo.addColumn("UNIDAD");
        modelo.addColumn("PRECIO");
        modelo.addColumn("CANTIDAD");
                
        ResultSet res;
        if(condiciones!=null){
            res= consultarDB("SELECT I.ID, P.NOMBRE, M.NOMBRE, PE.NOMBRE, U.NOMBRE, I.PRECIO, I.CANTIDAD FROM INVENTARIO I, PRODUCTO P, MARCA M, PRESENTACION PE, UNIDAD U WHERE I.PRODUCTO=P.ID AND I.MARCA=M.ID AND I.PRESENTACION=PE.ID AND I.UNIDAD=U.ID "
                                    +condiciones);     
        }else{
            res= consultarDB("SELECT I.ID, P.NOMBRE, M.NOMBRE, PE.NOMBRE, U.NOMBRE, I.PRECIO, I.CANTIDAD FROM INVENTARIO I, PRODUCTO P, MARCA M, PRESENTACION PE, UNIDAD U WHERE I.PRODUCTO=P.ID AND I.MARCA=M.ID AND I.PRESENTACION=PE.ID AND I.UNIDAD=U.ID");     
        }
                
        if (res != null) {
            try {  
                while(res.next()){
                    Object [] fila = new Object[7];
                    for (int i=0;i<7;i++){
                        fila[i] = res.getObject(i+1);
                    }               
                    modelo.addRow(fila);
                }                       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return modelo;
    }
    
    public DefaultTableModel getModelTransaccion(String condiciones){    
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("RESOLUCION");
        modelo.addColumn("NO SERIE");
        modelo.addColumn("TIPO");
        modelo.addColumn("NO INICIAL");
        modelo.addColumn("CORRELATIVO");        
        modelo.addColumn("NO FINAL");
        modelo.addColumn("TOTAL");
        modelo.addColumn("FECHA");
        modelo.addColumn("ESTADO");
                
        ResultSet res;
        if(condiciones!=null){
            res= consultarDB("SELECT T.ID, R.NO_RESOLUCION, R.NO_SERIE, R.TIPO, R.NO_INICIAL, T.CORRELATIVO, R.NO_FINAL, T.TOTAL, T.FECHA, T.ESTADO FROM TRANSACCION T, RESOLUCION R WHERE R.ID=T.RESOLUCION "
                                    +condiciones);     
        }else{
            res= consultarDB("SELECT T.ID, R.NO_RESOLUCION, R.NO_SERIE, R.TIPO, R.NO_INICIAL, T.CORRELATIVO, R.NO_FINAL, T.TOTAL, T.FECHA, T.ESTADO FROM TRANSACCION T, RESOLUCION R WHERE R.ID=T.RESOLUCION ");     
        }
                
        if (res != null) {
            try {  
                while(res.next()){
                    Object [] fila = new Object[10];
                    for (int i=0;i<10;i++){
                        fila[i] = res.getObject(i+1);
                    }               
                    modelo.addRow(fila);
                }                       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return modelo;
    }
    
    public DefaultTableModel getModelEmpleados(String condiciones){    
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("ROL");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDOS");
        modelo.addColumn("CLAVE");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("EMAIL");
                
        ResultSet res;
        if(condiciones!=null){
            res= consultarDB("SELECT E.ID, R.NOMBRE, E.NOMBRE, E.APELLIDOS, E.CLAVE, E.TELEFONO, E.EMAIL FROM EMPLEADO E, ROL R WHERE E.ROL=R.ID "
                                    +condiciones);     
        }else{
            res= consultarDB("SELECT E.ID, R.NOMBRE, E.NOMBRE, E.APELLIDOS, E.CLAVE, E.TELEFONO, E.EMAIL FROM EMPLEADO E, ROL R WHERE E.ROL=R.ID");     
        }
                
        if (res != null) {
            try {  
                while(res.next()){
                    Object [] fila = new Object[7];
                    for (int i=0;i<7;i++){
                        fila[i] = res.getObject(i+1);
                    }               
                    modelo.addRow(fila);
                }                       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return modelo;
    }
    
    public DefaultTableModel getModelClientes(String condiciones){    
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDOS");
        modelo.addColumn("NIT");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("EMAIL");
                
        ResultSet res;
        if(condiciones!=null){
            res= consultarDB("SELECT * FROM CLIENTE WHERE "+condiciones);     
        }else{
            res= consultarDB("SELECT * FROM CLIENTE ");     
        }
                
        if (res != null) {
            try {  
                while(res.next()){
                    Object [] fila = new Object[7];
                    for (int i=0;i<7;i++){
                        fila[i] = res.getObject(i+1);
                    }               
                    modelo.addRow(fila);
                }                       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return modelo;
    }
    
    public DefaultTableModel getModelRes(){    
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("TIPO");
        modelo.addColumn("RESOLUCION");
        modelo.addColumn("SERIE");
        modelo.addColumn("INICIAL");
        modelo.addColumn("FINAL");
        modelo.addColumn("ACTUAL");
        modelo.addColumn("ESTADO");
        modelo.addColumn("PEQUEÑO");
        modelo.addColumn("AUTORIZACION");
        modelo.addColumn("VENCIMIENTO");
        modelo.addColumn("INGRESO");
                
        ResultSet res = consultarDB("SELECT * FROM RESOLUCION");     
        if (res != null) {
            try {  
                while(res.next()){
                    Object [] fila = new Object[12];
                    for (int i=0;i<12;i++){
                        fila[i] = res.getObject(i+1);
                    }               
                    modelo.addRow(fila);
                }                       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return modelo;
    }
  
    public Configuracion getConfigClass(){
        Configuracion dbConfig=null;
        
        ResultSet res = consultarDB("SELECT * FROM CONFIGURACION WHERE ID=1");        
        if (res != null) {
            try {                
                while (res.next()) {                    
                    dbConfig = new Configuracion( res.getInt("ID"), res.getString("NOMBRE_COMERCIAL"), res.getString("RAZON_SOCIAL"), res.getString("DIRECCION"), res.getString("NIT") );                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dbConfig;
    }
    
    public ResolucionSistema getResSystemClass(){
        ResolucionSistema resSystem=null;
        
        ResultSet res = consultarDB("SELECT * FROM RESOLUCION_SISTEMA WHERE ID=1");        
        if (res != null) {
            try {                
                while (res.next()) {                    
                    resSystem = new ResolucionSistema( res.getInt("ID"), res.getString("NO_RESOLUCION"), res.getDate("FECHA_AUTORIZACION"), res.getInt("NO_MAQUINA") );                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resSystem;
    }    
    
    public boolean insertResolucion(Resolucion r){
        //VERIFICAR QUE NO HAYA NINGUNA RESOLUCION ACTIVA        
        ResultSet res = consultarDB("SELECT * FROM RESOLUCION WHERE ESTADO=1 AND TIPO=\'"+r.getTipo()+"\'");        
        if (res != null) {
            try {                
                while (res.next()) {                    
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        ResultSet res2 = consultarDB("SELECT * FROM RESOLUCION WHERE NO_RESOLUCION=\'"+r.getNoResolucion()+"\'");        
        if (res2 != null) {
            try {                
                while (res2.next()) {                                        
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        java.text.SimpleDateFormat formatoFecha = new java.text.SimpleDateFormat("yyyy-MM-dd");        
        return ejecutarDB("INSERT INTO RESOLUCION ( TIPO, NO_RESOLUCION, NO_SERIE, NO_INICIAL, NO_FINAL, NO_ACTUAL, ESTADO, CONTRIBUYENTE_CHICO, FECHA_AUTORIZACION, FECHA_VENCIMIENTO, FECHA_INGRESO)"
                +" VALUES ( \'"+r.getTipo()+"\',  \'"+r.getNoResolucion()+"\', \'"+r.getNoSerie()+"\', "+r.getNoInicial()+", "+r.getNoFinal()+", "+r.getNoActual()+", "+r.isEstado()+","
        +" "+r.isContribuyenteChico()+", \'"+formatoFecha.format( r.getFechaAutorizacion() )+"\', \'"+formatoFecha.format( r.getFechaVencimiento())+"\', \'"+formatoFecha.format( r.getFechaIngreso() )+"\' )");
    }
    
    public boolean insertArticulo(Inventario i){
        //VERIFICAR QUE NO HAYA NINGUN INVENTARIO IGUAL   
        
        ResultSet res = consultarDB("SELECT * FROM INVENTARIO WHERE PRODUCTO IN (SELECT ID FROM PRODUCTO WHERE NOMBRE=\'"+i.getProducto()+"\') "
        +"AND MARCA IN (SELECT ID FROM MARCA WHERE NOMBRE=\'"+i.getMarca()+"\')" 
        +"AND PRESENTACION IN (SELECT ID FROM PRESENTACION WHERE NOMBRE=\'"+i.getPresentacion()+"\')" 
        +"AND UNIDAD IN (SELECT ID FROM UNIDAD WHERE NOMBRE=\'"+i.getUnidad()+"\')" );        
        if (res != null) {
            try {                
                while (res.next()) {                    
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return ejecutarDB("INSERT INTO INVENTARIO (PRODUCTO, MARCA, PRESENTACION, UNIDAD, PRECIO) VALUES ( "
                + "(SELECT ID FROM PRODUCTO WHERE NOMBRE=\'"+i.getProducto()+"\'),"
        +"(SELECT ID FROM MARCA WHERE NOMBRE=\'"+i.getMarca()+"\')," 
        +"(SELECT ID FROM PRESENTACION WHERE NOMBRE=\'"+i.getPresentacion()+"\')," 
        +"(SELECT ID FROM UNIDAD WHERE NOMBRE=\'"+i.getUnidad()+"\'),"
        +" "+i.getPrecio()+" )"); 
    }
    
    public boolean changeStateInventario(String id, String valor){        
        return ejecutarDB("UPDATE INVENTARIO SET ESTADO="+valor+" WHERE ID=\'"+id+"\'"); 
    }
    
    public boolean changeStateEmpleado(String id, String valor){        
        return ejecutarDB("UPDATE EMPLEADO SET ESTADO="+valor+" WHERE ID=\'"+id+"\'"); 
    }
    
    public boolean insertReg(String reg, String table){
        return ejecutarDB("INSERT INTO "+table+" ( NOMBRE) VALUES ( \'"+reg+"\' )");
    }
    
    public boolean insertCliente(Cliente c){
        return ejecutarDB("INSERT INTO CLIENTE ( NOMBRE, APELLIDOS, TELEFONO, DIRECCION, NIT, EMAIL) VALUES ( \'"+c.getNombre()+"\', \'"+c.getApellidos()+"\', "
                + "\'"+c.getTelefono()+"\', \'"+c.getDireccion()+"\', \'"+c.getNit()+"\', \'"+c.getEmail()+"\' )");
    }
    
    public boolean insertEmpleado (Empleado e){
        return ejecutarDB("INSERT INTO EMPLEADO ( NOMBRE, APELLIDOS, CLAVE, TELEFONO, EMAIL, ROL) VALUES ( \'"+e.getNombre()+"\', \'"+e.getApellidos()+"\', "
                + "\'"+e.getClave()+"\', \'"+e.getTelefono()+"\', \'"+e.getEmail()+"\', \'"+e.getRol()+"\' )");
    }
    
    public boolean updateDownResolucion(int id){
        return ejecutarDB("UPDATE RESOLUCION SET ESTADO=0 WHERE ID="+id);
    }
    
    public boolean updateConfiguracion(Configuracion dbConfig){
        return ejecutarDB("UPDATE CONFIGURACION SET NOMBRE_COMERCIAL=\'"+dbConfig.getNombreComercial()+"\', RAZON_SOCIAL=\'"+dbConfig.getRazonSocial()+
                "\', DIRECCION=\'"+dbConfig.getDireccion()+"\', NIT=\'"+dbConfig.getNIT()+"\' WHERE ID=1");
    }
    
    public boolean updateResSystem(ResolucionSistema dbResSystem){
        java.text.SimpleDateFormat formatoFecha = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return ejecutarDB("UPDATE RESOLUCION_SISTEMA SET NO_RESOLUCION=\'"+dbResSystem.getNoResolucion()+"\', FECHA_AUTORIZACION=\'"+formatoFecha.format( dbResSystem.getFecha() )+
                "\', NO_MAQUINA=\'"+dbResSystem.getNoMaquiena()+"\' WHERE ID=1");
    }
    
    private boolean ejecutarDB(String sql) {
        try {
            Statement sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            sentencia.executeUpdate(sql);
            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }        
        return true;
    }
    
    private ResultSet consultarDB(String sql) {
        ResultSet resultado;
        try {
            Statement sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = sentencia.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }        
        return resultado;
    }
    
    private Connection getConexion() {
        String url = null;
        Connection conn = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de MySQL: " + ex);
            }

            url ="jdbc:mysql://" + host + ":" + port + "/" + database;

            conn = (Connection) DriverManager.getConnection( url, user, password);                         
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error al conectar con la base de datos de MySQL (" + url + "): " + sqle);
        }
        return conn;
    }
}
