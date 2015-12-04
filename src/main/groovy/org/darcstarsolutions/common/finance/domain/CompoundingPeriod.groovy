package org.darcstarsolutions.common.finance.domain

/**
 * Created by mharris on 12/2/15.
 */
public enum CompoundingPeriod {
    ANNUALLY(1),
    SEMI_ANNUALLY(2),
    QUARTERLY(4),
    MONTHLY(12),
    WEEKLY(52),
    DAILY(365),
    DAILY_LEAP_YEAR(364),
    DAILY_NO_LEAP(365),
    DAILY_360(360),
    MONTHLY_30_DAY(30),
    PERENNIAL(-1)

    int value

    CompoundingPeriod(int value) {
        this.value = value
    }


    public int getValue() {
        return value
    }
}