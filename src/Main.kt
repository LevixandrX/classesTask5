import kotlin.math.sqrt
import kotlin.math.abs

data class Point(val x: Double, val y: Double) {
    fun distanceTo(other: Point): Double {
        return sqrt((other.x - x) * (other.x - x) + (other.y - y) * (other.y - y))
    }
}

class Triangle(val p1: Point, val p2: Point, val p3: Point) {
    private fun area(): Double {
        return 0.5 * abs(p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y))
    }

    fun exists(): Boolean {
        return area() > 0
    }

    private fun sideLength(a: Point, b: Point): Double {
        return a.distanceTo(b)
    }

    fun incircleRadius(): Double {
        val a = sideLength(p2, p3)
        val b = sideLength(p1, p3)
        val c = sideLength(p1, p2)
        val s = (a + b + c) / 2
        return area() / s
    }

    fun incircleCenter(): Point {
        val a = sideLength(p2, p3)
        val b = sideLength(p1, p3)
        val c = sideLength(p1, p2)
        val perimeter = a + b + c

        val xCenter = (a * p1.x + b * p2.x + c * p3.x) / perimeter
        val yCenter = (a * p1.y + b * p2.y + c * p3.y) / perimeter

        return Point(xCenter, yCenter)
    }
}

fun main() {
    println("Программа для нахождения центра и радиуса вписанной в треугольник окружности.")

    fun readCoordinate(prompt: String): Double {
        while (true) {
            print(prompt)
            val input = readLine()
            if (input.isNullOrBlank()) {
                println("Ошибка ввода: Ввод не может быть пустым.")
                continue
            }

            val number = input.toDoubleOrNull()
            if (number != null) return number

            println("Ошибка ввода: Пожалуйста, введите корректное число.")
        }
    }

    println("Введите координаты вершин треугольника:")
    val p1 = Point(readCoordinate("X1: "), readCoordinate("Y1: "))
    val p2 = Point(readCoordinate("X2: "), readCoordinate("Y2: "))
    val p3 = Point(readCoordinate("X3: "), readCoordinate("Y3: "))

    val triangle = Triangle(p1, p2, p3)

    if (!triangle.exists()) {
        println("Ошибка: Треугольник с заданными вершинами не существует. Точки лежат на одной прямой.")
        return
    }

    val incircleCenter = triangle.incircleCenter()
    val radius = triangle.incircleRadius()

    println("Координаты центра вписанной окружности: (${incircleCenter.x}, ${incircleCenter.y})")
    println("Радиус вписанной окружности: $radius")
}