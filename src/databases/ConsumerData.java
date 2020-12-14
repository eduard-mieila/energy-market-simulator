package databases;

import input.ConsumerInputData;

public final class ConsumerData implements EnergeticEntity {
    private int id;
    private int budget;
    private int monthlyIncome;
    private boolean isBankrupt;
    private boolean hasContract;
    private boolean hasDebts;

    /**
     * Sets all fields as the fields of the parameter. Acts as a converter from ConsumerInputData
     * to Consumer Data
     * @param newData data to be written in the fields
     */
    public void setValues(final ConsumerInputData newData) {
        this.id = newData.getId();
        this.budget = newData.getInitialBudget();
        this.monthlyIncome = newData.getMonthlyIncome();
        this.isBankrupt = false;
        this.hasContract = false;
        this.hasDebts = false;
    }

    /**
     * For field hasDebts
     * @return hasDebts value
     */
    public boolean hasDebts() {
        return hasDebts;
    }

    public void setHasDebts(final boolean hasDebts) {
        this.hasDebts = hasDebts;
    }

    /**
     * For field hasContract
     * @return hasContract value
     */
    public boolean hasContract() {
        return hasContract;
    }

    public void setHasContract(final boolean hasContract) {
        this.hasContract = hasContract;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
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
        return "ConsumerData{"
                + "id=" + id
                + ", budget=" + budget
                + ", monthlyIncome=" + monthlyIncome
                + ", isBankrupt=" + isBankrupt
                + '}';
    }
}
