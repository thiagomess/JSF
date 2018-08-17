package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

/*@ManagedBean
@ViewScoped*/
@Named
@ViewScoped
public class VendasBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private BarChartModel vendasModel;

	@PostConstruct
	public void init() {
		createVendasModel();
	}



	private void createVendasModel() {
		vendasModel = initVendasModel();

		vendasModel.setTitle("Vendas");
		vendasModel.setLegendPosition("ne");

		Axis xAxis = vendasModel.getAxis(AxisType.X);
		xAxis.setLabel("Título");

		Axis yAxis = vendasModel.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setMin(0);
		yAxis.setMax(500);
	}

	private BarChartModel initVendasModel() {

		BarChartModel model = new BarChartModel();

		ChartSeries vendaSeries2015 = new ChartSeries();
		List<Venda> vendas = getVendas(1234);
		vendaSeries2015.setLabel("2015");

		for (Venda venda : vendas) {

			vendaSeries2015.set(venda.getLivro().getTitulo(), venda.getQuantidade());

		}

		model.addSeries(vendaSeries2015);

		ChartSeries vendaSeries2016 = new ChartSeries();
		vendas = getVendas(4321);
		vendaSeries2016.setLabel("2016");

		for (Venda venda : vendas) {

			vendaSeries2016.set(venda.getLivro().getTitulo(), venda.getQuantidade());

		}

		model.addSeries(vendaSeries2016);

		return model;
	}

	public List<Venda> getVendas(long seed) {

		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();
		Random random = new Random(seed);

		for (Livro livro : livros) {
			Integer quantidade = random.nextInt(500);

			vendas.add(new Venda(livro, quantidade));
		}

		return vendas;
	}

	public BarChartModel getVendasModel() {
		return vendasModel;
	}

	public void setVendasModel(BarChartModel vendasModel) {
		this.vendasModel = vendasModel;
	}

}
