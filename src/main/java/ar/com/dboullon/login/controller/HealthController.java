package ar.com.dboullon.login.controller;

import ar.com.dboullon.login.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    TestRepository testRepository;

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> health() {
        try{
            String version = testRepository.test();
            return ResponseEntity
                    .ok("{\"status\": \"Healthy\"," +
                    "\"mysql_version\":\"" + version + "\"}");

        }  catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"Unhealthy\"," +
                    "\"description\":\"" + e.getMessage() + "\"}");
        }

    }
}
