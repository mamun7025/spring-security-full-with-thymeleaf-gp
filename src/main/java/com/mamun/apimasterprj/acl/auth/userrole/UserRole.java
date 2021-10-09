package com.mamun.apimasterprj.acl.auth.userrole;

import com.mamun.apimasterprj.acl.auth.role.Role;
import com.mamun.apimasterprj.acl.auth.user.User;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AUTH_USER_ROLE")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    User user;
    @ManyToOne
    Role role;

    // System log fields
    Date creationDateTime;
    String creationUser;
    Date lastUpdateDateTime;
    String lastUpdateUser;

}
