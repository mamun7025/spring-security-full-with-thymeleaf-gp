package com.mamun.apimasterprj.acl.auth.role;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AUTH_ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    String authority;
    String description;

    // System log fields
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "CREATION_DATETIME")
    Date creationDateTime;
    @Column(name = "CREATION_USER")
    String creationUser;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "LAST_UPDATE_DATETIME")
    Date lastUpdateDateTime;
    @Column(name = "LAST_UPDATE_USER")
    String lastUpdateUser;


}
