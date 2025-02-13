import io.circe.syntax.EncoderOps

object PharmacyStore extends JSONWriter {

	lazy val cyclophosphamide = Drug("Cyclophosphamide", true, None, cyclophosphamideDosageAjustmentGFR)
	lazy val mitomycinC = Drug("Mitomycin C", true, Some("Consider a dose reduction for high doses of mitomycin when GFR 10-60 ml/min."), mitomycinCDosageAjustmentGFR)
	lazy val atenolol = Drug("Atenolol", true, None, atenololDosageAdjustmentGFR)
	lazy val bisoprolol = Drug("Bisoprolol", true, None, bisoprololDosageAdjustmentGFR)
	lazy val amiloride = Drug("Amiloride", true, None, amilorideDosageAdjustmentGFR)
	lazy val triamterene = Drug("Triamterene", true, None, triamtereneDosageAdjustmentGFR)

	val cyclophosphamideDosageAjustmentGFR = Some(Map((1 to 10) -> 50, (11 to 20) -> 75, (12 to 90) -> 100))
	val mitomycinCDosageAjustmentGFR = Some(Map((1 to 10) -> 75, (11 to 90) -> 100))
	val atenololDosageAdjustmentGFR = Some(Map((1 to 9) -> 25, (10 to 50) -> 50, (51 to 90)-> 100))
	val bisoprololDosageAdjustmentGFR = Some(Map((1 to 9) -> 50, (10 to 50) -> 75, (51 to 90) -> 100))
	val amilorideDosageAdjustmentGFR = Some(Map((1 to 9) -> 0, (10 to 50) -> 50, (51 to 90) -> 100))
	val triamtereneDosageAdjustmentGFR = Some(Map((1 to 9) -> 0, (10 to 50) -> 100, (51 to 90) -> 100))

	private def getListOfAllDrugs: List[Drug] = {
		List(cyclophosphamide, mitomycinC, atenolol, bisoprolol, amiloride, triamterene)
	}

	def saveAllDrugsAsSeparateEntities(): Unit = {
		getListOfAllDrugs.foreach(drug=> saveJsonToFile(drug, drug.asJson.noSpaces))
	}



}
