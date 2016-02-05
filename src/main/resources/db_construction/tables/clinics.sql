create table mole.clinics (
  clinic_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY
  , clinic_date TIMESTAMP
  , clinic_location_id INTEGER

  , created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  , created_by INTEGER
  , modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  , modified_by INTEGER
  , deleted BOOLEAN DEFAULT FALSE
)
;
