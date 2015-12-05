package org.darcstarsolutions.common.finance.domain.calculators.config

import org.darcstarsolutions.common.finance.domain.CompoundingEngine
import org.darcstarsolutions.common.finance.domain.calculators.BondCalculator
import org.darcstarsolutions.common.finance.domain.calculators.YieldCalculator
import org.darcstarsolutions.common.finance.domain.calculators.ZeroCouponBondCouponCalculator
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
class ZeroCouponBondCalculatorTestConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ZeroCouponBondCalculatorTestConfiguration.class)

    @Resource
    CompoundingEngine simpleAnnuallyCompoundingEngine

    @Resource
    YieldCalculator zeroCouponBondYieldCalculator

    @Resource
    ZeroCouponBondCouponCalculator zeroCouponBondCouponCalculator

    @Bean(name = "zeroCouponBondCalculator")
    BondCalculator zeroCouponBondCalculator() {
        return new BondCalculator(simpleAnnuallyCompoundingEngine, zeroCouponBondYieldCalculator, zeroCouponBondCouponCalculator)
    }

}
