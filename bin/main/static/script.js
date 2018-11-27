const apiURL = 'http://localhost:8080/api/'

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
        json.forEach(building => {
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

            buildingsTab.appendChild(row)
        });
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
        json.forEach(room => {
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

            roomsTab.appendChild(row)
        });
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
        json.forEach(light => {
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

            row.appendChild(id)
            row.appendChild(level)
            row.appendChild(roomId)
            row.appendChild(status)
            //row.onclick = switchLight(light.id);

            lightsTab.appendChild(row)
        });
    }
    request.send()
}

function getAll()
{
    getBuildings()
    getRooms()
    getLights()
}

function switchLight(id)
{
    let request = new XMLHttpRequest();

    request.open('PUT', apiURL + id + '/switch')
    request.send()
    getLights()
}

getAll()