package pers.vast.core;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.transform.CompileStatic;
import groovy.util.Eval;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.MultipleCompilationErrorsException;
import org.codehaus.groovy.control.customizers.ASTTransformationCustomizer;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.codehaus.groovy.control.customizers.SecureASTCustomizer;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by sengzin on 2018/5/1.
 */
public class GroovyTest {

    private static void print(String temp, Object... args) {
        System.out.println(String.format(temp, args));
    }

    // 简单表达式
    @Test
    public void simpleExp() {
        String exp1 = "2*7.7";
        Object retObj = Eval.me(exp1);
        // 浮点型计算返回 BigDecimal
        print("ret class: %s", retObj.getClass().getName());
        Number number = (Number) retObj;
        print("%s = %s -> toString() = %s", exp1, number, number.toString());
        // 转 double 没有精度问题
        print("%s = %s -> toDouble() = %s", exp1, number, number.doubleValue());
        String exp2 = "2 + 1";
        retObj = Eval.me(exp2);
        // 整数计算返回 Integer
        print("ret class: %s", retObj.getClass().getName());
        print("%s = %s", exp2, retObj);
    }

    private GroovyShell getBlackListGroovyShell()  {
        CompilerConfiguration conf = new CompilerConfiguration();
        SecureASTCustomizer cust = new SecureASTCustomizer();
        cust.setReceiversBlackList(Arrays.asList(System.class.getName()));
        conf.addCompilationCustomizers(cust);
        return new GroovyShell(conf);
    }

    /**
     * 天真的沙盒
     * 比起黑名单更建议用白名单，因为黑名单很容易漏掉一些问题
     */
    @Test// (expected = MultipleCompilationErrorsException.class)
    public void naiveSandbox() {
        GroovyShell sh = this.getBlackListGroovyShell();
        // Object ret = sh.evaluate("System.exit(-1)");
        // 能执行成功 ...
        // sys 被声明为 def，等于宣告它为 object，所以黑名单检测不到是 System 的方法被调用
        Object ret = sh.evaluate("def sys = System;sys.exit(-1)");
        print("exit(-1) = %s", ret);
        // 其他能绕过 AST 黑名单的方法
        // ((Object)System).exit(-1);
        // Class.forName('java.lang.System').exit(-1);
        // ('java.lang.Systemm' as Class).exit(-1);
        // import static java.lang.System.exit; exit(-1);
        // ...
        // Groovy 的动态使得编译时无法解决这些情况
    }

    /**
     * 类型检查
     */
    @Test
    public void testTypeChecked() {
        String exp = // "@groovy.transform.TypeChecked // or even @CompileStatic\n" +
                "@groovy.transform.CompileStatic \n" +
                "void foo() {\n" +
                "  def c = System\n" +
                "  c.exit(-1)\n" +
                "}\n" +
                "foo()";
        String expHead = "@groovy.transform.TypeChecked\n" +
                "void foo() {\n";
        String expBody = "def sys = System\n sys.exit(-1)\n";
        String expFoot = "}\n foo()";
        GroovyShell sh = this.getBlackListGroovyShell();
        sh.evaluate(exp);
        // sh.evaluate(expHead + expBody + expFoot);
    }

    /**
     * 简单的类型检查
     * 没成功，在 classpath 下找不到 SecureExtension1.groovy
     */
    @Test
    public void simpleTypechecked() {
        String exp = "@groovy.transform.TypeChecked(extensions=['SecureExtension1.groovy'])\n" +
                "void foo() {\n" +
                "  def c = System\n" +
                "  c.exit(-1)\n" +
                "}\n" +
                "foo()";
        GroovyShell sh = this.getBlackListGroovyShell();
        sh.evaluate(exp);
    }

    /**
     * 静态导入
     */
    @Test
    public void staticImport() {
        CompilerConfiguration conf = new CompilerConfiguration();
        ImportCustomizer cust = new ImportCustomizer();
        // 相当于 import static java.lang.Math.*
        cust.addStaticStars("java.lang.Math");
        // 加上这句会报错，编译器无法知道 socre 的类型，需要做类型检查扩展
        ASTTransformationCustomizer astCust = new ASTTransformationCustomizer(CompileStatic.class);
        conf.addCompilationCustomizers(cust, astCust);
        Binding bind = new Binding();
        bind.setVariable("score", 2.0);
        GroovyShell sh = new GroovyShell(bind, conf);
        String exp = "abs(cos(1 + score))";
        Number result = (Number) sh.evaluate(exp);
        print("%s = %s", exp, result);
    }

    public void demo() {

    }

}
