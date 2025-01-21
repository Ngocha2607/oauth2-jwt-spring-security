package com.springboot.eCommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Role {
    @Id
    private String name;
    private String description;

    @ManyToMany
    Set<Permission> permissions;
}
