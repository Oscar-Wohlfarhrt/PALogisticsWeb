<%-- 
    Document   : index
    Created on : 24 nov 2023, 13:58:17
    Author     : Oscar
--%>

<%@page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.EnderFire.PALogisticsWeb.Controllers.GenericJpaController"%>
<%@page import="java.util.List"%>
<%@page import="com.EnderFire.PALogisticsWeb.Models.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta pedidos por cliente</title>
    </head>
    <body>
        <h1>PALogistics Web Page</h1>
        <a href="index.html">Regresar</a><br><br>
        <form method="post" action="pedidosCliente.jsp">
            <select name="ctlId">
                <option value="">Eliga un cliente para consultar</option>
                <%
                GenericJpaController<Cliente> cJPA = new GenericJpaController<>(Cliente.class);
                List<Cliente> clientes = cJPA.findEntityEntities();
                request.setAttribute("clientes", clientes);
                %>
                <c:forEach var="cliente" items="${clientes}">
                    <option value="${cliente.getId()}">${cliente.toString()}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Consultar pedidos">
        </form>
    </body>
</html>
