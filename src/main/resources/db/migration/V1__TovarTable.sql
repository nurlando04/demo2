CREATE TABLE tovar (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    weight int,
    len int,
    width int,
    height int,
    presence bool,
    pathtoimage VARCHAR(100)
);