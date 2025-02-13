import PharmacyStore.{cyclophosphamide, mitomycinC}
import Service.getPercentageOfDoze

class ServiceTest extends munit.FunSuite {

	test("count percentage of doze for Cyclophosphamide then patients eGFR equals 14") {
		assertEquals(getPercentageOfDoze(cyclophosphamide, 14), 75)
	}

	test("count percentage of doze for Cyclophosphamide then patients eGFR equals 80") {
		assertEquals(getPercentageOfDoze(cyclophosphamide, 80), 100)
	}

	test("count percentage of dose for Mitomycin C then patients eGFR equals 90") {
		assertEquals(getPercentageOfDoze(mitomycinC, 90), 100)
	}

	test("count percentage of dose for Mitomycin C then patients eGFR equals 2") {
		assertEquals(getPercentageOfDoze(mitomycinC, 2), 75)
	}

	test("do not recommend any dose if patients eGFR score is invalid") {
		assertEquals(getPercentageOfDoze(mitomycinC, 100), 0)
		assertEquals(getPercentageOfDoze(mitomycinC, 91), 0)
		assertEquals(getPercentageOfDoze(cyclophosphamide, 0), 0)
		assertEquals(getPercentageOfDoze(cyclophosphamide, -1), 0)
	}

}

