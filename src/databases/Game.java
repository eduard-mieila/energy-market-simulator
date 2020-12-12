package databases;

import input.ConsumerInputData;
import input.DistributorInputData;
import input.GameInputData;
import input.MonthlyUpdateInputData;

import java.util.ArrayList;

public final class Game {
    private int numberOfTurns;
    private int currentTurn;
    private ArrayList<ConsumerData> consumers = new ArrayList<>();
    private ArrayList<DistributorData> distributors = new ArrayList<>();
    private ArrayList<MonthlyUpdateInputData> monthlyUpdates;

    public Game(final GameInputData data) throws Exception {
        this.numberOfTurns = data.getNumberOfTurns();
        this.currentTurn = 0;
        EnergeticEntityFactory factory = EnergeticEntityFactory.getInstance();
        for (ConsumerInputData newData : data.getInitialData().getConsumers()) {
            ConsumerData newConsumer = (ConsumerData) factory.getNewEntity("consumer");
            newConsumer.setValues(newData);
            consumers.add(newConsumer);
        }
        for (DistributorInputData newData : data.getInitialData().getDistributors()) {
            DistributorData newDistributor = (DistributorData) factory.getNewEntity("distributor");
            newDistributor.setValues(newData);
            distributors.add(newDistributor);
        }
        this.monthlyUpdates = data.getMonthlyUpdates();
    }

    public void runGame() {

    }

    public void signContracts() {

    }

    public ArrayList<ConsumerData> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<ConsumerData> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<DistributorData> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<DistributorData> distributors) {
        this.distributors = distributors;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    @Override
    public String toString() {
        return "Game{"
                + "numberOfTurns=" + numberOfTurns
                + ", consumers=" + consumers
                + ", distributors=" + distributors
                + ", monthlyUpdates=" + monthlyUpdates
                + '}';
    }

    public ArrayList<MonthlyUpdateInputData> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public void setMonthlyUpdates(final ArrayList<MonthlyUpdateInputData> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }
}
