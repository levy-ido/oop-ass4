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
    public Expression simplifyAssumingThereAreVariables() {
        Expression simplifiedOperand = super.getOperand().simplify();
        if (simplifiedOperand.toString().equals("0.0")) {
            return new Num(0.0);
        }
        return new Sin(simplifiedOperand);
    }
}
