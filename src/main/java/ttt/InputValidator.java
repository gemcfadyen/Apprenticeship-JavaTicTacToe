package ttt;

public interface InputValidator {

    boolean isValid(String input);

    String invalidReason(String input);
}
