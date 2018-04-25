package com.example.chris.pcalc.numeric;

public class BigDecimal implements Numeric<java.math.BigDecimal> {
    public static class Factory implements NumericFactory<java.math.BigDecimal, BigDecimal> {
        @Override
        public BigDecimal fromString(String str) {
            return new BigDecimal(new java.math.BigDecimal(str)
                    .setScale(10, java.math.BigDecimal.ROUND_UP)
            );
        }
    }

    public static Factory factory() {
        return new Factory();
    }
    public static BigDecimal ZERO = new BigDecimal(java.math.BigDecimal.ZERO);

    private java.math.BigDecimal self;

    public BigDecimal(java.math.BigDecimal self) {
        this.self = self;
    }

    @Override
    public Numeric<java.math.BigDecimal> add(Numeric<java.math.BigDecimal> rhs) {
        return new BigDecimal(self.add(rhs.getSelf()));
    }

    @Override
    public Numeric<java.math.BigDecimal> sub(Numeric<java.math.BigDecimal> rhs) {
        return new BigDecimal(self.subtract(rhs.getSelf()));
    }

    @Override
    public Numeric<java.math.BigDecimal> mul(Numeric<java.math.BigDecimal> rhs) {
        return new BigDecimal(self.multiply(rhs.getSelf()));
    }

    @Override
    public Numeric<java.math.BigDecimal> div(Numeric<java.math.BigDecimal> rhs) {
        return new BigDecimal(self.divide(rhs.getSelf(), java.math.BigDecimal.ROUND_HALF_DOWN));
    }

    @Override
    public Numeric<java.math.BigDecimal> pow(Numeric<java.math.BigDecimal> rhs) {
        return new BigDecimal(self.pow(rhs.intValue()));
    }

    @Override
    public Numeric<java.math.BigDecimal> mod(Numeric<java.math.BigDecimal> rhs) {
        return new BigDecimal(self.remainder(rhs.getSelf()));
    }

    @Override
    public Numeric<java.math.BigDecimal> and(Numeric<java.math.BigDecimal> rhs) {
        throw new UnsupportedOperationException("Decimals don't support bitwise operations");
    }

    @Override
    public Numeric<java.math.BigDecimal> or(Numeric<java.math.BigDecimal> rhs) {
        throw new UnsupportedOperationException("Decimals don't support bitwise operations");
    }

    @Override
    public Numeric<java.math.BigDecimal> xor(Numeric<java.math.BigDecimal> rhs) {
        throw new UnsupportedOperationException("Decimals don't support bitwise operations");
    }

    @Override
    public Numeric<java.math.BigDecimal> shl(Numeric<java.math.BigDecimal> rhs) {
        throw new UnsupportedOperationException("Decimals don't support bitwise operations");
    }

    @Override
    public Numeric<java.math.BigDecimal> shr(Numeric<java.math.BigDecimal> rhs) {
        throw new UnsupportedOperationException("Decimals don't support bitwise operations");
    }

    @Override
    public Numeric<java.math.BigDecimal> neg() {
        throw new UnsupportedOperationException("Decimals don't support bitwise operations");
    }

    @Override
    public Numeric<java.math.BigDecimal> not() {
        throw new UnsupportedOperationException("Decimals don't support bitwise operations");
    }

    @Override
    public java.math.BigDecimal getSelf() {
        return self;
    }

    public java.math.BigDecimal zero() {
        return java.math.BigDecimal.ZERO;
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
        return self.stripTrailingZeros().toString();
    }
}
