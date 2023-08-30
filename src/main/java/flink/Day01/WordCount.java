package flink.Day01;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.lang.reflect.Type;
import java.util.Arrays;

//import java.util.stream.Collector;

public class WordCount {
    public static void main(String[] args) throws Exception {
//        环境准备
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        读取文件
        DataStreamSource<String> lines = env.readTextFile("/Users/bytedance/java/FlinkLearning/src/main/java/input/words.txt");
//        转换数据格式
         SingleOutputStreamOperator<Tuple2<String, Long>> wordAndOne = lines.flatMap((String line, Collector<String> words) -> {
             Arrays.stream(line.split(" ")).forEach(words::collect);
         }).returns(Types.STRING).map(word -> Tuple2.of(word,1L))
                 .returns(Types.TUPLE(Types.STRING,Types.LONG));
//         分组
         KeyedStream<Tuple2<String,Long>,String>  wordAndOneKS = wordAndOne.keyBy(t -> t.f0);
//         求和
        SingleOutputStreamOperator<Tuple2<String, Long>> result = wordAndOneKS.sum(1);
        result.print();
        env.execute();

    }
}
