package com.xavier.hadoop.mapreduce.temperature;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class MyKey implements WritableComparable {
    private int year;
    private int month;
    private double air;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getAir() {
        return air;
    }

    public void setAir(double air) {
        this.air = air;
    }

    public int compareTo(Object o) {
        return this==o ? 0: -1;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(year);
        dataOutput.writeInt(month);
        dataOutput.writeDouble(air);
    }

    public void readFields(DataInput dataInput) throws IOException {
        year = dataInput.readInt();
        month = dataInput.readInt();
        air = dataInput.readDouble();
    }
}
