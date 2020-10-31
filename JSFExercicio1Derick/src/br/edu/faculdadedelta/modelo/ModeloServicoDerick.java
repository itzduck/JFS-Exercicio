package br.edu.faculdadedelta.modelo;

import java.util.Date;


public class ModeloServicoDerick {
	
	private  Long id;
	private String cliente;
	private String servico;
	private int quantidade;
	private double valor;
	private Date dataCadastro;
	
	
	public ModeloServicoDerick() {
		
	}


	public ModeloServicoDerick(Long id, String cliente, String servico, int quantidade, double valor,
			Date dataCadastro) {
		this.id = id;
		this.cliente = cliente;
		this.servico = servico;
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataCadastro = dataCadastro;
	}


	public String getCliente() {
		return cliente;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getServico() {
		return servico;
	}


	public void setServico(String servico) {
		this.servico = servico;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}


	public Date getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public double getValorTotal() {
		return (quantidade * valor);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloServicoDerick other = (ModeloServicoDerick) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
}
}
