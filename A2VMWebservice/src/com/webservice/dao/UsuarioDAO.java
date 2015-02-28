package com.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.webservice.factory.ConnectionFactory;
import com.webservice.model.Usuario;

public class UsuarioDAO extends ConnectionFactory{
	
	public Usuario buscar(String user) {
		
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		Usuario usuario = null;
		
		try {
			stmt = conn.prepareStatement("SELECT USUARIO, SENHA FROM USUARIOS "
					+ "WHERE USUARIO = ?");
			stmt.setString(1, user);
			resultSet = stmt.executeQuery();
			
			while (resultSet.next()) {
				usuario = new Usuario();
				usuario.setUsuario(resultSet.getString("usuario"));
				usuario.setSenha(resultSet.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return usuario;
	}

}
