package PART_3;

import PART_1.MailStore;

/**
 * Redis implementation of Factory
 */
public class RedisFactory implements FactoryMailStore{
    /**
     * createProduct() returns an instance of RedisMailStore()
     * @return
     */
    @Override
    public MailStore createProduct() {
        return RedisMailStore.getInstance();
    }
}
