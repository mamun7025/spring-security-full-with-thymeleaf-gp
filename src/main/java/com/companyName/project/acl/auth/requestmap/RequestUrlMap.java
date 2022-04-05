package com.companyName.project.acl.auth.requestmap;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ACL_REQUEST_URL_MAP")
public class RequestUrlMap  implements Serializable {

    private static final long serialVersionUID = 1;

    // "request_url_seq" is Oracle sequence name.
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACL_REQUEST_URLMAP_SEQ")
//    @SequenceGenerator(sequenceName = "ACL_REQUEST_URLMAP_SEQ", allocationSize = 1, name = "ACL_REQUEST_URLMAP_SEQ")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String url;
    String configAttribute;
    HttpMethod httpMethod;

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

    public RequestUrlMap(){
    }

    public RequestUrlMap(String url, String configAttribute, HttpMethod httpMethod, Date creationDateTime, String creationUser) {
        this.url = url;
        this.configAttribute = configAttribute;
        this.httpMethod = httpMethod;
        this.creationDateTime = creationDateTime;
        this.creationUser = creationUser;
    }


}
