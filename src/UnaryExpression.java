/**
 * Represents an unary expression.
 */
public abstract class UnaryExpression {
    private final Expression e;

    /**
     * Constructs a new UnaryExpression with the given expression.
     * @param e An Expression representing the expression to construct the new UnaryExpression with
     */
    public UnaryExpression(Expression e) {
        this.e = e;
    }

    /**
     * Returns this UnaryExpression's expression.
     * @return An Expression representing this UnaryExpression's expression
     */
    public Expression getE() {
        return this.e;
    }
}
