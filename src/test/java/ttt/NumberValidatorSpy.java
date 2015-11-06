package ttt;

public class NumberValidatorSpy implements InputValidator {
    private int numberOfTimesIsValidIsCalled = 0;
    private boolean[] fakeAnswers;
    private int fakeAnswerIndex = 0;

    public NumberValidatorSpy(boolean... fakeAnswers) {
        this.fakeAnswers = fakeAnswers;
    }

    @Override
    public boolean isValid(String input) {
        numberOfTimesIsValidIsCalled++;
        return fakeAnswers[fakeAnswerIndex++];
    }

    @Override
    public String invalidReason(String input) {
        return "[" + input + "] not a number - invalid message for test";
    }

    int numberOfTimesIsValidIsCalled() {
        return numberOfTimesIsValidIsCalled;
    }
}
