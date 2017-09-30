create external table presto_poc.orders (
  tran_id int,
  business_dt date,
  event_dt date,
  trader varchar(10),
  price decimal(16, 6),
  qty decimal(10, 0),
  instrument_id int
)
partitioned by(trade_dt date)
row format delimited
fields terminated by ','
lines terminated by '\n'
location 's3://presto-poc-txns-instruments/orders/csv';

alter table presto_poc.orders add partition (trade_dt='2017-09-01');