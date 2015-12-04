package org.darcstarsolutions.common.finance.domain

import spock.lang.Specification

/**
 * Created by mharris on 12/4/15.
 */
class BondSpec extends Specification {

    def "verify that compareTo returns #expected, for face values #bond1.faceValue, #bond2.faceValue "() {
        expect:
        bond1.compareTo(bond2) == expected

        where:
        faceValue1 | faceValue2 || expected
        1000.00    | 2000.00    || -1
        2000.00    | 1000.00    || 1
        1000.00    | 1000.00    || 0
        1000.01    | 1000.00    || 1
        1000.00    | 1000.01    || -1


        bond1 = new Bond.Builder().faceValue(faceValue1).build()
        bond2 = new Bond.Builder().faceValue(faceValue2).build()
    }

}
