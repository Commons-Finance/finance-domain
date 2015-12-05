package org.darcstarsolutions.common.finance.domain.calculators

import org.darcstarsolutions.common.finance.domain.Bond
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.math.RoundingMode

/**
 * Created by mharris on 12/5/15.
 */
class ZeroCouponBondCouponCalculator implements CouponCalculator<Bond> {

    private static final Logger logger = LoggerFactory.getLogger(ZeroCouponBondCouponCalculator.class)

    @Override
    BigDecimal calculateCouponValue(Bond bond) {
        logger.trace("Calculating coupon value for: {}", bond)
        def couponValue = bond.faceValue - bond.currentValue
        logger.debug("Calculated Coupon Value: {}", couponValue)
        BigDecimal result = BigDecimal.valueOf(couponValue).setScale(2, RoundingMode.HALF_EVEN)
        logger.debug("Returned Coupon Value: {}", result)
        return result
    }

    @Override
    BigDecimal calculateCouponRate(Bond bond) {
        logger.trace("Calculating coupon rate for: {}", bond)
        def numberOfPeriods = bond.timeToMaturity
        logger.debug("Number of Periods: {}", numberOfPeriods)
        def exponent = BigDecimal.ONE.divide(numberOfPeriods, 4, RoundingMode.HALF_EVEN)
        logger.debug("Effective exponent: {}", exponent)
        def ratio = BigDecimal.valueOf(bond.faceValue).divide(bond.currentValue, 2, RoundingMode.HALF_EVEN)
        logger.debug("Ratio of face value to current value: {}", ratio)
        def yieldToMaturity = Math.pow(ratio, exponent.doubleValue()) - 1.0
        logger.debug("Coupon rate: {}", yieldToMaturity)
        BigDecimal result = BigDecimal.valueOf(yieldToMaturity).setScale(4, RoundingMode.HALF_EVEN)
        logger.debug("Returned Coupon Rate: {}", result)
        return result
    }
}
