package pers.vast.core;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Fel 实现
 * https://code.google.com/archive/p/fast-el/
 * <p>
 * Created by sengzin on 2018/5/1.
 */
@Component("felEval")
public class FelEval implements Eval {

    @Override
    public BigDecimal me(String expr, Map<String, Object> params) {
        FelEngine fel = new FelEngineImpl();
        FelContext ctx = fel.getContext();
        params.entrySet().forEach(entry -> ctx.set(entry.getKey(), entry.getValue()));
        return new BigDecimal(fel.eval(expr).toString());
    }
}
