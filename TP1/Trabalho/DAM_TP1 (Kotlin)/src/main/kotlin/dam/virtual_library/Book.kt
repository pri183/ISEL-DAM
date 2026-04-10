package dam.virtual_library



abstract class Book (val title: String, val author: String, val publicationYear: Int, availableCopies : Int){

    init{
        println("Book '"+title+"' by "+author+" has been created.")
    }

    abstract fun getStorageInfo(): String

    override fun toString() : String{
        return return "Title: "+this.title+", Author: "+this.author+", Era: "+this.publicationEra+", Available: "+this.availableCopies+" copies" + "\nStorage: "+this.getStorageInfo()
    }


    //Setters
     var availableCopies: Int = availableCopies
         set(valor){
             if (valor > 0){
                 field = valor
             }
             else if (valor == 0){
                 field = valor
                 println("Warning: Book is now out of stock!")
             }
    }

    //getters
    val publicationEra: String
        get()= when{
            publicationYear < 1980 -> "Classic"
            publicationYear in 1980..2010-> "Modern"
            else -> "Contemporary"
        }
}