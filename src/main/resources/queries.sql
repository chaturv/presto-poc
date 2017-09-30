--presto-cli
--use hive.presto_poc;
select * from hive.presto_poc.orders limit 10;
select * from hive.presto_poc.orders where trade_dt = date '2017-09-01' limit 10;
select * from hive.presto_poc.instruments limit 10;
select count(*) from hive.presto_poc.orders;
select * from hive.presto_poc.orders where tran_id=1099981;


select trader, avg(qty) as avg_qty from hive.presto_poc.orders group by trader;
select trader, max(price) as max_price from hive.presto_poc.orders group by trader;
select trader, min(qty) as min_qty, max(qty) as max_qty, stddev_pop(qty) as stddev_qty from hive.presto_poc.orders group by trader;
select histogram(trader) from hive.presto_poc.orders;

select * from hive.presto_poc.orders where trade_dt = date '2017-08-27' limit 5;
select o.*, i.src_instrument from hive.presto_poc.orders o inner join hive.presto_poc.instruments i on (o.instrument_id = i.instrument_id) where o.trade_dt = date '2017-08-27' limit 5;
select o.trader, min(o.qty) as min_qty, max(o.qty) as max_qty, stddev_pop(o.qty) as stddev_qty from hive.presto_poc.orders o inner join hive.presto_poc.instruments i on (o.instrument_id = i.instrument_id) where o.trade_dt = date '2017-08-27' and i.src_instrument='GOOG' group by trader;
