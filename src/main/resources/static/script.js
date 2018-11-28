const apiURL = 'http://localhost:8080/api/'

function wait(ms)
{
    var d = new Date();
    var d2 = null;
    do { d2 = new Date(); }
    while(d2-d < ms);
}

function createLightRow(light)
{
    let row = document.createElement('tr')
    row.setAttribute('id', light.id)

    let id = document.createElement('td')
    id.textContent = light.id

    let level = document.createElement('td')
    level.textContent = light.level

    let roomId = document.createElement('td')
    roomId.textContent = light.roomId

    let status = document.createElement('td')
    status.textContent = light.status

    let onoff = document.createElement('td')
    let button = document.createElement('button')
    button.textContent = 'ON - OFF'
    button.setAttribute('onclick', 'switchLight(' + light.id + ')')
    onoff.appendChild(button)

    row.appendChild(id)
    row.appendChild(level)
    row.appendChild(roomId)
    row.appendChild(status)
    row.appendChild(onoff)

    return row
}

function createRoomRow(room)
{
    let row = document.createElement('tr')
    row.setAttribute('id', room.id)

    let id = document.createElement('td')
    id.textContent = room.id

    let name = document.createElement('td')
    name.textContent = room.name

    let level = document.createElement('td')
    level.textContent = room.level

    let lights = document.createElement('td')
    lights.textContent = room.lights.length

    row.appendChild(id)
    row.appendChild(name)
    row.appendChild(level)
    row.appendChild(lights)

    row.setAttribute('onclick', 'filterLights(' + room.id + ')')

    return row
}

function createBuildingRow(building)
{
    let row = document.createElement('tr')
    row.setAttribute('id', building.id)

    let id = document.createElement('td')
    id.textContent = building.id

    let name = document.createElement('td')
    name.textContent = building.name

    let rooms = document.createElement('td')
    rooms.textContent = building.rooms.length

    row.appendChild(id)
    row.appendChild(name)
    row.appendChild(rooms)

    row.setAttribute('onclick', 'filterRooms(' + building.id + ')')

    return row
}

function getBuildings()
{
    let request = new XMLHttpRequest()
    let buildingsTab = document.getElementById('buildings')

    while(buildingsTab.lastChild.nodeName == 'TR')
    {
        buildingsTab.removeChild(buildingsTab.lastChild)
    }

    request.open('GET', apiURL + 'buildings')
    request.onload = function()
    {
        let json = JSON.parse(this.response)
        json.forEach(building => { buildingsTab.appendChild(createBuildingRow(building)) });
    }
    request.send()
}

function getRooms()
{
    let request = new XMLHttpRequest()
    let roomsTab = document.getElementById('rooms')

    while(roomsTab.lastChild.nodeName == 'TR')
    {
        roomsTab.removeChild(roomsTab.lastChild)
    }

    request.open('GET', apiURL + 'rooms')
    request.onload = function()
    {
        let json = JSON.parse(this.response)
        json.forEach(room => { roomsTab.appendChild(createRoomRow(room)) });
    }
    request.send()
}

function getLights()
{
    let request = new XMLHttpRequest()
    let lightsTab = document.getElementById('lights')

    while(lightsTab.lastChild.nodeName == 'TR')
    {
        lightsTab.removeChild(lightsTab.lastChild)
    }

    request.open('GET', apiURL + 'lights')
    request.onload = function()
    {
        let json = JSON.parse(this.response)
        json.forEach(light => { lightsTab.appendChild(createLightRow(light))});
    }
    request.send()
}

function getAll()
{
    getBuildings()
    getRooms()
    getLights()
}

function refresh(type, id)
{
    let request = new XMLHttpRequest()
    request.open('GET', apiURL + type + 's/' + id)
    request.onload = function()
    {
        let tab = document.getElementById(type + 's')
        let element = tab.rows.namedItem(id)
        let json = JSON.parse(this.response)
        switch (type)
        {
            case 'light':
                tab.insertBefore(createLightRow(json), element)
            break
            case 'room' : 
                tab.insertBefore(createRoomRow(json), element)
            break
            case 'building' : 
                tab.insertBefore(createBuildingRow(json), element)
            break
        }
        tab.removeChild(element)
    }
    request.send()
}

function switchLight(id)
{
    let request = new XMLHttpRequest();

    request.open('PUT', apiURL + 'lights/' + id + '/switch')
    request.send()
    wait(60)
    refresh('light', id)
}

function filterLights(roomId)
{
    let lightsTab = document.getElementById('lights')

    while(lightsTab.lastChild.nodeName == 'TR')
    {
        lightsTab.removeChild(lightsTab.lastChild)
    }

    
}

function filterRooms(buildingId)
{}

getAll()