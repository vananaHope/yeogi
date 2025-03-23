package com.vanana.base.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TermsAndCondition {
    @Id
    @GeneratedValue
    private Long id;
}
