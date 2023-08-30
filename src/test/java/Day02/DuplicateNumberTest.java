package Day02;

import flink.algorithm.DuplicateNumber;

public class DuplicateNumberTest {
    public static void main(String[] args) {
        DuplicateNumber dn = new DuplicateNumber();
        int[] testarr = {2,3,1,0,2,5,3};
//        int result = dn.duplicate1(testarr);
        int result = dn.duplicate2(testarr);
        System.out.println(result);

    }
}
