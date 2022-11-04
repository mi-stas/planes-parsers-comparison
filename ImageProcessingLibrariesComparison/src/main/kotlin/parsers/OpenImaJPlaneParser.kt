package parsers

import org.openimaj.image.ImageUtilities
import structures.Point
import java.io.File

object OpenImaJPlaneParser : PlaneParser("OpenImaj") {
    override fun parse(filePath: String): Array<Point> {
        val file = File(filePath)
        val image = ImageUtilities.readMBF(file)

        val colourPoints = mutableListOf<Point>()

        for (y in 0 until image.height)
            for (x in 0 until image.width) {
                if (image.getPixel(x, y).sum() != 0.0f)
                    colourPoints.add(Point(x.toShort(), y.toShort()))
            }

        return colourPoints.toTypedArray()
    }
}
