package ar.com.dboullon.login.service.impl;

import ar.com.dboullon.login.model.Usuario;
import ar.com.dboullon.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {

        //Buscar el usuario con el repositorio y si no existe lanzar una exepcion
        Usuario appUser =
                userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));


        return appUser;
    }
}