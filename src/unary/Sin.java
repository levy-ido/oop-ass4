package unary;

import binary.Mult;
import rest.Expression;
import rest.Num;

import java.util.Map;

/**
 * Represents a sine expression.
 */
public class Sin extends UnaryExpression implements Expression {

    /**
     * Constructs a new Sin with the given expression.
     * @param expression An Expression representing the expression to sine
     */
    public Sin(Expression expression) {
        super(expression);
    }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.sin(Math.toRadians(super.getOperand().evaluate(assignment)));
    }

    @Override
    public double evaluate() throws Exception {
        return this.evaluate(Map.of());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(super.getOperand().assign(var, expression));
    }

    @Override
    public String toString() {
        return "sin(" + super.getOperand().toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        Expression operand = super.getOperand();
        Expression sineDerivative = new Cos(operand);
        Expression operandDerivative = operand.differentiate(var);
        return new Mult(sineDerivative, operandDerivative);
    }

    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception exception) {
            return new Sin(super.getOperand().simplify());
        }
    }
}
