package org.darcstarsolutions.common.finance.domain

import org.darcstarsolutions.common.finance.domain.config.BondCalculatorTestConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import static org.hamcrest.Matchers.instanceOf
import static org.junit.Assert.assertThat

/**
 * Created by mharris on 12/2/15.
 */

@ContextConfiguration(classes = [BondCalculatorTestConfiguration.class])
class SimpleCompoundingBondCalculatorSpec extends Specification {

    @Autowired
    BondCalculator simpleCompoundingBondCalculator

    def setup() {
        assertThat(simpleCompoundingBondCalculator, instanceOf(BondCalculator))
    }

    def "confirming that simpleCompoundingBondCalculator is compounding interest simply"() {
        expect:
        simpleCompoundingBondCalculator.getCompoundingType() equalTo(CompoundingType.SIMPLE)
    }
}