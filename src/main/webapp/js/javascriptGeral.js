/*Fun */
		function teste(){
			
		}

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
		console.log(nomeBusca);
    	if(nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') { 

    		var urlAction = document.getElementById("formUser").action; 
    	
			$.ajax({ 
			   method:"get",
			   url: urlAction,
			   data: "nomeBusca="+ nomeBusca + '&acao=buscarUserAjax',
			   success: function(response){
				   
				   alert(response);
			   }
			    			
			  }).fail(function(xhr,status,errorThrown){
			    alert('Erro ao Consulta usuário por Nome:'+ xhr.responseText )
		    });
    	} 
    }
