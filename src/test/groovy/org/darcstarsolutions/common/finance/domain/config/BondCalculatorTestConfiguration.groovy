package org.darcstarsolutions.common.finance.domain.config

import org.darcstarsolutions.common.finance.domain.BondCalculator
import org.darcstarsolutions.common.finance.domain.CompoundingType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by mharris on 12/2/15.
 */

@Configuration
class BondCalculatorTestConfiguration {

    @Bean(name = "simpleCompoundingBondCalculator")
    BondCalculator simpleCompoundingBondCalculator() {
        return new BondCalculator(CompoundingType.SIMPLE)
    }

}
