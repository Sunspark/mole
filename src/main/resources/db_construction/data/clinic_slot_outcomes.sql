INSERT INTO "MOLE"."CLINIC_SLOT_OUTCOMES" (
  code
  , long_name

  , "CREATED"
  , "CREATED_BY"
  , "MODIFIED"
  , "MODIFIED_BY"
)
VALUES (
  'GOOD'
  , 'Claimant seen'

  , CURRENT_TIMESTAMP
  , 101
  , CURRENT_TIMESTAMP
  , 101
)
;

INSERT INTO "MOLE"."CLINIC_SLOT_OUTCOMES" (
  code
  , long_name

  , "CREATED"
  , "CREATED_BY"
  , "MODIFIED"
  , "MODIFIED_BY"
)
VALUES (
  'NOSW'
  , 'Claimant did not attend'

  , CURRENT_TIMESTAMP
  , 101
  , CURRENT_TIMESTAMP
  , 101
)
;

INSERT INTO "MOLE"."CLINIC_SLOT_OUTCOMES" (
  code
  , long_name

  , "CREATED"
  , "CREATED_BY"
  , "MODIFIED"
  , "MODIFIED_BY"
)
VALUES (
  'CANC'
  , 'Slot was cancelled'

  , CURRENT_TIMESTAMP
  , 101
  , CURRENT_TIMESTAMP
  , 101
)
;
