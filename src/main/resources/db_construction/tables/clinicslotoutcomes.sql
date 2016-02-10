create table mole.clinic_slot_outcomes (
  clinic_slot_outcome_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY
  , code CHAR(4)
  , long_name VARCHAR2(100)

  , created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , created_by INTEGER NOT NULL
  , modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , modified_by INTEGER NOT NULL
  , deleted BOOLEAN DEFAULT FALSE NOT NULL
)
;
