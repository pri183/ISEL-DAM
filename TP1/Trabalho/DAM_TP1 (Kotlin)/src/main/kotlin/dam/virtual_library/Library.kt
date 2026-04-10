package dam.virtual_library

class Library(var name : String , var listOfBooks: ArrayList<Book> = ArrayList()) {

    companion object{

        private var totalBooksCreated = 0

        fun getTotalBooksCreated(): Int {
            return totalBooksCreated
        }
    }



    fun addBook(newBook : Book){
        listOfBooks.add(newBook)
        ++totalBooksCreated
    }

    fun borrowBook(title : String){
        for(book in listOfBooks){
            if(book.title == title && book.availableCopies > 0){

                --book.availableCopies
                println("Successfully borrowed '"+title+"'. Copies remaining: " + book.availableCopies)
                return
            }
        }

        println("Sorry, no copies available of "+ title+".")
    }

    fun returnBook(title : String){

        for(book in listOfBooks){
            if(book.title == title ){

                ++book.availableCopies
                println("Book '"+title+"' returned successfully. Copies available: " + book.availableCopies)
                return
            }
        }
    }

    fun showBooks(){
        println("--- Library Catalog ---")
        for (book in listOfBooks){
            println(book.toString())
        }
    }

    fun  searchByAuthor(author : String){

        println("Books by "+author+":")

        for(book in listOfBooks){

            if (book.author == author){
                println(" - "+book.title+" ("+book.publicationEra+","+book.availableCopies+" copy available)")
            }
        }


    }

}