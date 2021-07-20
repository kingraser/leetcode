package leetcode.util;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * @author Wit
 */
public class TestUtil {

    public static void testEquals(Object algorithm, Method method, Object[][] testDataMatrix) {
        test(algorithm, method, testDataMatrix, Assert::assertEquals);
    }

    public static void test(Object algorithm, Method method, Object[][] testDataMatrix, BiConsumer<Object, Object> consumer) {
        Arrays.stream(testDataMatrix)
                .forEach(testData -> consumer.accept(testData[0],
                        getResult(algorithm, method, Arrays.stream(testData, 1, testData.length).toArray())));
    }

    /**
     * test use assertEquals
     *
     * @param algorithm      the instance of the algorithm class which has the method to test
     * @param testDataMatrix the test cases
     *                       [0] is expected
     *                       [1 ... last] is input data
     */
    public static void testEquals(Object algorithm, Object[][] testDataMatrix) {
        Arrays.stream(algorithm.getClass().getDeclaredMethods())
                .filter(method -> !method.isAnnotationPresent(Test.class) && Modifier.isPublic(method.getModifiers()))
                .forEach(method -> test(algorithm, method, testDataMatrix, Assert::assertEquals));
    }

    /**
     * get the result with certain input
     *
     * @param algorithm the instance of the algorithm class which has the method to generate the result
     * @param method    the method to generate the result
     * @param args      the input args
     * @return result
     */
    static Object getResult(Object algorithm, Method method, Object... args) {
        try {
            return method.invoke(algorithm, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
