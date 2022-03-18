package ar.com.dboullon.login.controller;

import ar.com.dboullon.login.model.Provincia;
import ar.com.dboullon.login.service.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provincia")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping()
    public ResponseEntity<Page<Provincia>> getProvincias(Pageable page) {

        Page<Provincia> provincias = provinciaService.loadAll(page);

        return ResponseEntity.ok(provincias);
    }

}