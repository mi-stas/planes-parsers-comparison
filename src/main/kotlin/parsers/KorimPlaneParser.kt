package parsers

import BACKGROUND_COLOR_OFFSET
import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.toVfs
import convertSeparateToWholeRGB
import kotlinx.coroutines.runBlocking
import structures.Point
import java.io.File

object KorimPlaneParser : PlaneParser("Korim") {
    override fun parse(filePath: String): Map<Int, List<Point>> {
        val file = File(filePath)

        RegisteredImageFormats.register(PNG)
        RegisteredImageFormats.register(JPEGInfo)
        val bitmap: Bitmap32
        runBlocking {
            bitmap = file.toVfs().readBitmap(ImageDecodingProps.DEFAULT).toBMP32IfRequired()
        }

        val planesPoints = mutableMapOf<Int, MutableList<Point>>()
        bitmap.forEach { _, x, y ->
            val bitmapCell = bitmap[x, y]
            val r = bitmapCell.r
            val g = bitmapCell.g
            val b = bitmapCell.b
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

