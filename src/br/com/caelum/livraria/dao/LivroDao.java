package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Livro;

public class LivroDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject //Injeta o EntityManager nessa classe 
	private EntityManager em;
	private DAO<Livro> dao;
	
	@PostConstruct //. Com isso o CDI chamar� esse m�todo assim que inicializar o LivroDao, inicializando tamb�m o DAO gen�rico:
	void init() {
		this.dao = new DAO<Livro>(this.em, Livro.class);
	}

	public void adiciona(Livro t) {
		dao.adiciona(t);
	}

	public void remove(Livro t) {
		dao.remove(t);
	}

	public void atualiza(Livro t) {
		dao.atualiza(t);
	}

	public List<Livro> listaTodos() {
		return dao.listaTodos();
	}

	public Livro buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}


}
