package Day02;

import flink.Day02.FeatureClassify;

public class FeatureClassifyTest {
    public static void main(String[] args) {
        FeatureClassify featureClassify = new FeatureClassify();
        String name = "recommend_handle_type_sequence";
        Integer n = 30;
        System.out.println(featureClassify.eval(name,n));
    }
}
