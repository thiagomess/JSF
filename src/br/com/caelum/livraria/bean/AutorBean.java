package br.com.caelum.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.ForwardView;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}
	
	
	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);
		this.autor = new Autor();
		return  new RedirectView("livro");
	}

/*	Metodo que da um redirect e outro um forward
 * explicação em https://cursos.alura.com.br/course/jsf/task/1979
 * 
 * public ForwardView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);
		this.autor = new Autor();
		return  new ForwardView("livro");
	}*/
}
