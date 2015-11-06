package ttt;

public class ReplayOptionValidator implements InputValidator {
    @Override
    public boolean isValid(String input) {
        for (ReplayOptions replayOptions : ReplayOptions.values()) {
            if(replayOptions.name().equals(input)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String invalidReason(String input) {
        return "[" + input + "] is not a valid replay option. Please re-enter Y/N";
    }
}
