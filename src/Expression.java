import java.util.List;
import java.util.Map;

/**
 * Represents an arithmetical expression.
 */
public interface Expression {

    /**
     * Evaluates the expression using the provided assignment and returns the result.
     * If a variable in the expression is not assigned a value an exception is thrown.
     * @param assignment A Map containing key-value pairs of String and Double respectively
     * @return A double representing the evaluated expression
     * @throws Exception If a variable in the expression is not assigned a value
     */
    double evaluate(Map<String, Double> assignment) throws Exception;
    /**
     * Evaluates the expression using an empty assignment and returns the result.
     * @return A double representing the evaluated expression
     * @throws Exception If a variable in the expression is not assigned a value
     */
    double evaluate() throws Exception;

    /**
     * Returns a list containing the expression's variables.
     * @return A List containing the expression's variables.
     */
    List<String> getVariables();

    /**
     * Returns a new expression in which all occurrences of var are replaced with the provided expression.
     * @param var A String representing a variable name
     * @param expression An Expression representing the expression to replace var with
     * @return A new Expression in which all occurrences of var are replaced with expression
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns a new expression representing the derivative of this expression relative to var.
     * @param var A String representing the name of the variable to differentiate relative to
     * @return An Expression representing the derivative of this expression relative to var
     */
    Expression differentiate(String var);

    /**
     * Returns a simplified version of this expression assuming there are variables in this expression.
     * @return A new Expression representing a simplified version of this expression
     */
    Expression simplifyAssumingThereAreVariables();

    /**
     * Returns a simplified version of this expression.
     * @return A new Expression representing a simplified version of this expression
     */
    Expression simplify();
}