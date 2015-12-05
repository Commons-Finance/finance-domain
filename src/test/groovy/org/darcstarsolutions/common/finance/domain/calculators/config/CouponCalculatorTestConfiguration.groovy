package org.darcstarsolutions.common.finance.domain.calculators.config

import org.darcstarsolutions.common.finance.domain.calculators.StandardBondCouponCalculator
import org.darcstarsolutions.common.finance.domain.calculators.ZeroCouponBondCouponCalculator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by mharris on 12/5/15.
 */

@Configuration
class CouponCalculatorTestConfiguration {

    @Bean(name = "standardBondCouponCalculator")
    StandardBondCouponCalculator standardBondCouponCalculator() {
        return new StandardBondCouponCalculator()
    }

    @Bean(name = "zeroCouponBondCouponCalculator")
    ZeroCouponBondCouponCalculator zeroCouponBondCouponCalculator() {
        return new ZeroCouponBondCouponCalculator()
    }

}
