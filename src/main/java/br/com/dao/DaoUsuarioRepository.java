package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.connection.SingleConnection;
import br.com.model.ModelLogin;

public class DaoUsuarioRepository {

	private Connection connection;
	
	//Construtor
	public DaoUsuarioRepository() {
		
		connection = SingleConnection.getConnection();
	}
	
	/*
	 * Metodos que grava e consutla o ultimo usuario cadastro.
	 */
	public ModelLogin gravarUsuario(ModelLogin objetoLogin, Long userLogado ) throws SQLException {
	
		if(objetoLogin.isNovo()) {
			
			String sql = "INSERT INTO model_login(login, senha, nome, email, usuario_id, perfil )VALUES (?, ?, ?, ?, ?,?);" ;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, objetoLogin.getLogin());
			preparedStatement.setString(2, objetoLogin.getSenha());
			preparedStatement.setString(3, objetoLogin.getNome());
			preparedStatement.setString(4, objetoLogin.getEmail());
			preparedStatement.setLong(5, userLogado);
			preparedStatement.setString(6, objetoLogin.getPerfil());
			
			preparedStatement.execute();
			connection.commit();			
		}else {
			// ATUALIZA O USUARIO 
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=?, perfil=? WHERE id = "+objetoLogin.getId()+"; ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, objetoLogin.getLogin());
			preparedStatement.setString(2, objetoLogin.getSenha());
			preparedStatement.setString(3, objetoLogin.getNome());
			preparedStatement.setString(4, objetoLogin.getEmail());
			preparedStatement.setString(5, objetoLogin.getPerfil());
			preparedStatement.executeUpdate();
			connection.commit();	
		}

		
		return this.consultaUsuario(objetoLogin.getLogin(),userLogado);
	}
	
	/*
	 * Metodo que só consulta do Usuario Cadastrado.
	 */
	public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws SQLException{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, "%" +nome+ "%" );
		preparedStatement.setLong(2, userLogado );

		ResultSet resultado = preparedStatement.executeQuery();
		
		while(resultado.next()) {

			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setId( resultado.getLong("id") );
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			
			retorno.add(modelLogin);
			
		}
		
		
		return retorno;
	}

	public ModelLogin consultaUsuarioLogado(String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where  upper(login) = upper('"+login+"')";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		ResultSet resultado = preparedStatement.executeQuery();
		
		if( resultado.next() ) { //Verificando se tem algum elemento na pesquisa.
			
			modelLogin.setId( resultado.getLong("id") );
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
		}
		return modelLogin;
	}
	
	public ModelLogin consultaUsuario(String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where  upper(login) = upper('"+login+"') and useradmin is false";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		ResultSet resultado = preparedStatement.executeQuery();
		
		if( resultado.next() ) { //Verificando se tem algum elemento na pesquisa.
			
			modelLogin.setId( resultado.getLong("id") );
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
			
		}
		return modelLogin;
	}
	
	public ModelLogin consultaUsuario(String login, Long userLogado) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where  upper(login) = upper('"+login+"') and useradmin is false and usuario_id = "+userLogado;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		ResultSet resultado = preparedStatement.executeQuery();
		
		if( resultado.next() ) { //Verificando se tem algum elemento na pesquisa.
			
			modelLogin.setId( resultado.getLong("id") );
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			
		}
		return modelLogin;
	}

	public ModelLogin consultaUsuarioPorId(String id, Long userLogado) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where id = ? and useradmin is false and usuario_id = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		//Passando o parametro
		preparedStatement.setLong(1, Long.parseLong(id));
		preparedStatement.setLong(2, userLogado);

		ResultSet resultado = preparedStatement.executeQuery();
		
		if( resultado.next() ) { //Verificando se tem algum elemento na pesquisa.
			
			modelLogin.setId( resultado.getLong("id") );
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			
		}
		return modelLogin;
	}

	public boolean validarLogin(String login) throws SQLException {
		
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('"+login+"')";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		ResultSet resultado = preparedStatement.executeQuery();
		
		resultado.next();
		
		return resultado.getBoolean("existe");
		
	}
	
	public void deletarUser(String id) throws SQLException {
		
		String sql = "DELETE FROM model_login WHERE id = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, Long.parseLong(id));
		
		preparedStatement.executeUpdate();
		connection.commit();
	}
	
	public List<ModelLogin> consultaUsuarioTodos(Long userLogado) throws SQLException{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where useradmin is false and usuario_id ="+userLogado;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		ResultSet resultado = preparedStatement.executeQuery();
		
		while(resultado.next()) {

			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setId( resultado.getLong("id") );
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			
			retorno.add(modelLogin);
			
		}
		return retorno;
	}
	
}
