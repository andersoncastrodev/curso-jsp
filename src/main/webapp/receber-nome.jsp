<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina Nome</title>
</head>
<body>

<%
    
    String nome = request.getParameter("nome");

    String telefone = request.getParameter("telefone");

    out.print("Nome" +" - "+ "Telefone"+"<br>");
	out.print(nome +" - "+ telefone+"<br>"+"<br>");
	
    out.print("Nome:" +nome+"<br>");
	out.print("Telefone: "+telefone);
 
 %>
   <br> <br>
   <a href="index.jsp">Voltar</a>
   
  <!--
   
  PASSAR O PARAMETRO VIA URL GET.
  ex: 
  http://localhost:8080/curso-jsp/receber-nome.jsp?nome=Anderson
  
  FINAL: Use ?variavel e valor 
  
  receber-nome.jsp?nome=JAVA
  
  AGORA 2 PARAMETROS.
  Obs: Usar o & comercial 
  Ex:
  receber-nome.jsp?nome=Anderson&telefone=85748574
  
   -->
</body>
</html>