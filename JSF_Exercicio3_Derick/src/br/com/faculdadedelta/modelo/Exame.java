package br.com.faculdadedelta.modelo;

import java.util.Date;



public class Exame {

	private Long id;
	private String Paciente;
	private String Procedimento;
	private double valor;
	private Date Data_inicio;
	private Date Data_fim;
	private int Quantidade;
	
	
	public Exame() {
	}

	

	public Exame(Long id, String paciente, String procedimento, double valor, Date data_inicio, Date data_fim,
			int quantidade) {
		
		this.id = id;
		Paciente = paciente;
		Procedimento = procedimento;
		this.valor = valor;
		Data_inicio = data_inicio;
		Data_fim = data_fim;
		Quantidade = quantidade;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPaciente() {
		return Paciente;
	}


	public void setPaciente(String paciente) {
		Paciente = paciente;
	}


	public String getProcedimento() {
		return Procedimento;
	}


	public void setProcedimento(String procedimento) {
		Procedimento = procedimento;
	}


	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}


	public Date getData_inicio() {
		return Data_inicio;
	}


	public void setData_inicio(Date data_inicio) {
		Data_inicio = data_inicio;
	}


	public Date getData_fim() {
		return Data_fim;
	}


	public void setData_fim(Date data_fim) {
		Data_fim = data_fim;
	}


	public int getQuantidade() {
		return Quantidade;
	}


	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}
	
	public double getvalortotal() {
		double desconto = 0;
		double valorTotal = valor * Quantidade;
			
			Long intervalo = (Data_fim.getTime() - Data_inicio.getTime() + 3600000L) / 86400000L ;
			if(intervalo > 2) {
				desconto += valor * 0.025;
			}if(valor > 2000) {
				desconto += valorTotal * 0.015;
			}
			valorTotal = valorTotal - desconto ;
					
		return valorTotal;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0: id.hashCode());
		return result;	
	}
	
	@Override
	public boolean equals(Object obj) {
		if	(this == obj)
			return true;
		if	(obj == null)
			return false;
		if	(getClass() != obj.getClass())
			return false;
		Exame other = (Exame) obj;
		if (id == null) {
			if(other.id != null)
				return false;
		}else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
