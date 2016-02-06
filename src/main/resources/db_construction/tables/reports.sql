create table mole.reports (
  report_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY
  , case_id INTEGER
  , report_type_id INTEGER
  , complete_date TIMESTAMP
  , user_id INTEGER
  , due_date TIMESTAMP
  , sent_date TIMESTAMP
  , filepath VARCHAR2(500)

  , created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  , created_by INTEGER
  , modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  , modified_by INTEGER
  , deleted BOOLEAN DEFAULT FALSE
)
;
