package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/*@ManagedBean //Era usado para gerenciar pelo o JSF
@ViewScoped*/
@Named //usado para o CDI gerenciar o projeto
@ViewScoped //javax.faces.view.ViewScoped
public class TemaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String tema = "aristo"; //tema padr�o

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    
    public String[] getTemas() {
        return new String[] { "afterdark", "afternoon", "afterwork", "aristo",
                "black-tie", "blitzer", "bluesky", "bootstrap", "casablanca",
                "cupertino", "cruze", "dark-hive", "delta", "dot-luv",
                "eggplant", "excite-bike", "flick", "glass-x", "home",
                "hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc",
                "overcast", "pepper-grinder", "redmond", "rocket", "sam",
                "smoothness", "south-street", "start", "sunny", "swanky-purse",
                "trontastic", "ui-darkness", "ui-lightness", "vader" };
        }
	

}
