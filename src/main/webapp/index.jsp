<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="ISO-8859-1">

 <!-- Responsividade -->
 <meta name="viewport" content="width=device-width, initial-scale=1">
 
  <!-- BootStrap 5 na internet -->
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
 
 <!-- Corrigir o ERRO NO ICONE -->
<link rel="shortcut icon" href="#">

<title>Curso JSP</title>
</head>
<body>

      <div class="container" style=" margin-top: 150px" >
      
         <p class="h5" style=" text-align:center" >Login JSP e Servlet </p>
     
   
      <form action="servletlogin" method="post" >
      
        <input type="hidden" name="url" value="<%= request.getParameter("url") %>">

		<div lass="mb-3">
		    <label for="nome"  class="form-label" >Login:</label> 
		    <input type="text" class="form-control" id="login" name="login" autocomplete="off" value="admin" >
		</div> 
		
		 <div class="mb-3">
		   <label for="telefone" class="form-label" >Senha:</label> 
		   <input type="password" id="senha" name="senha" class="form-control" value="admin">
		   
    	 </div>
		 
		  <div class="d-grid gap-2 d-md-flex justify-content-md-end">
		  
		    <p>${ msg }</p>
		    
		    <input type="submit" value="Entrar" class="btn btn-primary" > 
		      
		  </div> 
		  
      </form>
      
	<!-- Content here -->
	</div>
      
   <!-- JavaScript do BootStrap 5 na internet -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>