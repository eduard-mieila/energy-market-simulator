package databases;

import input.DistributorInputData;

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


    public int getProfit() {
        return profit;
    }

    public void updateProfit() {
        this.profit = (int) Math.round(Math.floor(0.2 * productionCost));
    }

    public int getNumberOfConsumers() {
        return numberOfConsumers;
    }

    public void setNumberOfConsumers(int numberOfConsumers) {
        this.numberOfConsumers = numberOfConsumers;
    }

    public int getContractPrice() {
        return contractPrice;
    }

    public void updateContractPrice() {
        if (numberOfConsumers == 0) {
            this.contractPrice = infrastructureCost + productionCost + profit;
        } else {
            this.updateProfit();
            this.contractPrice = (int) Math.round(Math.floor(infrastructureCost / numberOfConsumers)
                    + productionCost + profit);
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
                + ", isBankrupt=" + isBankrupt
                + '}';
    }
}
