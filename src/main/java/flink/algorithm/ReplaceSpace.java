package flink.algorithm;
// 替换空格
public class ReplaceSpace {
    public String ReplaceSpaceSolution(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' '){
                sb.append("%20");
            }else{
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();

    }

}
