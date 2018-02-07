package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

@ViewScoped
@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();

	}

	// Removendo Autor do Banco
	public void removeAutor(Autor autor) {
		new DAO<Autor>(Autor.class).remove(autor);
	}

	public void carregaAutor(Autor autor) {
		System.out.println("carregando autor: " + autor.getNome());
		this.autor = autor;
	}

	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(autor);
		}
		this.autor = new Autor();
		return new RedirectView("livro");
	}

	/*
	 * Metodo que da um redirect e outro um forward explicação em
	 * https://cursos.alura.com.br/course/jsf/task/1979
	 * 
	 * public ForwardView gravar() { System.out.println("Gravando autor " +
	 * this.autor.getNome());
	 * 
	 * new DAO<Autor>(Autor.class).adiciona(this.autor); this.autor = new Autor();
	 * return new ForwardView("livro"); }
	 */
}
