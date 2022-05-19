CREATE TABLE building (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50),
  address VARCHAR(50),
  index VARCHAR(20),
  sector_code VARCHAR(50),
  energy_units INT,
  energy_unit_max INT
);

CREATE TABLE human (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50),
  livesin VARCHAR(50),
  gender VARCHAR(20),
  age INT
);

INSERT INTO building (id, name, address, index, sector_code, energy_units, energy_unit_max) VALUES
  (1, 'Big Building', 'Highway 420', 'NO600', 'O1', 400, 500),
  (2, 'Small Building', 'Highway 1200', 'NO90000', 'O1', 20, 100);

INSERT INTO human (id, name, livesin, gender, age) VALUES
  (1, 'Bobby Barkers', 'Big Building', 'Male', 26),
  (2, 'Jessica Stone', 'Small Building', 'Female', 22);