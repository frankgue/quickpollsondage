package com.gkfcsolution.quickpollsondage.security;

import com.gkfcsolution.quickpollsondage.model.User;
import com.gkfcsolution.quickpollsondage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuickPollUserDetailsService implements UserDetailsService {

//    @Autowired //Injection par @Autowired
    private UserRepository userRepository;

    //Favorisez l’injection par constructeur plutôt que @Autowired
    public QuickPollUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'", username));
        }

        //Create a granted authority based on user's role
        //Can't pass null authorities to user. Hence initialize with an empty arrayList
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (user.isAdmin()) {
            grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        }

        // Create a UserDetails object from data
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);

        return userDetails;

    }
}
