package com.chaturv.hadoop.mapr.aggr;

import com.chaturv.domain.SumCountContainer;
import com.chaturv.domain.TraderInstrumentKey;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TradesQuantityAvgReducer extends Reducer<TraderInstrumentKey, SumCountContainer,
        TraderInstrumentKey, DoubleWritable> {

    @Override
    protected void reduce(TraderInstrumentKey key, Iterable<SumCountContainer> values,
                          Context context) throws IOException, InterruptedException {

        Double sum = 0.0D;
        Integer count = 0;

        for (SumCountContainer value : values) {
            sum += value.getSum();
            count += value.getCount();
        }

        Double avg = sum / count;
        context.write(key, new DoubleWritable(avg));
    }
}
