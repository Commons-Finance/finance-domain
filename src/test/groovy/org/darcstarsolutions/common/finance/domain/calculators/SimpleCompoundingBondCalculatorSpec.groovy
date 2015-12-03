package org.darcstarsolutions.common.finance.domain.calculators

import org.darcstarsolutions.common.finance.domain.Bond
import org.darcstarsolutions.common.finance.domain.CompoundingEngine
import org.darcstarsolutions.common.finance.domain.calculators.config.BondCalculatorTestConfiguration
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

import javax.annotation.Resource

import static org.hamcrest.Matchers.*
import static org.junit.Assert.assertThat
/**
 * Created by mharris on 12/2/15.
 */

@ContextConfiguration(classes = [BondCalculatorTestConfiguration.class])
class SimpleCompoundingBondCalculatorSpec extends Specification {

    @Resource
    CompoundingEngine simpleAnnuallyCompoundingEngine

    @Resource
    BondCalculator simpleCompoundingBondCalculator

    def setup() {
        assertThat(simpleCompoundingBondCalculator, instanceOf(BondCalculator))
    }

    def "confirming that simpleCompoundingBondCalculator is using the simple and annually compounding engine"() {
        expect:
        assertThat(simpleCompoundingBondCalculator.compoundingEngine, equalTo(simpleAnnuallyCompoundingEngine))

    }

    @Unroll
    def "given a zero coupon bond with face value #bond.faceValue, interest rate #bond.interestRate, and time to maturity in years #bond.timeToMaturity calculate current value #currentValue"() {
        expect:
        assertThat(simpleCompoundingBondCalculator.calculateCurrentValue(bond), closeTo(currentValue, 0.01))

        where:
        faceValue | interestRate | timeToMaturity || current
        1000.00   | 0.05         | 6              || 746.22
        1000.00   | 0.05         | 40             || 142.05
        20000.00  | 0.055        | 20             || 6854.58
        10000.00  | 0.08         | 10             || 4631.93

        currentValue = BigDecimal.valueOf(current)
        bond = new Bond(faceValue, interestRate, timeToMaturity)
    }

    @Unroll
    def "given a zero coupon bond with face value #bond.faceValue, time to maturity in years #bond.timeToMaturity, and a current value #bond.currentValue calculate yield to maturity #yieldToMaturity"() {
        expect:
        assertThat(simpleCompoundingBondCalculator.calculateYieldToMaturity(bond), closeTo(yieldToMaturity, 0.001))

        where:
        faceValue | timeToMaturity | currentValue || yield
        1000.00   | 6              | 746.22       || 0.05
        1000.00   | 40             | 142.05       || 0.05
        20000.00  | 20             | 6854.58      || 0.055
        10000.00  | 10             | 4631.93      || 0.08

        yieldToMaturity = BigDecimal.valueOf(yield)
        bond = new Bond(faceValue, timeToMaturity, currentValue)
    }
}