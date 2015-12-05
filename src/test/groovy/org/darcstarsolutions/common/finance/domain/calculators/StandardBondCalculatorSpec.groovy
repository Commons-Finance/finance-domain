package org.darcstarsolutions.common.finance.domain.calculators

import au.com.bytecode.opencsv.CSVParser
import au.com.bytecode.opencsv.CSVReader
import org.darcstarsolutions.common.finance.domain.Bond
import org.darcstarsolutions.common.finance.domain.CompoundingPeriod
import org.darcstarsolutions.common.finance.domain.calculators.config.StandardBondCalculatorTestConfiguration
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import javax.annotation.Resource

import static org.hamcrest.Matchers.*
import static org.junit.Assert.assertThat
/**
 * Created by mharris on 12/4/15.
 */

@ActiveProfiles(value = ["test"])
@ContextConfiguration(classes = [StandardBondCalculatorTestConfiguration.class])
class StandardBondCalculatorSpec extends Specification {

    @Resource
    BondCalculator standardBondCalculator

    @Shared
    ClassPathResource bondCsvFile = new ClassPathResource("standard-bonds.csv")

    @Shared
    CSVReader bondTestSet = new CSVReader(new BufferedReader(new FileReader(bondCsvFile.getFile())), '|' as char, CSVParser.DEFAULT_QUOTE_CHARACTER, 1)

    @Shared
    def dataSet = bondTestSet.readAll()

    def setup() {
        assertThat(standardBondCalculator, is(not(null)))
        assertThat(bondTestSet, is(not(null)))
    }

    @Unroll
    def "check calculated coupon value #couponValue for face value #bond.faceValue, coupon rate #bond.couponRate and periods #bond.compoundingPeriod"() {
        expect:
        assertThat(standardBondCalculator.calculateCouponValue(bond), closeTo(BigDecimal.valueOf(Double.valueOf(couponValue)), 0.01))

        where:
        [faceValue, couponRate, compoundingPeriod, couponValue] << dataSet

        bond = new Bond.Builder().faceValue(Double.valueOf(faceValue)).couponRate(Double.valueOf(couponRate)).compoundingPeriod(CompoundingPeriod.valueOf(compoundingPeriod.toString().trim())).build()
    }

    @Unroll
    def "check calculated coupon rate #couponRate for face value #bond.faceValue, coupon rate #bond.couponRate and periods #bond.compoundingPeriod"() {
        expect:
        assertThat(standardBondCalculator.calculateCouponRate(bond), closeTo(BigDecimal.valueOf(Double.valueOf(couponRate)), 0.0001))

        where:
        [faceValue, couponRate, compoundingPeriod, couponValue] << dataSet

        bond = new Bond.Builder().faceValue(Double.valueOf(faceValue)).couponValue(Double.valueOf(couponValue)).compoundingPeriod(CompoundingPeriod.valueOf(compoundingPeriod.toString().trim())).build()
    }

}
