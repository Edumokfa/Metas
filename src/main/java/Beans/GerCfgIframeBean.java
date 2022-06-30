package Beans;

import Classes.GerCfgFrame;
import Classes.GerUsuario;
import Services.GerCfgFrameService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import utils.JsfUtil;
import utils.SecUtil;
import utils.StringUtil;

/**
 *
 * @author Eduardo Mokfa
 */
@Named
@ViewScoped
public class GerCfgIframeBean implements Serializable {

    @EJB
    GerCfgFrameService gcfs;

    @Inject
    private AcessoBean acesso;

    private GerCfgFrame iFrame = new GerCfgFrame();

    @PostConstruct
    private void init() {
        if (acesso.getUl() == null || !acesso.getUl().isAdmin()) {
            JsfUtil.redirecionar("/publico/index.xhtml");
        }

        iFrame = gcfs.busca(1);
        if (iFrame == null) {
            limpar();
        }
    }

    public void salva() {
        try {
            iFrame.setCodigo(1);
            gcfs.salvar(iFrame);
            JsfUtil.exibeMensagem("Sucesso");
            limpar();
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.fatal("Falha na inclusão");
        }
    }

    public void limpar() {
        iFrame = new GerCfgFrame();
    }

    public GerCfgFrame getiFrame() {
        return iFrame;
    }

    public void setiFrame(GerCfgFrame iFrame) {
        this.iFrame = iFrame;
    }
}
