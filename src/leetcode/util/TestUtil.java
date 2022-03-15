package leetcode.util;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        testEquals(getInstance(WALKER.getCallerClass()), testDataMatrix);
    }

    /**
     * test public methods without {@link Test} annotation in the given instance
     * assert the result equals to the expected
     *
     * @param instance       the instance to be tested
     * @param testDataMatrix each row for a test case
     *                       row[0] for expected
     *                       row[1]...row[n] for input arguments
     */
    public static void testEquals(Object instance, Object[][] testDataMatrix) {
        internalTest(instance, testDataMatrix, TestUtil::assertEquals, 1);
    }

    /**
     * test public methods without {@link Test} annotation in the same class
     * assert the result matches some pattern
     *
     * @param testDataMatrix  each row for a test case
     *                        row[0]...row[n] for input arguments
     * @param assertOperation assertion
     *                        first parameter: input arguments
     *                        second parameter: actual result
     *                        assert the actual result matches some pattern
     */
    public static void test(Object[][] testDataMatrix, BiConsumer<Object, Object> assertOperation) {
        internalTest(getInstance(WALKER.getCallerClass()), testDataMatrix, assertOperation, 0);
    }

    /**
     * test the given methods of the given instance with given test data
     * assert the result equals to the expected
     *
     * @param instance       the instance to be tested
     * @param methods        the methods to be tested
     * @param testDataMatrix each row for a test case
     *                       row[0] for expected
     *                       row[1]...row[n] for input arguments
     */
    public static void testEquals(Object instance, String[] methods, Object[][] testDataMatrix) {
        Assert.assertEquals("methods length does not equal to inputs length!", methods.length, testDataMatrix.length);

        Method[] methodsToTest = new Method[methods.length];
        Map<String, Method> methodMap = Arrays.stream(instance.getClass().getDeclaredMethods()).collect(Collectors.toMap(Method::getName, Function.identity()));
        for (int i = 0; i < methods.length; i++)
            if (Objects.isNull(methodsToTest[i] = methodMap.get(methods[i])))
                throw new RuntimeException("unknown method! " + methods[i]);
        for (int i = 0; i < methodsToTest.length; i++)
            baseTest(instance, methodsToTest[i], testDataMatrix[i], TestUtil::assertEquals, 1);
    }

    public static Object[][] getTestData(Object[] expects, Object[][] inputs) {
        List<Object[]> result = new ArrayList<>(inputs.length);
        for (int i = 0; i < inputs.length; i++)
            result.add(Stream.concat(Stream.of(expects[i]), Stream.of(inputs[i])).toArray());
        System.out.println("Test Data");
        result.forEach(line -> System.out.println(toString(line)));
        System.out.println();
        return result.toArray(Object[][]::new);
    }

    private static void internalTest(Object instance, Object[][] testDataMatrix, BiConsumer<Object, Object> assertOperation, int startIndex) {
        for (Method method : getMethodsToTest(instance.getClass())) {
            for (Object[] testData : testDataMatrix) baseTest(instance, method, testData, assertOperation, startIndex);
            System.out.println();
        }
    }

    @SneakyThrows
    private static void baseTest(Object instance, Method method, Object[] testData, BiConsumer<Object, Object> assertOperation, int startIndex) {
        Object[] input = getInput(testData, startIndex);
        String inputString = toString(input);
        Object[] inputCopy = hasExpected(startIndex) ? null : Arrays.stream(input).map(TestUtil::clone).toArray();
        long start = System.nanoTime();
        Object actual = method.invoke(instance, input);
        long end = System.nanoTime();
        System.out.printf("test: (class:%s) (method:%s) cost %,dns (input:%s) (actual:%s)%n",
                instance.getClass().getName(),
                method.getName(),
                end - start,
                inputString,
                toString(actual));
        assertOperation.accept(hasExpected(startIndex) ? testData[0] : inputCopy, actual);
    }

    @SneakyThrows
    private static Object getInstance(Class<?> testClass) {return testClass.getDeclaredConstructor().newInstance();}

    private static Method[] getMethodsToTest(Class<?> testClass) {
        return Arrays.stream(testClass.getDeclaredMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers()))
                .filter(method -> !Modifier.isStatic(method.getModifiers()))
                .filter(method -> !method.isAnnotationPresent(Test.class))
                .toArray(Method[]::new);
    }

    private static Object[] getInput(Object[] testData, int startIndex) {
        return Arrays.stream(testData, startIndex, testData.length).toArray();
    }

    private static boolean hasExpected(int startIndex) {
        return startIndex == 1;
    }

    private static Object clone(Object o) {
        if (o == null || o.getClass().isPrimitive()) return o;
        if (o.getClass().isArray()) {
            if (Objects.equals(o.getClass().getComponentType(), int.class))
                return Arrays.copyOf((int[]) o, Array.getLength(o));
            if (Objects.equals(o.getClass().getComponentType(), long.class))
                return Arrays.copyOf((long[]) o, Array.getLength(o));
            if (Objects.equals(o.getClass().getComponentType(), double.class))
                return Arrays.copyOf((double[]) o, Array.getLength(o));
            throw new RuntimeException("unknown type! " + o.getClass());
        } else if (Modifier.isFinal(o.getClass().getModifiers())) return o;
        throw new RuntimeException("unknown type! " + o.getClass());
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

    private static void assertEquals(Object expected, Object actual) {
        if (actual == null || !actual.getClass().isArray()) Assert.assertEquals(expected, actual);
        else assertArrayEquals(expected, actual);
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
        } else if (Objects.equals(expected.getClass().getComponentType(), long.class)) {
            Assert.assertArrayEquals((long[]) expected, (long[]) actual);
        } else {
            throw new UnsupportedOperationException("primitive types only support int and double and long right now!");
        }
    }
}
