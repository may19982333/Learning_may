package flink.algorithm;

import java.util.HashSet;
import java.util.Set;

public class DuplicateNumber {
//    解析1 ： 利用set不允许重复元素的特效
    public int duplicate1(int[] numbers){
        Set<Integer> as = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            // 找出重复数字
            if (!as.add(numbers[i])) {
                return numbers[i];
            }
        }
        return -1;
    }
//    解析2：
//        数组存放原则：numbers[i] = i
//        遍历数组所有元素，交换不符合数组存放原则的元素：
//        例如[2,3,1,0,2]
//        遍历0位元素2：（交换0位元素2和2位元素1）->[1,3,2,0,2]
//        遍历0位元素1：（交换0位元素1和1位元素3）->[3,1,2,0,2]
//        遍历0位元素3：（交换0位元素3和3位元素0）->[0,1,2,3,2]
//        依次遍历0、1、2、3位置元素，都符合存放原则numbers[i] = i，不做任何操作
//        遍历末位元素2，此时末位元素2和2位元素2相等，出现了两次，即数字2位重复元素

    public int duplicate2(int[] numbers){
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != i){
                if (numbers[i] == numbers[numbers[i]]){
                    return numbers[i];
                }
                int temp = numbers[numbers[i]];
                numbers[numbers[i]] = numbers[i];
                numbers[i] = temp;
            }
        }
        return -1;
    }
}
