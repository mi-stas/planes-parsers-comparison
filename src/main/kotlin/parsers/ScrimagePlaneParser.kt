package parsers

import BACKGROUND_COLOR_OFFSET
import com.sksamuel.scrimage.ImmutableImage
import structures.Point
import java.io.File

object ScrimagePlaneParser : PlaneParser("Scrimage") {
    override fun parse(filePath: String): Map<Int, List<Point>> {
        val file = File(filePath)
        val image = ImmutableImage.loader().fromFile(file)

        val planesPoints = mutableMapOf<Int, MutableList<Point>>()

        image.forEach { pixel ->
            val colour = pixel.noalpha().argb + BACKGROUND_COLOR_OFFSET
            if (colour != 0) {
                if (!planesPoints.contains(colour)) {
                    planesPoints[colour] = mutableListOf()
                }
                planesPoints[colour]?.add(Point(pixel.x.toShort(), pixel.y.toShort()))
            }
        }

        return planesPoints
    }
}
