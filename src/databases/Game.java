package databases;

import input.ConsumerInputData;
import input.DistributorUpdateInputData;
import input.DistributorInputData;
import input.GameInputData;
import input.MonthlyUpdateInputData;
import utils.Constants;

import java.util.ArrayList;

public final class Game {
    private int numberOfTurns;
    private ArrayList<ConsumerData> consumers = new ArrayList<>();
    private ArrayList<DistributorData> distributors = new ArrayList<>();
    private ArrayList<MonthlyUpdateInputData> monthlyUpdates;

    public Game(final GameInputData data) throws Exception {
        this.numberOfTurns = data.getNumberOfTurns();
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

    /**
     * Search for a distributor by id
     * @param id of the wanted distributor
     * @return found distributor
     * @throws Exception if no distributor found
     */
    public DistributorData getDistributorById(final int id) throws Exception {
        for (DistributorData currentDistributor : this.distributors) {
            if (currentDistributor.getId() == id) {
                return currentDistributor;
            }
        }
        throw new Exception("Distributor no. " + id + " could not be found");
    }

    private void payMonthlyIncome() {
        for (ConsumerData consumer : this.consumers) {
            if (!consumer.isBankrupt()) {
                consumer.setBudget(consumer.getBudget() + consumer.getMonthlyIncome());
            }
        }
    }

    /**
     * The method represents the game simulation
     * @throws Exception
     */
    public void runGame() throws Exception {
        EnergeticEntityFactory factory = EnergeticEntityFactory.getInstance();
        for (int i = 0; i <= this.numberOfTurns; i++) {

            // Updates
            if (i != 0) {
                MonthlyUpdateInputData currentUpdate = getMonthlyUpdates().get(i - 1);
                if (currentUpdate.getNewConsumers() != null) {
                    for (ConsumerInputData newConsumerInput : currentUpdate.getNewConsumers()) {
                        ConsumerData newConsumer = (ConsumerData) factory.getNewEntity("consumer");
                        newConsumer.setValues(newConsumerInput);
                        this.consumers.add(newConsumer);
                    }
                }
                if (currentUpdate.getCostsChanges() != null) {
                    for (DistributorUpdateInputData distributorUpdate
                                                                : currentUpdate.getCostsChanges()) {
                        DistributorData distributor = getDistributorById(distributorUpdate.getId());
                        distributor.setInfrastructureCost(distributorUpdate
                                                                        .getInfrastructureCost());
                        distributor.setProductionCost(distributorUpdate.getProductionCost());
                    }
                }
            }

            // Looks for the lowest price on the market
            DistributorData lowestPriceDistributor = this.getLowestPriceDistributor();

            for (DistributorData distributor : this.distributors) {
                distributor.getContracts().removeIf(e -> e.getRemainedContractMonths() == 0);
            }
            this.calculateNumberOfConsumers();

            // Monthly Income is paid
            this.payMonthlyIncome();

            // Signing contracts for consumers that do not have one already
            this.signContracts(lowestPriceDistributor);

            // Contracts are paid
            this.consumersPay();

            // Distribuitorii isi platesc taxele
            this.distributorsPay();

            // We'll erase all the contracts that have not been payed for more than 2 months
            for (DistributorData distributor : this.distributors) {
                distributor.getContracts().removeIf(e -> e.getRemainedContractMonths() == -1);
            }

            // Checking if all the Distributors are out of the game
            if (allDistributorsBankrupt()) {
                return;
            }

        }
    }

    /**
     * Checks if all Distributors in Database have isBankrupt set as true.
     * @return true if all Distributors are bankrupt, false otherwise
     */
    private boolean allDistributorsBankrupt() {
        for (DistributorData distributor : this.distributors) {
            if (!distributor.isBankrupt()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates number of consumers for each distributor based on the active contracts
     */
    public void calculateNumberOfConsumers() {
        for (DistributorData distributor : this.distributors) {
            distributor.setNumberOfConsumers(distributor.getContracts().size());
        }
    }

    /**
     * All of the distributors pays their taxes
     */
    public void distributorsPay() {
        for (DistributorData distributor : this.distributors) {
            int totalCost = distributor.getInfrastructureCost()
                    + distributor.getContracts().size() * distributor.getProductionCost();
            if (!distributor.isBankrupt()) {
                if (distributor.getBudget() >= totalCost) {
                    distributor.setBudget(distributor.getBudget() - totalCost);
                } else {
                    distributor.setBudget(distributor.getBudget() - totalCost);
                    distributor.setBankrupt(true);
                }
            }
        }
    }

    /**
     * All of the customers that are still in the game will pay their contracts and/or debts
     */
    public void consumersPay() throws Exception {
        for (DistributorData distributor : this.distributors) {
            for (ContractData contract : distributor.getContracts()) {
                ConsumerData consumer = this.getConsumerById(contract.getConsumerId());
                if (consumer.hasDebts()) {
                    if (consumer.getBudget() >= (int) Constants.STILL_CLIENT_DEBT_RATIO
                            * contract.getPrice()) {
                        consumer.setBudget((int) (consumer.getBudget()
                                - Constants.STILL_CLIENT_DEBT_RATIO * contract.getPrice()));
                        distributor.setBudget((int) (distributor.getBudget()
                                + Constants.STILL_CLIENT_DEBT_RATIO * contract.getPrice()));
                        contract.setRemainedContractMonths(0);
                        consumer.setHasContract(false);
                    } else {
                        consumer.setBankrupt(true);
                        contract.setRemainedContractMonths(-1);
                    }
                } else if (consumer.getBudget() >= contract.getPrice()) {
                    consumer.setBudget(consumer.getBudget() - contract.getPrice());
                    distributor.setBudget(distributor.getBudget() + contract.getPrice());
                    contract.setRemainedContractMonths(contract.getRemainedContractMonths() - 1);
                    if (contract.getRemainedContractMonths() == 0) {
                        consumer.setHasContract(false);
                    }
                } else {
                    if (contract.getRemainedContractMonths() > 0) {
                        consumer.setHasDebts(true);
                        contract.setRemainedContractMonths(contract.getRemainedContractMonths()
                                                                                            - 1);
                        if (contract.getRemainedContractMonths() == 0) {
                            contract.setRemainedContractMonths(Constants.CONTRACT_NOT_PAID_CODE);
                        }
                    } else if (contract.getRemainedContractMonths() == 0) {
                        consumer.setHasDebts(true);
                        contract.setRemainedContractMonths(Constants.CONTRACT_NOT_PAID_CODE);
                    }
                }
            }
        }
    }

    /**
     * Searches for a Consumer in the database by the id
     * @param id the id of the Consumer we are looking for
     * @return Consumer if found
     * @throws Exception if no Consumer is found
     */
    public ConsumerData getConsumerById(final int id) throws Exception {
        for (ConsumerData consumer : this.consumers) {
            if (consumer.getId() == id) {
                return consumer;
            }
        }
        throw new Exception("Consumer no. " + id + " could not be found");
    }

    /**
     * Finds a contract for all of the consumers that do not have one/that have debts
     * @param distributor Lowest Price Distributor
     */
    public void signContracts(final DistributorData distributor) {
        for (ConsumerData consumer : this.consumers) {
            if (!consumer.isBankrupt()) {
                if (!consumer.hasContract()) {
                    distributor.getContracts().add(
                            new ContractData(consumer.getId(), distributor.getContractPrice(),
                                                distributor.getContractLength()));
                    consumer.setHasContract(true);
                }
            }
        }
    }

    /**
     * Finds the Distributor that has the lowest contract price on the market
     * @return Lowest Price Distributor
     */
    public DistributorData getLowestPriceDistributor() {
        this.distributors.get(0).updateContractPrice();
        DistributorData lowestPriceDistributor = this.distributors.get(0);
        for (DistributorData currentDistributor : this.distributors) {
            if (!currentDistributor.isBankrupt()) {
                currentDistributor.updateContractPrice();
                if (currentDistributor.getContractPrice()
                                                    < lowestPriceDistributor.getContractPrice()) {
                    lowestPriceDistributor = currentDistributor;
                }
            }
        }
        return lowestPriceDistributor;
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
