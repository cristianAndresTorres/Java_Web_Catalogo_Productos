
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloProducto {
    //Atributos------------------------------------------------------------------
    //Conexion a la BD
    private ConexionBD miConBD = new ConexionBD();
    
    //Objetos para las consultas sql
    private Connection miConxionBD = null;
    private String sentenciaSQL = null;
    private PreparedStatement consultaPreparada = null;
    private Statement consulta = null;   
    private ResultSet resultadoTabla;
    
    public Producto obtenerProducto(String id_codigo){
        Producto miProducto = null;
        try {
            //1->Establecer el puente de conexion con el gestor de BD
            miConxionBD = miConBD.realizarConexionBD();
            //2->Establecer la sentencia sql(No-Estatica)-para PreparedStatement(realizar consultas sql)
            sentenciaSQL = "Select * from productos where CodigoArticulo=?;";
            consultaPreparada = miConxionBD.prepareStatement(sentenciaSQL);
            
            //3->Pasar parametros(Tipos) que varian al PreparedStatement 
            consultaPreparada.setString(1, id_codigo);
            
            //4->Ejecurar sentencia sql(construccion de la tabla imaginaria)
            resultadoTabla = consultaPreparada.executeQuery();
            
            //5->Verificar contenido
            if(resultadoTabla.next()){
                miProducto = new Producto(resultadoTabla.getString("CodigoArticulo"), resultadoTabla.getString("seccion"), resultadoTabla.getString("nombreArticulo"), resultadoTabla.getDouble("precio"), resultadoTabla.getDate("fecha"), resultadoTabla.getString("importado"), resultadoTabla.getString("paisOrigen"));                
            }            
            //6->Finalizar conexion
            resultadoTabla.close();
            miConxionBD.close();
        } catch (Exception e) {
            System.out.println("ModeloProducto-Error-obtenerProducto");
        }   
        return miProducto;   
    }
    
    public List<Producto> obtenerProductos(){
            List<Producto> listProducto = new ArrayList<>();

            try {
                //---------------------------------------
                //1->Establecer conexion
                miConxionBD = miConBD.realizarConexionBD();

                //2->preparar consulta sql estatica-definir sentencia sql
                sentenciaSQL = "select * from Productos;";

                consulta = miConxionBD.createStatement();

                //3->ejecutar sentencia sql y construir la matriz imaginaria
                resultadoTabla = consulta.executeQuery(sentenciaSQL);
                System.out.println("Hello__________________________");
                
                //4->recorrer la matriz
                while(resultadoTabla.next()){
                    Producto miProducto = new Producto(resultadoTabla.getString("CodigoArticulo"), 
                                resultadoTabla.getString("seccion"),resultadoTabla.getString("nombreArticulo"), 
                                    resultadoTabla.getDouble("precio"), resultadoTabla.getDate("fecha"), 
                                        resultadoTabla.getString("importado"),resultadoTabla.getString("paisOrigen"));
                    //Agregar a la lista
                    listProducto.add(miProducto);
                }
                //5->Terminar consulta
                resultadoTabla.close();
            } catch (Exception e) {
                System.out.println("Error-obtenerProductos");
            }
        return  listProducto;    
    }

    public void agregarProducto(Producto producto) {
            try {
                //---------------------------------------
                //1->Establecer conexion
                miConxionBD = miConBD.realizarConexionBD();

                //2->preparar consulta sql estatica-definir sentencia sql
                sentenciaSQL = "insert into Productos values(?,?,?,?,?,?,?);";

                //3->Pasar parametros de la sentencia sql predefinida
                consultaPreparada = miConxionBD.prepareStatement(sentenciaSQL);
                
                //4->Pasar parametros de la consulta 
                consultaPreparada.setString(1, producto.getCodArticulo());
                consultaPreparada.setString(2, producto.getSeccion());
                consultaPreparada.setString(3, producto.getNomArticulo());
                consultaPreparada.setDouble(4, producto.getPrecio());
                
                java.util.Date utilDate = producto.getFecha();
                
                java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
                
                
                consultaPreparada.setDate(5, fechaConvertida);
                consultaPreparada.setString(6, producto.getImportado());
                consultaPreparada.setString(7, producto.getPaisOrigen());
                        
                //5->Ejecutar sentencia
                consultaPreparada.execute();
                
              

                //5->Terminar consulta
                miConxionBD.close();
            } catch (Exception e) {
                System.out.println("ModeloProducto-Error-agregarProducto");
            }
        
        
    }
    
    public boolean actualizarProducto(Producto producto){
            boolean estado;
            try {
                estado = true;
                //1->Realizar/Pueste la conexion con la BD
                miConxionBD = miConBD.realizarConexionBD();
                //2->Establecer la sentencia sql(preprocesada) PreparedStatement
                sentenciaSQL = "Update productos set seccion=?, nombreArticulo=?, precio=?, fecha=?, importado=?, paisOrigen=?"
                        + "where CodigoArticulo=?";
                
                //3->Pasar parametros que varian al PreparedStatement
                consultaPreparada = miConxionBD.prepareStatement(sentenciaSQL);
                //3.1->parametros que varian
                consultaPreparada.setString(1, producto.getSeccion());
                consultaPreparada.setString(2, producto.getNomArticulo());
                consultaPreparada.setDouble(3, producto.getPrecio());
                java.util.Date utilDate = producto.getFecha();
                java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());                
                consultaPreparada.setDate(4, fechaConvertida);
                consultaPreparada.setString(5, producto.getImportado());
                consultaPreparada.setString(6, producto.getPaisOrigen());
                consultaPreparada.setString(7, producto.getCodArticulo());
                
                //4->Ejecutar la sentencia
                consultaPreparada.execute();
                
                //5->Desconectar la BD
                miConxionBD.close();
                                
            } catch (Exception e) {
                estado = false;
                System.out.println("ModeloProducto-Error-actualizarProducto");
            } 
        return estado;
    }
    
    public boolean eliminarRegistroPro(String idProducto){
        boolean estado;
            try {
                estado = true;
                //1->Puente/Conexion con la BD
                miConxionBD = miConBD.realizarConexionBD();
                //2->Definir sentencia sql(Pre procesada)
                sentenciaSQL = "Delete from Productos where CodigoArticulo=?;";
                consultaPreparada = miConxionBD.prepareStatement(sentenciaSQL);
                //3->Pasar parametro variante
                consultaPreparada.setString(1, idProducto);
                //4->Ejecutar
                consultaPreparada.execute();
                //5->Finalizar Conexion
                miConxionBD.close();
            } catch (Exception e) {
                System.out.println("ModeloProducto-Error-eliminarRegistroPro");
                estado = false;
            }
        return  estado;
    }
    
        
    
    
    
    
}
