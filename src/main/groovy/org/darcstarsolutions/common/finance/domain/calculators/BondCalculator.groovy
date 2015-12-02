package org.darcstarsolutions.common.finance.domain.calculators

import org.darcstarsolutions.common.finance.domain.CompoundingEngine

/**
 * Created by mharris on 12/2/15.
 */
class BondCalculator {
    CompoundingEngine compoundingEngine

    BondCalculator(CompoundingEngine compoundingEngine) {
        this.compoundingEngine = compoundingEngine
    }
}
