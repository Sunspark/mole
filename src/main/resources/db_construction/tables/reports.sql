create table mole.reports (
  report_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY
  , case_id INTEGER
  , report_type_id INTEGER
  , complete_date DATE
  , user_id INTEGER
  , due_date DATE
  , sent_date DATE
  , filepath VARCHAR2(500)

  , created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , created_by INTEGER NOT NULL
  , modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  , modified_by INTEGER NOT NULL
  , deleted BOOLEAN DEFAULT FALSE NOT NULL
)
;
