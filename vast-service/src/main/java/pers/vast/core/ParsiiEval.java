package pers.vast.core;

import org.springframework.stereotype.Component;
import parsii.eval.Parser;
import parsii.eval.Scope;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Parsii
 * Created by sengzin on 2018/5/1.
 */
@Component("defaultEval")
public class ParsiiEval implements Eval {

    @Override
    public BigDecimal me(String expr, Map<String, Object> params) {
        Scope scope = new Scope(); // 没有静态 create 方法啊
        // 值只能是 dubbo
        params.entrySet().forEach(entry -> scope.create(entry.getKey()).setValue(Double.parseDouble(entry.getValue().toString())));
        double result;
        try {
            result = Parser.parse(expr, scope).evaluate();
        } catch (Throwable e) {
            throw new RuntimeException("Parsii 计算失败", e);
        }
        return new BigDecimal(String.valueOf(result));
    }

}
