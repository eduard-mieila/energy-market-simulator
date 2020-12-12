package input;

import java.util.ArrayList;

public final class MonthlyUpdateInputData {
    private ArrayList<ConsumerInputData> newConsumerInputData;
    private ArrayList<DistributorUpdateInputData> costsChanges;

    public ArrayList<ConsumerInputData> getNewConsumers() {
        return newConsumerInputData;
    }

    public void setNewConsumers(final ArrayList<ConsumerInputData> newConsumerInputData) {
        this.newConsumerInputData = newConsumerInputData;
    }

    public ArrayList<DistributorUpdateInputData> getCostsChanges() {
        return costsChanges;
    }

    public void setCostsChanges(final ArrayList<DistributorUpdateInputData> costChanges) {
        this.costsChanges = costChanges;
    }

    @Override
    public String toString() {
        return "MonthlyUpdate{"
                + "newConsumers=" + newConsumerInputData
                + ", costChanges=" + costsChanges
                + '}';
    }
}
