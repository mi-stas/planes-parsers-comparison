package parsers

import structures.Point
import java.awt.image.DataBufferByte
import java.io.File
import javax.imageio.ImageIO
import kotlin.io.path.Path
import kotlin.io.path.extension

object BuiltInPlaneParser : PlaneParser("AWT (java built in)") {
    override fun parse(filePath: String): Array<Point> {
        val file = File(filePath)
        val bufferedImage = ImageIO.read(file)
        val byteDataArray = (bufferedImage.data.dataBuffer as DataBufferByte).data

        val planePoints = mutableListOf<Point>()

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
            val rgbComponentsSum = byteDataArray[i + colorByteOffset].toUByte() +
                    byteDataArray[i + colorByteOffset + 1].toUByte() +
                    byteDataArray[i + colorByteOffset + 2].toUByte()
            if (rgbComponentsSum != 0u)
                planePoints.add(Point(x, y))

            ++x
            if (x == imageWidth) {
                x = 0
                ++y
            }
        }

        return planePoints.toTypedArray()
    }
}
