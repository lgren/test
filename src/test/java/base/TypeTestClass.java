package base;

import java.util.function.IntFunction;
import java.util.function.IntSupplier;

/**
 * https://www.oschina.net/translate/hacking-lambda-expressions-in-java?cmp&p=3
 * @author lgren
 * @since 2020-11-25 5:48 下午
 */
public class TypeTestClass<T extends CharSequence & IntFunction> {
    public TypeTestClass(T data) {
        System.out.println(data);
    }


    public static void main(String[] args) {
        TypeTestClass typeTestClass = new TypeTestClass("String.class");
    }
}
