package flink.Day02;
import org.apache.flink.table.functions.ScalarFunction;

//实现一个转大写的功能，如输入：may，输出：MAY
public class SimpleUdf extends ScalarFunction{
    public void eval(String s){
//        return s.toUpperCase();
        System.out.println(s.toUpperCase());
    }
}
