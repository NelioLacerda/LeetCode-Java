package challenges.testing;

import challenges.Challenge;
import challenges.ChallengeRegistry;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Objects;
import java.util.stream.Stream;

public class ChallengeTest {
    @TestFactory
    Stream<DynamicTest> runAllChallenges() {
        String filter = "2091";

        return ChallengeRegistry.keys().stream()
                .filter(id -> filter == null || filter.isBlank() || id.toString().equals(filter))
                .flatMap(id -> TestRegistry.get(id).stream()
                        .map(tc -> DynamicTest.dynamicTest(
                                "Challenge " + id + " - " + tc.name(),
                                () -> runTest(id, tc)
                        )));
    }

    private void runTest(int id, TestCase<?> tc) {
        Challenge challenge = ChallengeRegistry.get(id);
        if (!(challenge instanceof Solvable<?> solvable)) {
            throw new AssertionError("Challenge " + id + " does not implement Solvable");
        }
        @SuppressWarnings("unchecked")
        var typed = (Solvable<Object>) solvable;
        var result = typed.solve(tc.input());

        if (!Objects.deepEquals(result, tc.expected())) {
            throw new AssertionError(
                    "Expected: " + describe(tc.expected()) +
                            " but got: " + describe(result)
            );
        }
    }

    private static String describe(Object o) {
        if (o instanceof int[] arr) return java.util.Arrays.toString(arr);
        if (o instanceof Object[] arr) return java.util.Arrays.deepToString(arr);
        return String.valueOf(o);
    }
}