package novares.uz.config.security;

import lombok.RequiredArgsConstructor;
import novares.uz.domain.auth.Role;
import novares.uz.domain.auth.User;
import novares.uz.repository.auth.RoleRepository;
import novares.uz.repository.auth.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String userIdOrEmail) throws UsernameNotFoundException {
        Optional<User> optional;
        try {
            // Try to find by ID (for JWT)
            optional = userRepository.findByIdAndDeletedFalse(Long.parseLong(userIdOrEmail));
        } catch (NumberFormatException e) {
            // If not an ID, try by email (for OAuth2)
            optional = userRepository.findByUsernameAndActiveTrueAndDeletedFalse(userIdOrEmail);
        }
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = optional.get();
        Set<Role> roleList = roleRepository.findRoleListByUserId(user.getId());
        return new CustomUserDetails(user, roleList);
    }
}
