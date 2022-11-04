import nu.pattern.OpenCV
import parsers.*

const val measurementsNumber = 100

fun main() {
    OpenCV.loadShared()

    val parsers = listOf(
        OpenCVPlaneParser,
        BoofCVPlaneParser,
        BuiltInPlaneParser,
        KorimPlaneParser,
        OpenImaJPlaneParser,
        ScrimagePlaneParser
    )

    val imageFileNames = listOf(
        "big.png",
        "normal.png",
        "small.png",
        "big.jpg",
        "normal.jpg",
        "small.jpg"
    )

    println("Number of measurements for each parser: $measurementsNumber")
    println("A measured time is in milliseconds.")
    imageFileNames.forEach { imageFileName ->
        println("Parsing image: $imageFileName")
        parsers.forEach {
            val averageParsingTime = PlaneParserTester.measureAvgParsingTimeMillis(
                it,
                "src/main/resources/$imageFileName",
                measurementsNumber
            )
            println("${it.name} average parsing time: $averageParsingTime")
        }
        println("==================================================")
        println("==================================================\n")
    }
}
