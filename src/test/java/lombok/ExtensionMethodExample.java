// package lombok;
//
//
// import lombok.experimental.ExtensionMethod;
// import org.junit.Test;
//
// @ExtensionMethod({java.util.Arrays.class, Extensions.class})
// public class ExtensionMethodExample {
//     @Test
//     public void test() {
//         int[] intArray = {5, 3, 8, 2};
//         intArray.sort();
//
//         String iAmNull = null;
//         String iAmNullOr = iAmNull.or("hELlO, WORlD!".toTitleCase());
//         System.out.println();
//     }
//
// }
//
// class Extensions {
//     public static <T> T or(T obj, T ifNull) {
//         return obj != null ? obj : ifNull;
//     }
//
//     public static String toTitleCase(String in) {
//         if (in.isEmpty()) return in;
//         return "" + Character.toTitleCase(in.charAt(0)) +
//                 in.substring(1).toLowerCase();
//     }
// }
