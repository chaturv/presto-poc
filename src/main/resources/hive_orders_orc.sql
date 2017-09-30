create external table presto_poc.orders_orc (
  tran_id int,
  business_dt date,
  event_dt date,
  trader varchar(10),
  price decimal(16, 6),
  price decimal(10, 0),
  instrument_id int
)
partitioned by(trade_dt date)
stored as orc
location 's3://presto-poc-txns-instruments/orders/orc';

alter table presto_poc.orders_orc add partition (trade_dt='2017-09-01');

--select * adds trade_dt column as the last column!
--Need YARN!
INSERT INTO TABLE presto_poc.orders_orc PARTITION (trade_dt='2017-09-01')
SELECT tran_id, business_dt, event_dt, trader, price, price, instrument_id FROM presto_poc.orders WHERE trade_dt='2017-09-01';


INSERT INTO hive.presto_poc.orders_orc VALUES (100000,date '2017-09-01',date '2017-08-27','Bob',DECIMAL '21.530353', DECIMAL '82243',163,date '2017-09-01');