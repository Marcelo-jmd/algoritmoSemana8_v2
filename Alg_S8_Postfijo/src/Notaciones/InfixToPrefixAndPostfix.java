package Notaciones;

import java.util.Scanner;
import java.util.Stack;

public class InfixToPrefixAndPostfix {

    static boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/');
    }

    // Método para verificar la precedencia de los operadores
    static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    // Método para invertir una cadena
    static String reverse(String str) {
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        return reversed.toString();
    }

    // Método para convertir notación infija a prefija
    static String infixToPrefix(String infix) {
        StringBuilder prefix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        String reversedInfix = reverse(infix);

        System.out.println("Pasos para convertir a prefija:");

        for (int i = 0; i < reversedInfix.length(); i++) {
            char c = reversedInfix.charAt(i);

            // Si el carácter es un operando, agregarlo al resultado final
            if (Character.isLetterOrDigit(c)) {
                prefix.append(c);
                System.out.println("Paso " + (i + 1) + ": Agregado operando '" + c + "'");
            } // Si el carácter es un paréntesis derecho, agregarlo a la pila
            else if (c == ')') {
                stack.push(c);
                System.out.println("Paso " + (i + 1) + ": Paréntesis derecho encontrado, agregado a la pila");
            } // Si el carácter es un paréntesis izquierdo, desapilar elementos de la pila y agregarlos al resultado final
            // hasta encontrar el paréntesis derecho correspondiente
            else if (c == '(') {
                while (!stack.isEmpty() && stack.peek() != ')') {
                    prefix.append(stack.pop());
                    System.out.println("Paso " + (i + 1) + ": Desapilado operador '" + prefix.charAt(prefix.length() - 1) + "'");
                }
                stack.pop(); // Desapilar el paréntesis derecho
                System.out.println("Paso " + (i + 1) + ": Paréntesis izquierdo encontrado, desapilado de la pila");
            } // Si el carácter es un operador, desapilar operadores de mayor o igual precedencia y agregarlos al resultado
            // final antes de agregar el operador actual a la pila
            else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(c) < precedence(stack.peek())) {
                    prefix.append(stack.pop());
                    System.out.println("Paso " + (i + 1) + ": Desapilado operador '" + prefix.charAt(prefix.length() - 1) + "'");
                }
                stack.push(c);
                System.out.println("Paso " + (i + 1) + ": Agregado operador '" + c + "' a la pila");
            }
        }

        // Desapilar cualquier operador restante de la pila
        while (!stack.isEmpty()) {
            prefix.append(stack.pop());
            System.out.println("Paso " + (reversedInfix.length() + stack.size() + 1) + ": Desapilado operador '" + prefix.charAt(prefix.length() - 1) + "'");
        }

        // Invertir la cadena resultante para obtener la notación prefija final
        return reverse(prefix.toString());
    }

    // Método para convertir notación infija a postfija
    static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        System.out.println("\nPasos para convertir a postfija:");

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            // Si el carácter es un operando, agregarlo al resultado final
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
                System.out.println("Paso " + (i + 1) + ": Agregado operando '" + c + "'");
            } // Si el carácter es un paréntesis izquierdo, agregarlo a la pila
            else if (c == '(') {
                stack.push(c);
                System.out.println("Paso " + (i + 1) + ": Paréntesis izquierdo encontrado, agregado a la pila");
            } // Si el carácter es un paréntesis derecho, desapilar elementos de la pila y agregarlos al resultado final
            // hasta encontrar el paréntesis izquierdo correspondiente
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                    System.out.println("Paso " + (i + 1) + ": Desapilado operador '" + postfix.charAt(postfix.length() - 1) + "'");
                }
                stack.pop(); // Desapilar el paréntesis izquierdo
                System.out.println("Paso " + (i + 1) + ": Paréntesis derecho encontrado, desapilado de la pila");
            } // Si el carácter es un operador, desapilar operadores de mayor o igual precedencia y agregarlos al resultado
            // final antes de agregar el operador actual a la pila
            else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                    System.out.println("Paso " + (i + 1) + ": Desapilado operador '" + postfix.charAt(postfix.length() - 1) + "'");
                }
                stack.push(c);
                System.out.println("Paso " + (i + 1) + ": Agregado operador '" + c + "' a la pila");
            }
        }

        // Desapilar cualquier operador restante de la pila
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
            System.out.println("Paso " + (infix.length() + stack.size() + 1) + ": Desapilado operador '" + postfix.charAt(postfix.length() - 1) + "'");
        }

        return postfix.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la expresión infija: ");
        String infixExpression = scanner.nextLine();

        System.out.println("\nExpresión infija: " + infixExpression);

        // Convertir a prefija
        String prefixExpression = infixToPrefix(infixExpression);
        System.out.println("\nExpresión prefija: " + prefixExpression);

        // Convertir a postfija
        String postfixExpression = infixToPostfix(infixExpression);
        System.out.println("\nExpresión postfija: " + postfixExpression);

        scanner.close();
    }
}
