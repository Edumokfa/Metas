package Beans;

import Classes.GerCfgFrame;
import Services.GerCfgFrameService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author edumokfa
 */
@Named
@ViewScoped
public class indexBean implements Serializable {

    @EJB
    private GerCfgFrameService gcfs;

    private String iFrame = "";

    @PostConstruct
    public void init() {
        GerCfgFrame frame = gcfs.busca(1);
        if (frame != null) {
            iFrame = frame.getFrame();
        }
    }

    public String getiFrame() {
        return iFrame;
    }

    public void setiFrame(String iFrame) {
        this.iFrame = iFrame;
    }
}
