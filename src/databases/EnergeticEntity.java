package databases;

/**
 * Interface used to create a link between consumer/distributor entities.
 * Used in EnergeticEntityFactory.
 */
public interface EnergeticEntity {
    /**
     * @return Returns the ID of current entity
     */
    int getEntityId();
}
