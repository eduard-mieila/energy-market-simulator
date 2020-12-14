package output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import databases.ConsumerData;

import java.io.Serializable;

@JsonPropertyOrder({"id", "isBankrupt", "budget"})
public final class ConsumerOutputData implements Serializable {
    private int id;
    private boolean isBakrupt;
    private int budget;

    public ConsumerOutputData(final ConsumerData data) {
        this.id = data.getId();
        this.isBakrupt = data.isBankrupt();
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
    public boolean isBakrupt() {
        return isBakrupt;
    }

    public void setBakrupt(final boolean bakrupt) {
        isBakrupt = bakrupt;
    }

    @JsonPropertyOrder("2")
    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }
}
