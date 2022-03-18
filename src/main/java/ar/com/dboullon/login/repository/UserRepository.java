package ar.com.dboullon.login.repository;

import java.util.Optional;

import ar.com.dboullon.login.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {


    Optional<Usuario> findByUsername(String username);
}