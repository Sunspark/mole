create table mole.record_types (
  record_type_id BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY
  , code VARCHAR(4)
  , long_name VARCHAR(100)

  , created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , created_by BIGINT NOT NULL
  , modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , modified_by BIGINT NOT NULL
  , deleted BOOLEAN DEFAULT FALSE NOT NULL
)
;
