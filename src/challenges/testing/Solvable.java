package challenges;

public interface Solvable<I, O> {
    O solve(I input);
}