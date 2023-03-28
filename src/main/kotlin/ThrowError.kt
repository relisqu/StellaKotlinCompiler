import org.syntax.stella.Absyn.Expr
import org.syntax.stella.Absyn.Type
import java.lang.Exception
import kotlin.system.exitProcess

class ThrowError (assertExpression: Boolean, error: String){
    init {
        if(!assertExpression) {
            println(error)
            exitProcess(1)
        }
    }
}