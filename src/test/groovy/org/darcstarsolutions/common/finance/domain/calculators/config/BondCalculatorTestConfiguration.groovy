package org.darcstarsolutions.common.finance.domain.calculators.config

import org.darcstarsolutions.common.finance.domain.CompoundingEngine
import org.darcstarsolutions.common.finance.domain.calculators.BondCalculator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

import javax.annotation.Resource
/**
 * Created by mharris on 12/2/15.
 */

@Configuration
@Import(value = [CompoundingTestConfiguration.class])
class BondCalculatorTestConfiguration {

    @Resource
    CompoundingEngine simpleAnnuallyCompoundingEngine

    @Bean(name = "simpleCompoundingBondCalculator")
    BondCalculator simpleCompoundingBondCalculator() {
        return new BondCalculator(simpleAnnuallyCompoundingEngine)
    }

}
