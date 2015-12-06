package org.darcstarsolutions.common.finance.domain.calculators

import org.darcstarsolutions.common.finance.domain.CompoundingEngine

/**
 * Created by mharris on 12/5/15.
 */
class BondCalculatorFactory {

    protected BondCalculatorFactory() {

    }

    static BondCalculator getInstance(BondCalculatorType bondCalculatorType, CompoundingEngine compoundingEngine) {
        switch (bondCalculatorType) {
            case BondCalculatorType.STANDARD_BOND_CALCULATOR: return createStandardCouponBondCalculator(compoundingEngine);
            case BondCalculatorType.ZERO_COUPON_BOND_CALCULATOR: return createZeroCouponBondCalculator(compoundingEngine);
        }
    }

    static BondCalculator createStandardCouponBondCalculator(CompoundingEngine compoundingEngine) {
        YieldCalculator yieldCalculator = new YieldCalculator(compoundingEngine)
        CouponCalculator<?> couponCalculator = new StandardBondCouponCalculator()
        return new BondCalculator(compoundingEngine, yieldCalculator, couponCalculator)
    }

    static BondCalculator createZeroCouponBondCalculator(CompoundingEngine compoundingEngine) {
        YieldCalculator yieldCalculator = new YieldCalculator(compoundingEngine)
        CouponCalculator<?> couponCalculator = new ZeroCouponBondCouponCalculator()
        return new BondCalculator(compoundingEngine, yieldCalculator, couponCalculator)
    }
}
