--execute via presto-cli
CREATE SCHEMA hive.presto_poc WITH (location = 's3://presto-poc-txns-instruments/');