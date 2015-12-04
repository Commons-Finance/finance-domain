package org.darcstarsolutions.common.finance.domain.calculators

import org.darcstarsolutions.common.finance.domain.Bond
import org.darcstarsolutions.common.finance.domain.CompoundingEngine
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.math.RoundingMode

/**
 * Created by mharris on 12/4/15.
 */
class YieldCalculator implements AssetCalculator<Bond> {

    private static final Logger logger = LoggerFactory.getLogger(YieldCalculator.class)

    CompoundingEngine compoundingEngine

    YieldCalculator(CompoundingEngine compoundingEngine) {
        this.compoundingEngine = compoundingEngine
    }

    BigDecimal calculateYieldToMaturity(Bond bond) {
        logger.trace("Calculating yield to maturity for: {}", bond)
        def numberOfPeriods = bond.timeToMaturity * compoundingEngine.compoundingPeriod.value
        logger.debug("Number of Periods: {}", numberOfPeriods)
        def exponent = BigDecimal.ONE.divide(numberOfPeriods, 4, RoundingMode.HALF_EVEN)
        logger.debug("Effective exponent: {}", exponent)
        def ratio = BigDecimal.valueOf(bond.faceValue).divide(bond.currentValue, 2, RoundingMode.HALF_EVEN)
        logger.debug("Ratio of face value to current value: {}", ratio)
        def yieldToMaturity = Math.pow(ratio, exponent.doubleValue()) - 1.0
        logger.debug("Yield to Maturity: {}", yieldToMaturity)
        BigDecimal result = BigDecimal.valueOf(yieldToMaturity).setScale(4, RoundingMode.HALF_EVEN)
        logger.debug("Returned Yield to Maturity: {}", result)
        return result
    }
}
