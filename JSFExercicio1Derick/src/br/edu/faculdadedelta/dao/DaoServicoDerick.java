package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.ModeloServicoDerick;
import br.edu.faculdadedelta.util.Conexao;


public class DaoServicoDerick {

		public void incluir (ModeloServicoDerick servico ) throws ClassNotFoundException, SQLException {
			Conexao conexao = new Conexao();
			Connection conn = conexao.conectarNoBanco();
			String sql = "INSERT INTO servicos (desc_cliente, desc_servico, valor_unitario_servico, qtde_servico, data_exec_servico) "
					+ " VALUES (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,servico.getCliente().trim());
			ps.setString(2, servico.getServico().trim());
			ps.setDouble(3, servico.getValor());
			ps.setInt(4, servico.getQuantidade());
			ps.setDate(5, new java.sql.Date(servico.getDataCadastro().getTime()));
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}
		
		
		public void alterar(ModeloServicoDerick servico) throws ClassNotFoundException, SQLException{
			Conexao conexao = new Conexao();
			Connection conn = conexao.conectarNoBanco();
			String sql = "UPDATE servicos SET desc_cliente  = ?, "
					+ " desc_servico = ?, "
					+ " valor_unitario_servico = ?, "
					+ " qtde_servico = ?, "
					+ " data_exec_servico = ? "
					+ " WHERE id_servico = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,servico.getServico().trim());
			ps.setString(2, servico.getCliente().trim());
			ps.setInt(3, servico.getQuantidade());
			ps.setDouble(4, servico.getValor());
			ps.setDate(5, new java.sql.Date(servico.getDataCadastro().getTime()));
			ps.setLong(6, servico.getId());
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		}
		public void excluir(ModeloServicoDerick servico) throws ClassNotFoundException, SQLException {
			Conexao conexao = new Conexao();
			Connection conn = conexao.conectarNoBanco();
			String sql = "DELETE FROM servicos WHERE id_servico = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, servico.getId());
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}
		public List<ModeloServicoDerick> listar() throws ClassNotFoundException, SQLException {
			Conexao conexao = new Conexao();
			Connection conn = conexao.conectarNoBanco();
			String sql = "SELECT  id_servico,desc_cliente, desc_servico, "
					+ " valor_unitario_servico, qtde_servico, data_exec_servico FROM servicos";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<ModeloServicoDerick> listaRetorno = new ArrayList<ModeloServicoDerick>();
	 		
			while (rs.next()) {
				ModeloServicoDerick servico = new ModeloServicoDerick();
				servico.setId(rs.getLong("id_servico"));
				servico.setCliente(rs.getString("desc_cliente").trim());
				servico.setServico(rs.getString("desc_servico").trim());
				servico.setValor(rs.getDouble("valor_unitario_servico"));
				servico.setQuantidade(rs.getInt("qtde_servico"));
				servico.setDataCadastro(rs.getDate("data_exec_servico"));
				listaRetorno.add(servico);
			}
			rs.close();
			ps.close();
			conn.close();
			return listaRetorno;
}	
}
