package authorisation_service.security;

import authorisation_service.user.ApplicationUser;
import authorisation_service.user.ApplicationUserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        System.out.println(applicationUser.toString());
        if (applicationUser != null){
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_" + applicationUser.getRole());
        System.out.println(applicationUser);
        return new DepartmentUser(applicationUser.getUsername(), applicationUser.getPassword(), grantedAuthorities, applicationUser.getDepartmentId());
        }
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }

}
