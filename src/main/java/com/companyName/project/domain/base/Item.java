package com.companyName.project.domain.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BAS_ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE", unique = true, nullable = false)
    String code;
    @Column(name = "DESCRIPTION", nullable = false)
    String description;

    @Column(name = "SELLING_PRICE")
    String sellingPrice;

    // System Log
    @Column(name = "CREATION_USER")
    String creationUser;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "CREATION_DATETIME")
    Date creationDateTime;

    @Column(name = "LAST_UPDATE_USER")
    String lastUpdateUser;
    @UpdateTimestamp
    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "LAST_UPDATE_DATETIME")
    Date lastUpdateDateTime;


    public Item(){
    }

    public Item(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Item(String code, String description, String creationUser, Date creationDateTime, String lastUpdateUser, Date lastUpdateDateTime) {
        this.code = code;
        this.description = description;
        this.creationUser = creationUser;
        this.creationDateTime = creationDateTime;
        this.lastUpdateUser = lastUpdateUser;
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(Date lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    @Override
    public String toString() {
        return "Item{id=" + id + ", code=" + code + "}";
    }


}
