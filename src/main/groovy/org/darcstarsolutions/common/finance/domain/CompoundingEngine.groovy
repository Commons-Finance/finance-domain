package org.darcstarsolutions.common.finance.domain
/**
 * Created by mharris on 12/2/15.
 */
class CompoundingEngine {
    CompoundingType compoundingType

    CompoundingPeriod compoundingPeriod

    CompoundingEngine(CompoundingType compoundingType, CompoundingPeriod compoundingPeriod) {
        this.compoundingType = compoundingType
        this.compoundingPeriod = compoundingPeriod
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof CompoundingEngine)) return false

        CompoundingEngine that = (CompoundingEngine) o

        if (compoundingPeriod != that.compoundingPeriod) return false
        if (compoundingType != that.compoundingType) return false

        return true
    }

    int hashCode() {
        int result
        result = (compoundingType != null ? compoundingType.hashCode() : 0)
        result = 31 * result + (compoundingPeriod != null ? compoundingPeriod.hashCode() : 0)
        return result
    }
}
