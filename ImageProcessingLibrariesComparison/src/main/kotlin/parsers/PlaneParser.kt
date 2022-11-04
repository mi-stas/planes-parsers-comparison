package parsers

import structures.Point

abstract class PlaneParser(val name: String) {
    abstract fun parse(filePath: String): Array<Point>
}
