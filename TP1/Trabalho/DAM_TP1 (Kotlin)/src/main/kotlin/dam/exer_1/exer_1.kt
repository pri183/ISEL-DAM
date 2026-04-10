package dam.exer_1

/*
Create and initialize an integer array with the first 50 perfect squares (12,22,32,...,502)
dynamically, using the following:
 */

fun main(args: Array<String>) {

    // a) Using IntArray constructor;
    val quad_perfeitos_a = IntArray(50, {(it+1)*(it+1)})
    println("\na)----------------------------------------- \n" + quad_perfeitos_a.joinToString())


    // b) Using a range and map();
    val numeros = (1..50)
    val quad_perfeitos_b = numeros.map { it * it }
    println("b)----------------------------------------- \n" +quad_perfeitos_b.joinToString())


    // c) Using Array with constructor;
    val quad_perfeitos_c = Array(50, {(it+1)*(it+1)})
    println("c)----------------------------------------- \n" +quad_perfeitos_c.joinToString())


}
