package leetcode.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionUtils {

    public static <T> List<T> asList(Iterator<T> iterator) {
        List<T> result = new ArrayList<>();
        iterator.forEachRemaining(result::add);
        return result;
    }

    public static <T> List<T> asList(List<Iterator<T>> iterators) {
        List<T> result = new ArrayList<>();
        iterators.forEach(iterator -> iterator.forEachRemaining(result::add));
        return result;
    }
}
