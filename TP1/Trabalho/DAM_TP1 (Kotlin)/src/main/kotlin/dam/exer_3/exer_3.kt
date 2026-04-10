package dam.exer_3

fun main(args: Array<String>) {

    var altura_inicial = 100.00 //metros

    val saltos = generateSequence(altura_inicial) {(it*0.6).takeIf {it >1}}

    println("\nOs 15 primeiros saltos:" +
            "\n${saltos.take(15).joinToString(separator = "\n"){"%.2f m".format (it)}}")

}