package DAM.exer_2

//Passo 1
class Cache <K :Any, V: Any > (){

    val entries = mutableMapOf<K,V>()

    fun put(key :K, value : V){
        entries.put(key,value)
    }

    fun get(key:K): V?{
        return entries.get(key) // a própria função já retorna nulo caso não exista uma entrada
    }

    fun evict(key: K){
        entries.remove(key)
    }

    fun size(): Int{
        return entries.size
    }


    //Passo 2
    fun getOrPut (key: K, default: () -> V): V{

        var value = this.get(key)

        //caso o valor exista para essa chave, devolver esse valor
        if(value != null){
            return value
        } else {
            //Caso não exista cria um valor, guarda e devolve a chave
            value = default()
            this.put(key, value)
            return value
        }
    }

    //Passo 3
    fun transform(key : K , action: (V) -> V): Boolean{
        val value = this.get(key)

        if(value == null) { //Se não existir uma chave, o valor será nulo portanto não pode transformar
            return false
        } else{
            val new_value = action(value) //Aplica a transformação ao valor
            this.put(key, new_value) //Subtitui o antigo valor
            return true
        }
    }

    //Passo 4
    fun snapshot(): Map <K,V>{

        return this.entries.toMap()
    }
}


fun main() {

    //Criar os dois caches
    val word_freq_cache = Cache<String, Int>()
    val id_regestry_cache = Cache<Int, String>()

    //Word Frequency Cache-----------
    println("\n--- Word frequency cache ---")

    //Adicionar as palavras e frequencias da mesmas
    word_freq_cache.put("kotlin",1)
    word_freq_cache.put("scala",1)
    word_freq_cache.put("haskell",1)

    //Mostrar os outputs e funcionamento das funções criadas
    println("Size: "+word_freq_cache.size()+"") // 3

    println("Frequency of \"kotlin\": "+word_freq_cache.get("kotlin")+"") //1

    println("getOrPut \"kotlin\": "+word_freq_cache.getOrPut("kotlin"){0}+"") //1
    println("getOrPut \"java\": "+word_freq_cache.getOrPut("java"){0}+"") //0
    println("Size after getOrPut: "+word_freq_cache.size()+"") // 4

    println("Transform \"kotlin\" (+1): "+word_freq_cache.transform("kotlin"){it + 1}+"") //true
    println("Transform \"cobol\" (+1): "+word_freq_cache.transform("cobol"){it + 1}+"") //false

    println("Snapshot: "+word_freq_cache.snapshot()+"") //{kotlin=2, scala=1, haskell=1, java=0}



    println("\n--- Id registry cache ---")

    //Adicionar ids e nomes
    id_regestry_cache.put(1, "Alice")
    id_regestry_cache.put(2, "Bob")

    //Mostrar os outputs e funcionamento das funções criadas
    println("Id 1-> "+id_regestry_cache.get(1)+"") //Alice
    println("Id 2-> "+id_regestry_cache.get(2)+"")// Bob

    id_regestry_cache.evict(1)
    println("After evict id 1, size: "+id_regestry_cache.size()+"") //1

    println("Id 1 after evict-> "+id_regestry_cache.get(1)+"") //null

}





