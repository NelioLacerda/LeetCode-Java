package challenges.testing;

public record TestCase<Expected>(String name, Object[] input, Expected expected) {}