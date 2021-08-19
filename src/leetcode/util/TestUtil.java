package leetcode.util;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Wit
 */
public class TestUtil {
    static final StackWalker WALKER = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    public static void testEquals(Object[][] testDataMatrix) {
        test(WALKER.getCallerClass(), testDataMatrix, Assert::assertEquals);
    }

    public static void testArrayEquals(Object[][] testDataMatrix) {
        test(WALKER.getCallerClass(), testDataMatrix, TestUtil::assertArrayEquals);
    }

    @SneakyThrows
    private static <T> void test(Class<T> classToTest, Object[][] testDataMatrix, BiConsumer<Object, Object> assertOperation) {
        Object instance = classToTest.getDeclaredConstructor().newInstance();
        Method[] methodsToTest = Arrays.stream(classToTest.getDeclaredMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers()))
                .filter(method -> !method.isAnnotationPresent(Test.class))
                .toArray(Method[]::new);
        for (Method method : methodsToTest)
            for (Object[] testData : testDataMatrix) {
                Object[] input = Arrays.stream(testData, 1, testData.length).toArray();
                Object actual = method.invoke(instance, input);
                System.out.printf("test: (class:%s) (method:%s) (input:%s) (expected:%s) (actual:%s)%n",
                        classToTest.getName(),
                        method.getName(),
                        toString(input),
                        toString(testData[0]),
                        toString(actual));
                assertOperation.accept(testData[0], actual);
            }
    }

    private static String toString(Object o) {
        if (Objects.isNull(o) || !o.getClass().isArray()) {return String.valueOf(o);}
        return IntStream.range(0, Array.getLength(o))
                .mapToObj(i -> Array.get(o, i))
                .map(TestUtil::toString)
                .collect(Collectors.joining(",", "[", "]"));
    }

    private static void assertArrayEquals(Object expected, Object actual) {
        if (Objects.isNull(expected) || Objects.isNull(actual)) {
            Assert.assertEquals(expected, actual);
            return;
        }
        if (!expected.getClass().isArray() || !actual.getClass().isArray()) {
            throw new IllegalArgumentException("expected and actual type should both be array!");
        }

        if (!Objects.equals(expected.getClass().getComponentType(), actual.getClass().getComponentType())) {
            throw new IllegalArgumentException("expected and actual type should be same!");
        }

        if (!expected.getClass().getComponentType().isPrimitive()) {
            Assert.assertArrayEquals((Object[]) expected, (Object[]) actual);
        } else if (Objects.equals(expected.getClass().getComponentType(), int.class)) {
            Assert.assertArrayEquals((int[]) expected, (int[]) actual);
        } else if (Objects.equals(expected.getClass().getComponentType(), double.class)) {
            Assert.assertArrayEquals((double[]) expected, (double[]) actual, Double.MIN_NORMAL);
        } else {
            throw new UnsupportedOperationException("primitive types only support int double right now!");
        }
    }
}
