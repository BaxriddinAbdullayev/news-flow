package novares.uz.domain.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.Auditable;
import novares.uz.enums.GeneralStatus;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "auth_users")
public class User extends Auditable<Long> {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private GeneralStatus status;

    @Column(name = "is_active")
    private Boolean active = Boolean.TRUE;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "auth_users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;
}
