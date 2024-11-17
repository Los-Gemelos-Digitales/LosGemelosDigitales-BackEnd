package com.example.twinguard.User.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "teachers")
public class Teacher extends User{

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "phone", nullable = true)
    private String phone;


}
