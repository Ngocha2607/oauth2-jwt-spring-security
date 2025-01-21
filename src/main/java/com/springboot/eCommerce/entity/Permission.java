package com.springboot.eCommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Permission {

    @Id
    private String name;
    private String description;

    public Permission() {
    }
    public Permission(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
