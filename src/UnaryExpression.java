import java.util.List;

/**
 * Represents an unary expression.
 */
public abstract class UnaryExpression extends BaseExpression {
    private final Expression operand;

    /**
     * Constructs a new UnaryExpression with the given expression.
     * @param operand An Expression representing the expression to construct the new UnaryExpression with
     */
    public UnaryExpression(Expression operand) {
        this.operand = operand;
    }

    /**
     * Returns this UnaryExpression's expression.
     * @return An Expression representing this UnaryExpression's expression
     */
    public Expression getOperand() {
        return this.operand;
    }

    /**
     * Returns this UnaryExpression's variables.
     * @return A List representing this UnaryExpression's variables.
     */
    public List<String> getVariables() {
        return this.operand.getVariables();
    }
}
