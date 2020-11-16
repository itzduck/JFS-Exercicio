package br.com.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.DaoBensDerick;
import br.edu.faculdadedelta.modelo.ModeloBensDerick;

@ManagedBean
@SessionScoped
public class BensControllerDerick {
	
	private ModeloBensDerick bens = new ModeloBensDerick();	
	private DaoBensDerick dao = new DaoBensDerick();
	
	
	public ModeloBensDerick getBens() {
		return bens;
	}
	public void setBens(ModeloBensDerick bens) {
		this.bens = bens;
	}
	
	public  void limparCampos() {
		bens = new ModeloBensDerick();
		
	}
	
	public String salvar() {
		try {
			if (bens.getId() == null) {
				dao.incluir(bens);
				FacesMessage mensagem = new FacesMessage("Inclusão Realizada!");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				limparCampos();
			}else {
				dao.alterar(bens);
				FacesMessage mensagem = new FacesMessage("Alteração Realizada!");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				limparCampos();
			}
		}catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro ao Realizar Operação."
					+ "Tente Novamente" + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		
			}
			return "listBens.xhtml";
		}
		public List<ModeloBensDerick> getLista(){
			List<ModeloBensDerick> listaRetorno = new ArrayList<ModeloBensDerick>();       
			try {
				listaRetorno = dao.listar();
			}catch (ClassNotFoundException | SQLException e) {
				FacesMessage mensagem = new FacesMessage("Erro ao Realizar Operação."
						+ "Tente Novamente" + e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				e.printStackTrace();
			}
			return listaRetorno;
		}
		public String editar() {
			return "cadBens.xhtml";
		}
		public String exlcuir() {
			try {
				dao.excluir(bens);
				FacesMessage mensagem = new FacesMessage("Exclusão realizada com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				limparCampos();
			} catch (ClassNotFoundException | SQLException e){
				FacesMessage mensagem = new FacesMessage("Erro ao realizar a operação. "
						+ "Tente novamente. " + e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				e.printStackTrace();
			}
			return "listaBens.xhtml";
}
}