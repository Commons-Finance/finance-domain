package org.darcstarsolutions.common.finance.domain.calculators

import org.darcstarsolutions.common.finance.domain.Bond
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.math.RoundingMode

/**
 * Created by mharris on 12/5/15.
 */
class StandardBondCouponCalculator implements CouponCalculator<Bond> {

    private static final Logger logger = LoggerFactory.getLogger(StandardBondCouponCalculator.class)

    @Override
    BigDecimal calculateCouponValue(Bond bond) {
        logger.trace("Calculating coupon value for: {}", bond)
        def couponValue = bond.faceValue * bond.couponRate / bond.compoundingPeriod.value
        logger.debug("Calculated Coupon Value: {}", couponValue)
        BigDecimal result = BigDecimal.valueOf(couponValue).setScale(2, RoundingMode.HALF_EVEN)
        logger.debug("Returned Coupon Value: {}", result)
        return result
    }

    @Override
    BigDecimal calculateCouponRate(Bond bond) {
        logger.trace("Calculating coupon rate for: {}", bond)
        def couponRate = bond.couponValue * bond.compoundingPeriod.value / bond.faceValue
        logger.debug("Calculated Coupon Rate: {}", couponRate)
        BigDecimal result = BigDecimal.valueOf(couponRate).setScale(2, RoundingMode.HALF_EVEN)
        logger.debug("Returned Coupon Rate: {}", result)
        return result
    }
}
