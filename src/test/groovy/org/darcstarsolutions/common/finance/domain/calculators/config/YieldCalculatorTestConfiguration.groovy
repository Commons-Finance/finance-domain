package org.darcstarsolutions.common.finance.domain.calculators.config

import org.darcstarsolutions.common.finance.domain.CompoundingEngine
import org.darcstarsolutions.common.finance.domain.calculators.YieldCalculator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

import javax.annotation.Resource

/**
 * Created by mharris on 12/4/15.
 */

@Configuration
@Import(value = [CompoundingTestConfiguration.class])
class YieldCalculatorTestConfiguration {

    @Resource
    CompoundingEngine simpleAnnuallyCompoundingEngine

    @Bean(name = "zeroCouponBondYieldCalculator")
    YieldCalculator zeroCouponBondYieldCalculator() {
        return new YieldCalculator(simpleAnnuallyCompoundingEngine)
    }
}
