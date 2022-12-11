package parsers

import convertSeparateToWholeRGB
import org.openimaj.image.ImageUtilities
import structures.Point
import java.io.File

object OpenImaJPlaneParser : PlaneParser("OpenImaj") {
    override fun parse(filePath: String): Map<Int, MutableList<Point>> {
        val file = File(filePath)
        val image = ImageUtilities.readMBF(file)

        val planesPoints = mutableMapOf<Int, MutableList<Point>>()

        for (y in 0 until image.height)
            for (x in 0 until image.width) {
                val colourSegments = image.getPixel(x, y).map { (it * 255).toInt() }
                val r = colourSegments[0]
                val g = colourSegments[1]
                val b = colourSegments[2]
                val colour = convertSeparateToWholeRGB(r, g, b)
                if (colour != 0) {
                    if (!planesPoints.contains(colour)) {
                        planesPoints[colour] = mutableListOf()
                    }
                        planesPoints[colour]?.add(Point(x.toShort(), y.toShort()))
                }
            }

        return planesPoints
    }
}
