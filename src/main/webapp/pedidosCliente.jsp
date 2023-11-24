<%-- 
    Document   : PedidosCliente
    Created on : 24 nov 2023, 15:01:40
    Author     : Oscar
--%>

<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.EnderFire.PALogisticsWeb.Models.*"%>
<%@page import="com.EnderFire.PALogisticsWeb.Controllers.GenericJpaController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedidos de Cliente</title>
        <style>
        table, th, td {
          border:1px solid black;
        }
        </style>
    </head>
    <body>
        <h1>Pedidos de Cliente</h1>
            <a href="consultaPedidos.jsp">Regresar</a><br><br>
            <%
            Long ctlId=Long.valueOf(-1);
            Cliente cliente = null;
            GenericJpaController<Cliente> cJPA = new GenericJpaController<>(Cliente.class);
            try{
                ctlId = Long.valueOf(request.getParameter("ctlId"));
                cliente = cJPA.findEntity(ctlId);
            }
            catch(Exception e){}
            if (ctlId>=0 && cliente !=null) {
                out.println("Cliente: "+cliente.toString());
                List<Pedido> pedidos = cliente.getPedidos();
                request.setAttribute("pedidos", pedidos);
            %>
            <br><br>
            <table>
                <tr>
                    <th>Id de Pedido</th>
                    <th>Nombre</th>
                    <th>Estado</th>
                    <th>Dirección de entrega</th>
                    <th>Ubicación</th>
                <tr>
                <c:forEach items="${pedidos}" var="pedido">
                <tr>
                    <td>${pedido.getId()}</td>
                    <td>${pedido.getName()}</td>
                    <td>${pedido.getState()}</td>
                    <td>${pedido.getDeliveryAddress()}</td>
                    <td>${pedido.getLocation()!=null?pedido.getLocation().getDetailedString():"Sin ubicacion asignada"}</td>
                </tr>
                </c:forEach>
            </table>
            <%
            }
            else{
            %>
            No seleccionó un cliente o la id es invalida.
            <%
            }
            %>
    </body>
</html>
