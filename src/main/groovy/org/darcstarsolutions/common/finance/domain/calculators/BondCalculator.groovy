package org.darcstarsolutions.common.finance.domain.calculators

import org.darcstarsolutions.common.finance.domain.Bond
import org.darcstarsolutions.common.finance.domain.CompoundingEngine

import java.math.RoundingMode

/**
 * Created by mharris on 12/2/15.
 */
class BondCalculator {
    CompoundingEngine compoundingEngine

    BondCalculator(CompoundingEngine compoundingEngine) {
        this.compoundingEngine = compoundingEngine
    }

    BigDecimal calculateCurrentValue(Bond bond) {
        def interestRate = bond.interestRate + 1
        int exponent = bond.timeToMaturity * compoundingEngine.compoundingPeriod.value
        def divisor = Math.pow(interestRate, exponent)
        def currentValue = bond.faceValue / divisor
        return BigDecimal.valueOf(currentValue).setScale(2, RoundingMode.HALF_EVEN)
    }

    BigDecimal calculateYieldToMaturity(Bond bond) {
        def exponent = 1.0 / bond.timeToMaturity
        def ratio = bond.faceValue / bond.currentValue
        def rate = Math.pow(ratio, exponent) - 1.0
        return BigDecimal.valueOf(rate).setScale(3, RoundingMode.HALF_EVEN)

    }
}
