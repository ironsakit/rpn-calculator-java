import java.util.*;
import java.util.function.BiFunction;
import java.util.ArrayList;
import java.util.List;

public class MathEvaluator {

    // Funzione per ottenere la priorità
    private int getPriority(String operator) {
        if (operator.equals("+") || operator.equals("-")) return 1;
        if (operator.equals("*") || operator.equals("/")) return 2;
        if (operator.equals("n")) return 3;
        return 0;
    }

    public double calculate(String expression) {
        char[] espressione = expression.toCharArray();
        // Negazione (opposto di un numero)
        char maxPrio = 'n';
        List<String> filaRisultato = new ArrayList<>();
        Deque<String> operatori = new ArrayDeque<>();
        Deque<Double> risultato = new ArrayDeque<>();
        StringBuilder numberBuffer = new StringBuilder();
        boolean isNeg = true;  // All'inizio dell'espressione è sempre negativa

        for (char unita : espressione) {

            if (Character.isDigit(unita) || unita == '.') {  
                // Se è un numero lo metto subito nella fila risultante
                numberBuffer.append(unita);
                isNeg = false;  // il meno non può più essere un neg ma diventa operatore

            } else {

                // Svuotamento del buffer dei numeri
                if (numberBuffer.length() > 0) {
                    filaRisultato.add(numberBuffer.toString());
                    numberBuffer.setLength(0);  // metto la lunghezza a 0
                }

                // Salto gli spazi
                if (String.valueOf(unita).trim().isEmpty()) continue;

                if (unita == '(') {
                    operatori.push(String.valueOf(unita));
                    isNeg = true;  // Dopo un ( diventa negazione il meno

                } else if (unita == ')') {  
                    // Se ho una parentesi chiusa
                    while (!operatori.peek().equals("(")) filaRisultato.add(operatori.pop());  // finché non trovo '('
                    if (!operatori.isEmpty()) operatori.pop();  // Rimuove la '(' dalla pila
                    isNeg = false;  // Dopo ) non è più negazione ma semplice meno

                } else {
                    // Hanno la stessa priorità o hai in mano un operatore meno prioritario
                    if (isNeg && unita == '-') unita = maxPrio;  // se l'operatore è un "-" ed è negazione
                    while (!operatori.isEmpty() && getPriority(operatori.peek()) >= getPriority(String.valueOf(unita)))
                        filaRisultato.add(operatori.pop());
                    operatori.push(String.valueOf(unita)); 
                    isNeg = true;  // Dopo un operatore il meno è Negazione
                }
            }
        }

        // Svuotamento finale del numberBuffer (metto un numero finale)
        if (numberBuffer.length() > 0) filaRisultato.add(numberBuffer.toString());

        // Metto tutto nella lista risultante
        while (!operatori.isEmpty()) filaRisultato.add(operatori.pop());

        // Definizione degli operatori binari usando Map
        Map<String, BiFunction<Double, Double, Double>> ops = Map.of(
                "+", (a, b) -> a + b,
                "-", (a, b) -> a - b,
                "*", (a, b) -> a * b,
                "/", (a, b) -> a / b
        );

        // Valutazione dell'espressione RPN
        for (String unita : filaRisultato) {
            if (unita.matches("-?\\d+(\\.\\d+)?")) {
                risultato.push(Double.parseDouble(unita));  // Se è un numero
            } else if (unita.equals("n")) {
                double op = risultato.pop();  // Negazione
                risultato.push(-op);
            } else {
                double op2 = risultato.pop();  // Pop del secondo operando
                double op1 = risultato.pop();  // Pop del primo operando
                risultato.push(ops.get(unita).apply(op1, op2));  // Applico l'operatore
            }
        }
        return risultato.pop();  // Risultato finale
    }
}
