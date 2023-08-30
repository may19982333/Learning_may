package Day02;

import flink.Day02.SimpleUdf;

public class SimpleUdfTest {
    public static void main(String[] args) {
        SimpleUdf simpleUdf = new SimpleUdf();
        simpleUdf.eval("may");
    }
}
