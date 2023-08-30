package flink.Day02;

import akka.japi.tuple.Tuple3;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.table.functions.ScalarFunction;
import org.apache.flink.table.functions.TableAggregateFunction;
//import scala.Int;

public class FeatureClassify extends ScalarFunction {

    public String eval(String s ,Integer n){
        if (!StringUtils.containsIgnoreCase(s,"dense") && (n > 1)){
            return "sequence";
        }else if (StringUtils.containsIgnoreCase(s,"dense")){
            return "dense";
        }else {
            return "category";
        }
    }
}
