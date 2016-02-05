create table mole.clinic_locations (
  clinic_location_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY
  , name VARCHAR2(100)

  , created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  , created_by INTEGER
  , modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  , modified_by INTEGER
  , deleted BOOLEAN DEFAULT FALSE
)
;
