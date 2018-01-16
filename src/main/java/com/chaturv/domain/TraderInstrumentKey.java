package com.chaturv.domain;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class TraderInstrumentKey implements WritableComparable<TraderInstrumentKey> {

    private String traderName;
    private String instrument;

    public TraderInstrumentKey() {

    }

    @Override
    public int compareTo(TraderInstrumentKey other) {
        String thisKey = this.traderName + "|" + this.instrument;
        String thatKey = other.traderName + "|" + other.instrument;

        return thisKey.compareTo(thatKey);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(traderName);
        out.writeUTF(instrument);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        traderName = in.readUTF();
        instrument = in.readUTF();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TraderInstrumentKey that = (TraderInstrumentKey) o;
        return Objects.equals(traderName, that.traderName) &&
                Objects.equals(instrument, that.instrument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(traderName, instrument);
    }

    @Override
    public String toString() {
        return traderName + "," + instrument;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }


}
