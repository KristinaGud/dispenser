import io.circe.{Encoder, KeyEncoder}
import io.circe.generic.semiauto.deriveEncoder

import java.io.{File, PrintWriter}

trait JSONWriter {

	implicit val drugEncoder: Encoder[Drug] = deriveEncoder[Drug]
	implicit val rangeKeyEncoder: KeyEncoder[Range.Inclusive] = (key: Range.Inclusive) => key.toString()

	def saveJsonToFile(drug: Drug, jsonString: String): Unit = {
		val writer = new PrintWriter(new File(s"./src/main/resources/entities/${drug.name.toLowerCase}"))
		try {
			writer.write(jsonString)
		} finally {
			writer.close()
		}
	}

}
