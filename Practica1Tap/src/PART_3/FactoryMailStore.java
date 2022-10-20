package PART_3;

import PART_1.MailStore;

/**
 * Interface for Factory
 */
public interface FactoryMailStore {

    /**
     * It returns MailStore
     * @return
     */
    public MailStore createProduct();

}
