package ar.com.dboullon.login.model;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "rol_has_modulo", catalog = "dboullon")
public class RolHasModulo implements java.io.Serializable {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "idRol", column = @Column(name = "id_rol", nullable = false)),
            @AttributeOverride(name = "idModulo", column = @Column(name = "id_modulo", nullable = false)),
            @AttributeOverride(name = "idPermiso", column = @Column(name = "id_permiso", nullable = false))})
    private RolHasModuloId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modulo", nullable = false, insertable = false, updatable = false)
    @ToString.Exclude
    private Modulo modulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permiso", nullable = false, insertable = false, updatable = false)
    @ToString.Exclude
    private Permiso permiso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false, insertable = false, updatable = false)
    @ToString.Exclude
    private Rol rol;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RolHasModulo that = (RolHasModulo) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


