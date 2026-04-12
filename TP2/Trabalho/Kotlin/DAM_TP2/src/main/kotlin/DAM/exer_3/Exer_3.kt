package DAM.exer_3

//Passo 1
class Pipeline{

    var steps = linkedMapOf <String, (List<String>) -> List<String>> () //LinkedHashMap para garantir a ordem

    fun addStage(name: String, transform: (List<String>)-> List<String>){
        steps.put(name, transform)
    }

    fun execute(input: List<String>): List<String> {
        var lista = input
        steps.values.forEach { transform ->
            lista = transform(lista) } // isto ainda é bem confuso

        return lista

    }

    fun describe(){

        val steps_names = steps.keys.toList()

        println("\nPipeline stages:")
        for(i in 0 .. steps_names.size-1){
            val step = steps_names[i]
            println(""+(i+1)+". "+step+"")
        }
    }



}

//Passo 2
fun buildPipeline(builder: Pipeline.() -> Unit): Pipeline{
    val ppl = Pipeline()
    ppl.builder()
    return ppl
}

val pipeline = buildPipeline{
    addStage("Trim") {lista_string -> lista_string.map{ it.trim()}}
    addStage("Filter errors") {lista_string -> lista_string.filter{ it.contains("ERROR")}}
    addStage("Uppercase") {lista_string -> lista_string.map{ it.uppercase()}}
    addStage("Add index") {lista_string -> lista_string.map{"" + (lista_string.indexOf(it)+1) + ". " + it} }
}


fun main() {

    val logs = listOf(
        " INFO: server started ",
        " ERROR: disk full ",
        " DEBUG: checking config ",
        " ERROR: out of memory ",
        " INFO: request received ",
        " ERROR: connection timeout "
    )


    pipeline.describe()

    println("\nResult:")
    println(pipeline.execute(logs).joinToString(separator = "\n"))
}