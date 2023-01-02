package parsers

import convertSeparateToWholeRGB
import structures.Point
import java.awt.image.DataBufferByte
import java.io.File
import javax.imageio.ImageIO
import kotlin.io.path.Path
import kotlin.io.path.extension

object BuiltInPlaneParser : PlaneParser("AWT (java built in)") {
    override fun parse(filePath: String): Map<Int, List<Point>> {
        val file = File(filePath)
        val bufferedImage = ImageIO.read(file)
        val byteDataArray = (bufferedImage.data.dataBuffer as DataBufferByte).data

        val planesPoints = mutableMapOf<Int, MutableList<Point>>()

        val imageWidth = bufferedImage.width.toShort()
        var x: Short = 0
        var y: Short = 0
        var colorComponentsNumber = 3
        var colorByteOffset = 0
        if (Path(filePath).extension == "png") {
            colorComponentsNumber = 4
            colorByteOffset = 1
        }

        for (i in byteDataArray.indices step colorComponentsNumber) {
            val r = byteDataArray[i + colorByteOffset + 2].toUByte().toInt()
            val g = byteDataArray[i + colorByteOffset + 1].toUByte().toInt()
            val b = byteDataArray[i + colorByteOffset].toUByte().toInt()
            val rgbComponentsSum = convertSeparateToWholeRGB(r, g, b)
            if (rgbComponentsSum != 0) {
                if (!planesPoints.contains(rgbComponentsSum)) {
                    planesPoints[rgbComponentsSum] = mutableListOf()
                }
                planesPoints[rgbComponentsSum]?.add(Point(x, y))

            }

            ++x
            if (x == imageWidth) {
                x = 0
                ++y
            }
        }

        return planesPoints
    }
}
