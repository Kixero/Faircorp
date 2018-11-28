package com.emse.spring.faircorp.model;

public class RoomDto
{
    private Long id;
    private String name;
    private Integer level;
    private Long buildingId;

    public RoomDto(Room room)
    {
        this.id = room.getId();
        this.name = room.getName();
        this.level = room.getLevel();
        this.buildingId = room.getBuildingId();
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

    public Long getBuildingId()
    {
        return buildingId;
    }
}
