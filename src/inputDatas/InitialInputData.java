package inputDatas;

import java.util.ArrayList;

public class InitialInputData {
    private ArrayList<ConsumerInputData> consumerInputData;
    private ArrayList<DistributorInputData> distributorInputData;

    public ArrayList<ConsumerInputData> getConsumers() {
        return consumerInputData;
    }

    public void setConsumers(ArrayList<ConsumerInputData> consumerInputData) {
        this.consumerInputData = consumerInputData;
    }

    public ArrayList<DistributorInputData> getDistributors() {
        return distributorInputData;
    }

    public void setDistributors(ArrayList<DistributorInputData> distributorInputData) {
        this.distributorInputData = distributorInputData;
    }

    @Override
    public String toString() {
        return "InitialData{" +
                "consumers=" + consumerInputData +
                ", distributors=" + distributorInputData +
                '}';
    }
}
