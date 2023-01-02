import nu.pattern.OpenCV
import parsers.*
import kotlin.math.sqrt

const val measurementsNumber = 10000

fun main() {
    OpenCV.loadShared()

    val parsers = listOf(
        BuiltInPlaneParser,
        KorimPlaneParser,
        OpenImaJPlaneParser,
        ScrimagePlaneParser
    )

    val imageFileNames = listOf(
        "planes_example_small.png",
        "planes_example_medium.png",
        "planes_example_big.png",
        "planes_example_small.jpg",
        "planes_example_medium.jpg",
        "planes_example_big.jpg"
    )

    println("Number of measurements for each parser: $measurementsNumber")
    println("A measured time is in milliseconds.")
    imageFileNames.forEach { imageFileName ->
        println("Parsing image: $imageFileName")
        parsers.forEach {
            val averageParsingTime = PlaneParserTester.measureParsingTime(
                it,
                "src/main/resources/$imageFileName",
                measurementsNumber
            )
            println("${it.name} average parsing time: ${averageParsingTime.first}")
            println("${it.name} standard deviation: ${sqrt(averageParsingTime.second)}")
        }
        println("==================================================")
        println("==================================================\n")
    }
}
