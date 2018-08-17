package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

/*@ViewScoped
@ManagedBean*/
@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Autor autor = new Autor();
	private Integer autorId;

	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public void carregarAutorPeloId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();

	}

	// Removendo Autor do Banco
	public void removeAutor(Autor autor) {
		try {
			new DAO<Autor>(Autor.class).remove(autor);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Não é possível excluir um autor com um livro vinculado", ""));
		}
		
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

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
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
