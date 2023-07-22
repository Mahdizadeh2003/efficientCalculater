package com.efficientcalculater

import java.util.Stack

class Expression(var infixExpression: MutableList<String>) {
    private fun infixToPostfix(): String {
        var result = ""
        val stack = Stack<String>()
        for (element in infixExpression) {
            if (element.all { it.isDigit() } || element.any { it == '.' }) {
                result += "$element "
            } else if (element == "(") {
                stack.push(element)
            } else if (element == ")") {
                while (stack.peek() != ")" && stack.isNotEmpty()) {
                    result = "${stack.pop()} "
                }
                if (stack.isNotEmpty())
                    stack.pop()
            } else {
                while (stack.isNotEmpty() && precedence(stack.peek())>=precedence(element)){
                    result += "${stack.pop()} "
                }
            }
        }
        while (stack.isNotEmpty()) {
            result += "${stack.pop()} "
        }
        return result
    }

    private fun precedence(operator: String): Int {
        return when (operator) {
            "*", "/" -> 2
            "-", "+" -> 1
            else -> -1
        }
    }

    fun evaluateExpression(): Double {

    }

}