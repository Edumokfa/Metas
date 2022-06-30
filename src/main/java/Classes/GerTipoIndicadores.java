/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author edumokfa
 */
@Entity
@Table(name = "GER_TIPO_INDICADORES")
public class GerTipoIndicadores implements Serializable {

    @Id
    @SequenceGenerator(name = "GEN_GER_TIPO_INDICADORES", sequenceName = "GEN_GER_TIPO_INDICADORES", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "GEN_GER_TIPO_INDICADORES")
    @Column(name = "TPI_COD")
    private Integer tpiCod;
    @NotNull
    @Column(name = "TPI_DESC")
    private String tpiDesc;
    @NotNull
    @Column(name = "TPI_ATIVO")
    private Boolean tpiAtivo = true;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tpfCodTipo", orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy(value = "tpfPercPremiacao")
    private List<GerTipoIndXFaixas> tpIndXFaixas = new ArrayList<>();

    public GerTipoIndicadores() {
    }

    public Integer getTpiCod() {
        return tpiCod;
    }

    public void setTpiCod(Integer tpiCod) {
        this.tpiCod = tpiCod;
    }

    public String getTpiDesc() {
        return tpiDesc;
    }

    public void setTpiDesc(String TpiDesc) {
        this.tpiDesc = TpiDesc;
    }

    public Boolean getTpiAtivo() {
        return tpiAtivo;
    }

    public void setTpiAtivo(Boolean tpiAtivo) {
        this.tpiAtivo = tpiAtivo;
    }

    public List<GerTipoIndXFaixas> getTpIndXFaixas() {
        return tpIndXFaixas;
    }

    public void setTpIndXFaixas(List<GerTipoIndXFaixas> tpIndXFaixas) {
        this.tpIndXFaixas = tpIndXFaixas;
    }

    public String getDescSituacao() {
        return tpiAtivo ? "Ativo" : "Inativo";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.tpiCod);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GerTipoIndicadores other = (GerTipoIndicadores) obj;
        return Objects.equals(this.tpiCod, other.tpiCod);
    }
}
