package org.stella.typecheck

import ThrowError
import org.syntax.stella.Absyn.*

object TypeCheck {

    fun typecheckFunction(funct: DeclFun) {

    }



@Throws(Exception::class)
fun typecheckProgram(program: Program) {
    var context = Context(mutableMapOf());
    when (program) {
        is AProgram -> program.listdecl_.map {
            when (it) {
                is DeclFun -> {

                    val name = it.stellaident_
                    val returnType = it.returntype_;
                    val params = it.listparamdecl_;
                    val expr = it.expr_;
                    for (param in params){
                        when(param){
                            is AParamDecl->{
                                println(param.type_)
                                context.currentVars[param.stellaident_] = calculateExpression(context, expr)
                            }
                        }
                    }

                    val calculatedExpression=calculateExpression(context, expr)
                    context.currentVars[name]=calculatedExpression;

                        when(returnType){
                        is SomeReturnType->{
                            ThrowError(returnType.type_ == calculatedExpression,"Expected $returnType, got $calculatedExpression")
                        }
                    }
                    println(calculateExpression(context, expr))
                    println(context.currentVars.toString())
                }


            }
        }

    }
}

class Context(
    var currentVars: MutableMap<String, Type>
) {

}


fun calculateExpression(context: Context, expr: Expr): Type {
    when (expr) {
        is ConstFalse, is ConstTrue -> return TypeBool();
        is If -> {
            val ifCondition = calculateExpression(context, expr.expr_1)
            val trueExp = calculateExpression(context, expr.expr_2)
            val falseExp = calculateExpression(context, expr.expr_3)

            ThrowError(trueExp == falseExp,"Expected types to be equal: $trueExp and $falseExp")
            ThrowError(ifCondition == TypeBool(),"Expected boolean type in if condition, got $ifCondition")
            return trueExp
        }

        is Succ -> {
            return CheckIfExprIsNumber(context, expr.expr_)
        }

        is Pred -> {
            return CheckIfExprIsNumber(context, expr.expr_)
        }

        is NatRec -> {
            val number = CheckIfExprIsNumber(context, expr.expr_1)
            val initValue = calculateExpression(context, expr.expr_2)

            when (val function = calculateExpression(context, expr.expr_3)) {
                is TypeFun -> {
                    //  ThrowError( function.listtype_[0] == )
                }
            }
            println("NOT IMPLEMENTED")
            return CheckIfExprIsNumber(context, expr.expr_2);
        }

        is ConstInt -> {
            return TypeNat();
        }
        is Application ->{
            val returnExpr = expr.expr_
        }
    }
    return TypeBool();

}

fun CheckIfExprIsNumber(context: Context, expr: Expr): Type {
    val innerExpr = calculateExpression(context, expr)
    ThrowError(innerExpr == TypeNat(),"Expected number, got $innerExpr")
    return innerExpr
}

}
