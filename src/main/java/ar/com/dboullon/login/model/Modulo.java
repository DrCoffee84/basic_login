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
@Table(name = "modulo", catalog = "dboullon")
public class Modulo implements java.io.Serializable {

    @Id
    @Column(name = "id_modulo", unique = true, nullable = false)
    private Long idModulo;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "id_denuncia", nullable = false)
    private Long idDenuncia;

    @Column(name = "url_base", length = 45)
    private String urlBase;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "modulo")
    @ToString.Exclude
    private Set<RolHasModulo> rolHasModulos  /*Hash?*/;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Modulo modulo = (Modulo) o;
        return idModulo != null && Objects.equals(idModulo, modulo.idModulo);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


