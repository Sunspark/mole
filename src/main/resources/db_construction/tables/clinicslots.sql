create table mole.clinic_slots (
  clinic_slot_id BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY
  , clinic_id BIGINT
  , datetime TIMESTAMP
  , agency_id BIGINT
  , clinic_slot_outcome_id BIGINT
  , claimant_id BIGINT

  , created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , created_by BIGINT NOT NULL
  , modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , modified_by BIGINT NOT NULL
  , deleted BOOLEAN DEFAULT FALSE NOT NULL
)
;
