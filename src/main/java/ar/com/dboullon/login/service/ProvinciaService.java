package ar.com.dboullon.login.service;

import ar.com.dboullon.login.model.Provincia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProvinciaService {

    Page<Provincia> loadAll(Pageable pageable);
}