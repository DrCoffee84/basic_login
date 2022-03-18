package ar.com.dboullon.login.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "usuario" , catalog = "dboullon")
public class Usuario implements Serializable, UserDetails {

    @Id
    @Column(name = "id_usuario", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false)
    @ToString.Exclude
    private Persona persona;

    @Column(name = "nombre_usuario", nullable = false, length = 45)
    private String username;

    @Column(name = "password", nullable = false, length = 500)
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "usuarios")
    @ToString.Exclude
    private Set<Rol> rols  /*Hash?*/;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_has_grupo_usuario", catalog = "dboullon", joinColumns = {
            @JoinColumn(name = "id_usuario", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "id_grupo_usuario", nullable = false, updatable = false)})
    @ToString.Exclude
    private Set<GrupoUsuario> grupoUsuarios  /*Hash?*/;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario != null && Objects.equals(idUsuario, usuario.idUsuario);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // user details
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    // Cambiar el por atributos de tabla  si hace falta.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


