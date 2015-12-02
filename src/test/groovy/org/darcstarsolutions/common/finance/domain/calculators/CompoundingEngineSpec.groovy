package org.darcstarsolutions.common.finance.domain.calculators

import org.darcstarsolutions.common.finance.domain.CompoundingEngine
import org.darcstarsolutions.common.finance.domain.CompoundingPeriod
import org.darcstarsolutions.common.finance.domain.CompoundingType
import org.darcstarsolutions.common.finance.domain.calculators.config.CompoundingTestConfiguration
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.annotation.Resource


/**
 * Created by mharris on 12/2/15.
 */

@ContextConfiguration(classes = [CompoundingTestConfiguration.class])
class CompoundingEngineSpec extends Specification {

    @Resource
    CompoundingEngine simpleAnnuallyCompoundingEngine

    def "confirming that simpleAnnuallyCompoundingEngine is set for simple interest and annually compounding"() {
        expect:
        simpleAnnuallyCompoundingEngine.compoundingType.equals(CompoundingType.SIMPLE)
        simpleAnnuallyCompoundingEngine.compoundingPeriod.equals(CompoundingPeriod.ANNUALLY)
    }

}