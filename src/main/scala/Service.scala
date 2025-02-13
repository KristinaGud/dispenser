import PharmacyStore.saveAllDrugsAsSeparateEntities

object Service extends App with JSONWriter with JSONReader {

	def getPercentageOfDoze(drug: Drug, kidneyFailureScore: Int): Int = {
		val fullDose = 100
		val doNotRecommend = 0

		if (drug.effectsKidneysFailure) {
			if (validateKidneyFailureScore(kidneyFailureScore)) {
				val dose = drug.doses.flatMap { doses =>
					doses.find { case (range, percentage) =>
						range.contains(kidneyFailureScore)
					}
				}.map(_._2)
				dose.getOrElse(fullDose)
			} else doNotRecommend
		} else {
			fullDose
		}
	}

	private def validateKidneyFailureScore(score: Int): Boolean = {
		val scoreIsValid = (1 to 90).contains(score)
		if (!scoreIsValid) println("Given eGFR is invalid")
		scoreIsValid
	}

	saveAllDrugsAsSeparateEntities()


}
