package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.VendaDao;
import br.com.caelum.livraria.modelo.Venda;
import br.com.caelum.livraria.tx.Transacional;

/*@ManagedBean //Era usado para gerenciar pelo o JSF
@ViewScoped*/
@Named //usado para o CDI gerenciar o projeto
@ViewScoped// Tag do pacote para o CDI javax.faces.view.ViewScoped
public class VendasBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private BarChartModel vendasModel;
	
	@Inject
	private VendaDao dao;

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
		List<Venda> vendas = getVendas(2016);
		vendaSeries2015.setLabel("2016");

		for (Venda venda : vendas) {

			vendaSeries2015.set(venda.getLivro().getTitulo(), venda.getQuantidade());

		}

		model.addSeries(vendaSeries2015);

		ChartSeries vendaSeries2016 = new ChartSeries();
		vendas = getVendas(2017);
		vendaSeries2016.setLabel("2017");

		for (Venda venda : vendas) {

			vendaSeries2016.set(venda.getLivro().getTitulo(), venda.getQuantidade());

		}

		model.addSeries(vendaSeries2016);

		return model;
	}
	
	@Transacional
	public List<Venda> getVendas(Integer ano) {
		
		List<Venda> buscaVendas = this.dao.buscaVendas(ano);
		
		return buscaVendas;
	}

	public BarChartModel getVendasModel() {
		return vendasModel;
	}

	public void setVendasModel(BarChartModel vendasModel) {
		this.vendasModel = vendasModel;
	}

}
