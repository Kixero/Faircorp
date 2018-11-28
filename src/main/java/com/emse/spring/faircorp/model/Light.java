package com.emse.spring.faircorp.model;

import javax.persistence.*;

@Entity
@Table(name = "LIGHT")
public class Light {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false)
    private Long roomId;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    
    public Light() {
    }
    
    public Light(Room room, Integer level, Status status) {
        this.level = level;
        this.status = status;
        this.roomId = room.getId();
        room.addLight(this);
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getLevel() {
        return level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getRoom() {
        return this.roomId;
    }

    public void setRoom(Long room) {
        this.roomId = room;
    }
}