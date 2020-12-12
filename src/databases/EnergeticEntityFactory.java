package databases;

public final class EnergeticEntityFactory {
    private static EnergeticEntityFactory factory = null;

    private EnergeticEntityFactory() { }

    /**
     * THis class is a Singleton one, so it needs a getInstance method.
     * @return Singleton Instance of EnergeticEntityFactory
     */
    public static EnergeticEntityFactory getInstance() {
        if (factory == null) {
            factory = new EnergeticEntityFactory();
        }
        return factory;
    }

    /**
     * Returns a new Consumer oa Distributor
     * @param entityType "consumer"/"distributor"
     * @return a new entity as requested
     * @throws Exception
     */
    public static EnergeticEntity getNewEntity(final String entityType) throws Exception {
        if (factory == null) {
            factory = new EnergeticEntityFactory();
        }
        switch (entityType) {
            case "consumer":
                return new ConsumerData();

            case "distributor" :
                return new DistributorData();

            default:
                throw new Exception("Unknown entity type arrived at the factory");
        }
    }
}
