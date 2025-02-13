case class Drug(
								 name: String,
								 effectsKidneysFailure: Boolean,
								 notes: Option[String],
								 doses: Option[Map[Range.Inclusive, Int]]
							 )



