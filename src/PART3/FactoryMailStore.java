package PART3;

import PART1.MailStore;

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
