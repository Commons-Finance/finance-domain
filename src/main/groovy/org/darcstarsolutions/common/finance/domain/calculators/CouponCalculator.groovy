package org.darcstarsolutions.common.finance.domain.calculators

import org.darcstarsolutions.common.finance.domain.Bond

/**
 * Created by mharris on 12/5/15.
 */
interface CouponCalculator<T extends Bond> extends AssetCalculator<T> {

    public BigDecimal calculateCouponValue(T bond)

}
