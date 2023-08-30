package flink.Day02;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.types.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// 将 json 打平
public class FeatureFlatten extends TableFunction<Row> {
    public void eval(String json){
        JSONObject jsonArr = JSONObject.parseObject(json);
        Set<String> keys = jsonArr.keySet(); // 拿到了所有的key
        for (String key : keys){
            JSONArray valList = jsonArr.getJSONArray(key); //拿到了每个key的值
            if (!StringUtils.containsIgnoreCase(key,"dense")){
                List<String> listKey = new ArrayList<>();
                List<String> listVal = new ArrayList<>();
                List<Double> listRate = new ArrayList<>();
                // 拿到的非dense类的值
                for (int i = 0; i < valList.size(); i++) {
                    String extraVal = valList.getString(i);
                    listKey.add(key);
                    listVal.add(extraVal);
                    // 判断是否有异常值
                    if (!StringUtils.equalsIgnoreCase(extraVal,"") && !StringUtils.equalsIgnoreCase(extraVal,"-999") &&
                            !StringUtils.equalsIgnoreCase(extraVal,"0") && !StringUtils.equalsIgnoreCase(extraVal,"-1")){
                        listRate.add(1d);
                    }else {
                        listRate.add(0d);
                    }
                }
                // cover_rate 计算
                Double count = 0d;
                double coverRate = 0d;
                for (Double d : listRate) {
                    count += d;
                }
                if (!StringUtils.containsIgnoreCase(key,"sequence") && listRate.size() < 2){
                    coverRate = count/listRate.size();
                }else {
                    coverRate = count/30;
                }
                for (int i = 0; i < valList.size(); i++) {
//                        collect(Row.of(listStr.get(i), listVal.get(i), listType.get(i), coverRate));
                    System.out.println(listKey.get(i)+" "+listVal.get(i)+" "+ coverRate + " " + listRate.size());
                }

            }
            else {
                // 拿到的是dense类的值
                List<String> listKey = new ArrayList<>();
                List<String> listVal = new ArrayList<>();
                List<Double> listRate = new ArrayList<>();
                for (int i = 0; i < valList.size(); i++) {
                    String denseVal = valList.getString(i);
                    listKey.add(key);
                    listVal.add(denseVal);
                    // 判断是否有异常值
                    if (!StringUtils.equalsIgnoreCase(denseVal,"") && !StringUtils.equalsIgnoreCase(denseVal,"-999") &&
                            !StringUtils.equalsIgnoreCase(denseVal,"0") && !StringUtils.equalsIgnoreCase(denseVal,"-1")){
                        listRate.add(1d);
                    }else {
                        listRate.add(0d);
                    }
                    System.out.println(listKey.get(i)+"["+i+"]"+" "+listVal.get(i) +" "+ listRate.get(i) + " " + 999);
                }

            }

        }

    }
    @Override
    public TypeInformation<Row> getResultType() {
        TypeInformation[] typeInformations = new TypeInformation[]{Types.STRING, Types.STRING, Types.DOUBLE, Types.INT};
        String[] fieldNames = new String[]{"feature_name", "feature_value","cover_rate","value_size"};
        return Types.ROW_NAMED(fieldNames, typeInformations);
    }


}
