package binary;

import rest.BaseExpression;
import rest.Expression;

import java.util.List;

/**
 * Represents an expression containing a binary operation.
 */
public abstract class BinaryExpression extends BaseExpression {
    private final Expression first;
    private final Expression second;

    /**
     * Constructs a new BinaryExpression with the given operands.
     * @param first An Expression representing the new BinaryExpression's first operand
     * @param second An Expression representing the new BinaryExpression's second operand
     */
    public BinaryExpression(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns this BinaryExpression's first operand.
     * @return An Expression representing this BinaryExpression's first operand
     */
    public Expression getFirst() {
        return this.first;
    }
    /**
     * Returns this BinaryExpression's second operand.
     * @return An Expression representing this BinaryExpression's second operand
     */
    public Expression getSecond() {
        return this.second;
    }

    /**
     * Returns a list of this BinaryExpression's variables.
     * @return A List of this BinaryExpression's variables
     */
    public List<String> getVariables() {
        List<String> firstVariables = this.first.getVariables();
        List<String> secondVariables = this.second.getVariables();
        firstVariables.addAll(secondVariables);
        return firstVariables;
    }
}
