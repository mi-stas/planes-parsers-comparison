package parsers

import com.sksamuel.scrimage.ImmutableImage
import structures.Point
import java.io.File

object ScrimagePlaneParser : PlaneParser("Scrimage") {
    private const val backgroundColorOffset = 16777216
    override fun parse(filePath: String): Array<Point> {
        val file = File(filePath)
        val image = ImmutableImage.loader().fromFile(file)

        val planePoints = mutableListOf<Point>()

        image.forEach { pixel ->
            if (pixel.noalpha().argb + backgroundColorOffset != 0)
                planePoints.add(Point(pixel.x.toShort(), pixel.y.toShort()))
        }

        return planePoints.toTypedArray()
    }
}
