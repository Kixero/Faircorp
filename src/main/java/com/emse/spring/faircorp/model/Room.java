package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.List;

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
    
    @OneToMany
    private List<Light> lights;
    
    public Room()
    {
    
    }
    
    public Room(String name, int level)
    {
        this.name = name;
        this.level = level;
    }
    
    public long getId()
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

    public List<Light> getLights()
    {
        return lights;
    }

    public void setLights(List<Light> lights)
    {
        this.lights = lights;
    }

    public void addLight(Light light)
    {
        lights.add(light);
    }
}
