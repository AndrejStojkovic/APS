// K1 5

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression){
        // Vasiot kod tuka
        int res = 0;

        String [] add = expression.split("\\+");

        for (String s : add) {
            String[] mult = s.split("\\*");
            int m = 1;

            for (String string : mult) {
                m *= Integer.parseInt(string);
            }

            res += m;
        }

        return res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}
