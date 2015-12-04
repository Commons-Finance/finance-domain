package org.darcstarsolutions.common.finance.domain.calculators

import org.darcstarsolutions.common.finance.domain.Bond
import org.darcstarsolutions.common.finance.domain.CompoundingPeriod
import org.darcstarsolutions.common.finance.domain.calculators.config.BondCalculatorTestConfiguration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

import javax.annotation.Resource

import static org.hamcrest.Matchers.*
import static org.junit.Assert.assertThat

/**
 * Created by mharris on 12/4/15.
 */

@ActiveProfiles(value = ["test"])
@ContextConfiguration(classes = [BondCalculatorTestConfiguration.class])
class StandardBondCalculatorSpec extends Specification {

    @Resource
    BondCalculator standardBondCalculator

    def setup() {
        assertThat(standardBondCalculator, is(not(null)))
    }

    @Unroll
    def "check calculated coupon value #couponValue for face value #bond.faceValue, coupon rate #bond.couponRate and periods #bond.compoundingPeriod"() {
        expect:
        assertThat(standardBondCalculator.calculateCouponValue(bond), closeTo(couponValue, 0.01))

        where:
        faceValue  | couponRate | compoundingPeriod               || couponValue
        1000.00    | 0.05       | CompoundingPeriod.ANNUALLY      || 50.00
        1000.00    | 0.05       | CompoundingPeriod.SEMI_ANNUALLY || 25.00
        1000.00    | 0.05       | CompoundingPeriod.QUARTERLY     || 12.50
        1000.00    | 0.05       | CompoundingPeriod.MONTHLY       || 4.17
        1000.00    | 0.05       | CompoundingPeriod.DAILY         || 0.14
        1000.00    | 0.05       | CompoundingPeriod.DAILY_360     || 0.14
        1000.00    | 0.10       | CompoundingPeriod.ANNUALLY      || 100.00
        1000.00    | 0.10       | CompoundingPeriod.SEMI_ANNUALLY || 50.00
        1000.00    | 0.10       | CompoundingPeriod.QUARTERLY     || 25.00
        1000.00    | 0.10       | CompoundingPeriod.MONTHLY       || 8.33
        1000.00    | 0.10       | CompoundingPeriod.DAILY         || 0.28
        1000.00    | 0.10       | CompoundingPeriod.DAILY_360     || 0.28
        2000.00    | 0.05       | CompoundingPeriod.ANNUALLY      || 100.00
        2000.00    | 0.05       | CompoundingPeriod.SEMI_ANNUALLY || 50.00
        2000.00    | 0.05       | CompoundingPeriod.QUARTERLY     || 25.00
        2000.00    | 0.05       | CompoundingPeriod.MONTHLY       || 8.33
        2000.00    | 0.05       | CompoundingPeriod.DAILY         || 0.28
        2000.00    | 0.05       | CompoundingPeriod.DAILY_360     || 0.28
        2000.00    | 0.10       | CompoundingPeriod.ANNUALLY      || 200.00
        2000.00    | 0.10       | CompoundingPeriod.SEMI_ANNUALLY || 100.00
        2000.00    | 0.10       | CompoundingPeriod.QUARTERLY     || 50.00
        2000.00    | 0.10       | CompoundingPeriod.MONTHLY       || 16.66
        2000.00    | 0.10       | CompoundingPeriod.DAILY         || 0.56
        2000.00    | 0.10       | CompoundingPeriod.DAILY_360     || 0.56
        10000.00   | 0.10       | CompoundingPeriod.ANNUALLY      || 1000.00
        10000.00   | 0.10       | CompoundingPeriod.SEMI_ANNUALLY || 500.00
        10000.00   | 0.10       | CompoundingPeriod.QUARTERLY     || 250.00
        10000.00   | 0.10       | CompoundingPeriod.MONTHLY       || 83.33
        10000.00   | 0.10       | CompoundingPeriod.DAILY         || 2.74
        10000.00   | 0.10       | CompoundingPeriod.DAILY_360     || 2.78
        1000000.00 | 0.10       | CompoundingPeriod.ANNUALLY      || 100000.00
        1000000.00 | 0.10       | CompoundingPeriod.SEMI_ANNUALLY || 50000.00
        1000000.00 | 0.10       | CompoundingPeriod.QUARTERLY     || 25000.00
        1000000.00 | 0.10       | CompoundingPeriod.MONTHLY       || 8333.33
        1000000.00 | 0.10       | CompoundingPeriod.DAILY         || 273.97
        1000000.00 | 0.10       | CompoundingPeriod.DAILY_360     || 277.78

        bond = new Bond.Builder().faceValue(faceValue).couponRate(couponRate).compoundingPeriod(compoundingPeriod).build()
    }

}