# MathEvaluator — Reverse Polish Notation (RPN) Calculator in Java

A math expression evaluator that converts **infix expressions** (like `3 + 4 * (2 - 1)`) into **Reverse Polish Notation (RPN)** and evaluates them — with full support for floating-point numbers, parentheses, and unary negation.

---------------------------------------------------------

## 1) Codewars
I found this problem in Codewars (competitive coding website) and this is the link:
 https://www.codewars.com/kata/52a78825cdfc2cfc87000005

---------------------------------------------------------

## 2) Features
- `+`, `-`, `*`, `/` operators
- Handles **nested parentheses**
- Handles **negative numbers** (e.g., `5 * -2`)
- Supports **floating point numbers** (e.g., `3.14 * 2`)
- Converts infix → postfix (RPN) using the **Shunting-Yard algorithm**
- Evaluates the RPN stack-based expression

---------------------------------------------------------

Open a terminal in your project folder and run:

```
$ javac src/*.java -d .
$ java Main
```
---------------------------------------------------------

## Tags
`#java` `#rpn` `#calculator` `#parser` `#datastructures` `#algorithms`

