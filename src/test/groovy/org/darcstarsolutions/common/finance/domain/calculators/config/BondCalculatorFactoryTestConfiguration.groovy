package org.darcstarsolutions.common.finance.domain.calculators.config

import org.darcstarsolutions.common.finance.domain.CompoundingEngine
import org.darcstarsolutions.common.finance.domain.CompoundingPeriod
import org.darcstarsolutions.common.finance.domain.CompoundingType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by mharris on 12/5/15.
 */

@Configuration
class BondCalculatorFactoryTestConfiguration {

    @Bean(name = "simpleAnnuallyCompoundingEngine")
    CompoundingEngine simpleAnnuallyCompoundingEngine() {
        return new CompoundingEngine(CompoundingType.SIMPLE, CompoundingPeriod.ANNUALLY)
    }

}
