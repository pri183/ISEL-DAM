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
        spent = lista_de_compras_username.sumOf{it.amount}
    }

    return String.format("%.2f",spent).replace(",", ".").toDouble()
}

//Passo 4
fun processEvents(eventos: List<Event>, handler: (Event) -> Unit){
    eventos.forEach{handler(it)}
}

//Passo 5
fun main(args: Array<String>) {

    //Passo 6
    val events = listOf(
        Login("alice", 1_000),
        Purchase("alice", 49.99, 1_100),
        Purchase("bob", 19.99, 1_200),
        Login("bob", 1_050),
        Purchase("alice", 15.00, 1_300),
        Logout("alice", 1_400),
        Logout("bob", 1_500)
    )

    //Passo 5
    processEvents(events) { when(it){
        is Login -> println("[LOGIN]  "+it.username+" logged in at t="+it.timestamp+"")
        is Purchase -> println("[PURCHASE]  "+it.username+" spent \$"+it.amount+" at t="+it.timestamp+"")
        is Logout -> println("[LOGOUT]  "+it.username+"  logged out at t="+it.timestamp+"")
    } }

    //Passo 6
    println("\nTotal spent by alice: $"+events.totalSpent("alice")+"")
    println("Total spent by alice: $"+events.totalSpent("bob")+"")
    println()

    val eventos_alice = events.filterByUser("alice")
    processEvents(eventos_alice) { when(it){
        is Login -> println("Login(username="+it.username+", timestamp="+it.timestamp+")")
        is Purchase -> println("Purchase(username="+it.username+", amount="+it.amount+", timestamp="+it.timestamp+")")
        is Logout -> println("Logout(username="+it.username+", timestamp="+it.timestamp+")")
    } }





}




