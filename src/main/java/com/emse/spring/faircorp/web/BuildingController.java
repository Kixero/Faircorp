package com.emse.spring.faircorp.web;


import com.emse.spring.faircorp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/buildings")
@Transactional
public class BuildingController
{
    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private LightDao lightDao;

    @GetMapping
    public List<BuildingDto> findAll()
    {
        return buildingDao.findAll()
                .stream()
                .map(BuildingDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto dto)
    {
        Building building = null;
        if (dto.getId() != null)
        {
            building = buildingDao.findById(dto.getId()).orElse(null);
        }

        if (building == null)
        {
            building = buildingDao.save(new Building(dto.getName()));
        }
        else
        {
            building.setName(dto.getName());
            building.setRooms(dto.getRooms());
        }

        return new BuildingDto(building);
    }

    @GetMapping(path = "/{id}")
    public BuildingDto findById( @PathVariable Long id) {return buildingDao.findById(id).map(BuildingDto::new).orElse(null);}

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id)
    {
        for (Room room : buildingDao.findById(id).orElseThrow(IllegalArgumentException::new).getRooms())
        {
            for (Light light : roomDao.findById(id).orElseThrow(IllegalArgumentException::new).getLights())
            {
                lightDao.delete(light);
            }
            roomDao.deleteById(id);
        }
        buildingDao.deleteById(id);
    }
}
