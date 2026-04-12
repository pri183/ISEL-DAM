package DAM.exer_4

import kotlin.math.sqrt
import kotlin.math.pow


//Passo 1
data class Vec2 (var x : Double,var y : Double): Comparable <Vec2>{

    //Passo 2
    operator fun plus(other : Vec2): Vec2{
        return Vec2(x+other.x, y+other.y)
    }

    operator fun minus(other : Vec2): Vec2{
        return Vec2(x-other.x, y-other.y)
    }

    operator fun times(scale : Double): Vec2{
        return Vec2(x*scale, y*scale)
    }

    operator fun unaryMinus(): Vec2{
        return Vec2(-x, -y)
    }

    //Passo 3
    override fun compareTo(other: Vec2): Int = magnitude().compareTo(other.magnitude())

    //Passo 4
    fun magnitude(): Double{
        return sqrt((x).pow(2) + (y).pow(2))
    }
    fun dot(other: Vec2): Double{
        return (x*other.x) + (y*other.y)
    }

    fun normalized(): Vec2{

        check(this.magnitude() != 0.00) {"Vetor nulo, não tem como normalizar."}

        return Vec2(x/this.magnitude(), y/this.magnitude())
    }


    //passo 5
    operator fun get(index: Int): Double{

        if (index > 1 || index < 0) throw IndexOutOfBoundsException("Index deve ser 0(Vec2.x) ou 1 (Vec2.y)")

        return if(index == 0) x else y

    }
}


//passo 6
fun main() {
    val a = Vec2(3.0, 4.0)
    val b = Vec2(1.0, 2.0)

    println("a = $a")           // a = Vec2(x=3.0, y=4.0)
    println("b = $b")           // b = Vec2(x=1.0, y=2.0)
    println("a + b = ${a + b}") // a + b = Vec2(x=4.0, y=6.0)
    println("a - b = ${a - b}") // a - b = Vec2(x=2.0, y=2.0)
    println("a * 2.0 = ${a * 2.0}") // a * 2.0 = Vec2(x=6.0, y=8.0)
    println("-a = ${-a}")       // -a = Vec2(x=-3.0, y=-4.0)
    println("|a| = ${a.magnitude()}") // |a| = 5.0
    println("a dot b = ${a.dot(b)}") // a dot b = 11.0
    println("norm(a) = ${a.normalized()}") // norm(a) = Vec2(x=0.6, y=0.8)
    println("a[0] = ${a[0]}")   // a[0] = 3.0
    println("a[1] = ${a[1]}")   // a[1] = 4.0
    println("a > b = ${a > b}") // a > b = true
    println("a < b = ${a < b}") // a < b = false

    val vectors = listOf(Vec2(1.0, 0.0), Vec2(3.0, 4.0), Vec2(0.0, 2.0))
    println("Longest = ${vectors.max()}")  // Longest = Vec2(x=3.0, y=4.0)
    println("Shortest = ${vectors.min()}") // Shortest = Vec2(x=1.0, y=0.0)
}


