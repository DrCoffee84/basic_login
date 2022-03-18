package ar.com.dboullon.login.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;


@Embeddable
@Data
public class RolHasModuloId implements java.io.Serializable {

    @Column(name = "id_rol", nullable = false)
    private Long idRol;
    @Column(name = "id_modulo", nullable = false)
    private Long idModulo;

    @Column(name = "id_permiso", nullable = false)
    private Long idPermiso;


    public boolean equals(Object other) {
        if ((this == other)) return true;
        if ((other == null)) return false;
        if (!(other instanceof RolHasModuloId)) return false;
        RolHasModuloId castOther = (RolHasModuloId) other;

        return (Objects.equals(this.getIdRol(), castOther.getIdRol()))
                && (Objects.equals(this.getIdModulo(), castOther.getIdModulo()))
                && (Objects.equals(this.getIdPermiso(), castOther.getIdPermiso()));
    }


}


