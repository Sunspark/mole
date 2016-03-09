create table mole.reports (
  report_id BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY
  , case_id BIGINT
  , report_type_id BIGINT
  , complete_date TIMESTAMP
  , user_id BIGINT
  , due_date DATE
  , sent_date TIMESTAMP
  , filepath VARCHAR(500)

  , created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , created_by BIGINT NOT NULL
  , modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , modified_by BIGINT NOT NULL
  , deleted BOOLEAN DEFAULT FALSE NOT NULL
)
;
