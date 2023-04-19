package br.com.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.com.dao.DaoLoginRepository;
import br.com.dao.DaoUsuarioRepository;
import br.com.model.ModelLogin;

// @WebServlet("/servletlogin") - Normal com apenas 1 URL, mas recebe um Array.

@WebServlet(urlPatterns = {"/servletlogin","/principal/servletlogin"}) // Mapeamento de 2 URL link que o formulario vai enviar os pedidos. 
public class ServletLogin extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
      
	private DaoLoginRepository daoLoginRepository = new DaoLoginRepository();

	private DaoUsuarioRepository daoUsuarioRepository = new DaoUsuarioRepository();
	
    public ServletLogin() {
        super();

    }

    // RECEBE OS DADOS VIA GET PELA URL Via Parametros. 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if( acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate(); //Invalida a Seçao para pedir o login novamente.
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
			
		}else {
			//Redirecionando para o post.
			doPost(request, response);
		}

	}

	// RECEBE OS DADOS VIA POST. SECRETO.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		if( login != null && !login.isEmpty()  &&  senha !=null && !senha.isEmpty()   ) {
			
			ModelLogin loginModel = new ModelLogin();
			loginModel.setLogin(login);
			loginModel.setSenha(senha);
			
			if(daoLoginRepository.validarAutenticacao(loginModel) ) {
             
				
				// AQUI ENTRA NO SISTEMA. 
				//Passos Para Seçao Usuario 
				
				request.getSession().setAttribute("usuario", loginModel.getLogin() );
				//Passo passar Só o nome do Usuario  ou o OBJETO INTEIRO. ou só a senha loginModel.getSenha() etc..
				
				loginModel = daoUsuarioRepository.consultaUsuarioLogado(login);
				
				//Passando o Objeto Inteiro para o secessão 
				request.getSession().setAttribute("isAdmin", loginModel.getUseradmin());
				
				if(url == null || url.equals("null")) {
					url = "principal/principal.jsp";
				}
				
				RequestDispatcher redirect = request.getRequestDispatcher(url);
		
				redirect.forward(request, response);
				
				///////////////////
				
			}else {
				
				//Criando o Redirecionar. 
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informe o login e senha Corretamente!"); //Colocando um Messagem para Enviar.
				//Redirecionando.
				redirecionar.forward(request, response);
			}
			
			
		}else {
			
			//Criando o Redirecionar. 
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Campo em Branco."); //Colocando um Messagem para Enviar.
			//Redirecionando.
			redirecionar.forward(request, response);
		}

			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

}
