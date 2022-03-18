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
@Table(name = "rol", catalog = "dboullon")
public class Rol implements java.io.Serializable {

    @Id
    @Column(name = "id_rol", unique = true, nullable = false)
    private Long idRol;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rol_has_grupo_usuario", catalog = "dboullon", joinColumns = {
            @JoinColumn(name = "id_rol", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "id_grupo_usuario", nullable = false, updatable = false)})
    @ToString.Exclude
    private Set<GrupoUsuario> grupoUsuarios  /*Hash?*/;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rol_has_usuario", catalog = "dboullon", joinColumns = {
            @JoinColumn(name = "id_rol", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "id_usuario", nullable = false, updatable = false)})
    @ToString.Exclude
    private Set<Usuario> usuarios  /*Hash?*/;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
    @ToString.Exclude
    private Set<RolHasModulo> rolHasModulos  /*Hash?*/;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rol rol = (Rol) o;
        return idRol != null && Objects.equals(idRol, rol.idRol);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


