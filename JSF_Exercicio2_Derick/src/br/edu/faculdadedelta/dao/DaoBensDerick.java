package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.ModeloBensDerick;
import br.edu.faculdadedelta.util.Conexao;

public class DaoBensDerick {

	public void incluir (ModeloBensDerick bens) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = " INSERT INTO bens (nome_bem, especificacao_bem, desc_departamento, valor_bem, data_cadastro_bem)"
					+"VALUES (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, bens.getNome().trim().trim());
		ps.setString(2, bens.getEspecificacao().trim());
		ps.setString(3, bens.getDepartamento().trim());
		ps.setDouble(4, bens.getValor());
		ps.setDate(5, new java.sql.Date(bens.getDataCadastro().getTime()));
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}	
	
	public void alterar(ModeloBensDerick bens) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "UPDATE bens SET nome_bem  = ?,"
				+ "especificacao_bem = ?,"
				+ "desc_departamento = ?,"
				+ "valor_bem = ?,"
				+ "data_cadastro_bem = ?"
				+ "WHERE id_bem = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, bens.getNome().trim().trim());
		ps.setString(2, bens.getEspecificacao().trim());
		ps.setString(3, bens.getDepartamento().trim());
		ps.setDouble(4, bens.getValor());
		ps.setDate(5, new java.sql.Date(bens.getDataCadastro().getTime()));
		ps.setLong(6, bens.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void excluir(ModeloBensDerick bens) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "DELETE FROM bens WHERE id_bem = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, bens.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public List<ModeloBensDerick> listar() throws ClassNotFoundException, SQLException{
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "SELECT id_bem,nome_bem,especificacao_bem,"
				+ "desc_departamento, valor_bem,data_cadastro_bem FROM bens";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<ModeloBensDerick> listaRetorno = new ArrayList<ModeloBensDerick>();
		
		while (rs.next()) {
			ModeloBensDerick bens = new ModeloBensDerick();
			bens.setId(rs.getLong("id_bem"));
			bens.setNome(rs.getString("nome_bem").trim());
			bens.setEspecificacao(rs.getString("desc_departamento").trim());
			bens.setDepartamento(rs.getString("especificacao_bem").trim());
			bens.setValor(rs.getDouble("valor_bem"));
			bens.setDataCadastro(rs.getDate("data_cadastro_bem"));
			listaRetorno.add(bens);
		}
		rs.close();
		rs.close();
		conn.close();
		return listaRetorno;
	}
	
}
