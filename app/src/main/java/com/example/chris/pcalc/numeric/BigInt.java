package com.example.chris.pcalc.numeric;

import java.math.BigInteger;

public class BigInt implements Numeric<BigInteger> {
    public static class Factory implements NumericFactory<BigInteger, BigInt> {
        @Override
        public BigInt fromString(String str) {
            return new BigInt(new BigInteger(str));
        }
    }

    public static Factory factory() {
        return new Factory();
    }

    public static final BigInt ZERO = new BigInt(BigInteger.ZERO);

    private BigInteger self;

    public BigInt(BigInteger self) {
        this.self = self;
    }

    public BigInteger getSelf() {
        return self;
    }

    @Override
    public Numeric<BigInteger> add(Numeric<BigInteger> rhs) {
        return new BigInt(self.add(rhs.getSelf()));
    }

    @Override
    public Numeric<BigInteger> sub(Numeric<BigInteger> rhs) {
        return new BigInt(self.subtract(rhs.getSelf()));
    }

    @Override
    public Numeric<BigInteger> mul(Numeric<BigInteger> rhs) {
        return new BigInt(self.multiply(rhs.getSelf()));
    }

    @Override
    public Numeric<BigInteger> div(Numeric<BigInteger> rhs) {
        return new BigInt(self.divide(rhs.getSelf()));
    }

    @Override
    public Numeric<BigInteger> pow(Numeric<BigInteger> rhs) {
        return new BigInt(self.pow(rhs.intValue()));
    }

    @Override
    public Numeric<BigInteger> mod(Numeric<BigInteger> rhs) {
        return new BigInt(self.mod(rhs.getSelf()));
    }

    @Override
    public Numeric<BigInteger> and(Numeric<BigInteger> rhs) {
        return new BigInt(self.and(rhs.getSelf()));
    }

    @Override
    public Numeric<BigInteger> or(Numeric<BigInteger> rhs) {
        return new BigInt(self.or(rhs.getSelf()));
    }

    @Override
    public Numeric<BigInteger> xor(Numeric<BigInteger> rhs) {
        return new BigInt(self.xor(rhs.getSelf()));
    }

    @Override
    public Numeric<BigInteger> shl(Numeric<BigInteger> rhs) {
        return new BigInt(self.shiftLeft(rhs.intValue()));
    }

    @Override
    public Numeric<BigInteger> shr(Numeric<BigInteger> rhs) {
        return new BigInt(self.shiftRight(rhs.intValue()));
    }

    @Override
    public Numeric<BigInteger> neg() {
        return new BigInt(self.negate());
    }

    @Override
    public Numeric<BigInteger> not() {
        return new BigInt(self.not());
    }

    @Override
    public byte byteValue() {
        return self.byteValue();
    }

    @Override
    public short shortValue() {
        return self.shortValue();
    }

    @Override
    public int intValue() {
        return self.intValue();
    }

    @Override
    public long longValue() {
        return self.longValue();
    }

    @Override
    public float floatValue() {
        return self.floatValue();
    }

    @Override
    public double doubleValue() {
        return self.doubleValue();
    }

    @Override
    public String toString() {
        return self.toString();
    }
}
