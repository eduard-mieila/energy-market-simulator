package input;

import java.util.ArrayList;

public final class GameInputData {
    private int numberOfTurns;
    private InitialInputData initialInputData;
    private ArrayList<MonthlyUpdateInputData> monthlyUpdateInputData;

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public InitialInputData getInitialData() {
        return initialInputData;
    }

    public void setInitialData(final InitialInputData initialInputData) {
        this.initialInputData = initialInputData;
    }

    public ArrayList<MonthlyUpdateInputData> getMonthlyUpdates() {
        return monthlyUpdateInputData;
    }

    public void setMonthlyUpdates(final ArrayList<MonthlyUpdateInputData> monthlyUpdateInputData) {
        this.monthlyUpdateInputData = monthlyUpdateInputData;
    }

    @Override
    public String toString() {
        return "GameData{"
                + "numberOfTurns=" + numberOfTurns
                + ", initialData=" + initialInputData
                + ", monthlyUpdates=" + monthlyUpdateInputData
                + '}';
    }
}
