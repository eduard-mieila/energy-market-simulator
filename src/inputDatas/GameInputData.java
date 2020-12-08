package inputDatas;

import java.util.ArrayList;

public class GameInputData {
    private int numberOfTurns;
    private InitialInputData initialInputData;
    private ArrayList<MonthlyUpdateInputData> monthlyUpdateInputData;

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public InitialInputData getInitialData() {
        return initialInputData;
    }

    public void setInitialData(InitialInputData initialInputData) {
        this.initialInputData = initialInputData;
    }

    public ArrayList<MonthlyUpdateInputData> getMonthlyUpdates() {
        return monthlyUpdateInputData;
    }

    public void setMonthlyUpdates(ArrayList<MonthlyUpdateInputData> monthlyUpdateInputData) {
        this.monthlyUpdateInputData = monthlyUpdateInputData;
    }

    @Override
    public String toString() {
        return "GameData{" +
                "numberOfTurns=" + numberOfTurns +
                ", initialData=" + initialInputData +
                ", monthlyUpdates=" + monthlyUpdateInputData +
                '}';
    }
}
