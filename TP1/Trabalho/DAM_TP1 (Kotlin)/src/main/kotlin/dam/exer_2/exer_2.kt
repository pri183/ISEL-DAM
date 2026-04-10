package dam.exer_2

/*
Create a console-based calculator that allows users to perform various operations. Your
implementation should incorporate the following Kotlin features:

1. The when expression for handling different operations.
2. Exceptions to handle potential errors (e.g., division by zero, invalid input).
3. String templates and string formatting for a clear and structured output.

Your calculator should support the following:

(Ensure that the program provides user-friendly prompts and gracefully handles
invalid input.)
*/
fun main(args: Array<String>) {

    var done = false

    //1. Basic arithmetic operations: Addition, subtraction, multiplication, and division.
    val operacoesAritmeticas = OperacoesAritmeticas()
    //2. Boolean operators: AND (&&), OR (||), NOT (!).
    val operacoesBooleanas = OperacoesBooleanas()
    //3. Bitwise shift operators: Left shift (shl), right shift (shr).
    val operacoesBitwiseShift = OperacoesBitwiseShift()

    do{
        //Menu de operações
        println()
        println("======== Calculadora  ========")
        println("Que tipo de Operações deseja fazer? (Selecione um número)")
        println(" 1 - Operações Aritméticas Básicas;\n" +
                " 2 - Operações Booleanas;\n" +
                " 3 - Operações Bitwise Shift;\n" +
                " 0 - Sair;")

        //Buscar operações a serem feitas
        print(">")
        try{
            val input = readln().toInt()
            when (input){
                //Operações Aritméticas
                1 -> operacoesAritmeticas.menu()
                //Operações Boleanas
                2 -> operacoesBooleanas.menu()
                //Operações Bitwise Shift
                3 -> operacoesBitwiseShift.menu()
                //Sair
                0 -> done = true
                //Input Inválido
                else -> error("Entrada Inválida: ${input}.")
            }

        }catch(e: IllegalStateException ){
            //Informar o input inválido
            println("\n${e.message}")
            //Informar os inputs válidos disponíveis.
            println("Tente 1, 2, 3 ou 0 como opções.")
            //Voltar ao menu
            println("Pressione ENTER para continuar.")
            readln()


        }catch(e: NumberFormatException){ //Foi passado uma string em vez de números (exceção lançado pelo input = readln().toInt() )
            println()
            //Informar o input inválido
            println("Entrada Inválida: texto em vez de números.")
            //Voltar ao menu
            print("Pressione ENTER para tentar novamente.")
            readln()
        }
    }while(!done)
}
