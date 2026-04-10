package dam.exer_2

//fun main(args: Array<String>) {
//    val operacoes = OperacoesBitwiseShift()
//    operacoes.menu()
//}

class OperacoesBitwiseShift{

    fun menu(){
        var done = false

        do {
            //Menu de operações
            println()
            println("-------- Operações Bitwise Shift--------")
            println("Que tipo de Operações deseja fazer? (Selecione um número)")
            println(
                " 1 - Shift para Esquerda;\n" +
                        " 2 - Shift para Direita;\n" +
                        " 0 - Voltar ao Menu Inicial;"
            )

            //Buscar operações a serem feitas
            print(">")
            try {
                val input = readln().toInt()
                println()
                when (input) {
                    //And
                    1 -> leftShift()
                    //or
                    2 -> rightShift()
                    //Voltar/sair
                    0 -> done = true
                    //Input Inválido
                    else -> error("Entrada Inválida: ${input}.")
                }
            }
            catch (e: IllegalStateException) {
                //Informar o input inválido
                println("\n${e.message}")
                //Informar os inputs válidos disponíveis.
                println("Tente 1, 2 ou 0 como opções")
                //Voltar ao menu
                println("Pressione ENTER para continuar")
                readln()
            }
            catch (e: NumberFormatException) { //Foi passado uma string em vez de números (exceção lançado pelo input = readln().toInt() )
                println()
                //Informar o input inválido
                println("Entrada Inválida: texto em vez de números.")
                //Voltar ao menu
                print("Pressione ENTER para tentar novamente.")
                readln()
            }

        } while (!done)
        println()

    }

    fun leftShift(){
        var valorStr: String

        println("<<<<<< Shift para Esquerda <<<<<< ")

        //Numero:
        do {
            //Pedir :
            print("Insira o A (A << B):")
            //Obter valor
            valorStr = readln().trim()
            //Verificar valor
        } while (!verificarNumero(valorStr))
        //Converter para double
        val numero = valorStr.toInt()


        //Shift:
        //Este tem que estar compreendido entre 0 e 31 pois trabalhamos com integers
        var shift_valido = false
        do {
            try {
                //Pedir a valor:
                print("Insira o valor B (0 a 31) (A << B):")
                //Obter valor
                valorStr = readln().trim()
                //Verificar valor
                if (verificarNumero(valorStr)) {
                    //Conferir se está compreendido entre 0 e 31, pois como trabalhamos com integers, que só usam 5 bits,
                    //caso o shift seja superior a 31 ele dá a volta.
                    require(valorStr.toInt() in 0..31) { "Erro: O shift tem que estar compreendido entre 0 e 31."}
                    shift_valido = true
                }
            }
            catch(e : IllegalArgumentException){
                println()
                println(e.message +
                    "\nPressione ENTER para tentar novamente.")
                readln()
            }

        } while (!shift_valido)
        //Converter para double
        val shift = valorStr.toInt()

        //Fazer a resultado
        val resultado = numero shl shift

        //Devolver o resultado
        println("\n${numero} << ${shift} = ${resultado} (HEX: ${resultado.toString(16).uppercase()})")

        //Voltar
        println("\nPressione ENTER para voltar.")
        readln()

    }

    fun rightShift(){
        var valorStr: String

        println(">>>>>> Shift para Direita >>>>>> ")

        //Numero:
        do {
            //Pedir :
            print("Insira o A (A >> B):")
            //Obter valor
            valorStr = readln().trim()
            //Verificar valor
        } while (!verificarNumero(valorStr))
        //Converter para double
        val numero = valorStr.toInt()


        //Shift:
        //Este tem que estar compreendido entre 0 e 31 pois trabalhamos com integers
        var shift_valido = false
        do {
            try {
                //Pedir a valor:
                print("Insira o valor B (0 a 31) (A >> B):")
                //Obter valor
                valorStr = readln().trim()
                //Verificar valor
                if (verificarNumero(valorStr)) {
                    //Conferir se está compreendido entre 0 e 31, pois como trabalhamos com integers, que só usam 5 bits,
                    //caso o shift seja superior a 31 ele dá a volta.
                    require(valorStr.toInt() in 0..31) { "Erro: O shift tem que estar compreendido entre 0 e 31."}
                    shift_valido = true
                }
            }
            catch(e : IllegalArgumentException){
                println()
                println(e.message +
                        "\nPressione ENTER para tentar novamente.")
                readln()
            }

        } while (!shift_valido)
        //Converter para double
        val shift = valorStr.toInt()

        //Fazer a resultado
        val resultado = numero shr shift

        //Devolver o resultado
        println("\n${numero} >> ${shift} = ${resultado} (HEX: ${resultado.toString(16).uppercase()})")

        //Voltar
        println("\nPressione ENTER para voltar.")
        readln()

    }

    fun verificarNumero(numeroStr: String): Boolean {


        try {
            numeroStr.toInt()
            //Se não der erro continuará, e será considerado como válido
            return true
        } catch (e: NumberFormatException) {
            println()
            println("Entrada Inválida.")
            println(
                "Não utilize ',' ou '.' ,texto ou outro tipo de carácter a não ser números e '-' para números negativos." +
                        "\nSão só aceitos números inteiros, com formato semelhante a estes:" +
                        "\n-> -12" +
                        "\n-> 25333" +
                        "\n-> 956" +
                        "\nPressione ENTER para tentar novamente."
            )
            readln()
            println()
        }

        //Retorna falso após apanhar o erro e executado a exceção
        return false
    }

}