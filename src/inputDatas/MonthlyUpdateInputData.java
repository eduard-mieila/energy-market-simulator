package inputDatas;

import java.util.ArrayList;

public class MonthlyUpdateInputData {
    private ArrayList<ConsumerInputData> newConsumerInputData;
    private ArrayList<DistributorUpdateInputData> costsChanges;

    public ArrayList<ConsumerInputData> getNewConsumers() {
        return newConsumerInputData;
    }

    public void setNewConsumers(ArrayList<ConsumerInputData> newConsumerInputData) {
        this.newConsumerInputData = newConsumerInputData;
    }

    public ArrayList<DistributorUpdateInputData> getCostsChanges() {
        return costsChanges;
    }

    public void setCostsChanges(ArrayList<DistributorUpdateInputData> costChanges) {
        this.costsChanges = costChanges;
    }

    @Override
    public String toString() {
        return "MonthlyUpdate{" +
                "newConsumers=" + newConsumerInputData +
                ", costChanges=" + costsChanges +
                '}';
    }
}
