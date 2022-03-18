package ar.com.dboullon.login.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "permiso", catalog = "dboullon")
public class Permiso implements java.io.Serializable {

    @Id
    @Column(name = "id_permiso", unique = true, nullable = false)
    private Long idPermiso;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "permiso")
    @ToString.Exclude
    private Set<RolHasModulo> rolHasModulos  /*Hash?*/;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Permiso permiso = (Permiso) o;
        return idPermiso != null && Objects.equals(idPermiso, permiso.idPermiso);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


