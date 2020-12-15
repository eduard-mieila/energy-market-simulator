package databases;

public final class EnergeticEntityFactory {
    private static EnergeticEntityFactory factory = null;

    private EnergeticEntityFactory() { }

    /**
     * This class is a Singleton one, so it needs a getInstance method.
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
     * @throws Exception If entityType is not recognized, throw exception
     */
    public static EnergeticEntity getNewEntity(final String entityType) throws Exception {
        if (factory == null) {
            factory = new EnergeticEntityFactory();
        }

        return switch (entityType) {
            case "consumer" -> new ConsumerData();
            case "distributor" -> new DistributorData();
            default -> throw new Exception("Unknown entity type arrived at the factory");
        };
    }
}
