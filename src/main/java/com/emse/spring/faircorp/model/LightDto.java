package com.emse.spring.faircorp.model;

public class LightDto {

    private final Long id;
    private final Integer level;
    private final Status status;

    public LightDto(Light light)
    {
        this.id = light.getId();
        this.level = light.getLevel();
        this.status = light.getStatus();
    }

    public Long getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public Status getStatus() {
        return status;
    }

    public long getRoomId()
    {
        return 1; //TODO
    }
}