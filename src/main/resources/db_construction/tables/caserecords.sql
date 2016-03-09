create table mole.case_records (
  case_record_id BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY
  , case_id BIGINT
  , record_type_id BIGINT
  , record_status_id BIGINT
  , filepath VARCHAR(500)

  , created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , created_by BIGINT NOT NULL
  , modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , modified_by BIGINT NOT NULL
  , deleted BOOLEAN DEFAULT FALSE NOT NULL
)
;
