package com.chaturv.filegen;

import com.chaturv.filegen.generators.*;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class GenerateCsvFile {

    private static final String TXN_FILE_NAME = "orders.csv";
    private static final String INSTR_FILE_NAME = "instruments.csv";

    private static final List<String> TRADERS = Arrays.asList("Bob", "Alice", "John", "Sam", "Cobra");
    private static final List<String> INSTRUMENTS = Arrays.asList("IBM", "GOOG", "CITI", "JPM", "LG", "SAMS");

    private Map<String, ValueGenerator> txnColsToValueGens = new LinkedHashMap<String, ValueGenerator>();
    private Map<String, ValueGenerator> instrColsToValueGens = new LinkedHashMap<String, ValueGenerator>();

    private int noOfDays;
    private int rowsPerDay;
    private LocalDate startDate; //yyyy-mm-dd
    private String outputDir;


    private GenerateCsvFile(int noOfDays, int rowsPerDay, LocalDate startDate, String outputDir) {
        this.noOfDays = noOfDays;
        this.rowsPerDay = rowsPerDay;
        this.outputDir = outputDir;
        this.startDate = startDate;
        init();
    }

    private void init() {
        txnColsToValueGens.put("TRAN_ID", new ContinuousIntegerGenerator(100000));
        txnColsToValueGens.put("BUSINESS_DT", new ContinuousDatesGenerator(startDate, rowsPerDay, 0));
        txnColsToValueGens.put("TRADE_DT", new ContinuousDatesGenerator(startDate, rowsPerDay, 5));
        txnColsToValueGens.put("TRADER", new RandomStringFromListGenerator(TRADERS));
        txnColsToValueGens.put("PRICE", new DecimalRangeGenerator(BigDecimal.valueOf(10.88), BigDecimal.valueOf(88.10)));
        txnColsToValueGens.put("QTY", new IntegerRangeGenerator(100, 100000));
        txnColsToValueGens.put("INSTRUMENT_ID", new IntegerRangeGenerator(100, 200));

        //INSTRUMENT_ID will be added as first column
        instrColsToValueGens.put("SRC_INSTRUMENT", new RandomStringFromListGenerator(INSTRUMENTS));
    }

    private void generate() throws IOException {
        //write txn file
        FileWriter writer = new FileWriter(outputDir + "\\" + TXN_FILE_NAME);
        String line_sep = "\n";

        //cols
        Set<String> cols = txnColsToValueGens.keySet();
        writer.append(cols.stream().collect(Collectors.joining(",")));
        writer.append(line_sep);

        Set<Integer> distinctInstIds = new HashSet<Integer>();
        for (int i = 0; i < noOfDays * rowsPerDay; i++) {
            StringBuilder row = new StringBuilder("");
            for (String col : cols) {
                Object val = txnColsToValueGens.get(col).generate();
                row.append(val).append(",");

                if (col.equals("INSTRUMENT_ID")) {
                    distinctInstIds.add((Integer) val);
                }
            }
            row.deleteCharAt(row.length() - 1);
            writer.append(row.toString());
            writer.append(line_sep);
        }

        writer.close();

        //write instruments file
        writer = new FileWriter(outputDir + "\\" + INSTR_FILE_NAME);
        cols = instrColsToValueGens.keySet();
        writer.append("INSTRUMENT_ID,").append(cols.stream().collect(Collectors.joining(",")));
        writer.append(line_sep);

        for (Integer instId : distinctInstIds) {
            StringBuilder row = new StringBuilder(instId + ",");
            for (String col : cols) {
                Object val = instrColsToValueGens.get(col).generate();
                row.append(val).append(",");
            }
            row.deleteCharAt(row.length() - 1);
            writer.append(row.toString());
            writer.append(line_sep);
        }

        writer.close();
    }

    public static void main(String[] args) throws IOException {
        int noOfDays = Integer.valueOf(args[0]);
        int rowsPerDay = Integer.valueOf(args[1]);
        LocalDate startDate = LocalDate.parse(args[2], DateTimeFormatter.ISO_LOCAL_DATE);
        String outputDir = args[3];

        new GenerateCsvFile(noOfDays, rowsPerDay, startDate, outputDir).generate();
    }
}
