package com.companyName.project.acl.auth.role;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@Entity
@Table(name = "ACL_ROLE")
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



    public Role(){
    }
    public Role(String authority, String description) {
        this.authority = authority;
        this.description = description;
    }



    @PrePersist
    private void onPreInsert() {

        this.creationDateTime = new Date();
        if(this.creationUser == null){
            Authentication authenticationContext = SecurityContextHolder.getContext().getAuthentication();
            if ( authenticationContext != null ) {
                this.creationUser = authenticationContext.getName();
            }
        }

    }

    @PreUpdate
    private void onPreUpdate() {
        this.lastUpdateDateTime = new Date();
        if(this.lastUpdateUser == null){
            Authentication authenticationContext = SecurityContextHolder.getContext().getAuthentication();
            if ( authenticationContext != null ) {
                this.lastUpdateUser = authenticationContext.getName();
            }
        }
    }

    @PreRemove
    private void onPreDelete() {
        //
    }


}
