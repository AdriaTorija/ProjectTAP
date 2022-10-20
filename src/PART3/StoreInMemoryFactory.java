package PART3;

import PART1.*;

/**
 * StoreInMemory implementation of Factory
 */
public class StoreInMemoryFactory implements FactoryMailStore{
    /**
     *
     * @return it returns StoreInMemory mail Store implementation
     */
    @Override
    public MailStore createProduct() {
        return new StoreInMemory();
    }
}








