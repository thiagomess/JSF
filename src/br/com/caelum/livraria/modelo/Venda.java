package br.com.caelum.livraria.modelo;

import java.io.Serializable;

public class Venda implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Livro livro;
	private Integer quantidade;
	
	public Venda(Livro livro, Integer quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	

}
