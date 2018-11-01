package CLASES;


import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
    
    public ArrayList<Cliente> getListClientes(){    
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();               
        ResultSet res =consultarDB("SELECT * FROM CLIENTE");                
        if (res != null) {
            try {  
                while(res.next()){
                    clientes.add(new Cliente(res.getInt("ID"), res.getString("NOMBRE"), res.getString("APELLIDOS"), res.getString("telefono"), res.getString("DIRECCION"), res.getString("EMAIL"), res.getString("NIT")));
                }                       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientes;
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
        modelo.addColumn("INGRESO");
                
        ResultSet res = consultarDB("SELECT * FROM RESOLUCION");     
        if (res != null) {
            try {  
                while(res.next()){
                    Object [] fila = new Object[11];
                    for (int i=0;i<11;i++){
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
        return ejecutarDB("INSERT INTO RESOLUCION ( TIPO, NO_RESOLUCION, NO_SERIE, NO_INICIAL, NO_FINAL, NO_ACTUAL, ESTADO, CONTRIBUYENTE_CHICO, FECHA_AUTORIZACION, FECHA_INGRESO)"
                +" VALUES ( \'"+r.getTipo()+"\',  \'"+r.getNoResolucion()+"\', \'"+r.getNoSerie()+"\', "+r.getNoInicial()+", "+r.getNoFinal()+", "+r.getNoActual()+", "+r.isEstado()+","
        +" "+r.isContribuyenteChico()+", \'"+formatoFecha.format( r.getFechaAutorizacion() )+"\', \'"+formatoFecha.format( r.getFechaIngreso() )+"\' )");
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
    
    public boolean insertReg(String reg, String table){
        return ejecutarDB("INSERT INTO "+table+" ( NOMBRE) VALUES ( \'"+reg+"\' )");
    }
    
    public boolean insertCliente(Cliente c){
        return ejecutarDB("INSERT INTO CLIENTE ( NOMBRE, APELLIDOS, TELEFONO, DIRECCION, NIT, EMAIL) VALUES ( \'"+c.getNombre()+"\', \'"+c.getApellidos()+"\', "
                + "\'"+c.getTelefono()+"\', \'"+c.getDireccion()+"\', \'"+c.getNit()+"\', \'"+c.getEmail()+"\' )");
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
