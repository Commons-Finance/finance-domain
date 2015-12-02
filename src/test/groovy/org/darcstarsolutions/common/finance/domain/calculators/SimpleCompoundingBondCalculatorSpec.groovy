package org.darcstarsolutions.common.finance.domain.calculators

import org.darcstarsolutions.common.finance.domain.CompoundingEngine
import org.darcstarsolutions.common.finance.domain.calculators.config.BondCalculatorTestConfiguration
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.annotation.Resource

import static org.hamcrest.Matchers.instanceOf
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
        simpleCompoundingBondCalculator.compoundingEngine.equals(simpleAnnuallyCompoundingEngine)

    }
}