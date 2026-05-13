package annotations
@Target(AnnotationTarget.FUNCTION) // As anotações só podem ser aplicadas a funções
@Retention(AnnotationRetention.SOURCE) // As anotações só são usadas durante o tempo de complicação e não de runtime
annotation class Greeting(val message: String) //conseguimos passar uma mensagem como argumento da anotação