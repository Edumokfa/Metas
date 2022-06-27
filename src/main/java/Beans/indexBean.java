package Beans;

import Classes.MovIndXTipos;
import Classes.MovIndicadores;
import Services.MovIndicadoresService;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import utils.DateUtil;
import utils.GraficoXLegenda;
import utils.JsfUtil;

/**
 *
 * @author edumokfa
 */
@Named
@ViewScoped
public class indexBean implements Serializable {

    @EJB
    private MovIndicadoresService mis;

    private List<GraficoXLegenda> graficoXLegenda = new ArrayList<>();
    private Date dataIni = new Date();
    private Date dataFim = new Date();

    @PostConstruct
    public void init() {
        dataIni = DateUtil.adicionaDias(dataIni, -30);
        pesquisar();
    }

    public void pesquisar() {
        if (dataIni.compareTo(dataFim) > 0) {
            JsfUtil.exibeAviso("Período inválido");
            return;
        }

        graficoXLegenda.clear();
        List<MovIndicadores> movIndList = mis.buscaMovimentosPorPeriodo(dataIni, dataFim);
        NumberFormat z = NumberFormat.getInstance();

        for (MovIndicadores movIndicadores : movIndList) {
            Map<Integer, String> legenda = new TreeMap<>();

            BarChartModel barModel = new BarChartModel();
            ChartSeries series = new ChartSeries();
            for (MovIndXTipos movIndXTipo : movIndicadores.getMovIndXTipos()) {
                String resultado = z.format(movIndXTipo.getMviVlrResultado());
                if (legenda.isEmpty()) {
                    legenda.put(1, movIndXTipo.getMviCodTipo().getTpiDesc() + " (" + resultado + ")");
                } else {
                    legenda.put(legenda.size() + 1, movIndXTipo.getMviCodTipo().getTpiDesc() + " (" + resultado + ")");
                }
                series.set(legenda.size(), movIndXTipo.getMviPercCalculado());
            }
            Axis yAxis = barModel.getAxis(AxisType.Y);
            yAxis.setLabel("Percentual");
            yAxis.setMin(20);
            yAxis.setTickInterval("5");
            yAxis.setMax(55);

            barModel.setTitle(movIndicadores.getDtHrFormatada());
            barModel.addSeries(series);

            GraficoXLegenda gxl = new GraficoXLegenda(getFormataLegenda(legenda), barModel);
            graficoXLegenda.add(gxl);
        }
        JsfUtil.exibeMensagem("Pesquisa realizada com sucesso");
    }

    public String getFormataLegenda(Map<Integer, String> legenda) {
        StringBuilder legendaMontada = new StringBuilder();
        legendaMontada.append("Legenda: <br>");
        for (Entry<Integer, String> entry : legenda.entrySet()) {
            legendaMontada.append(entry.getKey() + " = " + entry.getValue() + "<br>");
        }
        return legendaMontada.toString();
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public List<GraficoXLegenda> getGraficoXLegenda() {
        return graficoXLegenda;
    }

    public void setGraficoXLegenda(List<GraficoXLegenda> graficoXLegenda) {
        this.graficoXLegenda = graficoXLegenda;
    }
}
