CREATE EXTERNAL TABLE presto_poc.INSTRUMENTS (
  INSTRUMENT_ID INT,
  SRC_INSTRUMENT VARCHAR(10)
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
LOCATION 's3://presto-poc-txns-instruments/instruments/';