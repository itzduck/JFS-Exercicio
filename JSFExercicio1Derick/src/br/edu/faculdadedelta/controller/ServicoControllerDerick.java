package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.DaoServicoDerick;
import br.edu.faculdadedelta.modelo.ModeloServicoDerick;

@ManagedBean
@SessionScoped
public class ServicoControllerDerick{ 
	
	private ModeloServicoDerick servico = new ModeloServicoDerick();
	private DaoServicoDerick dao = new DaoServicoDerick();
	
	
	public ModeloServicoDerick getServico() {
		return servico;
	}
	public void setServico(ModeloServicoDerick servico) {
		this.servico = servico;
	}
	
	public void limparCampos() {
		servico = new ModeloServicoDerick();
	}
	
	public String salvar() {
		try {
			if (servico.getId() == null) {
				// inc
				dao.incluir(servico);
				FacesMessage mensagem = new FacesMessage("Inclusão realizada !");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				limparCampos();
			} else {
				// alt
				dao.alterar(servico);
				FacesMessage mensagem = new FacesMessage("Alteração realizada !");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro ao realizar a operação. "
					+ "Tente novamente mais tarde. " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		return "cadServico.xhtml";
	}
	public List<ModeloServicoDerick> getLista() {
		List<ModeloServicoDerick> listaRetorno = new ArrayList<ModeloServicoDerick>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro ao realizar a operação. "
					+ "Tente novamente mais tarde. " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		return listaRetorno;
	}
	public String editar() {
		return "cadServico.xhtml";
	}
	public String excluir() {
		try {
			dao.excluir(servico);
			FacesMessage mensagem = new FacesMessage("Exclusão realizada com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro ao realizar a operação. "
					+ "Tente novamente mais tarde. " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		return "listServico.xhtml";
	}
}