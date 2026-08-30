package challenges;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class ChallengeRegistry {
    private static final Map<Integer, Supplier<Challenge>> REGISTRY = new HashMap<>();

    public static Set<Integer> keys() { return  REGISTRY.keySet(); }

    static {
        REGISTRY.put(2948, LexicographicallySmallestArray::new);
        REGISTRY.put(2091, MinimumDeletions::new);
    }

    public static Challenge get(int id) {
        Supplier<Challenge> supplier = REGISTRY.get(id);
        if (supplier == null) {
            throw new  IllegalArgumentException("Id invalid");
        }
        return supplier.get();
    }
}