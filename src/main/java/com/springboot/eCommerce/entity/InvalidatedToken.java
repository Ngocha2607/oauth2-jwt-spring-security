package com.springboot.eCommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvalidatedToken {
    @Id
    String id;
    Date expiryTime;

    public InvalidatedToken() {
    }

    public InvalidatedToken(String id, Date expiryTime) {
        this.id = id;
        this.expiryTime = expiryTime;
    }
}
