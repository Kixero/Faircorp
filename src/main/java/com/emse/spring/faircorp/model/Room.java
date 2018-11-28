package com.emse.spring.faircorp.model;

import javax.persistence.*;

@Entity
@Table(name = "ROOM")
public class Room
{
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Integer level;
    
    @Column(nullable = false)
    private Long buildingId;
    
    public Room()
    {
    
    }
    
    public Room(String name, int level, Long buildingId)
    {
        this.name = name;
        this.level = level;
        this.buildingId = buildingId;
    }
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public void setLevel(int level)
    {
        this.level = level;
    }

    public Long getBuildingId()
    {
        return buildingId;
    }

    public void setBuildingId(Long buildingId)
    {
        this.buildingId = buildingId;
    }
}
