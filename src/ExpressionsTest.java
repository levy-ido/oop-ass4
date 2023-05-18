import java.util.Map;

/**
 * A Test class for all the code I created!
 */
public class ExpressionsTest {
    /**
     * Returns the test expression.
     * @return A new Expression representing the test expression
     */
    public static Expression createExpression() {
        Num two = new Num(2.0);
        Var x = new Var("x");
        Mult twoX = new Mult(two, x);
        Num four = new Num(4.0);
        Var y = new Var("y");
        Mult fourY = new Mult(four, y);
        Expression sineOfFourY = new Sin(fourY);
        Var e = new Var("e");
        Expression eToThePowerOfX = new Pow(e, x);
        return new Plus(twoX, new Plus(sineOfFourY, eToThePowerOfX));
    }
    /**
     * The entry point of the test.
     * @param args String[]. Ignored
     */
    public static void main(String[] args) throws Exception {
        Expression expression = createExpression();
        System.out.println(expression);
        Map<String, Double> assignment = Map.of("x", 2.0, "y", 0.25, "e", 2.71);
        System.out.println(expression.evaluate(assignment));
        Expression differentiatedExpressionRelativeToX = expression.differentiate("x");
        System.out.println(differentiatedExpressionRelativeToX);
        System.out.println(differentiatedExpressionRelativeToX.evaluate(assignment));
        System.out.println(differentiatedExpressionRelativeToX.simplify());
    }
}
