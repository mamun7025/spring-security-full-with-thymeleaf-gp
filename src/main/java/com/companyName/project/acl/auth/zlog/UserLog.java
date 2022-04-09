package com.companyName.project.acl.auth.zlog;


import com.companyName.project.acl.auth.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "LOG_ACL_USER")
public class UserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;    // userId

    @Size(max = 15)
    @NotNull
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
    String displayName;          // marge firstName and lastName
    String phoneNumber;          // as username // maximum length of 15 digits
    String email;                // [user]@[mysite].com = 64 + 255, but it should be 254
    String gender;               // Optional
    // securityCode ------------ 4 digit code sent to mobile and need put this input box

    String deviceType;
    String deviceToken;
    Boolean activeOnline;


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

    public UserLog(){
    }

    public UserLog(User entityInst, String action){
        this.username = entityInst.getUsername();
        this.password = entityInst.getPassword();
        this.displayName = entityInst.getDisplayName();
        this.phoneNumber = entityInst.getPhoneNumber();
    }

}
