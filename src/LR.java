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
    private int[][] table = {{-1,-1,2,-1,3,-1,1},
                     {4,5,-1,-1,-1,0,-1},
                     {-1,-1,2,-1,3,-1,6},
                     {104,104,-1,104,-1,104,-1},
                     {-1,-1,2,-1,3,-1,7},
                     {-1,-1,2,-1,3,-1,8},
                     {4,5,-1,9,-1,-1,-1},
                     {101,5,-1,101,-1,101,-1},
                     {102,102,-1,102,-1,102,-1},
                     {103,103,-1,103,-1,103,-1},
    };
    private Stack symbol_stack = new Stack();//符号栈
    private Stack state_stack = new Stack();//状态栈
    private String input = null; //输入串,初始化时默认加上#结尾
    private int state=0;

    int index=0;//记录当前读到字符串第几个字符
    HashMap<String,Integer> keywords = new HashMap<String,Integer>();

    public LR (String input){//初始化分析
        this.input=input+"#";//输入串加上'#' 初始化输入串
        init_keywords();
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
        String symbol = null;//记录找到的预设符号
        int Do = -1;//记录对应的表格内的数字
        try{
            state_stack.push(state);
            symbol = find();
            symbol_stack.push(symbol);
            Do = table[state][keywords.get(symbol)];

            if(Do<0){
                throw new Exception("出错!分析表没有对应符号处理");
            }
            else if(Do>0&&Do<=100){

            }
            else if(Do>100){

            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



    private String find() throws Exception{//找到之后是"+","*",...中的哪一个,都不属于则报错
        int num_flag=0;
        String next=null;//next 代表整个字符串里 下一个有效的输入 (标识符,符号)
        if(isDigit(input.charAt(index))){
            index++;
            while (isDigit(input.charAt(index))){ index++; }
            return "i";
        }
        else if(isLetter(input.charAt(index))){
            index++;
            int flag=0;

            while (isLetter(input.charAt(index))){ index++; flag++;}

            if(flag==0){
                if(keywords.containsKey(input.charAt(index-1))){
                    return input.charAt(index-1)+"";
                }
            }
            return "i";
        }
        else if(keywords.containsKey(input.charAt(index))){
            index++;
            return input.charAt(index)+"";
        }
        else {
            throw new Exception("第"+(index+1)+"个输入字符为非法输入!");
        }
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
    //判断是否是$与下划线
    boolean is$_(char letter)
    {
        return (letter == '$') || (letter == '_');
    }
    boolean isComment1_s(char letter)
    {
        return (letter == '$') || (letter == '_');
    }
    boolean isComment1_e(char letter)
    {
        return (letter == '$') || (letter == '_');
    }
    boolean isComment2_s(char letter)
    {
        return (letter == '$') || (letter == '_');
    }
    boolean isComment2_e(char letter)
    {
        return (letter == '$') || (letter == '_');
    }

}
