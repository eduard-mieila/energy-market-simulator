package input;

import java.util.ArrayList;

public final class InitialInputData {
    private ArrayList<ConsumerInputData> consumerInputData;
    private ArrayList<DistributorInputData> distributorInputData;

    public ArrayList<ConsumerInputData> getConsumers() {
        return consumerInputData;
    }

    public void setConsumers(final ArrayList<ConsumerInputData> consumerInputData) {
        this.consumerInputData = consumerInputData;
    }

    public ArrayList<DistributorInputData> getDistributors() {
        return distributorInputData;
    }

    public void setDistributors(final ArrayList<DistributorInputData> distributorInputData) {
        this.distributorInputData = distributorInputData;
    }

    @Override
    public String toString() {
        return "InitialData{"
                + "consumers=" + consumerInputData
                + ", distributors=" + distributorInputData
                + '}';
    }
}
