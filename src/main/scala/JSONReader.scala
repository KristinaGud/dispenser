import io.circe.{Decoder, KeyDecoder}
import io.circe.generic.semiauto.deriveDecoder
import io.circe.jawn.decode
import scala.io.Source

trait JSONReader {

	implicit val drugDecoder: Decoder[Drug] = deriveDecoder[Drug]
	implicit val rangeKeyDecoder: KeyDecoder[Range.Inclusive] = (key: String) => {
		val split = key.split(" to ")
		val first = split.head.substring(6).toInt
		val last = split(1).toInt
		Option(first to last)
	}

	def getDrug(name: String): Drug = {
		val resource = Source.fromResource(s"entities/${name.toLowerCase}")
		val json = resource.getLines.mkString
		decode[Drug](json).getOrElse(throw new RuntimeException(s"no drug file found for $name"))
	}
}
