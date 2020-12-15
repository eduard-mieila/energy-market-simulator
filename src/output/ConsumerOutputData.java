package output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import databases.ConsumerData;

import java.io.Serializable;

@JsonPropertyOrder({"id", "isBankrupt", "budget"})
public final class ConsumerOutputData implements Serializable {
    private int id;
    private final boolean isBankrupt;
    private int budget;

    public ConsumerOutputData(final ConsumerData data) {
        this.id = data.getId();
        this.isBankrupt = data.isBankrupt();
        this.budget = data.getBudget();
    }

    @JsonPropertyOrder("0")
    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    @JsonPropertyOrder("1")
    @JsonProperty("isBankrupt")
    public boolean isBankrupt() {
        return isBankrupt;
    }


    @JsonPropertyOrder("2")
    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }
}
