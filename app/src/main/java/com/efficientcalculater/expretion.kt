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
                while (stack.isNotEmpty() && precedence(stack.peek()) >= precedence(element)) {
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

    fun evaluateExpression(postfix: String): Number {
        var i = 0
        val stack = Stack<Double>()
        var number = ""
        while (i < postfix.length) {
            if (postfix[i] == ' ') {
                i++
                continue
            } else if (Character.isDigit(postfix[i])) {
                while (Character.isDigit(postfix[i]) || postfix[i] == '.') {
                    number += postfix[i]
                    i++
                }
                stack.push(number.toDouble())
            } else {
                val x = stack.pop()
                val y = stack.pop()
                when (postfix[i]) {
                    '*' -> stack.push(x * y)
                    '/' -> stack.push(x / y)
                    '-' -> stack.push(x - y)
                    '+' -> stack.push(x + y)
                }
            }
            i++
        }
        return if (stack.peek() / stack.pop().toInt() == 1.0) stack.peek().toInt() else stack.peek()
    }

}