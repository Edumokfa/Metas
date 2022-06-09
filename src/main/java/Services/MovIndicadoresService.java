/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Classes.GerTipoIndicadores;
import Classes.MovIndicadores;
import com.projeto2.metas.resources.Crud.GenericDAO;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import utils.DateUtil;
import utils.ListUtil;

/**
 *
 * @author edumokfa
 */
@Stateless
@LocalBean
public class MovIndicadoresService extends GenericDAO<MovIndicadores> {

    @Override
    public void salvar(MovIndicadores object) {
        super.salvar(object);
    }

    public MovIndicadores busca(Integer id) {
        return super.busca(MovIndicadores.class, id);
    }

    public GerTipoIndicadores buscaIndicadorPorValor(Double valor, Integer tpiCod) {
        List<GerTipoIndicadores> mviList = super.executeNativeQuery("SELECT FIRST 1 * FROM GER_TIPO_INDICADORES GTI "
                + "LEFT JOIN GER_TIPO_INDXFAIXAS GTXF ON GTXF.TPF_CODTIPO = GTI.TPI_COD "
                + "WHERE GTI.TPI_COD = " + tpiCod
                + " AND GTXF.TPF_FAIXA_INI <= " + valor
                + " AND GTXF.TPF_FAIXA_FIM >= " + valor, GerTipoIndicadores.class);
        GerTipoIndicadores tiIndRetorno = new GerTipoIndicadores();
        if (mviList != null && !mviList.isEmpty()) {
            tiIndRetorno = mviList.get(0);
        }
        return tiIndRetorno;
    }

    public List<MovIndicadores> buscaMovimentosPorPeriodo(Date dataIni, Date dataFim) {
        return super.executeNativeQuery("SELECT * FROM  MOV_INDICADORES MI"
                + " WHERE MI.MOV_DTHR >= "
                + DateUtil.dataFirebird(dataIni)
                + " AND MI.MOV_DTHR <= " + DateUtil.dataFirebird(dataFim) + " ORDER BY MI.MOV_DTHR", MovIndicadores.class);
    }

    public boolean isPossuiMovimentoNaData(Date data, Integer id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM MOV_INDICADORES MI ");
        query.append("WHERE MI.MOV_DTHR = ").append(DateUtil.dataFirebird(data));
        if (id != null) {
            query.append("AND MI.MOV_COD <> ").append(id);
        }
        return ListUtil.isNotEmpty(super.executeNativeQuery(query.toString(), MovIndicadores.class));
    }
}
