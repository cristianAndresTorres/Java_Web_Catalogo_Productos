/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ModeloProducto;
import modelo.Producto;

/**
 *
 * @author 57301
 */
@WebServlet(name = "ControladorListaProducto", urlPatterns = {"/ControladorListaProducto"})
public class ControladorListaProducto extends HttpServlet {
    //Atributos
    private ModeloProducto modeloProducto;
    
    //Pagina de apertura
    @Override
    public void init()throws ServletException{
       super.init();  
       
       modeloProducto = new ModeloProducto();
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1->Leer el parametro de llegada
        String parametro = request.getParameter("instruccion");
        String id=null;
        
        //2->si no se envia el parametro, listar productos
        if(parametro==null)parametro = "listar";
        else if(parametro.contains("Actualizar")){
            id = parametro.replace("Actualizar ", "");
            parametro="Actualizar";
        }else if(parametro.contains("Elimiar")) {
            id = parametro.replace("Elimiar ", "");
            parametro="Elimiar";
        }
        
        switch(parametro){
            case "insetarBBDD":    
                this.agregarProductos(request, response);
            break;
            case "Actualizar":
                System.out.println("Actualizar"+"id:"+id);
                this.seleccionarProducto(request, response, id);
            break;   
            case "Elimiar":
                System.out.print("Elimiar"+"id:"+id);
                this.eliminarProducto(request, response, id);
            break;    
            case "listar": 
                this.listarProductos(request, response);
            break;
            
            case "modificarProductoBBDD":
                System.out.println("modificarProductoBBDD");
                this.actualizarProducto(request, response);
            break;
        }
        
        
    }
    
    private void listarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         //1->obtener la lista de productos desde el modelo
        List<Producto> listProductos = modeloProducto.obtenerProductos();
        
        
        //2->Agregar(atributo) la lista de productos al request
        request.setAttribute("listaProductos", listProductos);
        
        //3->Establecer el puente/conexion para el paso de los recursos
        RequestDispatcher miPuente = request.getRequestDispatcher("/listarProductos.jsp");
        
        
        //4->Enviar/Reenviar ese request a la pagina jsp
        miPuente.forward(request, response);
    }

    private void agregarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            //1->leer la nformación del producto que viene del formulario
            String CodigoArticulo = request.getParameter("codigo");
            String seccion = request.getParameter("seccion");
            String nombreArticulo = request.getParameter("nomArticulo");
            double precio = Double.parseDouble(request.getParameter("precio"));
            String importado = request.getParameter("importado");
            String paisOrigen = request.getParameter("pais");
            
            //Formato de la fecha
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            
            Date fecha = null;
            
            try {
                fecha = formatoFecha.parse(request.getParameter("fecha"));
            } catch (Exception e) {
                System.out.println("Error-Fecha");
            }
            
        //2->Crear el objeto producto
            Producto producto = new Producto(CodigoArticulo, seccion, nombreArticulo, precio, fecha, importado, paisOrigen);
        
        //3->Enviar el objeto al modelo y despues insertar el objeto producto en la bbdd
            modeloProducto = new ModeloProducto();
            modeloProducto.agregarProducto(producto);
        //4->listar la tabla de productos
        this.listarProductos(request, response);
    }

    private void seleccionarProducto(HttpServletRequest request, HttpServletResponse response, String idCodigo) throws ServletException, IOException{
        //1->Crear objeto producto
        //2->LLamar metodo que devuelve un producto
       Producto miProducto = modeloProducto.obtenerProducto(idCodigo); 
       if(miProducto != null){
           //3->Encapsular informacion(Clave-Valor)
           request.setAttribute("productoSeleccionado", miProducto);
           //4->Redireccionar al jsp
           //4.1->Establecer el puente/conexion para el paso de los recursos
           RequestDispatcher miPuente = request.getRequestDispatcher("/modificarProducto.jsp");                  
           //4.2->Enviar/Reenviar ese request a la pagina jsp       
           miPuente.forward(request, response);
        }else{
           this.listarProductos(request, response);
        }   
    }
    
    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{       
            //1-> Realizar el objeto Producto con los datos del formulario
            Producto miProducto = null;
            //Formato de la fecha
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = null;           
            try {
                fecha = formatoFecha.parse(request.getParameter("fecha"));
                miProducto = new Producto(request.getParameter("codigo"), request.getParameter("seccion"), 
                                request.getParameter("nomArticulo"), Double.parseDouble(request.getParameter("precio")), 
                                    fecha, request.getParameter("importado"), request.getParameter("pais"));
            //2->Ejecutar sentencia sql
            if(modeloProducto.actualizarProducto(miProducto)){
                //3->Redireccionar al usuario   
                this.listarProductos(request, response);
            }else{
                seleccionarProducto(request, response, request.getParameter("codigo"));
            }
            } catch (Exception e) {
                this.seleccionarProducto(request, response, request.getParameter("codigo"));
                System.out.println("actualizarProducto-Error-Asignación");
            }
    }
    
    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response, String codigo) throws ServletException, IOException{
        //Ejecutar sentencia SQL para eliminar producto
        if(modeloProducto.eliminarRegistroPro(codigo)){
            System.out.println("Producto-Eliminado");
        }else{
            System.out.println("Producto-No_Eliminado");
        }        
        this.listarProductos(request, response);
    }

}
