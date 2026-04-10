package dam.virtual_library

data class LibraryMember(
    val nome : String,
    val membershipId : String ,
    var borrowedBooks : ArrayList <String> = ArrayList <String>()
)