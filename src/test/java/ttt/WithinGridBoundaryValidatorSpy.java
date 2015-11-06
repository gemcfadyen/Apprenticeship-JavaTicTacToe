package ttt;

public class WithinGridBoundaryValidatorSpy implements InputValidator {
    private final boolean[] fakeAnswers;
    private int fakeAnswerIndex = 0;
    private int numberOfTimesIsValidIsCalled;

    public WithinGridBoundaryValidatorSpy(boolean... fakeAnswers) {
        this.fakeAnswers = fakeAnswers;
        numberOfTimesIsValidIsCalled = 0;
    }

    @Override
    public boolean isValid(String input) {
        numberOfTimesIsValidIsCalled++;
        return fakeAnswers[fakeAnswerIndex++];
    }

    @Override
    public String invalidReason(String input) {
        return "[" + input + "] outside grid boundary - invalid message for test";
    }

    public int numberOfTimesIsValidIsCalled() {
        return numberOfTimesIsValidIsCalled;
    }
}
