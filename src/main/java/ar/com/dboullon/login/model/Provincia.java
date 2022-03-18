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
@Table(name = "provincia", catalog = "dboullon")
public class Provincia implements java.io.Serializable {

    @Id
    @Column(name = "id_provincia", unique = true, nullable = false)
    private Long idProvincia;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Provincia provincia = (Provincia) o;
        return idProvincia != null && Objects.equals(idProvincia, provincia.idProvincia);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


