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
    private static final StackWalker WALKER = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    /**
     * test public methods without {@link Test} annotation in the same class
     * assert the result equals to the expected
     *
     * @param testDataMatrix each row for a test case
     *                       row[0] for expected
     *                       row[1]...row[n] for input arguments
     */
    public static void testEquals(Object[][] testDataMatrix) {
        test(WALKER.getCallerClass(), testDataMatrix, Assert::assertEquals, 1);
    }

    /**
     * test public methods without {@link Test} annotation in the same class
     * assert the result is array and equals to the expected
     *
     * @param testDataMatrix each row for a test case
     *                       row[0] for expected
     *                       row[1]...row[n] for input arguments
     */
    public static void testArrayEquals(Object[][] testDataMatrix) {
        test(WALKER.getCallerClass(), testDataMatrix, TestUtil::assertArrayEquals, 1);
    }

    /**
     * test public methods without {@link Test} annotation in the same class
     * assert the result matches some pattern
     *
     * @param testDataMatrix  each row for a test case
     *                        row[0]...row[n] for input arguments
     * @param assertOperation assertion
     */
    public static void test(Object[][] testDataMatrix, BiConsumer<Object, Object> assertOperation) {
        test(WALKER.getCallerClass(), testDataMatrix, assertOperation, 0);
    }

    /**
     * test method
     *
     * @param classToTest     the class to new an instance to test
     * @param testDataMatrix  each row for a test case
     *                        row[0] for expected
     *                        row[1]...row[n] for input arguments
     * @param assertOperation assert operation
     * @param startIndex      the startIndex of input arguments, inclusive.
     */
    @SneakyThrows
    private static void test(Class<?> classToTest, Object[][] testDataMatrix, BiConsumer<Object, Object> assertOperation, int startIndex) {
        test(classToTest.getDeclaredConstructor().newInstance(), testDataMatrix, assertOperation, startIndex);
    }

    @SneakyThrows
    public static void test(Object instance, Object[][] testDataMatrix, BiConsumer<Object, Object> assertOperation, int startIndex) {
        Class<?> classToTest = instance.getClass();
        Method[] methodsToTest = Arrays.stream(classToTest.getDeclaredMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers()))
                .filter(method -> !method.isAnnotationPresent(Test.class))
                .toArray(Method[]::new);
        for (Method method : methodsToTest) {
            for (Object[] testData : testDataMatrix) {
                Object[] input = Arrays.stream(testData, startIndex, testData.length).toArray();
                String inputString = toString(input);
                long start = System.nanoTime();
                Object actual = method.invoke(instance, input);
                long end = System.nanoTime();
                System.out.printf("test: (class:%s) (method:%s) cost %,dns (input:%s) (actual:%s)%n",
                        classToTest.getName(),
                        method.getName(),
                        end - start,
                        inputString,
                        toString(actual));
                assertOperation.accept(testData[0], actual);
            }
            System.out.println();
        }
    }

    /**
     * toString function compatible with array
     *
     * @param o object to transfer to string
     * @return string for the input object
     */
    public static String toString(Object o) {
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
            throw new UnsupportedOperationException("primitive types only support int and double right now!");
        }
    }
}
