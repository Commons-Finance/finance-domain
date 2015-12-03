package org.darcstarsolutions.common.finance.domain

/**
 * Created by mharris on 12/3/15.
 */
class Bond {
    double faceValue
    double interestRate
    int timeToMaturity
    double currentValue

    Bond(double faceValue, double interestRate, int timeToMaturity) {
        this.faceValue = faceValue
        this.interestRate = interestRate
        this.timeToMaturity = timeToMaturity
    }

    Bond(double faceValue, int timeToMaturity, double currentValue) {
        this.faceValue = faceValue
        this.timeToMaturity = timeToMaturity
        this.currentValue = currentValue
    }
}
