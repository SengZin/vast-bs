package pers.vast.core;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 表达式执行接口
 * Created by sengzin on 2018/5/1.
 */
public interface Eval {

    BigDecimal me(String expr, Map<String, Object> params);

}
