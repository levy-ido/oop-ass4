package unary;

import rest.BaseExpression;
import rest.Expression;

import java.util.List;

/**
 * Represents an expression containing a unary operation.
 */
public abstract class UnaryExpression extends BaseExpression {
    private final Expression operand;

    /**
     * Constructs a new UnaryExpression with the given operand.
     * @param operand An Expression representing the new UnaryExpression's operand
     */
    public UnaryExpression(Expression operand) {
        this.operand = operand;
    }

    /**
     * Returns this UnaryExpression's operand.
     * @return An Expression representing this UnaryExpression's operand
     */
    public Expression getOperand() {
        return this.operand;
    }

    /**
     * Returns a list of this UnaryExpression's variables.
     * @return A List of this UnaryExpression's variables.
     */
    public List<String> getVariables() {
        return this.operand.getVariables();
    }
}
