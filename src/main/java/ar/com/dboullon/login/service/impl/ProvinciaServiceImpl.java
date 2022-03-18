package ar.com.dboullon.login.service.impl;

import ar.com.dboullon.login.repository.ProvinciaRepository;
import ar.com.dboullon.login.service.ProvinciaService;
import ar.com.dboullon.login.model.Provincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {

    @Autowired
    ProvinciaRepository provinciaRepository;


    public Page<Provincia> loadAll(Pageable pageable) {

        Page<Provincia> provincias = provinciaRepository.findAll(pageable);

        return provincias;
    }
}