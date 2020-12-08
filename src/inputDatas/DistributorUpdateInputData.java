package inputDatas;

public class DistributorUpdateInputData {
    private int id;
    private int infrastructureCost;
    private int productionCost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public int getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(int productionCost) {
        this.productionCost = productionCost;
    }

    @Override
    public String toString() {
        return "DistributorUpdate{" +
                "id=" + id +
                ", infrastructureCost=" + infrastructureCost +
                ", productionCost=" + productionCost +
                '}';
    }
}
