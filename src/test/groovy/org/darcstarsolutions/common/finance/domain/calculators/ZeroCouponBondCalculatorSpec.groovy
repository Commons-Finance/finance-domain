package org.darcstarsolutions.common.finance.domain.calculators
import au.com.bytecode.opencsv.CSVParser
import au.com.bytecode.opencsv.CSVReader
import org.darcstarsolutions.common.finance.domain.Bond
import org.darcstarsolutions.common.finance.domain.CompoundingEngine
import org.darcstarsolutions.common.finance.domain.calculators.config.ZeroCouponBondCalculatorTestConfiguration
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
 * Created by mharris on 12/2/15.
 */

@ActiveProfiles(value = ["test"])
@ContextConfiguration(classes = [ZeroCouponBondCalculatorTestConfiguration.class])
class ZeroCouponBondCalculatorSpec extends Specification {

    @Resource
    CompoundingEngine simpleAnnuallyCompoundingEngine

    @Resource
    BondCalculator zeroCouponBondCalculator

    @Shared
    ClassPathResource bondCsvFile = new ClassPathResource("zero-coupon-bonds.csv")

    @Shared
    CSVReader bondTestSet = new CSVReader(new BufferedReader(new FileReader(bondCsvFile.getFile())), '|' as char, CSVParser.DEFAULT_QUOTE_CHARACTER, 1)

    @Shared
    def dataSet = bondTestSet.readAll()

    def setup() {
        assertThat(zeroCouponBondCalculator, instanceOf(BondCalculator))
    }

    def "confirming that simpleCompoundingBondCalculator is using the simple and annually compounding engine"() {
        expect:
        assertThat(zeroCouponBondCalculator.compoundingEngine, equalTo(simpleAnnuallyCompoundingEngine))
    }

    @Unroll
    def "given a zero coupon bond with face value #bond.faceValue, interest rate #bond.interestRate, and time to maturity in years #bond.timeToMaturity calculate current value #currentValue"() {
        expect:
        assertThat(zeroCouponBondCalculator.calculateCurrentValue(bond), closeTo(BigDecimal.valueOf(Double.valueOf(currentValue)), 0.01))

        where:
        [faceValue, interestRate, timeToMaturity, currentValue] << dataSet

        bond = new Bond.Builder().faceValue(Double.valueOf(faceValue)).interestRate(Double.valueOf(interestRate)).timeToMaturity(Integer.valueOf(timeToMaturity)).build()
    }

    @Unroll
    def "given a zero coupon bond with face value #bond.faceValue, time to maturity in years #bond.timeToMaturity, and a current value #bond.currentValue calculate yield to maturity #yieldToMaturity"() {
        expect:
        assertThat(zeroCouponBondCalculator.calculateYieldToMaturity(bond), closeTo(BigDecimal.valueOf(Double.valueOf(yieldToMaturity)), 0.001))

        where:
        [faceValue, yieldToMaturity, timeToMaturity, currentValue] << dataSet

        bond = new Bond.Builder().faceValue(Double.valueOf(faceValue)).timeToMaturity(Integer.valueOf(timeToMaturity)).currentValue(Double.valueOf(currentValue)).build()
    }

    @Unroll
    def "given a zero coupon bond with current value #bond.currentValue, interest rate #bond.interestRate, and time to maturity in years #bond.timeToMaturity calculate the face value #faceValue"() {
        expect:
        assertThat(zeroCouponBondCalculator.calculateFaceValue(bond), closeTo(BigDecimal.valueOf(Double.valueOf(faceValue)), 0.05))

        where:
        [faceValue, interestRate, timeToMaturity, currentValue] << dataSet

        bond = new Bond.Builder().currentValue(Double.valueOf(currentValue)).interestRate(Double.valueOf(interestRate)).timeToMaturity(Integer.valueOf(timeToMaturity)).build()
    }

    @Unroll
    def "given a zero coupon bond with current value #bond.currentValue and face value #bond.faceValue calculate the coupon value #couponValue"() {
        expect:
        assertThat(zeroCouponBondCalculator.calculateCouponValue(bond), closeTo(BigDecimal.valueOf(Double.valueOf(couponValue)), 0.0001))

        where:
        [faceValue, _, _, currentValue, couponValue] << dataSet

        bond = new Bond.Builder().faceValue(Double.valueOf(faceValue)).currentValue(Double.valueOf(currentValue)).build()
    }

    @Unroll
    def "given a zero coupon bond with current value #bond.currentValue, time to maturity #bond.timeToMaturity in years, and face value #bond.faceValue calculate the coupon rate #couponRate"() {
        expect:
        assertThat(zeroCouponBondCalculator.calculateCouponRate(bond), closeTo(BigDecimal.valueOf(Double.valueOf(couponRate)), 0.05))

        where:
        [faceValue, couponRate, timeToMaturity, currentValue] << dataSet

        bond = new Bond.Builder().faceValue(Double.valueOf(faceValue)).currentValue(Double.valueOf(currentValue)).timeToMaturity(Integer.valueOf(timeToMaturity)).build()
    }
}