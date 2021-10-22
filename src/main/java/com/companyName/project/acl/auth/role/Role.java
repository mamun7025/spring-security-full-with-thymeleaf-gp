package com.companyName.project.acl.auth.role;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.persistence.*;
import java.util.Date;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public Date getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(Date lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }



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
