package org.darcstarsolutions.common.finance.domain

/**
 * Created by mharris on 12/3/15.
 */
class Bond extends Asset<Bond> {
    final double faceValue
    final double interestRate
    final double couponRate
    final double couponValue
    final int timeToMaturity
    final CompoundingPeriod compoundingPeriod

    private Bond(Builder builder) {
        this.faceValue = builder.faceValue
        this.currentValue = builder.currentValue
        this.interestRate = builder.interestRate
        this.couponRate = builder.couponRate
        this.couponValue = builder.couponValue
        this.timeToMaturity = builder.timeToMaturity
        this.compoundingPeriod = builder.compoundingPeriod
    }

    @Override
    int compareTo(Bond o) {
        return Double.compare(faceValue, o.faceValue)
    }

    @Override
    public String toString() {
        return "Bond{" +
                "faceValue=" + faceValue +
                ", currentValue=" + currentValue +
                ", interestRate=" + interestRate +
                ", couponRate=" + couponRate +
                ", timeToMaturity=" + timeToMaturity +
                ", compoundingPeriod=" + compoundingPeriod +
                '}';
    }


    public static class Builder {

        private double faceValue
        private double currentValue
        private double interestRate
        private double couponRate
        private double couponValue
        private int timeToMaturity
        private CompoundingPeriod compoundingPeriod

        Builder faceValue(double faceValue) {
            this.faceValue = faceValue
            return this
        }

        Builder currentValue(double currentValue) {
            this.currentValue = currentValue
            return this
        }

        Builder interestRate(double interestRate) {
            this.interestRate = interestRate
            return this
        }

        Builder timeToMaturity(int timeToMaturity) {
            this.timeToMaturity = timeToMaturity
            return this
        }

        Builder couponRate(double couponRate) {
            this.couponRate = couponRate
            return this
        }

        Builder compoundingPeriod(CompoundingPeriod compoundingPeriod) {
            this.compoundingPeriod = compoundingPeriod
            return this
        }

        Builder couponValue(double couponValue) {
            this.couponValue = couponValue
            return this
        }

        public Bond build() {
            return new Bond(this)
        }

    }

}
