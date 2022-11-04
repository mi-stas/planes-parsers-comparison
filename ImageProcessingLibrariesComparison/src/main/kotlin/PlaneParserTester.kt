import parsers.PlaneParser
import kotlin.system.measureTimeMillis

object PlaneParserTester {
    fun measureAvgParsingTimeMillis(parser: PlaneParser, filePath: String, measurementNumber: Int): Double {
        val measurementTimes = mutableListOf<Long>()
        repeat (measurementNumber) {
            measurementTimes.add(measureTimeMillis { parser.parse(filePath) })
        }

        return measurementTimes.average()
    }
}
