<%-- 
    Document   : modificarProducto
    Created on : 20-may-2021, 21:00:37
    Author     : 57301
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Producto;"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        //Establecer el objeto a actualizar
        Producto miProducto = (Producto)request.getAttribute("productoSeleccionado");
    %>
    <style style="text/css">
        .cabecera_1{
            font-size:1.2em;  
            font-weight: bold;
            color:black;
            background-color: cadetblue;
            text-align: center;
        }
        .cabecera_2{
            font-size:1.2em;  
            font-weight: bold;
            color:#FFFFFF;
            background-color: activeborder; 
        }
        
        .filas{
            text-align: center;
            background-color: cadetblue;
        }
        
        #contenedorBoton{
            margin-left:50px;
        }
    </style>
    <body>
        <h1>Actualizar producto</h1>
        <form action="ControladorListaProducto" method="get">
            <input type="hidden" name="instruccion" value="modificarProductoBBDD">
            <input type="hidden" name="codigo" value="<%=miProducto.getCodArticulo()%>">
            <table>
                <tr>
                    <td class="cabecera_2">Ingrese el Codigo</td>
                    <td class="filas"><%=miProducto.getCodArticulo()%></td>
                </tr>
                <tr>
                    <td class="cabecera_2">Ingrese la sección</td>
                    <td class="filas"><input type="text" name="seccion" value="<%=miProducto.getSeccion()%>"></td>
                </tr>
                <tr>
                    <td class="cabecera_2">Ingrese el nombre</td>
                    <td class="filas"><input type="text" name="nomArticulo" value="<%=miProducto.getNomArticulo()%>"></td>
                </tr>
                <tr>
                    <td class="cabecera_2">Ingrese el precio</td>
                    <td class="filas"><input type="text" name="precio" value="<%=miProducto.getPrecio()%>"></td>
                </tr>
                <tr>
                    <td class="cabecera_2">Ingrese la fecha</td>
                    <td class="filas"><input type="text" name="fecha" value="<%=miProducto.getFecha()%>"></td>
                </tr>
                <tr>
                    <td class="cabecera_2">¿És importado?</td>
                    <td class="filas"><input type="text" name="importado" value="<%=miProducto.getImportado()%>"></td>
                </tr>
                <tr>
                    <td class="cabecera_2">Ingrese el país</td>
                    <td class="filas"><input type="text" name="pais" value="<%=miProducto.getPaisOrigen()%>"></td>
                </tr>
            </table>            
            <input type="submit" value="Actualizar producto">           
        </form>
    </body>
</html>
