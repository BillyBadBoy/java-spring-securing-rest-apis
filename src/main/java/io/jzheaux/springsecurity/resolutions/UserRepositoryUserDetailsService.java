package io.jzheaux.springsecurity.resolutions;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserRepositoryUserDetailsService implements UserDetailsService {

    private final UserRepository users;

    public UserRepositoryUserDetailsService(UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.findByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("no user"));
    }

    private UserAdapter map(User user) {

        Collection<GrantedAuthority> auths = user.getUserAuthorities().stream()
                .flatMap(auth -> ("ROLE_ADMIN".equals(auth.getAuthority())) ?
                        Stream.of(
                                new SimpleGrantedAuthority(auth.getAuthority()),
                                new SimpleGrantedAuthority("resolution:read"),
                                new SimpleGrantedAuthority("resolution:write")) :
                        Stream.of(
                                new SimpleGrantedAuthority(auth.getAuthority())))
                .collect(Collectors.toSet());

        return new UserAdapter(user, auths);
    }

    private static class UserAdapter extends User implements UserDetails {

        private final Collection<GrantedAuthority> authorities;

        public UserAdapter(User user, Collection<GrantedAuthority> authorities) {
            super(user);
            this.authorities = authorities;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.unmodifiableCollection(authorities);
        }

        @Override
        public boolean isAccountNonExpired() {
            return isEnabled();
        }

        @Override
        public boolean isAccountNonLocked() {
            return isEnabled();
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return isEnabled();
        }
    }
}
