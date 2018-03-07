package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;
	
	public void carregarLivroPeloId() {
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(livroId);
	}	

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();

	}

	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			// throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor."));// pegando a exception e jogando como
																				// mensagem na tela
			return;
		}
		// IF para incluir se for um livro novo ou atualizar os dados de um livro
		if (this.livro.getId() == null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);

		} else {
			new DAO<Livro>(Livro.class).atualiza(livro);
		}

		this.livro = new Livro();
	}

	// carregando livro nos textbox para alteração
	public void carregar(Livro livro) {
		System.out.println("Carregando livro: '" + livro.getTitulo() + "' para alteracao");
		this.livro = livro;

	}

	// metodo para remover o livro
	public void remover(Livro livro) {
		System.out.println("Removendo livro: " + livro.getTitulo());
		new DAO<Livro>(Livro.class).remove(livro);
	}

	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
	}

	// Removendo autor do livro e usando o metodo na classe Livro.
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}

	// Tratamento de validação manual do xhtml
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN Deveria começar com 1"));
		}
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	/*
	 * O Redirecionamento para a pagina poderia ser feito tambem atraves da classe
	 * java, util quando se tem biredirecionamento de paginas com IF Usa se na
	 * action no xhtml "#{livroBean.formAutor}" Explicacao>
	 * https://cursos.alura.com.br/course/jsf/task/1978
	 * 
	 * public String formAutor() {
	 * 
	 * return "autor?faces-redirect=true";
	 * 
	 * }
	 */

}
