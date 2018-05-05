package pers.vast.core;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 表达式测试用例
 * Created by sengzin on 2018/5/1.
 */
public class EvalTest {
    // 表达式
    List<String> EXPS = Lists.newArrayList(
            "1 + 1",
            "x * 5 / 7 + 4",
            "1 / 3",
            "y^3",
            "12 % 2",
            "sin(0.5)"
    );
    // 参数
    Map<String, Object> params = ImmutableMap.of(
            "x", 7.7,
            "y", 3
    );

    @Test
    public void testFelEval() {
        Eval eval = new FelEval();
        EXPS.forEach(exp -> print("Use FelEval: %s = %s", exp, eval.me(exp, params)));
    }

    @Test
    public void testGroovyEval() {
        Eval eval = new GroovyEval();
        EXPS.forEach(exp -> print("Use GroovyEval: %s = %s", exp, eval.me(exp, params)));
    }

    @Test
    public void testJexlEval() {
        Eval eval = new JexlEval();
        EXPS.forEach(exp -> print("Use JexlEval: %s = %s", exp, eval.me(exp, params)));
        // wrong
        // 1 / 3 = 0
        // y^10 = 8
    }

    @Test
    public void testParsiiEval() {
        Eval eval = new ParsiiEval();
        EXPS.forEach(exp -> print("Use ParsiiEval: %s = %s", exp, eval.me(exp, params)));
    }

    private static void print(String temp, Object... args) {
        System.out.println(String.format(temp, args));
    }
}
