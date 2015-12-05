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
}
