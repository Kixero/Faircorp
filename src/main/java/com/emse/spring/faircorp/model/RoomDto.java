package com.emse.spring.faircorp.model;

import java.util.List;

public class RoomDto
{
    private Long id;
    private String name;
    private Integer level;
    private List<Light> lights;

    public RoomDto(Room room)
    {
        this.id = room.getId();
        this.name = room.getName();
        this.level = room.getLevel();
        this.lights = room.getLights();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    public List<Light> getLights() {
        return lights;
    }
}
