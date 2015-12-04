package org.darcstarsolutions.common.finance.domain.calculators

import org.darcstarsolutions.common.finance.domain.Bond
import org.darcstarsolutions.common.finance.domain.CompoundingEngine
import org.darcstarsolutions.common.finance.domain.calculators.config.BondCalculatorTestConfiguration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

import javax.annotation.Resource
import java.math.RoundingMode

import static org.hamcrest.Matchers.*
import static org.junit.Assert.assertThat
/**
 * Created by mharris on 12/2/15.
 */

@ActiveProfiles(value = ["test"])
@ContextConfiguration(classes = [BondCalculatorTestConfiguration.class])
class ZeroCouponBondCalculatorSpec extends Specification {

    @Resource
    CompoundingEngine simpleAnnuallyCompoundingEngine

    @Resource
    BondCalculator zeroCouponBondCalculator

    def setup() {
        assertThat(zeroCouponBondCalculator, instanceOf(BondCalculator))
    }

    def "confirming that simpleCompoundingBondCalculator is using the simple and annually compounding engine"() {
        expect:
        assertThat(zeroCouponBondCalculator.compoundingEngine, equalTo(simpleAnnuallyCompoundingEngine))

    }

    @Unroll
    def "given a zero coupon bond with face value #bond.faceValue, interest rate #bond.interestRate, and time to maturity in years #bond.timeToMaturity calculate current value #currentValue"() {
        expect:
        assertThat(zeroCouponBondCalculator.calculateCurrentValue(bond), closeTo(currentValue, 0.01))

        where:
        faceValue | interestRate | timeToMaturity || current
        1000.00  | 0.05  | 6  || 746.27
        1000.00   | 0.05         | 40             || 142.05
        20000.00 | 0.055 | 20 || 6849.32
        10000.00 | 0.08  | 10 || 4629.63

        currentValue = BigDecimal.valueOf(current)
        bond = new Bond.Builder().faceValue(faceValue).interestRate(interestRate).timeToMaturity(timeToMaturity).build()
    }

    @Unroll
    def "given a zero coupon bond with face value #bond.faceValue, time to maturity in years #bond.timeToMaturity, and a current value #bond.currentValue calculate yield to maturity #yieldToMaturity"() {
        expect:
        assertThat(zeroCouponBondCalculator.calculateYieldToMaturity(bond), closeTo(yieldToMaturity, 0.001))

        where:
        faceValue | timeToMaturity | currentValue || yield
        1000.00  | 6  | 746.27  || 0.05
        1000.00   | 40             | 142.05       || 0.05
        20000.00 | 20 | 6849.32 || 0.055
        10000.00 | 10 | 4629.63 || 0.08

        yieldToMaturity = BigDecimal.valueOf(yield)
        bond = new Bond.Builder().faceValue(faceValue).timeToMaturity(timeToMaturity).currentValue(currentValue).build()
    }

    @Unroll
    def "given a zero coupon bond with current value #bond.currentValue, interest rate #bond.interestRate, and time to maturity in years #bond.timeToMaturity calculate the face value #faceValue"() {
        expect:
        assertThat(zeroCouponBondCalculator.calculateFaceValue(bond), closeTo(faceValue, 0.05))

        where:
        currentValue | interestRate | timeToMaturity || face
        746.27       | 0.05         | 6              || 1000.00
        142.05       | 0.05         | 40             || 1000.00
        6849.32      | 0.055        | 20             || 20000.00
        4629.63      | 0.08         | 10             || 10000.00

        faceValue = BigDecimal.valueOf(face).setScale(2, RoundingMode.HALF_EVEN)
        bond = new Bond.Builder().currentValue(currentValue).interestRate(interestRate).timeToMaturity(timeToMaturity).build()
    }
}