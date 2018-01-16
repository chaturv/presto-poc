package com.chaturv.hadoop.mapr.aggr;

import com.chaturv.domain.SumCountContainer;
import com.chaturv.domain.TraderInstrumentKey;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TradesQuantityMapper extends Mapper<LongWritable, Text, TraderInstrumentKey, SumCountContainer> {


    @Override
    public void map(LongWritable lineNo, Text text, Context context) throws IOException, InterruptedException {
        String line = text.toString();
        if (line.startsWith("#")) {
            return;
        }

        String[] tokens = line.split(",");

        String traderName = tokens[3];
        String instrument = tokens[6];
        Double quantity = Double.valueOf(tokens[5]);

        TraderInstrumentKey keyOut = new TraderInstrumentKey();
        keyOut.setTraderName(traderName);
        keyOut.setInstrument(instrument);

        SumCountContainer sumCount = new SumCountContainer();
        sumCount.setSum(quantity);
        sumCount.setCount(1);

        context.write(keyOut, sumCount);
    }
}
