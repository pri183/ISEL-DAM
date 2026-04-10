package dam.exer_2

//fun main(args: Array<String>) {
//    val operacoes = OperacoesAritmeticas()
//    operacoes.menu()
//}

class OperacoesAritmeticas {

    fun menu() {

        var done = false

        do {
            //Menu de operações
            println()
            println("-------- Operações Aritméticas --------")
            println("Que tipo de Operações deseja fazer? (Selecione um número)")
            println(
                " 1 - Soma;\n" +
                        " 2 - Subtração;\n" +
                        " 3 - Multiplicação;\n" +
                        " 4 - Divisão;\n" +
                        " 0 - Voltar ao Menu Inicial;"
            )

            //Buscar operações a serem feitas
            print(">")
            try {
                val input = readln().toInt()
                println()
                when (input) {
                    //Soma
                    1 -> soma()
                    //Subtração
                    2 -> subtracao()
                    //Multiplicação
                    3 -> multiplicacao()
                    //Divisão
                    4 -> divisao()
                    //Voltar/sair
                    0 -> done = true
                    //Input Inválido
                    else -> error("Entrada Inválida: ${input}.")
                }

            } catch (e: IllegalStateException) {
                //Informar o input inválido
                println("\n${e.message}")
                //Informar os inputs válidos disponíveis.
                println("Tente 1, 2, 3, 4 ou 0 como opções")
                //Voltar ao menu
                println("Pressione ENTER para continuar")
                readln()
            } catch (e: NumberFormatException) { //Foi passado uma string em vez de números (exceção lançado pelo input = readln().toInt() )
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

    fun soma() {

        var parcelaStr: String

        println("++++++ SOMA ++++++")

        //Parcela nº1:
        do {
            //Pedir a parcela:
            print("Insira a primeira parcela:")
            //Obter parcela
            parcelaStr = readln().trim()
            //Verificar parcela
        } while (!verificarNumero(parcelaStr))
        //Converter para double
        val parcela_1 = parcelaStr.toDouble()


        //Parcela nº2:
        do {
            //Pedir a parcela:
            print("Insira a segunda parcela:")
            //Obter parcela
            parcelaStr = readln().trim()
            //Verificar parcela
        } while (!verificarNumero(parcelaStr))
        //Converter para double
        val parcela_2 = parcelaStr.toDouble()

        //Fazer a soma
        val soma = parcela_1 + parcela_2

        //Devolver o resultado
        println("\n(${parcela_1}) + (${parcela_2}) = ${String.format("%.2f", soma)}")

        //Voltar
        println("\nPressione ENTER para voltar.")
        readln()
    }


    fun subtracao() {

        var parcelaStr: String

        println("------ SUBTRAÇÃO ------")

        //Parcela nº1
        do {
            //Pedir a parcela:
            print("Insira a primeira parcela:")
            //Obter parcela
            parcelaStr = readln().trim()
            //Verificar parcela
        } while (!verificarNumero(parcelaStr))
        //Converter para double
        val parcela_1 = parcelaStr.toDouble()

        //Parcela nº2:
        do {
            //Pedir a parcela:
            print("Insira a segunda parcela:")
            //Obter parcela
            parcelaStr = readln().trim()
            //Verificar parcela
        } while (!verificarNumero(parcelaStr))
        //Converter para double
        val parcela_2 = parcelaStr.toDouble()

        //Fazer a subtração
        val diferenca = parcela_1 - parcela_2

        //Devolver o resultado
        println("\n(${parcela_1}) - (${parcela_2}) = ${String.format("%.2f", diferenca)}")

        //Voltar
        println("\nPressione ENTER para voltar.")
        readln()
    }


    fun multiplicacao() {

        println("×××××× MULTIPLICAÇÃO ××××××")
        var fatorStr: String

        //Fator nº1:
        do {
            //Pedir a fator:
            print("Insira o primeiro fator:")
            //Obter fator
            fatorStr = readln().trim()
            //Verificar parcela
        } while (!verificarNumero(fatorStr))
        //Converter para double
        val fator_1 = fatorStr.toDouble()

        //Fator nº2:
        do {
            //Pedir a fator:
            print("Insira o segundo fator:")
            //Obter fator
            fatorStr = readln().trim()
            //Verificar parcela
        } while (!verificarNumero(fatorStr))
        //Converter para double
        val fator_2 = fatorStr.toDouble()

        //Fazer a multiplicação
        val produto = fator_1 * fator_2

        //Devolver o resultado
        println("\n(${fator_1}) * (${fator_2}) = ${String.format("%.2f", produto)}")

        //Voltar
        println("\nPressione ENTER para voltar.")
        readln()
    }


    fun divisao() {

        var numeroStr: String

        println("÷÷÷÷÷÷ DIVISÃO ÷÷÷÷÷÷")

        //Numerador:
        do {
            //Pedir a numero:
            print("Insira o numerador (numerador÷denominador):")
            //Obter numero
            numeroStr = readln().trim()
            //Verificar parcela
        } while (!verificarNumero(numeroStr))
        //Converter para double
        val numerador = numeroStr.toDouble()

        //Denominador:
        var denominador_valido = false //Para testar se o denominador é diferente que zero
        do {
            try {
                //Pedir a parcela:
                print("Insira o denominador (numerador÷denominador):")
                //Obter numero
                numeroStr = readln().trim()
                //Verificar numero
                if (verificarNumero(numeroStr)) {
                    //Verificar se é diferente de zero
                    require(numeroStr.toDouble() != 0.0) { "ERRO: O denominadador não pode ser igual a zero." }
                    denominador_valido = true
                }

            } catch (e: IllegalArgumentException) {
                println()
                println(e.message)
                println("Prima ENTER para tentar novamente")
                readln()
                println()
            }
        } while (!denominador_valido)
        //Converter para double
        val denominador = numeroStr.toDouble()

        //Fazer a subtração
        val quociente = numerador / denominador

        //Devolver o resultado (com mais casas decimais para números mais pequenos)
        println("\n(${numerador}) ÷ (${denominador}) = ${String.format("%.4f", quociente)}")

        //Voltar
        println("\nPressione ENTER para voltar.")
        readln()
    }


    //Funções auxiliares
    /*
    * Verifica se um número tem como converter para double, e assim trata a exceção resultante.
    * Ao retornar um valor booleano, é usado como flag para continuar a operação ou pedir
    * o número novamente.
    * */
    fun verificarNumero(parcelaStr: String): Boolean {


        try {
            parcelaStr.toDouble()
            //Se não der erro continuará, e será considerado como válido
            return true
        } catch (e: NumberFormatException) {
            println()
            println("Entrada Inválida.")
            println(
                "Não utilize ',' ,texto ou outro tipo de carácter a não ser números, '.' e '-' para números decimais ou/e negativos, respetivamente." +
                        "\nSiga um formato semelhante aos exemplos:" +
                        "\n-> -12.3456" +
                        "\n-> 25333" +
                        "\n-> 956.2785638"
            )
            print("Pressione ENTER para tentar novamente.")
            readln()
            println()
        }

        //Retorna falso após apanhar o erro e executado a exceção
        return false
    }
}