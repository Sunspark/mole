create table mole.case_records (
  case_record_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY
  , case_id INTEGER
  , record_type_id INTEGER
  , record_status_id INTEGER
  , filepath VARCHAR2(500)

  , created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , created_by INTEGER NOT NULL
  , modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , modified_by INTEGER NOT NULL
  , deleted BOOLEAN DEFAULT FALSE NOT NULL
)
;
