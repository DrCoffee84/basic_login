package ar.com.dboullon.login.repository;

import ar.com.dboullon.login.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Usuario, Long> {


    @Query(value = "SELECT version()", nativeQuery = true)
    String test();
}