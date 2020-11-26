package br.com.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ExamDaoDerick;
import br.com.faculdadedelta.modelo.Exame;

@ManagedBean
@SessionScoped
public class ExamControllerDerick {
	
	private Exame exame = new Exame();
	private ExamDaoDerick dao = new  ExamDaoDerick();
	
	private Date dataInicial;
	private Date dataFinal;
	
	
	public Exame getExame() {
		return exame;
	}
	public void setExame(Exame exame) {
		this.exame = exame;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public void limparCampos() {
		exame = new Exame();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage (mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public String Salvar() {
		try 
		{
			if(exame.getData_inicio().after(new Date())) 
			{
				if(exame.getData_fim().after(exame.getData_inicio()))
				{
		            if(exame.getId() == null)
		            {
				    dao.incluir(exame);
	                FacesMessage mensagem = new FacesMessage("Inclusao realizada com sucesso!!");	
	                FacesContext.getCurrentInstance().addMessage(null, mensagem);
	                limparCampos();
			
		            }
		            else
		            {
			        dao.alterar(exame);
			        FacesMessage mensagem = new FacesMessage("Alteracao realizada com sucesso!!");	
	                FacesContext.getCurrentInstance().addMessage(null, mensagem);
	                limparCampos();
		                }
		            }
				else
				{
		            	exibirMensagem("A data final deve ser maior que a data inicial!");
		            }
			}
				else
				{
		             exibirMensagem("A data inicial deve ser maior que a data atual!");
		            }      
		}catch (ClassNotFoundException | SQLException e) {
			FacesMessage message = new FacesMessage("Erro ao realizar operação "
					+ "Salvar!" + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		return "cadExam.xhtml";
	}
	
	public List<Exame> getLista(){
		List<Exame> listaRetorno = new ArrayList<Exame>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro a realizar opera�ao"
					+ " tente novamente mais tarde" + e.getMessage());	
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		
		return listaRetorno;
	}
	 public String editar() {
		 return "cadExam.xhtml";
	 }
	public String excluir() {
		try {
			dao.excluir(exame);
			exibirMensagem("[Excluido com Sucesso]");
			limparCampos();
		}catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("[Erro ao realizar operação!]");
			e.printStackTrace();
		}
		return "listExam.xhtml";
	}
}
