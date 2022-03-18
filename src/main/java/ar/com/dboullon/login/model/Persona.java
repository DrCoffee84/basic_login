package ar.com.dboullon.login.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "persona", catalog = "dboullon")
public class Persona implements java.io.Serializable {

    @Id
    @Column(name = "id_persona", unique = true, nullable = false)
    private Long idPersona;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "apellido", length = 45)
    private String apellido;

    @Column(name = "mail", length = 45)
    private String mail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "persona")
    @ToString.Exclude
    private Set<Usuario> usuarios;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "persona_has_provincia", catalog = "dboullon",
            joinColumns = @JoinColumn(name = "id_persona"),
            inverseJoinColumns = @JoinColumn(name = "id_provincia"))
    @ToString.Exclude
    private Set<Provincia> provincias  /*Hash?*/;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "personas")
    @ToString.Exclude
    private Set<Telefono> telefonos  /*Hash?*/;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Persona persona = (Persona) o;
        return idPersona != null && Objects.equals(idPersona, persona.idPersona);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
