package pers.vast.core;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * jexl 实现
 * 学习成本大，有些表达式不通用，不建议用
 * Created by sengzin on 2018/5/1.
 */
@Deprecated
@Component("jexlEval")
public class JexlEval implements Eval {

    @Override
    public BigDecimal me(String expr, Map<String, Object> params) {
        JexlEngine jexl = new JexlEngine();
        Expression exp = jexl.createExpression( expr );
        // Create a context and add data
        JexlContext ctx = new MapContext();
        params.entrySet().forEach(entry -> ctx.set(entry.getKey(), entry.getValue()));
        return new BigDecimal(exp.evaluate(ctx).toString());
    }
}
