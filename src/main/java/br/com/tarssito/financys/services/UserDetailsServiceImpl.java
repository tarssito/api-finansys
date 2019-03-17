package br.com.tarssito.financys.services;

import br.com.tarssito.financys.domain.User;
import br.com.tarssito.financys.jwt.UserDetailsImpl;
import br.com.tarssito.financys.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * É utilizado para buscar o usuário pelo login para autenticação.
 *
 * @author Tarssito
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        return new UserDetailsImpl(user.getId(), user.getLogin(), user.getPassword(), user.getProfiles());
    }
}
