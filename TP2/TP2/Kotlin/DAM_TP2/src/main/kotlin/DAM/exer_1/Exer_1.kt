package DAM.exer_1


//Passo 1
sealed class Event(val username: String, val timestamp: Long)
class Login(username:String, timestamp: Long) : Event(username, timestamp)
class Purchase(username:String,val amount : Double, timestamp: Long) : Event(username, timestamp)
class Logout (username:String, timestamp: Long): Event(username, timestamp)


//Passo 2
fun  List<Event>.filterByUser(username : String): List<Event>{
    return this.filter{it.username == username}
}

//Passo 3
fun List<Event>.totalSpent(username : String): Double{
    var spent: Double
    spent = 0.00

    val lista_de_compras_username = this.filterIsInstance<Purchase>().filter{it.username == username}

    if (!lista_de_compras_username.isEmpty()){
        return lista_de_compras_username.sumOf{it.amount}
    }

    return spent
}

//Passo 4
fun processEvents(eventos: List<Event>, handler: (Event) -> Unit){
    eventos.forEach{handler(it)}
}