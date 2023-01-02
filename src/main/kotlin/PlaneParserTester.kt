import parsers.PlaneParser
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

object PlaneParserTester {
    private const val measurementOffset = 100
    fun measureParsingTime(parser: PlaneParser, filePath: String, measurementNumber: Int): Pair<Double, Double> {
        val measurementTimes = mutableListOf<Double>()
       for (i in 1..(measurementNumber + measurementOffset)) {
           if (i > measurementOffset) {
               measurementTimes.add(measureNanoTime { parser.parse(filePath) } / 1e6)
           }
        }
        val expectation = measurementTimes.average()
        val variance = findVariance(measurementTimes)

        return Pair(expectation, variance)
    }

    private fun findVariance(times: List<Double>): Double {
        val expectation = times.average()
        return times.map {
            (it - expectation) * (it - expectation)
        }.average()
    }
}
