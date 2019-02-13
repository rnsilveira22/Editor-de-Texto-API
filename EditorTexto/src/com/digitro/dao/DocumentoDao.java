package com.digitro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.digitro.conectaBD.ConectaPostgres;
import com.digitro.model.Documento;

public class DocumentoDao {

	

	public Documento salvar(Documento documento) throws DaoException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try(Connection con = ConectaPostgres.conectaPostgres()) {
			pstmt = con.prepareStatement("INSERT INTO documento(titulo,corpo) VALUES(?,?)RETURNING id,data;");
			pstmt.setString(1, documento.getTitulo());
			pstmt.setString(2, documento.getCorpo());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				documento.setId(rs.getLong("id"));
				documento.setData(rs.getDate("data"));
			}

			pstmt.close();
			

		} catch (Exception e) {
			
			throw new DaoException("", e); 
		} 
		return documento;
	}

	public List<Documento> listar(String titulo, String corpo) throws DaoException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Documento> lista = new ArrayList<>();
		
		
		try(Connection con = ConectaPostgres.conectaPostgres();) {

			String script = "select * FROM documento WHERE 1 = 1";

			if (titulo != null && !titulo.isEmpty()) {
				script += " and titulo LIKE '%" + titulo + "%' ";
			}
			if (corpo != null && !corpo.isEmpty()) {
				script += " and corpo LIKE '%" + corpo + "%' ";
			}
			pstmt = con.prepareStatement(script);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Documento documento = new Documento();
				documento.setId(rs.getLong("id"));
				documento.setTitulo(rs.getString("titulo"));
				documento.setCorpo(rs.getString("corpo"));
				documento.setData(rs.getDate("Data"));
				lista.add(documento);
			}
			
		} catch (Exception e) {
			throw new DaoException("", e);
		} 
		return lista;
	}

	public Documento get(Long id) throws DaoException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Documento documento = new Documento();
		try(Connection con = ConectaPostgres.conectaPostgres();) {
			pstmt = con.prepareStatement("SELECT * FROM documento WHERE id=?;");
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				documento.setId(rs.getLong("id"));
				documento.setTitulo(rs.getString("titulo"));
				documento.setCorpo(rs.getString("corpo"));
				documento.setData(rs.getDate("Data"));
			}
			
		}catch (Exception e) {
			throw new DaoException("", e);
		} 
		return documento;
	}

	public Documento atualizar(Documento documento) throws DaoException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try(Connection con = ConectaPostgres.conectaPostgres();) {

			pstmt = con.prepareStatement(
					"UPDATE documento SET titulo= ?,corpo= ? WHERE id= ? RETURNING id,titulo,corpo,data ;");
			pstmt.setString(1, documento.getTitulo());
			pstmt.setString(2, documento.getCorpo());
			pstmt.setLong(3, documento.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				documento.setId(rs.getLong("id"));
				documento.setTitulo(rs.getString("titulo"));
				documento.setCorpo(rs.getString("corpo"));
				documento.setData(rs.getDate("data"));
			}
		} catch (Exception e) {
			throw new DaoException("", e);
		} 
		return documento;
	}

	public void excluir(Long id) throws DaoException {
		
		PreparedStatement pstmt = null;
		
		try(Connection con = ConectaPostgres.conectaPostgres();) {
			pstmt = con.prepareStatement("DELETE FROM documento WHERE id= ?");
			pstmt.setLong(1, id);
			pstmt.execute();

		} catch (Exception e) {
			throw new DaoException("", e);
		} 
		
	}

}