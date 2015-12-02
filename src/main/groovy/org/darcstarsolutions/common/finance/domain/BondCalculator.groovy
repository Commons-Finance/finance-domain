package org.darcstarsolutions.common.finance.domain

/**
 * Created by mharris on 12/2/15.
 */
class BondCalculator {
    CompoundingType compoundingType

    BondCalculator(CompoundingType compoundingType) {
        this.compoundingType = compoundingType
    }
}
