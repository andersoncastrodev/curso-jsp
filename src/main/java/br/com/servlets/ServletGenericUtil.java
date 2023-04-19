package br.com.servlets;

import java.io.Serializable;
import java.sql.SQLException;
import br.com.dao.DaoUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ServletGenericUtil extends HttpServlet  implements Serializable {

	private static final long serialVersionUID = 1L;

	private DaoUsuarioRepository daoUsuarioRepository = new DaoUsuarioRepository();
	
	public Long getUserLogado(HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		return daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado).getId();
	}
}
