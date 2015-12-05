package org.darcstarsolutions.common.finance.domain.calculators.config
import org.darcstarsolutions.common.finance.domain.CompoundingEngine
import org.darcstarsolutions.common.finance.domain.CompoundingPeriod
import org.darcstarsolutions.common.finance.domain.CompoundingType
import org.darcstarsolutions.common.finance.domain.calculators.BondCalculator
import org.darcstarsolutions.common.finance.domain.calculators.StandardBondCouponCalculator
import org.darcstarsolutions.common.finance.domain.calculators.YieldCalculator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
/**
 * Created by mharris on 12/2/15.
 */

@Configuration
class StandardBondCalculatorTestConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(StandardBondCalculatorTestConfiguration.class)

    @Bean
    CompoundingEngine simpleAnnuallyCompoundingEngine() {
        return new CompoundingEngine(CompoundingType.SIMPLE, CompoundingPeriod.ANNUALLY)
    }

    @Bean(name = "zeroCouponBondYieldCalculator")
    YieldCalculator zeroCouponBondYieldCalculator() {
        return new YieldCalculator(simpleAnnuallyCompoundingEngine())
    }

    @Bean(name = "standardBondCouponCalculator")
    StandardBondCouponCalculator standardBondCouponCalculator() {
        return new StandardBondCouponCalculator()
    }

    @Bean(name = "standardBondCalculator")
    BondCalculator standardBondCalculator() {
        return new BondCalculator(simpleAnnuallyCompoundingEngine(), zeroCouponBondYieldCalculator(), standardBondCouponCalculator())
    }

}
