package output;

import databases.ConsumerData;
import databases.DistributorData;
import databases.Game;

import java.util.ArrayList;

public final class OutputData {
    private ArrayList<ConsumerOutputData> consumers;
    private ArrayList<DistributorOutputData> distributors;

    /**
     * Converts a Game to GameOutput class
     * @param game Game to be converted
     */
    public OutputData(final Game game) {
        this.consumers = new ArrayList<>();
        for (ConsumerData consumer : game.getConsumers()) {
            ConsumerOutputData current = new ConsumerOutputData(consumer);
            this.consumers.add(current);
        }

        this.distributors = new ArrayList<>();
        for (DistributorData distributor : game.getDistributors()) {
            DistributorOutputData current = new DistributorOutputData(distributor);
            this.distributors.add(current);
        }
    }

    public ArrayList<ConsumerOutputData> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<ConsumerOutputData> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<DistributorOutputData> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<DistributorOutputData> distributors) {
        this.distributors = distributors;
    }
}
