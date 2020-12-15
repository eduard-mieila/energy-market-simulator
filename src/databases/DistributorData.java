package databases;

import input.DistributorInputData;
import utils.Constants;

import java.util.ArrayList;

public final class DistributorData implements EnergeticEntity {
    private int id;
    private int contractLength;
    private int budget;
    private int infrastructureCost;
    private int productionCost;
    private int numberOfConsumers;
    private int contractPrice;
    private int profit;
    private boolean isBankrupt;
    private ArrayList<ContractData> contracts = new ArrayList<>();

    public ArrayList<ContractData> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<ContractData> contracts) {
        this.contracts = contracts;
    }

    public int getProfit() {
        return profit;
    }

    /**
     * Updates profit for a Distributor
     */
    public void updateProfit() {
        this.profit = (int) Math.round(Math.floor(Constants.PROFIT_RATIO * productionCost));
    }

    public void setNumberOfConsumers(final int numberOfConsumers) {
        this.numberOfConsumers = numberOfConsumers;
    }

    public int getContractPrice() {
        return contractPrice;
    }

    /**
     * Computes the price of this Distributor's contracts
     */
    public void updateContractPrice() {
        this.updateProfit();
        if (this.getContracts().size() == 0) {
            this.contractPrice = infrastructureCost + productionCost + profit;
        } else {
            this.updateProfit();
            this.contractPrice = (int) Math.round(Math.floor
                        (infrastructureCost / this.contracts.size()) + productionCost + profit);
        }
    }

    /**
     * Sets all fields as the fields of the parameter. Acts as a converter from DistributorInputData
     * to DistributorData
     * @param newData data to be written in the fields
     */
    public void setValues(final DistributorInputData newData) {
        this.id = newData.getId();
        this.contractLength = newData.getContractLength();
        this.budget = newData.getInitialBudget();
        this.infrastructureCost = newData.getInitialInfrastructureCost();
        this.productionCost = newData.getInitialProductionCost();
        this.isBankrupt = false;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public int getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(final int productionCost) {
        this.productionCost = productionCost;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    @Override
    public int getEntityId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DistributorData{"
                + "id=" + id
                + ", contractLength=" + contractLength
                + ", budget=" + budget
                + ", infrastructureCost=" + infrastructureCost
                + ", productionCost=" + productionCost
                + ", numberOfConsumers=" + numberOfConsumers
                + ", contractPrice=" + contractPrice
                + ", profit=" + profit
                + ", isBankrupt=" + isBankrupt
                + '}';
    }
}
