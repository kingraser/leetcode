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

/**
 * @author Wit
 */
public class TestUtil {
    private static final StackWalker WALKER = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    /**
     * test public non-static methods without {@link Test} annotation in the same class
     * assert the result equals to the expected
     *
     * @param expects   the expects
     * @param arguments the input arguments
     */
    public static void testEquals(Object[] expects, Object[][] arguments) {
        internalTest(getInstance(WALKER.getCallerClass()), arguments, expects, TestUtil::assertEquals);
    }

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
        TestDataMatrix testData = new TestDataMatrix(testDataMatrix);
        internalTest(instance, testData.inputs, testData.expects, TestUtil::assertEquals);
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
        internalTest(
                getInstance(WALKER.getCallerClass()),
                testDataMatrix,
                Arrays.stream(testDataMatrix)
                        .map(a -> Arrays.stream(a)
                                .map(TestUtil::clone)
                                .toArray(Object[]::new))
                        .toArray(Object[][]::new),
                assertOperation);
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
    @Deprecated
    public static void testEquals(Object instance, String[] methods, Object[][] testDataMatrix) {
        TestDataMatrix testData = new TestDataMatrix(testDataMatrix);
        testEquals(instance, methods, testData.expects, testData.inputs);
    }

    /**
     * test the given methods of the given instance with given test data
     *
     * @param instance  the instance to be tested
     * @param methods   the methods to be tested
     * @param expects   the expects
     * @param arguments each row for a test case input
     */
    public static void testEquals(Object instance, String[] methods, Object[] expects, Object[][] arguments) {
        Assert.assertEquals("methods length does not equal to inputs length!", methods.length, arguments.length);
        Assert.assertEquals("inputs length does not equal to expects length!", arguments.length, expects.length);

        Method[] methodsToTest = new Method[methods.length];
        Map<String, Method> methodMap = Arrays.stream(instance.getClass().getDeclaredMethods()).collect(Collectors.toMap(Method::getName, Function.identity()));
        for (int i = 0; i < methods.length; i++)
            if (Objects.isNull(methodsToTest[i] = methodMap.get(methods[i])))
                throw new RuntimeException("unknown method! " + methods[i]);
        for (int i = 0; i < methodsToTest.length; i++)
            baseTest(instance, methodsToTest[i], arguments[i], expects[i], TestUtil::assertEquals);
    }

    private static void internalTest(Object instance, Object[][] inputs, Object[] expects, BiConsumer<Object, Object> assertOperation) {
        Assert.assertEquals(expects.length, inputs.length);
        for (Method method : getMethodsToTest(instance.getClass())) {
            for (int i = 0; i < inputs.length; i++) baseTest(instance, method, inputs[i], expects[i], assertOperation);
            System.out.println();
        }
    }

    @SneakyThrows
    private static void baseTest(Object instance, Method method, Object[] input, Object expected, BiConsumer<Object, Object> assertOperation) {
        String inputString = toString(input);
        long start = System.nanoTime();
        Object actual = method.invoke(instance, input);
        long end = System.nanoTime();
        System.out.printf("test: (class:%s) (method:%s) cost %,dns (input:%s) (actual:%s) (expected;%s)%n",
                instance.getClass().getName(),
                method.getName(),
                end - start,
                inputString,
                toString(actual),
                toString(expected));
        assertOperation.accept(expected, actual);
    }

    @SneakyThrows
    private static Object getInstance(Class<?> testClass) {return testClass.getDeclaredConstructor().newInstance();}

    private static Method[] getMethodsToTest(Class<?> testClass) {
        return Arrays.stream(testClass.getDeclaredMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers()))
                .filter(method -> !Modifier.isStatic(method.getModifiers()))
                .filter(method -> !method.isAnnotationPresent(Test.class))
                .sorted(Comparator.comparing(Method::getName, Comparator.naturalOrder()))
                .toArray(Method[]::new);
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

