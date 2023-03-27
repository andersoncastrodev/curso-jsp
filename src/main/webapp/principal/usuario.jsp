<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-BR">

<!-- Incluindo a pagina -->
<jsp:include page="head.jsp"></jsp:include>

<body>

	<!-- Incluindo a pagina -->
	<jsp:include page="theme-loader.jsp"></jsp:include>

	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<!-- Incluindo a pagina -->
			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<!-- Incluindo a pagina -->
					<jsp:include page="navbarmenulateral.jsp"></jsp:include>


					<div class="pcoded-content">

						<!-- Incluindo a pagina -->
						<jsp:include page="page-header.jsp"></jsp:include>

						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
		<div class="page-wrapper">
				<!-- Page-body start -->
				<div class="page-body">
								
                           <div class="card">
								 <div class="card-block">	
           									 <div class="col-md-10">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Cadastro de Usuário</h5>         
                                                    </div>
                                                    <div class="card-block">
                                                        <form class="form-material" action="<%= request.getContextPath() %>/servletusuariocontroller" method="post" id="formUser" >
                                                          
                                                           <input type="hidden" name="acao" id="acao" value="">
                                                            
                                                           <div class="form-group form-default form-static-label">
                                                                <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${modolLogin.id }" >
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Id</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="nome" id="nome" class="form-control" required="required" value="${modolLogin.nome }">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Nome</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="email" name="email" email="email"  class="form-control" required="required" autocomplete="off" value="${modolLogin.email}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Email</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="login" id="login" class="form-control" required="required" autocomplete="off" value="${modolLogin.login }">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Login</label>
                                                            </div>
                                                                                                                        
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off" value="${modolLogin.senha }">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha</label>
                                                            </div>
                                                            
                                                           
                                                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm();">Novo</button>
												            <button type="submit" class="btn btn-success waves-effect waves-light">Salvar</button>
									            			<button type="button" class="btn btn-danger waves-effect waves-light" onclick="criarDelete();">Deletar</button>
		                                                     
															<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#pesquisaUsuario"> Pesquisar </button> 
															                       
                                                        </form>
                                                        	 
                                                    </div>
                                                </div>
                                            </div>   
									</div>
							</div>	
							<span>${msg}</span>
					</div>
			<!-- Page-body end -->
			</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
		<!-- Modal PESQUISA COMO BOOTSTRAP -->
		<div class="modal fade" id="pesquisaUsuario" tabindex="-1" role="dialog" aria-labelledby="pesquisaUsuario" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usuário</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		       <!-- CORPO -->
				
				<div class="input-group mb-3">
				  <input type="text" class="form-control" placeholder="Nome" aria-label="nome" id="nomeBusca"  aria-describedby="basic-addon2">
				  <div class="input-group-append">
				    <button class="btn btn-success" type="button" onclick="buscarUsuario();">Buscar</button>
				  </div>
				</div>

				<div style="height: 350px; overflow: scroll;">
				
					<table class="table" id="tableResultados">
					  <thead>
					    <tr>
					      <th scope="col">Id</th>
					      <th scope="col">Nome</th>
					      <th scope="col">Ver</th>
					    </tr>
					  </thead>
					  <tbody>
					    
					  </tbody>
					</table>			        
		       </div>
		           <span id="totalResultados"></span>	
		         
		        <!-- FIM DO CORPO -->
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
		        <!-- <button type="button" class="btn btn-primary">Enviar</button> -->
		      </div>
		    </div>
		  </div>
		</div>
		<!-- FIM DO Modal PESQUISA COMO BOOTSTRAP -->	


<!-- Incluindo a pagina -->
<jsp:include page="javascriptfile.jsp"></jsp:include>

<script type="text/javascript">


    function limparForm(){
		var elementos = document.getElementById("formUser").elements;
		
		for(p=0; p < elementos.length; p++){
			elementos[p].value = "";
		}
	}
    	
    function criarDelete(){
    	
    	if( confirm('Deseja realmente Excluir?') ){
    		document.getElementById("formUser").method = 'get'
    	    document.getElementById("acao").value = 'deletar'
    	    document.getElementById("formUser").submit();		
    	}
    	
    }
  
     function criarDeleteComAjax(){
    	
    	if( confirm('Deseja realmente Excluir?') ) {
			
    		var urlAction = document.getElementById("formUser").action;
    		var idUser = document.getElementById("id").value;
    		
    		$.ajax({
    			method:"get",
    			url: urlAction,
    			data: "id="+ idUser + '&acao=deletarajax',
    			sucess: function(response){
    			   alert(response);
    			}
    			
    		}).fail(function(xhr,status,errorThrown){
    			alter('Erro ao deletar usuário por id:'+ xhr.responseText );
    		});
    		
    	}
    	
    }
    
    function buscarUsuario(){   
    	
    	var nomeBusca = document.getElementById("nomeBusca").value;

    	if(nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') { 

    		var urlAction = document.getElementById("formUser").action; 

			$.ajax({ 
			   method:"get",
			   url: urlAction,
			   data: "nomeBusca="+ nomeBusca + '&acao=buscarUserAjax',
			   success:function(response){
				 		
			     let json = JSON.parse(response);
				   
				  //Removendo os elementos da tabela.
				  $('#tableResultados > tbody > tr').remove(); 
				  
				  	for(let p = 0; p < json.length; p++){
				  		$('#tableResultados > tbody').append('<tr> <td>'+json[p].id+'</td> <td>'+json[p].nome+'</td> <td><button type="button" class="btn btn-info" onclick="verEditar('+json[p].id+')">Ver</button></td>  </tr>');
				  	}
				   
				  	document.getElementById("totalResultados").textContent = 'Resultados: '+json.length;
			   }
			    			
			  }).fail(function(xhr,status,errorThrown){
			    alert('Erro ao Consulta usuário por Nome:'+ xhr.responseText )
		    });
    	} 
    }
 
function verEditar(id) {
	console.log(id);
	let urlAction = document.getElementById("formUser").action; 
	
	window.location.href = urlAction + '?acao=buscarEditar&id='+id;
}
</script>
	  
</body>
</html>