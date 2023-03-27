import org.syntax.stella.Absyn.Expr
import org.syntax.stella.Absyn.Type
import kotlin.system.exitProcess

class ThrowError (error: String){
    init {
        println(error)
        exitProcess(-1)
    }
}