INSERT INTO "MOLE"."USERS" (
  "FIRST_NAME"
  , "LAST_NAME"
  , "EMAIL"
  , "PASSWORD"
  , "POWER"
  , "CREATED"
  , "CREATED_BY"
  , "MODIFIED"
  , "MODIFIED_BY"
)
VALUES (
  'William'
  , 'List'
  , 'dev@dev.com'
  , '0879ebe8a5bae60d92444f09dcfa361a2ad5c422cae993dcef4983c1e4635ce5'
  , 9
  , CURRENT_TIMESTAMP
  , 101
  , CURRENT_TIMESTAMP
  , 101
)
;

INSERT INTO "MOLE"."USERS" (
  "FIRST_NAME"
  , "LAST_NAME"
  , "EMAIL"
  , "PASSWORD"
  , "POWER"
  , "CREATED"
  , "CREATED_BY"
  , "MODIFIED"
  , "MODIFIED_BY"
)
VALUES (
  'Mole'
  , 'System'
  , 'dev@mole.com'
  , '0879ebe8a5bae60d92444f09dcfa361a2ad5c422cae993dcef4983c1e4635ce4'
  , 0
  , CURRENT_TIMESTAMP
  , 101
  , CURRENT_TIMESTAMP
  , 101
)
;
