package PART_3;

import PART_1.*;

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








