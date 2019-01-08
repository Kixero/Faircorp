const apiURL = 'https://faircorp-anthony-meranger.cleverapps.io/api/';

function createLightRow(light)
{
    let row = document.createElement('tr');
    row.setAttribute('id', light.id);

    let id = document.createElement('td');
    id.textContent = light.id;

    let level = document.createElement('td');
    level.textContent = light.level;

    let roomId = document.createElement('td');
    roomId.textContent = light.roomId;

    let status = document.createElement('td');
    status.textContent = light.status;

    let onoff = document.createElement('td');
    let button = document.createElement('button');
    button.textContent = 'ON - OFF';
    button.setAttribute('onclick', 'switchLight(' + light.id + ')');
    onoff.appendChild(button);

    row.appendChild(id);
    row.appendChild(roomId);
    row.appendChild(level);
    row.appendChild(status);
    row.appendChild(onoff);

    return row
}

function createRoomRow(room)
{
    let row = document.createElement('tr');
    row.setAttribute('id', room.id);

    let id = document.createElement('td');
    id.textContent = room.id;

    let name = document.createElement('td');
    name.textContent = room.name;

    let level = document.createElement('td');
    level.textContent = room.level;

    let buildingId = document.createElement('td');
    buildingId.textContent = room.buildingId;

    row.appendChild(id);
    row.appendChild(name);
    row.appendChild(level);
    row.appendChild(buildingId);

    row.setAttribute('onclick', 'filterLights(' + room.id + ')');

    return row
}

function createBuildingRow(building)
{
    let row = document.createElement('tr');
    row.setAttribute('id', building.id);

    let id = document.createElement('td');
    id.textContent = building.id;

    let name = document.createElement('td');
    name.textContent = building.name;

    row.appendChild(id);
    row.appendChild(name);

    row.setAttribute('onclick', 'filterRooms(' + building.id + ')');

    return row
}

function getBuildings()
{
    let request = new XMLHttpRequest();
    let buildingsTab = document.getElementById('buildings');

    request.open('GET', apiURL + 'buildings');
    request.onload = function()
    {
        while(buildingsTab.lastChild.nodeName === 'TR')
        {
            buildingsTab.removeChild(buildingsTab.lastChild)
        }
        let json = JSON.parse(this.response);
        json.forEach(building => { buildingsTab.appendChild(createBuildingRow(building)) });
    };
    request.send()
}

function getRooms()
{
    let request = new XMLHttpRequest();
    let roomsTab = document.getElementById('rooms');

    request.open('GET', apiURL + 'rooms');
    request.onload = function()
    {
        while(roomsTab.lastChild.nodeName === 'TR')
        {
            roomsTab.removeChild(roomsTab.lastChild)
        }
        let json = JSON.parse(this.response);
        json.forEach(room => { roomsTab.appendChild(createRoomRow(room)) });
    };
    request.send()
}

function getLights()
{
    let request = new XMLHttpRequest();
    let lightsTab = document.getElementById('lights');

    request.open('GET', apiURL + 'lights');
    request.onload = function()
    {
        while(lightsTab.lastChild.nodeName === 'TR')
        {
            lightsTab.removeChild(lightsTab.lastChild)
        }
        let json = JSON.parse(this.response);
        json.forEach(light => { lightsTab.appendChild(createLightRow(light))});
    };
    request.send()
}

function getAll()
{
    getBuildings();
    getRooms();
    getLights()
}

function refresh(type, id)
{
    let request = new XMLHttpRequest();
    request.open('GET', apiURL + type + 's/' + id);
    request.onload = function()
    {
        let tab = document.getElementById(type + 's');
        let element = tab.rows.namedItem(id);
        let json = JSON.parse(this.response);
        switch (type)
        {
            case 'light':
                tab.insertBefore(createLightRow(json), element);
            break;
            case 'room' : 
                tab.insertBefore(createRoomRow(json), element);
            break;
            case 'building' : 
                tab.insertBefore(createBuildingRow(json), element);
            break
        }
        tab.removeChild(element)
    };
    request.send()
}

function switchLight(id)
{
    let request = new XMLHttpRequest();

    request.open('PUT', apiURL + 'lights/' + id + '/switch');
    request.onload = function()
    {
        let tab = document.getElementById('lights');
        let element = tab.rows.namedItem(id);
        let light = JSON.parse(this.response);
        tab.insertBefore(createLightRow(light), element);
        tab.removeChild(element);
    };
    request.send();
}

function filterLights(roomId)
{
    let lightsTab = document.getElementById('lights');
    let request = new XMLHttpRequest();

    request.open('GET', apiURL + 'lights');
    request.onload = function()
    {
        while(lightsTab.lastChild.nodeName === 'TR')
        {
            lightsTab.removeChild(lightsTab.lastChild)
        }
        let json = JSON.parse(this.response);
        json.forEach(light => { 
            if (light.roomId === roomId)
            {
                lightsTab.appendChild(createLightRow(light))
            }
        });
    };
    request.send()
}

function filterRooms(buildingId)
{
    let roomsTab = document.getElementById('rooms');
    let request = new XMLHttpRequest();

    request.open('GET', apiURL + 'rooms');
    request.onload = function()
    {
        while(roomsTab.lastChild.nodeName === 'TR')
        {
            roomsTab.removeChild(roomsTab.lastChild)
        }
        let json = JSON.parse(this.response);
        json.forEach(room => { 
            if (room.buildingId === buildingId)
            {
                roomsTab.appendChild(createRoomRow(room));
            }
        });
    };
    request.send()
}

function addBuilding()
{
    let request = new XMLHttpRequest();
    let buildingsTab = document.getElementById('buildings');
    let body = JSON.stringify({
                    "id": -1,
                    "name": document.getElementById('buildingName').value
                });
    request.open('POST', apiURL + 'buildings');
    request.setRequestHeader("Content-Type", "application/json");
    request.onload = function()
    {
        let building = JSON.parse(this.response);
        buildingsTab.appendChild(createBuildingRow(building));
    };
    request.send(body);
    document.getElementById('buildingName').value = '';
}

function addRoom()
{
    let request = new XMLHttpRequest();
    let roomsTab = document.getElementById('rooms');
    let body = JSON.stringify({
        "id": -1,
        "name": document.getElementById('roomName').value,
        "level": document.getElementById('roomLevel').value,
        "buildingId": document.getElementById('roomBuildingId').value
    });
    request.open('POST', apiURL + 'rooms');
    request.setRequestHeader("Content-Type", "application/json");
    request.onload = function()
    {
        let room = JSON.parse(this.response);
        roomsTab.appendChild(createRoomRow(room));
    };
    request.send(body);
    document.getElementById('roomName').value = '';
    document.getElementById('roomLevel').value = '';
    document.getElementById('roomBuildingId').value = '';
}

function addLight()
{
    let request = new XMLHttpRequest();
    let lightsTab = document.getElementById('lights');
    let body = JSON.stringify({
        "id": -1,
        "level": 0,
        "roomId": document.getElementById('lightRoomId').value,
        "status": "OFF"
    });
    request.open('POST', apiURL + 'lights');
    request.setRequestHeader("Content-Type", "application/json");
    request.onload = function()
    {
        let light = JSON.parse(this.response);
        lightsTab.appendChild(createLightRow(light));
    };
    request.send(body);
    document.getElementById('lightRoomId').value = '';
}

getAll();