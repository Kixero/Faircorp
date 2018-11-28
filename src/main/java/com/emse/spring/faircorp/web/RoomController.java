package com.emse.spring.faircorp.web;

import com.emse.spring.faircorp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/rooms")
@Transactional
public class RoomController
{
    @Autowired
    private RoomDao roomDao;

    @Autowired
    private LightDao lightDao;

    @GetMapping
    public List<RoomDto> findAll()
    {
        return roomDao.findAll()
                .stream()
                .map(RoomDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto)
    {
        Room room = null;
        if (dto.getId() != null)
        {
            room = roomDao.findById(dto.getId()).orElse(null);
        }

        if (room == null)
        {
            room = roomDao.save(new Room(dto.getName(), dto.getLevel(), dto.getBuildingId()));
        }
        else
        {
            room.setLevel(dto.getLevel());
            room.setName(dto.getName());
            room.setBuildingId(dto.getBuildingId());
            roomDao.save(room);
        }

        return new RoomDto(room);
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById( @PathVariable Long id)
    {
        return roomDao.findById(id).map(RoomDto::new).orElse(null);
    }

    @GetMapping(path = "/{id}/lights")
    public List<LightDto> findAllLights(@PathVariable Long id)
    {
        ArrayList<Light> list = new ArrayList<Light>();

        for (Light light : lightDao.findAll())
        {
            if (light.getRoom() == id)
            {
                list.add(light);
            }
        }

        return list.stream()
                   .map(LightDto::new)
                   .collect(Collectors.toList());
    }

    @PutMapping(path = "/{id}/switchLights")
    public RoomDto switchStatus(@PathVariable Long id)
    {
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);

        for (Light light : lightDao.findAll())
        {
            if (light.getRoom() == id)
            {
                light.setStatus(light.getStatus() == Status.ON ? Status.OFF: Status.ON);
            }
        }
        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id)
    {
        for (Light light : lightDao.findAll())
        {
            if (light.getRoom() == id)
            {
                lightDao.delete(light);
            }
        }
        roomDao.deleteById(id);
    }
}
