import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LR {
    /*
    1、文法如下：
	0) S→E
	1) E→E+E
	2) E→E*E
	3) E→(E)
	4) E→i
2、对应的LR分析表如下：
状态	 ACTION	          GOTO
	+	*	(	)	i	#	E
0	　	　	S2	　	S3	　	1
1	S4	S5	　	　	　	acc	　
2	　	　	S2	　	S3	　	6
3	r4	r4	　	r4	　	r4	　
4	　	　	S2	　	S3	　	7
5	　	　	S2	　	S3	　	8
6	S4	S5	　	S9	　	　	　
7	r1	S5	　	r1	　	r1	　
8	r2	r2	　	r2	　	r2	　
9	r3	r3	　	r3	　	r3	　
     */
    private int[][] table =
                    {{-1,-1,2,-2,3,-1,1},
                     {4,5,-3,-2,-3,0,-10},
                     {-1,-1,2,-2,3,-1,6},
                     {104,104,-3,104,-3,104,-10},
                     {-1,-1,2,-2,3,-1,7},
                     {-1,-1,2,-2,3,-1,8},
                     {4,5,-3,9,-3,-4,-10},
                     {101,5,-3,101,-3,101,-10},
                     {102,102,-3,102,-3,102,-10},
                     {103,103,-3,103,-3,103,-10}};
    // <-1代表
    // 0代表接受 acc
    // 1~100 代表移进
    // >101 代表规约

    private Stack symbol_stack = new Stack();//符号栈
    private Stack state_stack = new Stack();//状态栈
    private String input = null; //输入串,初始化时默认加上#结尾

    int index=0;//记录当前读到字符串第几个字符
    HashMap<String,Integer> keywords = new HashMap<String,Integer>();

    private int state=0;//记录一次状态
    String symbol = "";//记录找到的预设符号
    int act = -1;//记录对应的表格内的数字

    public LR (String input){//初始化分析
        this.input=input+"#";//输入串加上'#' 初始化输入串
        init_keywords();
        try {
            find();//将初始输入转换成标准格式
            symbol="#";
            state=0;
            state_stack.push(state);
            symbol_stack.push(symbol);//初始化栈
            index=0;//指向输入串第一个,输入串初始化完成
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private int init_keywords(){//将输入符号 转换成 LR分析表中的对应列数
        keywords.put("+",0);
        keywords.put("*",1);
        keywords.put("(",2);
        keywords.put(")",3);
        keywords.put("i",4);
        keywords.put("#",5);
        keywords.put("E",6);
        return 0;//正常
    }

    public void show(){

        try{
            while( (act = table[state][keywords.get(symbol)]) !=0 ){
                process();
            }
            if(act==0){
                System.out.println("ok fine!");
            }

        System.out.println(input);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }





    private void process(){//处理不同的移进或规约

        while (true) {

            if (act < 0) {

            }

        }
    }



    private void errorMessage() throws Exception{//显示出错原因
        throw new Exception("i don't know");
    }

    /*
    将初始输入转换成标准格式
     */
    private void find() throws Exception{//找到之后是"+","*",...中的哪一个,都不属于则报错
        String out = "";
        while(index<input.length()) {
            if (isDigit(input.charAt(index))) {
                index++;
                while (isDigit(input.charAt(index))) {
                    index++;
                }
                out += "i";
            } else if (isLetter(input.charAt(index))) {
                String tmp = "";
                int flag = 0;

                while (isLetter(input.charAt(index))) {
                    tmp+=input.charAt(index);
                    flag++;
                    index++;
                }
                if(flag!=0){
                    while (isDigit(input.charAt(index))){
                        tmp+=input.charAt(index);
                        index++;
                    }
                }
                if (keywords.containsKey(tmp)) {
                    out += tmp;
                }else {
                    out+="i";
                }

            } else if (keywords.containsKey(input.charAt(index)+"")) {
                index++;
                out += input.charAt(index-1) + "";
            } else {
                throw new Exception("第" + (index + 1) + "个输入字符"+input.charAt(index)+"为非法输入!");
            }
        }
        input = out ;
    }


    //判断是否是字母
    boolean isLetter(char letter)
    {
        return (letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z');
    }
    //判断是否是数字
    boolean isDigit(char digit)
    {
        return digit >= '0' && digit <= '9';
    }


}
