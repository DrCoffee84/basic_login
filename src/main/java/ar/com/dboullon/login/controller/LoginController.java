package ar.com.dboullon.login.controller;

import ar.com.dboullon.login.DTO.LoginRequestDTO;
import ar.com.dboullon.login.DTO.LoginResponseDTO;
import ar.com.dboullon.login.config.JwtTokenUtil;
import ar.com.dboullon.login.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService usuarioServicie;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody() LoginRequestDTO request) {


        Usuario userDetails;

        try {

            // Obtener usuario
            userDetails = (Usuario) usuarioServicie.loadUserByUsername(request.getUsername());

            // Validar contraseña
            authenticate(request);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String jwt = jwtTokenUtil.generateToken(userDetails);

        // armar respuesta
        return ResponseEntity.ok(new LoginResponseDTO(
                jwt,
                userDetails.getUsername()
        ));
    }

    private void authenticate(LoginRequestDTO request) throws Exception {
        String username = request.getUsername();
        String password = request.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
