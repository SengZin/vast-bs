package pers.vast.core

/**
 * Created by sengzin on 2018/5/1.
 */
onMethodSelection { expr, methodNode ->
    if (methodNode.declaringClass.name == 'java.lang.System') {
        addStaticTypeError("(From pers.vast.core.SecureExtension1) Method call is not allowed!", expr)
    }
}