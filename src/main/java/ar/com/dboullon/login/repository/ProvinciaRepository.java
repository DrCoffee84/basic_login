package ar.com.dboullon.login.repository;

import ar.com.dboullon.login.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

}