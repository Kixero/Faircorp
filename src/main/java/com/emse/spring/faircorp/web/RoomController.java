package com.emse.spring.faircorp.web;

import com.emse.spring.faircorp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
            room = roomDao.save(new Room(dto.getName(),dto.getLevel()));
        }
        else
        {
            room.setLevel(dto.getLevel());
            room.setName(dto.getName());
            room.setLights(dto.getLights());
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
        return roomDao.findById(id).get().getLights()
                                         .stream()
                                         .map(LightDto::new)
                                         .collect(Collectors.toList());
    }

    @PutMapping(path = "/{id}/switchLight")
    public RoomDto switchStatus(@PathVariable Long id)
    {
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);

        for (Light light : room.getLights())
        {
            light.setStatus(light.getStatus() == Status.ON ? Status.OFF: Status.ON);
        }
        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id)
    {
        for (Light light : roomDao.findById(id).orElseThrow(IllegalArgumentException::new).getLights())
        {
            lightDao.delete(light);
        }
        roomDao.deleteById(id);
    }
}
