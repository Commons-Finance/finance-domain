package org.darcstarsolutions.common.finance.domain

/**
 * Created by mharris on 12/3/15.
 */
class Bond extends Asset<Bond> {
    final double faceValue
    final double interestRate
    final int timeToMaturity
    final double currentValue

    private Bond(Builder builder) {
        this.faceValue = builder.faceValue
        this.currentValue = builder.currentValue
        this.interestRate = builder.interestRate
        this.timeToMaturity = builder.timeToMaturity
    }

    @Override
    public String toString() {
        return "Bond{" +
                "faceValue=" + faceValue +
                ", interestRate=" + interestRate +
                ", timeToMaturity=" + timeToMaturity +
                ", currentValue=" + currentValue +
                '}';
    }

    @Override
    int compareTo(Bond o) {
        return Double.compare(faceValue, o.faceValue)
    }

    public static class Builder {

        private double faceValue
        private double currentValue
        double interestRate
        int timeToMaturity

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

        public Bond build() {
            return new Bond(this)
        }
    }


}
