package ttt.ui;

public class StandardTextPresenter implements TextPresenter {

    @Override
    public String winningMessage(String winner) {
        return "Congratulations - " + winner + " has won";
    }

    @Override
    public String drawMessage() {
        return "No winner this time";
    }
}
