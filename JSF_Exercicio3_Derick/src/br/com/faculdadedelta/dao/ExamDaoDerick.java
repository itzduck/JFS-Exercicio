package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.Exame;
import br.edu.faculdadedelta.util.Conexao;

public class ExamDaoDerick {
	
	public void incluir(Exame exame) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
	    Connection conn = conexao.conectarNoBanco();
		String sql = "INSERT INTO procedimentos("
				+ "paciente_desc, procedimento_desc, "
				+ "valor_procedimento, data_inicio_procedimento, "
				+ "data_fim_procedimento, quantidade_exame_procedimento)"
				+ "VALUES ( ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, exame.getPaciente());
		ps.setString(2, exame.getProcedimento());
		ps.setDouble(3, exame.getValor());
		ps.setDate(4, new java.sql.Date(exame.getData_inicio().getTime()));
		ps.setDate(5, new java.sql.Date(exame.getData_fim().getTime()));
		ps.setInt(6, exame.getQuantidade());
		ps.executeUpdate();
		
		ps.close();
	    conn.close();
	}
	
	public void alterar (Exame exame) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
	    Connection conn = conexao.conectarNoBanco();
		String sql = "UPDATE procedimentos SET paciente_desc = ?, "
	    		+ "procedimento_desc = ?,"
	    		+ "valor_procedimento = ?,"
	    		+ " data_inicio_procedimento = ?, "
	    		+ "data_fim_procedimento = ?,"
	    		+ " quantidade_exame_procedimento = ?"
	    		+ "	WHERE id_procedimento = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, exame.getPaciente());
		ps.setString(2, exame.getProcedimento());
		ps.setDouble(3, exame.getValor());
		ps.setDate(4, new java.sql.Date(exame.getData_inicio().getTime()));
		ps.setDate(5, new java.sql.Date(exame.getData_fim().getTime()));
		ps.setInt(6, exame.getQuantidade());
		ps.setLong(7, exame.getId());
		ps.executeUpdate();
		
		ps.close();
	    conn.close();

	}
	
	public void excluir(Exame exame) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
	    Connection conn = conexao.conectarNoBanco();
		String sql = "DELETE FROM procedimentos WHERE  id_procedimento = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, exame.getId());
		
		ps.executeUpdate();
		
		ps.close();
	    conn.close();
	}
	
	
		public List<Exame> listar() throws ClassNotFoundException, SQLException{
			Conexao conexao = new Conexao();
		    Connection conn = conexao.conectarNoBanco();
			String sql = "SELECT id_procedimento, paciente_desc, "
					+ "procedimento_desc, valor_procedimento, "
					+ "data_inicio_procedimento, data_fim_procedimento,"
					+ " quantidade_exame_procedimento"
					+ "FROM procedimentos";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Exame> listaRetorno = new ArrayList<Exame>();
			
			
			while (rs.next()) {
				Exame exame = new Exame();
				exame.setId(rs.getLong("id_procedimento"));
				exame.setPaciente(rs.getString("paciente_desc").trim());
				exame.setProcedimento(rs.getString("procedimento_desc").trim());
				exame.setValor(rs.getDouble(" valor_procedimento"));;
				exame.setData_inicio(rs.getDate("data_inicio_procedimento"));
				exame.setData_fim(rs.getDate("data_fim_procedimento"));
				exame.setQuantidade(rs.getInt("quantidade_exame_procedimento"));
				listaRetorno.add(exame);
				
			}
			ps.close();
		    conn.close();
			return listaRetorno;
		}		
}
