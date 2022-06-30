package Classes;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "GER_CFGFRAME")
public class GerCfgFrame implements Serializable {

    @Id
    @Column(name = "CG_CODIGO")
    private Integer codigo;
    @Column(name = "CG_IFRAMEPOWERBI")
    private String frame;

    public GerCfgFrame() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.codigo);
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
        final GerCfgFrame other = (GerCfgFrame) obj;
        return Objects.equals(this.codigo, other.codigo);
    }
}
