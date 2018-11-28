package com.emse.spring.faircorp.model;

import javax.persistence.*;

@Entity
@Table(name = "BUILDING")
public class Building
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    public Building()
    {

    }

    public Building(String name)
    {
        this.name = name;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}
