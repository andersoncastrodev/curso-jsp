package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.connection.SingleConnection;
import br.com.model.ModelLogin;

public class DaoLoginRepository {

	private Connection connection;
	
	public DaoLoginRepository() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarAutenticacao(ModelLogin modelLogin) {
		
		try {
			
		String sql = "select * from model_login where login = ? and senha = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			return true;
		}

		} catch(Exception e) {
					
			e.printStackTrace();
		 }
		
		return false;
	}
	
	
	
	
}
