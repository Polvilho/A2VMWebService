package com.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.webservice.factory.ConnectionFactory;
import com.webservice.model.Autuacao;

public class AutuacaoDAO extends ConnectionFactory {
	
	public int verificaCadastro(String placa) {
		
		int id = 0;
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		
		try {
			stmt = conn.prepareStatement("SELECT ID FROM AUTUACOES WHERE PLACA = ?");
			stmt.setString(1, placa);
			resultSet = stmt.executeQuery();
			
			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return id;
	}
	
	public int inserir(Autuacao autuacao) {
		
		Connection conn = null;
		conn = getConnection();
		int sucesso = 0;
		int prodsCadastrados = verificaCadastro(autuacao.getPlaca());
		
		if (prodsCadastrados == 0) {
			PreparedStatement stmt = null;
			
			try {
				stmt = conn.prepareStatement("INSERT INTO "
						+ "AUTUACOES (ESTADO, MUNICIPIO, PLACA, MARCA, MODELO, ANO, COR, AUTUACAO, PROPRIETARIO) VALUES (?,?,?,?,?,?,?,?,?)");
				
				stmt.setString(1, autuacao.getEstado());
				stmt.setString(2, autuacao.getMunicipio());
				stmt.setString(3, autuacao.getPlaca());
				stmt.setString(4, autuacao.getMarca());
				stmt.setString(5, autuacao.getModelo());
				stmt.setString(6, autuacao.getAno());
				stmt.setString(7, autuacao.getCor());
				stmt.setString(8, autuacao.getAutuacao());
				stmt.setString(9, autuacao.getProprietario());
				
				sucesso = stmt.executeUpdate();
				
				if (sucesso > 0) {
					System.out.println("AUTUACAO REGISTRADA");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("ERRO AO REGISTRAR AUTUACAO");
			} finally {
				closeConnection(conn, stmt);
			}
		}
		else {
			System.out.println("ERRO: AUTUACAO JA REGISTRADA");
			closeConnection(conn);
		}
		return sucesso;
	}
	
	public Autuacao buscar(String placa) {
		
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		Autuacao autuacao = null;
		
		try {
			stmt = conn.prepareStatement("SELECT ESTADO, MUNICIPIO, PLACA, "
					+ "MARCA, MODELO, ANO, COR, AUTUACAO, PROPRIETARIO, "
					+ "DATA_AUTUACAO, HORA_AUTUACAO FROM AUTUACOES WHERE PLACA = ?");
			stmt.setString(1, placa);
			resultSet = stmt.executeQuery();
			
			while (resultSet.next()) {
				autuacao = new Autuacao();
				autuacao.setEstado(resultSet.getString("estado"));
				autuacao.setMunicipio(resultSet.getString("municipio"));
				autuacao.setPlaca(resultSet.getString("placa"));
				autuacao.setMarca(resultSet.getString("marca"));
				autuacao.setModelo(resultSet.getString("modelo"));
				autuacao.setAno(resultSet.getString("ano"));
				autuacao.setCor(resultSet.getString("cor"));
				autuacao.setAutuacao(resultSet.getString("autuacao"));
				autuacao.setProprietario(resultSet.getString("proprietario"));
				autuacao.setData(resultSet.getString("data_autuacao"));
				autuacao.setHora(resultSet.getString("hora_autuacao"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return autuacao;
	}
	
	public ArrayList<Autuacao> buscarTodos() {
		
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		conn = getConnection();
		ArrayList<Autuacao> listaAutuacoes = null;
		
		try {
			stmt = conn.prepareStatement("SELECT ESTADO, MUNICIPIO, PLACA, "
					+ "MARCA, MODELO, ANO, COR, AUTUACAO, PROPRIETARIO, "
					+ "DATA_AUTUACAO, HORA_AUTUACAO FROM AUTUACOES ORDER BY ID");
			resultSet = stmt.executeQuery();
			listaAutuacoes = new ArrayList<Autuacao>();
			
			while (resultSet.next()) {
				Autuacao autuacao = new Autuacao();
				autuacao.setEstado(resultSet.getString("estado"));
				autuacao.setMunicipio(resultSet.getString("municipio"));
				autuacao.setPlaca(resultSet.getString("placa"));
				autuacao.setMarca(resultSet.getString("marca"));
				autuacao.setModelo(resultSet.getString("modelo"));
				autuacao.setAno(resultSet.getString("ano"));
				autuacao.setCor(resultSet.getString("cor"));
				autuacao.setAutuacao(resultSet.getString("autuacao"));
				autuacao.setProprietario(resultSet.getString("proprietario"));
				autuacao.setData(resultSet.getString("data_autuacao"));
				autuacao.setHora(resultSet.getString("hora_autuacao"));
				listaAutuacoes.add(autuacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			listaAutuacoes = null;
		} finally {
			closeConnection(conn, stmt, resultSet);
		}
		return listaAutuacoes;
	}

}
