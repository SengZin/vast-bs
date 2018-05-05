package pers.vast.core;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * groovy 实现
 * todo 安全问题
 * http://melix.github.io/blog/2015/03/sandboxing.html 改进了Groovy脚本的沙盒
 * http://kohsuke.org/2012/04/27/groovy-secureastcustomizer-is-harmful/ 这篇也看看
 * Created by sengzin on 2018/5/1.
 */
@Component("groovyEval")
public class GroovyEval implements Eval {
    @Override
    public BigDecimal me(String expr, Map<String, Object> params) {
        Binding bind = new Binding();
        params.entrySet().forEach(entry -> bind.setVariable(entry.getKey(), entry.getValue()));
        GroovyShell sh = new GroovyShell(bind);
        return new BigDecimal(sh.evaluate(expr).toString());
    }
}
