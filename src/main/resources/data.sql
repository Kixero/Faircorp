INSERT INTO BUILDING(ID, NAME) VALUES(0, 'EF');
INSERT INTO BUILDING(ID, NAME) VALUES(1, '158');

INSERT INTO ROOM(ID, NAME, LEVEL, BUILDING_ID) VALUES(-10, 'Room1', 1, 0);
INSERT INTO ROOM(ID, NAME, LEVEL, BUILDING_ID) VALUES(-9, 'Room2', 1, 0);

INSERT INTO LIGHT(ID, LEVEL, STATUS, ROOM_ID) VALUES (-1, 8, 'ON', -10);
INSERT INTO LIGHT(ID, LEVEL, STATUS, ROOM_ID) VALUES (-2, 0, 'OFF', -10);

