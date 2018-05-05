package pers.vast.core;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * https://www.ibm.com/developerworks/cn/java/j-lo-jep/index.html
 * Created by sengzin on 2018/5/1.
 */
@Component("jepEval")
public class JepEval implements Eval {
    @Override
    public BigDecimal me(String expr, Map<String, Object> params) {
        return null;
    }
}