    /**
     * get array from string input with []
     *
     * @param s the array described in string like [[0,0],[1,1]]
     * @return the array
     */
    public static Object getArray(String s) {
        s = s.replaceAll(" ", "");
        int maxLevel = 1;
        TestArray root = new TestArray(1);
        Stack<TestArray> stack = new Stack<>() {{push(root);}};
        for (int i = 1, c, prev, lastCommaOrBracketIndex = 0; i < s.length(); i++)
            if ((c = s.charAt(i)) == '[') {
                stack.push(new TestArray(stack.peek().level + 1));
                maxLevel = Integer.max(maxLevel, stack.size());
                lastCommaOrBracketIndex = i;
            } else if (c == ']') {
                if ((prev = s.charAt(i - 1)) != '[' && prev != ']')
                    stack.peek().add(s.substring(lastCommaOrBracketIndex + 1, i));
                TestArray top = stack.pop();
                if (!stack.isEmpty()) stack.peek().add(top);
            } else if (c == ',') {
                if (s.charAt(i - 1) != ']') stack.peek().add(s.substring(lastCommaOrBracketIndex + 1, i));
                lastCommaOrBracketIndex = i;
            } else if (c == '"') while (s.charAt(++i) != '"') ;
        return root.toArray(maxLevel);
    }

    public static Object[][] getInputs(String s) {
        return transferToObjectArray(getArray(s));
    }

    public static Object[][] transferToObjectArray(Object array) {
        Object result[][] = new Object[Array.getLength(array)][], row, input[];
        for (int i = 0, j; i < result.length; result[i++] = input)
            for (j = 0, input = new Object[Array.getLength(row = Array.get(array, i))]; j < input.length; j++)
                input[j] = Array.get(row, j);
        return result;
    }

    public static class TestArray {
        private static final Map<Class<?>, Function<String, Object>> DESERIALIZATION_MAP = new HashMap<>() {{
            put(int.class, Integer::parseInt);
            put(long.class, Long::parseLong);
            put(double.class, Double::parseDouble);
            put(String.class, s -> s.substring(1, s.length() - 1));
        }};

        private static final String STRING_FLAG = "\"", NULL = "null";

        List<Object> list = new ArrayList<>();
        int level;
        Set<Class<?>> types = new HashSet<>();

        public TestArray(int level) {this.level = level;}

        public void add(TestArray testArray) {
            list.add(testArray);
            types.addAll(testArray.types);
        }

        public void add(String s) {
            list.add(s);
            if (!isNull(s)) types.add(getType(s));
        }

        Object toArray(int levelCount) {
            return toArray(levelCount, null);
        }

        Object toArray(int levelCount, Class<?> parentType) {
            Class<?> type = getType(parentType);
            Object result = Array.newInstance(type, IntStream.concat(
                            IntStream.of(list.size()),
                            IntStream.range(type == Object.class ? levelCount : level, levelCount))
                    .toArray()), element = null;
            for (int i = 0; i < list.size(); i++)
                try {
                    Array.set(result, i, (element = list.get(i)) instanceof TestArray
                            ? ((TestArray) element).toArray(levelCount, type)
                            : deserialize((String) element));
                } catch (Throwable e) {
                    System.out.printf("result is %s, index is %d, element is %s\n", TestUtil.toString(result), i, TestUtil.toString(element));
                    e.printStackTrace(System.out);
                }
            return result;
        }

        Class<?> getType(Class<?> parentType) {
            if (types.isEmpty()) return parentType;
            if (types.contains(long.class)) types.remove(int.class);
            return types.size() == 1 ? types.iterator().next() : Object.class;
        }

        Class<?> getType(String s) {
            if (s.startsWith(STRING_FLAG) && s.endsWith(STRING_FLAG)) return String.class;
            else if (s.contains(".") || s.endsWith("d") || s.endsWith("D")) return double.class;
            else if (Long.parseLong(s) > Integer.MAX_VALUE || s.endsWith("l") || s.endsWith("L")) return long.class;
            else return int.class;
        }

        Object deserialize(String s) {
            if (isNull(s)) return null;
            Class<?> type = getType(s);
            Function<String, Object> function = DESERIALIZATION_MAP.get(type);
            if (function == null)
                throw new IllegalArgumentException("Unsupported type: " + type + "! Only support int,double,long,String!");
            return function.apply(s);
        }

        boolean isNull(String s) {return NULL.equalsIgnoreCase(s);}
    }

    public static class TestDataMatrix {
        Object[] expects, inputs[];

        public TestDataMatrix(Object[][] testDataMatrix) {
            expects = new Object[testDataMatrix.length];
            inputs = new Object[testDataMatrix.length][];
            for (int i = 0; i < testDataMatrix.length; i++) {
                expects[i] = testDataMatrix[i][0];
                inputs[i] = Arrays.stream(testDataMatrix[i]).skip(1).toArray();
            }
        }
    }
}
