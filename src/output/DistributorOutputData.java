package output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import databases.ContractData;
import databases.DistributorData;

import java.util.ArrayList;

@JsonPropertyOrder({"id", "budget", "isBankrupt", "contracts"})
public final class DistributorOutputData {
    private int id;
    private int budget;
    private final boolean isBankrupt;
    private ArrayList<ContractData> contracts;

    public DistributorOutputData(final DistributorData data) {
        this.id = data.getId();
        this.budget = data.getBudget();
        this.isBankrupt = data.isBankrupt();
        this.contracts = data.getContracts();
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

    @JsonProperty("isBankrupt")
    public boolean isBankrupt() {
        return isBankrupt;
    }

    public ArrayList<ContractData> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<ContractData> contracts) {
        this.contracts = contracts;
    }
}
