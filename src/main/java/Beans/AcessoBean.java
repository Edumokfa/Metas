/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Beans;

import Classes.GerUsuario;
import Services.GerUsuarioService;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import utils.JsfUtil;
import utils.ListUtil;
import utils.SecUtil;
import utils.StringUtil;

/**
 *
 * @author Eduardo
 */
@Named
@SessionScoped
public class AcessoBean implements Serializable {

    private String cpf;
    private String senha;
    private GerUsuario ul;

    @EJB
    private GerUsuarioService gus;

    public void valida() throws ServletException, IOException {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("cpf", StringUtil.onlyNumbers(cpf));
        filtros.put("senha", SecUtil.Encript(senha));
        List<GerUsuario> usuarios = gus.filtrar(filtros);
        if (ListUtil.isNotEmpty(usuarios)) {
            ul = usuarios.get(0);
            JsfUtil.redirecionar("/publico/index.xhtml");
        } else {
            JsfUtil.exibeErro("Usuário inválido");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void logout() {
        ul = null;
        JsfUtil.redirecionar("/login.xhtml");
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public GerUsuario getUl() {
        return ul;
    }

    public void setUl(GerUsuario ul) {
        this.ul = ul;
    }
}
