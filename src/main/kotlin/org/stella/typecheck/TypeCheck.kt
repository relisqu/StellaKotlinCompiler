package org.stella.typecheck

import ThrowError
import org.syntax.stella.Absyn.*

object TypeCheck {
    @Throws(Exception::class)
    fun typecheckProgram(program: Program) {
        when (program) {
            is AProgram -> program.listdecl_.map {
                when (it) {
                    is DeclFun -> {
                        val name = it.stellaident_
                        val returnType= it.returntype_;
                        val params = it.listparamdecl_;
                        val expr= it.expr_;
                        println("Declared function $name")
                    }


                }
            }

        }
    }

    class Context(
        var currentVars: Map<String, Type>
        )
    {

    }


    fun calculateExpression(context: Context, expr: Expr ): Type {
        when (expr) {
            is ConstFalse, is ConstTrue -> return TypeBool();
            is If ->{
                val ifCondition = calculateExpression(context, expr.expr_1)
                val trueExp= calculateExpression(context, expr.expr_2)
                val falseExp = calculateExpression(context, expr.expr_3)

                assert(trueExp==falseExp){
                    print("Expected types to be equal: $trueExp and $falseExp" )
                }

                assert(ifCondition == TypeBool()){
                    ThrowError("Expected boolean type in if condition, got $ifCondition")
                }
                return trueExp
            }

            is Succ-> {
                return CheckIfExprIsNumber(context, expr.expr_)
            }

            is Pred-> {
                return CheckIfExprIsNumber(context, expr.expr_)
            }

        }


    }

    fun CheckIfExprIsNumber(context: Context, expr: Expr) : Type{
        val innerExpr = calculateExpression(context, expr)
        assert( innerExpr== TypeNat()) {
            ThrowError("Expected number, got $innerExpr")
        }
        return innerExpr
    }

}
