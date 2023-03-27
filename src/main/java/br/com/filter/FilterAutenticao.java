package br.com.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.connection.SingleConnection;

// Intercepta Todoas as requisiçoes que vieram do projeto.
@WebFilter(urlPatterns = { "/principal/*" })
public class FilterAutenticao implements Filter {

	private static Connection connection;

	public FilterAutenticao() {
		super();

	}

	/*
	 * Encerra os processo quando o servidor é paradao. Ex: Encerra a conexao com o
	 * banco de dados.
	 */
	public void destroy() {
		// Fechando a Conexao com o Banco de Dados.
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Tudo do sistema vai passar pode aqui. Ex: Validar de usuario Ex: Faz commit
	 * ou rolbook de transações no banco de dados. Ex: Validar e Fazer
	 * Redirecionamento de Paginas.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			
			HttpServletRequest req = (HttpServletRequest) request;

			HttpSession session = req.getSession();

			String usuarioLog = (String) session.getAttribute("usuario"); // Pegar o Usuario Logado.

			String urlAutenticar = req.getServletPath(); // Pegar a URL do momento.

			/*
			 * AGORA É SO VERIFICAR SE O USUARIO NAO É NULO ou EMBRANCO. E SE A URL ESTA
			 * DENTRO DO SISTEAMA.
			 */
			if (usuarioLog == null && !urlAutenticar.equalsIgnoreCase("/principal/servletlogin")) {

				RequestDispatcher ridireciona = request.getRequestDispatcher("/index.jsp?url=" + urlAutenticar);
				request.setAttribute("msg", "Por favor Faça o Login");

				ridireciona.forward(request, response);
				return; // Para a Execução

			} else {

				chain.doFilter(request, response); // segue o sistema normal
			}
			
			connection.commit(); // SE DEU TUDO CERTO NO BANCO DE DADOS.
			
		} catch (Exception e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
			}
			
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

	/*
	 * Inicia os processos ou recursos. Ex: Iniciar a Conexao com o banco de dados.
	 */
	public void init(FilterConfig fConfig) throws ServletException {

		// Iniciando a Conexao.
		connection = SingleConnection.getConnection();
	}

}
