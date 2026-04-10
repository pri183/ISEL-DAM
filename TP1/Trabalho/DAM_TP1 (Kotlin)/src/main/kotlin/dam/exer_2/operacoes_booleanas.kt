package dam.exer_2


//fun main(args: Array<String>) {
//    val operacoes = OperacoesBooleanas()
//    operacoes.menu()
//}

class OperacoesBooleanas {

    fun menu() {

        var done = false

        do {
            //Menu de operações
            println()
            println("-------- Operações Booleanas--------")
            println("Que tipo de Operações deseja fazer? (Selecione um número)")
            println(
                " 1 - AND;\n" +
                        " 2 - OR;\n" +
                        " 3 - NOT;\n" +
                        " 0 - Voltar ao Menu Inicial;"
            )

            //Buscar operações a serem feitas
            print(">")
            try {
                val input = readln().toInt()
                println()
                when (input) {
                    //And
                    1 -> and()
                    //or
                    2 -> or()
                    //Not
                    3 -> not()
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
                println("Tente 1, 2, 3 ou 0 como opções")
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

    fun and() {
        var valorStr: String

        println("&&&&&& AND &&&&&&")

        //A:
        do {
            //Pedir a valor:
            print("Insira o valor A (A && B):")
            //Obter valor
            valorStr = readln().trim().lowercase()
            //Verificar valor
        }
        while (!verificarInput(valorStr))
        //Converter para double
         val a = valorStr.toBoolean()


        //B:
        do {
            //Pedir a valor:
            print("Insira o valor B (A && B):")
            //Obter valor
            valorStr = readln().trim().lowercase()
            //Verificar valor
        }
        while (!verificarInput(valorStr))
        //Converter para double
        val b = valorStr.toBoolean()

        //Fazer a soma
        val resultado = a && b

        //Devolver o resultado
        println("\n${a} && ${b} = ${resultado}")

        //Voltar
        println("\nPressione ENTER para voltar.")
        readln()

    }

    fun or() {
        var valorStr: String

        println("|||||| OR ||||||")

        //A:
        do {
            //Pedir a valor:
            print("Insira o valor A (A || B):")
            //Obter valor
            valorStr = readln().trim().lowercase()
            //Verificar valor
        }
        while (!verificarInput(valorStr))
        //Converter para double
        val a = valorStr.toBoolean()


        //B:
        do {
            //Pedir a valor:
            print("Insira o valor B (A || B):")
            //Obter valor
            valorStr = readln().trim().lowercase()
            //Verificar valor
        }
        while (!verificarInput(valorStr))
        //Converter para double
        val b = valorStr.toBoolean()

        //Fazer a soma
        val resultado = a || b

        //Devolver o resultado
        println("\n${a} || ${b} = ${resultado}")

        //Voltar
        println("\nPressione ENTER para voltar.")
        readln()

    }

    fun not() {
        var valorStr: String

        println("!!!!!! NOT !!!!!!")

        //A:
        do {
            //Pedir a valor:
            print("Insira o valor booleano:")
            //Obter valor
            valorStr = readln().trim().lowercase()
            //Verificar valor
        }
        while (!verificarInput(valorStr))
        //Converter para double
        val a = valorStr.toBoolean()



        //Fazer a soma
        val resultado = !a

        //Devolver o resultado
        println("\n!${a} = ${resultado}")

        //Voltar
        println("\nPressione ENTER para voltar.")
        readln()

    }

    /*
    * Verificar se o input é igual 1 ou 0 ou true ou false
    * */
    fun verificarInput(valorStr : String): Boolean{

        try {
            check(valorStr.trim()== "true" || valorStr == "false"){"$'{valorStr}' não é um valor aceite"}
            //Se não der erro continuará, e será considerado como válido
            return true
        }
        catch (e: IllegalStateException) {
            println()
            println("Entrada Inválida." +
                    "\n${e.message} " +
                    "\nOs únicos valores aceites são true(verdadeiro) ou false(falso)." +
                    "\nPressione ENTER para tentar novamente.")
            readln()
            println()
        }
        //Retorna falso após apanhar o erro e executado a exceção
        return false

    }
}