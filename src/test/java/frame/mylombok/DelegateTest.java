package frame.mylombok;

import lombok.Data;
import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class DelegateTest {
    private interface SimpleCollection {
        boolean add(String item);

        boolean remove(Object item);
    }

    @Delegate(types = SimpleCollection.class)
    private final Collection<String> collection = new ArrayList<>();

    public static void main(String[] args) {
        DelegateTest delegateTest = new DelegateTest();
        delegateTest.add("你好呀");
        System.out.println(delegateTest.getCollection());
        delegateTest.remove("你好呀");
        System.out.println(delegateTest.getCollection());
    }
}
