create table mole.clinic_slots (
  clinic_slot_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY
  , clinic_id INTEGER
  , date_time TIMESTAMP
  , agency_id INTEGER
  , clinic_slot_outcome_id INTEGER
  , claimant_id INTEGER

  , created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , created_by INTEGER NOT NULL
  , modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , modified_by INTEGER NOT NULL
  , deleted BOOLEAN DEFAULT FALSE NOT NULL
)
;
