package it.unicam.cs.asdl2021.mp1;

import java.util.HashMap;

/**
 * An object of this class is an actor that uses an ASDL2021Deque<Character> as
 * a Stack in order to check that a sequence containing the following
 * characters: '(', ')', '[', ']', '{', '}' in any order is a string of balanced
 * parentheses or not. The input is given as a String in which white spaces,
 * tabs and newlines are ignored.
 * 
 * Some examples:
 * 
 * - " (( [( {\t (\t) [ ] } ) \n ] ) ) " is a string o balanced parentheses - " " is a
 * string of balanced parentheses - "(([)])" is NOT a string of balanced
 * parentheses - "( { } " is NOT a string of balanced parentheses - "}(([]))" is
 * NOT a string of balanced parentheses - "( ( \n [(P)] \t ))" is NOT a string
 * of balanced parentheses
 * 
 * @author Template: Luca Tesei, Implementation: Luca Mozzoni - luca.mozzoni@studenti.unicam.it
 *
 */
public class BalancedParenthesesChecker {

    // The stack is to be used to check the balanced parentheses
    private ASDL2021Deque<Character> stack;

    /**
     * Create a new checker.
     */
    public BalancedParenthesesChecker() {
        this.stack = new ASDL2021Deque<Character>();
    }

    /**
     * Check if a given string contains a balanced parentheses sequence of
     * characters '(', ')', '[', ']', '{', '}' by ignoring white spaces ' ',
     * tabs '\t' and newlines '\n'.
     *
     * @param s the string to check
     * @return true if s contains a balanced parentheses sequence, false
     * otherwise
     * @throws IllegalArgumentException if s contains at least a character
     *                                  different form:'(', ')', '[', ']',
     *                                  '{', '}', white space ' ', tab '\t'
     *                                  and newline '\n'
     */
    public boolean check(String s) {
        //Lo stack viene svuotato ad ogni utilizzo del metodo
        stack.clear();
        //Itera ogni elemento della stringa specificata
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            //Se il carattere corrente è uno di quelli specificati,
            // lo inserisce nello stack e passa al prossimo carattere da iterare
            if (currentChar == '(' || currentChar == '[' || currentChar == '{') {
                stack.push(currentChar);
                continue;
            }
            //Se uno degli elementi che compone questa stringa
            // è diverso da tutti i caratteri specificati (esclusi
            // quelli controllati con l'if precedente), lancia un'eccezione
            if (currentChar != ')' && currentChar != ']' && currentChar != '}' &&
                    currentChar != ' ' && currentChar != '\t' && currentChar != '\n')
                throw new IllegalArgumentException("La stringa fornita non è valida.");
            //Arrivato a questo punto, se lo stack è vuoto,
            // la stringa specificata non è bilanciata
            if (stack.isEmpty())
                return false;
            char openPar;
            //In base al carattere corrente controllo che
            // il primo elemento dello stack sia quello
            // giusto per garantire una stringa bilanciata.
            // Se non lo è, ritorna false.
            if (currentChar == ')'){
                openPar = stack.pop();
                if (openPar == '{' || openPar == '[')
                    return false;
            } else if (currentChar == ']') {
                openPar = stack.pop();
                if (openPar == '{' || openPar == '(')
                    return false;
            } else if (currentChar == '}') {
                openPar = stack.pop();
                if (openPar == '(' || openPar == '[')
                    return false;
            }
        }
        //Se, arrivato a questo punto, lo stack è vuoto,
        // siamo certi che la stringa specificata fosse bilanciata
        return stack.isEmpty();
    }
}