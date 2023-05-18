import java.util.List;
import java.util.Map;

/**
 * Represents an expression.
 */
public interface Expression {

    /**
     * Evaluates the expression using the provided assignment and returns the result.
     * @param assignment A Map mapping variables to values
     * @return A double representing the evaluation result
     * @throws Exception If a variable is not assigned a value
     */
    double evaluate(Map<String, Double> assignment) throws Exception;
    /**
     * Evaluates the expression using an empty assignment and returns the result.
     * @return A double representing the evaluation result
     * @throws Exception If a variable is not assigned a value
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of this expression's variables.
     * @return A List of this expression's variables.
     */
    List<String> getVariables();

    /**
     * Replaces all occurrences of var with the provided expression and returns the resulting expression.
     * @param var A String representing a variable name
     * @param expression An Expression representing the expression to replace var with
     * @return An Expression representing the resulting expression
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the derivative of this expression relative to var.
     * @param var A String representing a variable name
     * @return An Expression representing the derivative of this expression relative to var
     */
    Expression differentiate(String var);

    /**
     * Returns a simplified version of this expression.
     * @return An Expression representing a simplified version of this expression
     */
    Expression simplify();
}