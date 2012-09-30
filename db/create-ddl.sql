DROP TABLE server_info IF EXISTS;
CREATE TABLE server_info (
  id INTEGER NOT NULL PRIMARY KEY,
  name varchar(255),
  kind varchar(31),
  url varchar(255)
);
CREATE INDEX server_info_name ON server_info(name);
