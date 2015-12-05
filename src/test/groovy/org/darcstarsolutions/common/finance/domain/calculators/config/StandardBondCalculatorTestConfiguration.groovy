package org.darcstarsolutions.common.finance.domain.calculators.config
import org.darcstarsolutions.common.finance.domain.CompoundingEngine
import org.darcstarsolutions.common.finance.domain.calculators.BondCalculator
import org.darcstarsolutions.common.finance.domain.calculators.StandardBondCouponCalculator
import org.darcstarsolutions.common.finance.domain.calculators.YieldCalculator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

import javax.annotation.Resource
/**
 * Created by mharris on 12/2/15.
 */

@Configuration
@Import(value = [CompoundingTestConfiguration.class, YieldCalculatorTestConfiguration.class, CouponCalculatorTestConfiguration.class])
class StandardBondCalculatorTestConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(StandardBondCalculatorTestConfiguration.class)

    @Resource
    CompoundingEngine simpleAnnuallyCompoundingEngine

    @Resource
    YieldCalculator zeroCouponBondYieldCalculator

    @Resource
    StandardBondCouponCalculator standardBondCouponCalculator

    @Bean(name = "standardBondCalculator")
    BondCalculator standardBondCalculator() {
        return new BondCalculator(simpleAnnuallyCompoundingEngine, zeroCouponBondYieldCalculator, standardBondCouponCalculator)
    }

}
