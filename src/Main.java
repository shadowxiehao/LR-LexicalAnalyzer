import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] arg){
        System.out.println("Please enter an arithmetic expression to analyse!");
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        String expression;
        try {
            expression = in.readLine();//读入输入
            LR test=new LR(expression);
            test.show();//开始分析

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("输入出错！");
        }

    }
}
