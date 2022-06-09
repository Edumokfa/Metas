package utils;

import java.io.IOException;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Eduardo
 */
public class JsfUtil {

    public static void exibeErro(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção", mensagem));
    }

    public static void exibeMensagem(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", mensagem));
    }

    public static void exibeAviso(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", mensagem));
    }

    public static void fatal(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", mensagem));
    }

    public static void redirecionar(String page) {
        if (page == null) {
            page = "";
        }
        try {
            getEC().redirect(getAppPath() + page);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ExternalContext getEC() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public static String getAppPath() {
        return getEC().getRequestContextPath();
    }

    public static void hideDlg(String... wVars) {
        for (String wvs : wVars) {
            primeFacesExecute("PF('" + wvs + "').hide();");
        }
    }

    public static void showDlg(String... wVars) {
        for (String wvs : wVars) {
            primeFacesExecute("PF('" + wvs + "').show();");
        }
    }

    public static void primeFacesExecute(String summary) {
        PrimeFaces.current().executeScript(summary);
    }

    public static void primeFacesUpdate(String... summary) {
        PrimeFaces.current().ajax().update(summary);
    }

    public static Map<String, Object> getMapaSessao() {
        return getExternalContext().getSessionMap();
    }

    public static void enviarRequisicao(String str) throws ServletException, IOException {
        getRequisicao().getRequestDispatcher(str).forward(getRequisicao(), getResposta());
    }

    public static ExternalContext getExternalContext() {
        return getFc().getExternalContext();
    }

    public static FacesContext getFc() {
        return FacesContext.getCurrentInstance();
    }

    public static HttpServletRequest getRequisicao() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public static HttpServletResponse getResposta() {
        return (HttpServletResponse) getExternalContext().getResponse();
    }
}
