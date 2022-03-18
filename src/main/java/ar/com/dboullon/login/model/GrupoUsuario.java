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
@Table(name = "grupo_usuario", catalog = "dboullon")
public class GrupoUsuario implements java.io.Serializable {

    @Id
    @Column(name = "id_grupo_usuario", unique = true, nullable = false)
    private Long idGrupoUsuario;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "grupoUsuarios")
    @ToString.Exclude
    private Set<Rol> rols  /*Hash?*/;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "grupoUsuarios")
    @ToString.Exclude
    private Set<Usuario> usuarios  /*Hash?*/;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GrupoUsuario that = (GrupoUsuario) o;
        return idGrupoUsuario != null && Objects.equals(idGrupoUsuario, that.idGrupoUsuario);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


