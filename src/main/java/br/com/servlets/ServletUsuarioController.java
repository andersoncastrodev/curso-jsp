package br.com.servlets;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dao.DaoUsuarioRepository;
import br.com.model.ModelLogin;

@WebServlet("/servletusuariocontroller") //NOME DO MAPEAMENTO QUE SERA USADO NO LINKS LÁ NO HTML.
public class ServletUsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
	
	//CRAINDO UMA INSTANCIA DO MODEL USUARIO 
	DaoUsuarioRepository usuarioRepository = new DaoUsuarioRepository();
	
    public ServletUsuarioController() {
        super();
       
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	 try {
			
		String acao = request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
			String id = request.getParameter("id");
			usuarioRepository.deletarUser(id);
			request.setAttribute("msg", "Usuario Deletado com Sucesso");
			
			//Mesmo de forma resumida 
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		}
		 else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
			  
			  String nomeBusca = request.getParameter("nomeBusca");
			  
		     List<ModelLogin> dadosJsonUser = usuarioRepository.consultaUsuarioList(nomeBusca);
			  
		     ObjectMapper mapper = new ObjectMapper(); //CRIANDO OBJETO JSON
		     
		     String json = mapper.writeValueAsString(dadosJsonUser); //TRANSFORMANDO ARRAY LIST PARA JSON.
		     
		     response.getWriter().write(json);//ENVIANDO PARA VIEW O JSON
		}
		 else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
			
			 String id = request.getParameter("id");
			
			ModelLogin login = usuarioRepository.consultaUsuarioPorId(id);
			
			
			//Manda uma Messagem de SUCESSO PARA VIEW.
			request.setAttribute("msg", "Usuário em Edição");
			//Manda o objeto para a pagina.
			request.setAttribute("modolLogin", login);
			
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
	    }
		 else {
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		}

        }catch (SQLException e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
		String msg = "Usuario Salva com Sucesso!";
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		ModelLogin objLogin = new ModelLogin();
		
		objLogin.setId( id !=null && !id.isEmpty()? Long.parseLong(id): null );
		objLogin.setNome(nome);
		objLogin.setEmail(email);
		objLogin.setLogin(login);
		objLogin.setSenha(senha);
		
		System.out.println(objLogin.toString());
		
		if(usuarioRepository.validarLogin(objLogin.getLogin()) && objLogin.getId() == null) {
			msg = "Usuario Já Cadastro";
			
		}else {
			if(objLogin.isNovo()) {
				msg = "Usuario Salva com Sucesso!";
			}else {
			   msg = "Usuario Atualziado com Sucesso!";
			}
			//PASSSANDO O OBJETO CARREGADOR PARA O SALVAR.
			objLogin = usuarioRepository.gravarUsuario(objLogin);
		}
		//RequestDispatcher redireciona = request.getRequestDispatcher("principal/usuario.jsp");
		//redireciona.forward(request, response);
		
		//Manda o objeto para pagina.
		request.setAttribute("modolLogin", objLogin);
		
		//Manda uma Messagem de SUCESSO PARA VIEW.
		request.setAttribute("msg", msg);
		
		//Mesmo de forma resumida 
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		
		} catch (SQLException e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
