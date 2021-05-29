<%-- 
    Document   : listarProductos
    Created on : 19-may-2021, 13:28:06
    Author     : 57301
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Producto;"%>

<!DOCTYPE html>
<html>
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
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%List<Producto> listaProducto = (List<Producto>)request.getAttribute("listaProductos");%>
    <body>
        <h1 class="cabecera_1">Tabla productos</h1>
        <form action="ControladorListaProducto" method="get">
            <table>          
                <tr>
                    <th class="cabecera_2">CodigoArticulo</th>
                    <th class="cabecera_2">Seccion</th>
                    <th class="cabecera_2">NombreArticulo</th>
                    <th class="cabecera_2">Precio</th>
                    <th class="cabecera_2">Fecha</th>
                    <th class="cabecera_2">Importado</th>
                    <th class="cabecera_2">PaisOrigen</th>
                    <th class="cabecera_2">Actualizar</th> 
                    <th class="cabecera_2">Eliminar</th>
                </tr>

                <%for(int j=0; j<listaProducto.size();j++){%>            
                    <tr>
                        <td class="filas"><%=listaProducto.get(j).getCodArticulo()%></td>
                        <td class="filas"><%=listaProducto.get(j).getSeccion()%></td>
                        <td class="filas"><%=listaProducto.get(j).getNomArticulo()%></td>
                        <td class="filas"><%=listaProducto.get(j).getPrecio()%></td>
                        <td class="filas"><%=listaProducto.get(j).getFecha()%></td>
                        <td class="filas"><%=listaProducto.get(j).getImportado()%></td>
                        <td class="filas"><%=listaProducto.get(j).getPaisOrigen()%></td>
                        <td class="filas"><input type="submit" name="instruccion" value="Actualizar <%=listaProducto.get(j).getCodArticulo()%>"></td>
                        <td class="filas"><input type="submit" name="instruccion" value="Elimiar <%=listaProducto.get(j).getCodArticulo()%>"></td>
                    </tr>
                <%}%>
            </table>
        </form>
        <div id="contenedorBoton">
            <input type="button" value="Insertar Registro" onclick="window.location.href='insertarProducto.jsp'">
        </div>
        
    </body>
</html>
