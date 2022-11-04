package parsers

import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.toVfs
import kotlinx.coroutines.runBlocking
import structures.Point
import java.io.File

object KorimPlaneParser : PlaneParser("Korim") {
    override fun parse(filePath: String): Array<Point> {
        val file = File(filePath)

        RegisteredImageFormats.register(PNG)
        RegisteredImageFormats.register(JPEGInfo)
        val bitmap: Bitmap32
        runBlocking {
            bitmap = file.toVfs().readBitmap(ImageDecodingProps.DEFAULT).toBMP32IfRequired()
        }

        val colorPoints = mutableListOf<Point>()
        bitmap.forEach { _, x, y ->
            if (bitmap[x, y].rgb != 0)
                colorPoints.add(Point(x.toShort(), y.toShort()))
        }

        return colorPoints.toTypedArray()
    }
}

