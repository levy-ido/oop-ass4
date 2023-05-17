import java.util.HashMap;

/**
 * An expression root class.
 */
public abstract class BaseExpression {
    /**
     * Evaluates the expression using an empty assignment and returns the result.
     * @return A double representing the evaluated expression
     * @throws Exception If a variable in the expression is not assigned a value
     */
    public double evaluate() throws Exception {
        return ((Expression) this).evaluate(new HashMap<>());
    }

    /**
     * Returns a simplified version of this expression.
     * @return A new Expression representing a simplified version of this expression
     */
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            return ((Expression) this).simplifyAssumingThereAreVariables();
        }
    }
}
