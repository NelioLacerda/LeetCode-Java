import challenges.Challenge;
import challenges.ChallengeRegistry;

void main() {
    int id = 2948; //2948, 2091

    Challenge challenge = ChallengeRegistry.get(id);
    challenge.getSolutions();
}