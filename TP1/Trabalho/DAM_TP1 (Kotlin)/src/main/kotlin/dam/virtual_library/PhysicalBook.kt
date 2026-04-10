package dam.virtual_library

class PhysicalBook(title : String, author : String, publicationYear : Int, availableCopies : Int,
    val weight: Int, val hasHardcover : Boolean = true) : Book(title,author, publicationYear, availableCopies) {

    override fun getStorageInfo(): String {
        return  "Physical book: "+this.weight+" g, Hardcover: "+ when (this.hasHardcover){
            true -> "Yes"; else -> "No"
        }
    }
}