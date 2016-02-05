create table mole.users (
  user_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 101) PRIMARY KEY
  , first_name VARCHAR(50)
  , last_name VARCHAR(50)
  , email VARCHAR(200)
  , password CHAR(40)
  , power TINYINT -- between 0 and 9
  , created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  , created_by INTEGER
  , modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  , modified_by INTEGER
  , deleted BOOLEAN DEFAULT FALSE
)
;