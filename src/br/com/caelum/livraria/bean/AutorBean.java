package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

/*@ManagedBean //Era usado para gerenciar pelo o JSF
@ViewScoped*/
@Named //usado para o CDI gerenciar o projeto
@ViewScoped// Tag do pacote para o CDI javax.faces.view.ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject //Injeta o dao nessa classe ao inves de instanciarmos manualmente (new AutorDao();)
	private AutorDao dao;
	
	private Autor autor = new Autor();
	private Integer autorId;
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public void carregarAutorPeloId() {
		this.autor = this.dao.buscaPorId(autorId);
	}

	public List<Autor> getAutores() {
		return this.dao.listaTodos();

	}

	// Removendo Autor do Banco
	public void removeAutor(Autor autor) {
		try {
			this.dao.remove(autor);
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
			this.dao.adiciona(this.autor);
		} else {
			this.dao.atualiza(autor);
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
	 * this.dao.adiciona(this.autor); this.autor = new Autor();
	 * return new ForwardView("livro"); }
	 */
}
