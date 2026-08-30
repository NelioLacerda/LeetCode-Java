package challenges.testing;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TestRegistry {
    private static final Map<Integer, List<TestCase<?>>> REGISTRY = new HashMap<>();

    static {
        REGISTRY.put(2948, List.of(
                new TestCase<>("case1", new Object[]{ new int[]{1,5,3,9,8}, 2 }, new int[]{1,3,5,8,9}),
                new TestCase<>("case2", new Object[]{ new int[]{1,7,6,18,2,1}, 3 }, new int[]{1,6,7,18,1,2}),
                new TestCase<>("case3", new Object[]{ new int[]{1,7,28,19,10}, 3 }, new int[]{1,7,28,19,10}),
                new TestCase<>("case4", new Object[]{ new int[]{5,100,44,45,16,30,14,65,83,64}, 15 }, new int[]{5,100,14,16,30,44,45,64,83,65})
                ));
        REGISTRY.put(2091, List.of(
                new TestCase<>("case1", new Object[]{ new int[]{2,10,7,5,4,1,8,6} }, 5),
                new TestCase<>("case2", new Object[]{ new int[]{0,-4,19,1,8,-2,-3,5} }, 3),
                new TestCase<>("case3", new Object[]{ new int[]{101} }, 1),
                new TestCase<>("case4", new Object[]{ new int[]{-14,61,29,-18,59,13,-67,-16,55,-57,7,74} }, 6)
        ));
    }

    public static List<TestCase<?>> get(int id) {
        return REGISTRY.getOrDefault(id, List.of());
    }
}