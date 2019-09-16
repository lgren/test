package test.interfaceTest;

public interface IB {
    default String get() {
        return "b";
    }
}
