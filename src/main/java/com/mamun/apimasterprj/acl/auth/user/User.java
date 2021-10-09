package com.mamun.apimasterprj.acl.auth.user;

import com.mamun.apimasterprj.acl.auth.role.Role;
import org.springframework.beans.factory.annotation.Value;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "AUTH_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Size(max = 15)
    @NotEmpty
    @NotBlank(message = "*Name is mandatory")
    @Column(name = "USERNAME", length = 15, nullable = false)
    private String username;
    @NotBlank(message = "*Password is mandatory")
    @Column(name = "PASSWORD")
    private String password;
    @Value("${true}")
    @Column(columnDefinition = "boolean default true")
    private boolean enabled = true;
    private boolean accountExpired;
    boolean accountLocked;
    boolean passwordExpired;

    // added attributes
    String firstName;
    String lastName;
    String displayName;          // marge firstName and lastName
    String phoneNumber;          // as username // maximum length of 15 digits

    String email;                // [user]@[mysite].com = 64 + 255, but it should be 254
    String gender;               // Optional
    // securityCode ------------ 4 digit code sent to mobile and need put this input box

    String deviceType;
    String deviceToken;
    Boolean activeOnline;

    @Column(name = "PROFILE_PIC_PATH", length = 300)
    String profilePicPath;

    //    private List<Role> roles;
    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "auth_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


}
