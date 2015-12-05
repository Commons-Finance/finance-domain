package org.darcstarsolutions.common.finance.domain.calculators

import org.darcstarsolutions.common.finance.domain.Bond
import org.darcstarsolutions.common.finance.domain.CompoundingEngine
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.math.RoundingMode
/**
 * Created by mharris on 12/2/15.
 */
class BondCalculator implements AssetCalculator<Bond> {
    private static final Logger logger = LoggerFactory.getLogger(BondCalculator.class)

    CompoundingEngine compoundingEngine
    YieldCalculator yieldCalculator
    CouponCalculator<? extends Bond> couponCalculator

    BondCalculator(CompoundingEngine compoundingEngine, YieldCalculator yieldCalculator, CouponCalculator<? extends Bond> couponCalculator) {
        this.compoundingEngine = compoundingEngine
        this.yieldCalculator = yieldCalculator
        this.couponCalculator = couponCalculator
    }

    BigDecimal calculateCurrentValue(Bond bond) {
        logger.trace("Calculating current value for: {}", bond)
        def interestRate = bond.interestRate + 1
        logger.debug("Interest rate: {}", interestRate)
        int numberOfPeriods = bond.timeToMaturity * compoundingEngine.compoundingPeriod.value
        logger.debug("Number of periods: {}", numberOfPeriods)
        def divisor = BigDecimal.valueOf(interestRate).pow(numberOfPeriods).setScale(2, RoundingMode.HALF_EVEN)
        logger.debug("Divisor: {}", divisor)
        def currentValue = BigDecimal.valueOf(bond.faceValue).divide(divisor, 2, RoundingMode.HALF_EVEN)
        logger.debug("Current Value: {}", currentValue)
        BigDecimal result = BigDecimal.valueOf(currentValue).setScale(2, RoundingMode.HALF_EVEN)
        logger.debug("Returned Current Value: {}", result)
        return result
    }


    BigDecimal calculateFaceValue(Bond bond) {
        logger.trace("Calculating face value for: {}", bond)
        def normalizedInterestRate = (1.0 + bond.interestRate)
        logger.debug("Normalized Interest Rate: {}", normalizedInterestRate)
        def numberOfPeriods = bond.timeToMaturity * compoundingEngine.compoundingPeriod.value
        logger.debug("Number of periods: {}", numberOfPeriods)
        def effectiveInterestRate = BigDecimal.valueOf(normalizedInterestRate).pow(numberOfPeriods).setScale(2, RoundingMode.HALF_EVEN)
        logger.debug("Effective Interest Rate: {}", effectiveInterestRate)
        def faceValue = BigDecimal.valueOf(bond.currentValue).multiply(effectiveInterestRate).setScale(2, RoundingMode.HALF_EVEN)
        logger.debug("Face Value: {}", faceValue)
        BigDecimal result = BigDecimal.valueOf(faceValue).setScale(2, RoundingMode.HALF_EVEN)
        logger.debug("Returned Face Value: {}", result)
        return result
    }

    BigDecimal calculateYieldToMaturity(Bond bond) {
        return yieldCalculator.calculateYieldToMaturity(bond)
    }

    BigDecimal calculateCouponValue(Bond bond) {
        return couponCalculator.calculateCouponValue(bond)
    }

    BigDecimal calculateCouponRate(Bond bond) {
        logger.trace("Calculating coupon rate for: {}", bond)
        def couponRate = bond.couponValue * bond.compoundingPeriod.value / bond.faceValue
        logger.debug("Calculated Coupon Rate: {}", couponRate)
        BigDecimal result = BigDecimal.valueOf(couponRate).setScale(2, RoundingMode.HALF_EVEN)
        logger.debug("Returned Coupon Rate: {}", result)
        return result
    }
}
