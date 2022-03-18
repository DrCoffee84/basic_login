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
@Table(name = "telefono", catalog = "dboullon")
public class Telefono implements java.io.Serializable {

    @Id
    @Column(name = "id_telefono", unique = true, nullable = false)
    private Long idTelefono;

    @Column(name = "numero", length = 45)
    private String numero;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "telefono_has_persona", catalog = "dboullon", joinColumns = {
            @JoinColumn(name = "id_telefono", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "id_persona", nullable = false, updatable = false)})
    @ToString.Exclude
    private Set<Persona> personas  /*Hash?*/;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Telefono telefono = (Telefono) o;
        return idTelefono != null && Objects.equals(idTelefono, telefono.idTelefono);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


