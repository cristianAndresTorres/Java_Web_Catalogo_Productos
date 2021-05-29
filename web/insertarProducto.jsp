<%-- 
    Document   : insertarProducto
    Created on : 19-may-2021, 18:28:57
    Author     : 57301
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nuevo producto</h1>
        <form action="ControladorListaProducto" method="get">
            <input type="hidden" name="instruccion" value="insetarBBDD">
            <table>
                <tr>
                    <td>Ingrese el Codigo</td>
                    <td><input type="text" name="codigo"></td>
                </tr>
                <tr>
                    <td>Ingrese la sección</td>
                    <td><input type="text" name="seccion"></td>
                </tr>
                <tr>
                    <td>Ingrese el nombre</td>
                    <td><input type="text" name="nomArticulo"></td>
                </tr>
                <tr>
                    <td>Ingrese el precio</td>
                    <td><input type="text" name="precio"></td>
                </tr>
                <tr>
                    <td>Ingrese la fecha</td>
                    <td><input type="text" name="fecha"></td>
                </tr>
                <tr>
                    <td>¿És importado?</td>
                    <td><input type="text" name="importado"></td>
                </tr>
                <tr>
                    <td>Ingrese el país</td>
                    <td><input type="text" name="pais"></td>
                </tr>
            </table>
                
            
            <input type="submit" value="RegistrarProducto">           
        </form>
    </body>
</html>
