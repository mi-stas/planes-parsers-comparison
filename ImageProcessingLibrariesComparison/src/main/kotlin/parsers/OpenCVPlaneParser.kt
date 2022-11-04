package parsers

import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.MatOfPoint
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import structures.Point

object OpenCVPlaneParser : PlaneParser("OpenCV") {
    override fun parse(filePath: String): Array<Point> {
        val mat = Imgcodecs.imread(filePath)
        val grayMat = Mat()
        Imgproc.cvtColor(mat, grayMat, Imgproc.COLOR_BGR2GRAY)

        val pointsMat = Mat()
        Core.findNonZero(grayMat, pointsMat)
        val planeMatPoints = MatOfPoint(pointsMat).toList()

        return planeMatPoints.map {
            Point(it.x.toInt().toShort(), it.y.toInt().toShort())
        }.toTypedArray()
    }
}

