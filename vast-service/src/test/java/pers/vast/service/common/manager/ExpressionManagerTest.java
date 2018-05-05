package pers.vast.service.common.manager;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.util.Eval;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.jeval.Evaluator;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.SecureASTCustomizer;
import org.junit.Test;
import org.nfunk.jep.JEP;
import parsii.eval.Parser;
import parsii.eval.Scope;

import java.util.Arrays;
import java.util.Collections;

/**
 * 表达式 test
 * Created by sengzin on 2018/4/30.
 */
@Slf4j
public class ExpressionManagerTest {

    static String EXP_SIMPLE = "2 * x";
    static String EXP = "sin(0.5)";
    static String EXP_4_JEVAL = "2+(7 - 5) * 3.14159*#{x}+ sin(0)";
    static String EXP_ASCII_MATH = "sum_(i=1)^n i^3=((n(n+1))/2)^2";
    static String EXP_LOGIC = "if (x > 6) {x*x} else {x}";
    static double VALUE_X = 6.6;

    private static void print(String lib, String exp, Object result) {
        log.info("\nUse lib：{}, {} = {}", lib, exp, result);
    }

    /**
     * parsii
     */
    @Test
    public void parsii() throws Exception {
        // 编译
        Scope scope = new Scope(); // 没有静态 create 方法啊
        scope.create("x").setValue(VALUE_X);
        scope.create("n").setValue(2);
        // 执行 return double
        print("parsii", EXP_SIMPLE, Parser.parse(EXP_SIMPLE, scope).evaluate());
        print("parsii", EXP, Parser.parse(EXP, scope).evaluate());
        //print("parsii", EXP_ASCII_MATH, Parser.parse(EXP_ASCII_MATH, scope).evaluate());
    }

    /**
     * JEval
     * 传参必须是 字符串
     * 不支持 '^'
     */
    @Test
    public void jeval() throws Exception {
        // 编译
        Evaluator jevalEvaluator = new Evaluator();
        jevalEvaluator.setVariables(Collections.singletonMap("x", String.valueOf(VALUE_X)));
        // 执行 return String
        print("JEval", EXP_4_JEVAL, jevalEvaluator.evaluate(EXP_4_JEVAL));
        // print 43.468987999999996
        // 其他
        // 可以做字符串相加
        print("JEval", "'A' + 'C'", jevalEvaluator.evaluate("'A' + 'C'"));
        // 附带了十多种数学函数
        // round(3);3 的随机数
        // abs(-1); 绝对值
        // abs ,acos ,asin ,atan ,atan2 ,ceil ,cos ,exp ,floor ,log ,max ,min ,pow ,random ,rint ,round ,sin ,sqrt ,tan
        // 如果不够用，还可以自己添加函数只要implements Function这个类就可以了
        // Q：不支持乘方肿么破？不是可以自己实现函数吗？
    }

    /**
     * JEPLite
     * http://mvnrepository.com/artifact/org.cheffo/jeplite
     * 跟 JEP 不是一个库
     */
    @Test
    public void jepLite() throws Exception {
        // 编译
        JEP jep = new JEP();
        jep.addVariable("x", VALUE_X);
        jep.parseExpression(EXP);
        // 执行
        print("JEPLite", EXP, jep.getValue());
        // print NaN
        jep.parseExpression(EXP_SIMPLE);
        print("JEP", EXP_SIMPLE, jep.getValue());
    }

    /**
     * JEP
     * http://www.singularsys.com/jep/
     * 支持自定义变量、常量、函数
     * 包含大量通用的数学函数和常量
     */
    @Test
    public void jep() throws Exception {
        // 编译

        // 执行
        // print("", EXP, );
        // print
    }

    /**
     * Expression4J
     * http://expression4j.sourceforge.net/
     * 高度定制的，可自定义文法，如 "f(x, b) = 2*x-cos(b)" , "g(x, y)= f(y, x) * -2" 等
     * 还可以定义函数目录（函数集）、支持 XML 配置文件
     * 12 年评价，还不是十分成熟的框架，仍在不断完善中
     * 20006 年出、没更新
     * https://blog.csdn.net/earbao/article/details/39830495 使用 demo
     */
    @Test
    public void expression4j() throws Exception {
        // 编译

        // 执行
        // print("", EXP, );
        // print
    }

    /**
     * groovy
     */
    @Test
    public void groovy() throws Exception {

    }

    /**
     * demo
     */
    @Test
    public void demo() throws Exception {
        // 编译

        // 执行
        // print("", EXP, );
        // print
    }
}
