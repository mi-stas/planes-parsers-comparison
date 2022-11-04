package parsers

import boofcv.io.image.ConvertBufferedImage
import boofcv.io.image.UtilImageIO.loadImage
import boofcv.struct.image.GrayU8
import structures.Point

object BoofCVPlaneParser : PlaneParser("BoofCV") {
    override fun parse(filePath: String): Array<Point> {
        val image = loadImage(filePath)
        val grayImage = ConvertBufferedImage.convertFromSingle(image, null, GrayU8::class.java)

        val colourPoints = mutableListOf<Point>()

        grayImage.forEachPixel { x, y, value ->
            if (value > 0)
                colourPoints.add(Point(x.toShort(), y.toShort()))
        }

        return colourPoints.toTypedArray()
    }
}
