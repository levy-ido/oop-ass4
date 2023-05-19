package unary;

import binary.Mult;
import rest.Expression;
import rest.Num;

import java.util.Map;

/**
 * Represents a cosine expression.
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     * Constructs a new Cos with the provided expression.
     * @param expression An Expression representing the expression to cosine
     */
    public Cos(Expression expression) {
        super(expression);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(Math.toRadians(super.getOperand().evaluate(assignment)));
    }

    @Override
    public double evaluate() throws Exception {
        return this.evaluate(Map.of());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(super.getOperand().assign(var, expression));
    }

    @Override
    public String toString() {
        return "cos(" + super.getOperand().toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        Expression operand = super.getOperand();
        Expression cosineDerivative = new Neg(new Sin(operand));
        Expression operandDerivative = operand.differentiate(var);
        return new Mult(cosineDerivative, operandDerivative);
    }

    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception exception) {
            return new Cos(super.getOperand().simplify());
        }
    }
}
