/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Classes.GerCfgFrame;
import com.projeto2.metas.resources.Crud.GenericDAO;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author edumokfa
 */
@Stateless
@LocalBean
public class GerCfgFrameService extends GenericDAO<GerCfgFrame> {

    @Override
    public void salvar(GerCfgFrame object) {
        super.salvar(object);
    }

    public GerCfgFrame busca(Integer id) {
        return super.busca(GerCfgFrame.class, id);
    }
}
