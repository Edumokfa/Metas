/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Beans;

import Classes.GerTipoIndXFaixas;
import Classes.GerTipoIndicadores;
import Services.GerTipoIndicadoresService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import utils.JsfUtil;

/**
 *
 * @author edumokfa
 */
@Named
@ViewScoped
public class GerTipoIndicadoresBean implements Serializable {

    @EJB
    GerTipoIndicadoresService gti;

    @Inject
    private AcessoBean acesso;

    private GerTipoIndicadores tipoIndicadores = new GerTipoIndicadores();
    private List<GerTipoIndicadores> tipoIndicadoresList = new ArrayList<>();

    @PostConstruct
    private void init() {
        if (acesso.getUl() == null || !acesso.getUl().isAdmin()) {
            JsfUtil.redirecionar("/publico/index.xhtml");
        }
    }

    public void pesquisa() {
        tipoIndicadoresList = gti.buscaLista();
        JsfUtil.showDlg("dlgBscIndicadores");
        JsfUtil.primeFacesUpdate("form1:tabInd");
    }

    public void salva() {
        try {
            gti.salvar(tipoIndicadores);
            JsfUtil.exibeMensagem("Sucesso");
            tipoIndicadores = new GerTipoIndicadores();
        } catch (Exception e) {
            JsfUtil.fatal("Falha no cadastro");
        }
    }

    public void adiciona() {
        GerTipoIndXFaixas tpixf = new GerTipoIndXFaixas();
        tpixf.setTpfCodTipo(tipoIndicadores);
        tipoIndicadores.getTpIndXFaixas().add(tpixf);
        JsfUtil.exibeMensagem("Registro adicionado com sucesso");
    }

    public void remove(int index) {
        tipoIndicadores.getTpIndXFaixas().remove(index);
        JsfUtil.exibeMensagem("Registro removido com sucesso");
    }

    public void cancela() {
        tipoIndicadores = new GerTipoIndicadores();
    }

    public void excluir() {
        if (tipoIndicadores.getTpiCod() == null) {
            JsfUtil.exibeAviso("Pesquise um Indicador para excluí-lo");
            return;
        }
        gti.excluir(tipoIndicadores);
        JsfUtil.exibeMensagem("Indicador excluído com sucesso");
        cancela();
    }

    public void selecionaTpInd(GerTipoIndicadores tipoInd) {
        tipoIndicadores = tipoInd;
        JsfUtil.exibeMensagem("Registro selecionado com sucesso");
        JsfUtil.hideDlg("dlgBscIndicadores");
    }

    public GerTipoIndicadores getTipoIndicadores() {
        return tipoIndicadores;
    }

    public void setTipoIndicadores(GerTipoIndicadores tipoIndicadores) {
        this.tipoIndicadores = tipoIndicadores;
    }

    public GerTipoIndicadoresService getGti() {
        return gti;
    }

    public void setGti(GerTipoIndicadoresService gti) {
        this.gti = gti;
    }

    public List<GerTipoIndicadores> getTipoIndicadoresList() {
        return tipoIndicadoresList;
    }

    public void setTipoIndicadoresList(List<GerTipoIndicadores> tipoIndicadoresList) {
        this.tipoIndicadoresList = tipoIndicadoresList;
    }
}
