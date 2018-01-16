package com.chaturv.hadoop.mapr.aggr;

import com.chaturv.domain.SumCountContainer;
import com.chaturv.domain.TraderInstrumentKey;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class AggrMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf, "Trades Qty Aggregation");
        job.setJarByClass(AggrMain.class);

        job.setMapperClass(TradesQuantityMapper.class);
        job.setCombinerClass(TradesQuantitySumReducer.class);
        job.setReducerClass(TradesQuantityAvgReducer.class);

        job.setOutputKeyClass(TraderInstrumentKey.class);
        job.setOutputValueClass(SumCountContainer.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
