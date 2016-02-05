create table mole.clinic_slots (
  clinic_slot_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY
  , clinic_id INTEGER
  , date_time TIMESTAMP
  , agency_id INTEGER
  , clinic_slot_outcome_id INTEGER
  , claimant_id INTEGER

  , created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  , created_by INTEGER
  , modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  , modified_by INTEGER
  , deleted BOOLEAN DEFAULT FALSE
)
;
