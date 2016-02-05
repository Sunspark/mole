create table mole.comments (
  comment_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1) PRIMARY KEY
  , case_id INTEGER
  , comment_text CLOB

  , created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  , created_by INTEGER
  , modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  , modified_by INTEGER
  , deleted BOOLEAN DEFAULT FALSE
)
;
