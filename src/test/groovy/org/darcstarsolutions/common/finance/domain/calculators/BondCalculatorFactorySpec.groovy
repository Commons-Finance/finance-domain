package org.darcstarsolutions.common.finance.domain.calculators

import org.darcstarsolutions.common.finance.domain.CompoundingEngine
import org.darcstarsolutions.common.finance.domain.calculators.config.BondCalculatorFactoryTestConfiguration
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

import javax.annotation.Resource

import static org.hamcrest.Matchers.instanceOf
import static org.junit.Assert.assertThat

/**
 * Created by mharris on 12/5/15.
 */

@ContextConfiguration(classes = [BondCalculatorFactoryTestConfiguration.class])
class BondCalculatorFactorySpec extends Specification {

    @Resource
    CompoundingEngine simpleAnnuallyCompoundingEngine

    @Unroll
    def "given a bond calculator type #bondCalculatorType, expect a bond factory of type #clazz.getSimpleName"() {
        expect:
        assertThat(BondCalculatorFactory.getInstance(bondCalculatorType, simpleAnnuallyCompoundingEngine).couponCalculator, instanceOf(clazz))

        where:
        bondCalculatorType                             | clazz
        BondCalculatorType.ZERO_COUPON_BOND_CALCULATOR | ZeroCouponBondCouponCalculator.class
        BondCalculatorType.STANDARD_BOND_CALCULATOR    | StandardBondCouponCalculator.class
    }

}