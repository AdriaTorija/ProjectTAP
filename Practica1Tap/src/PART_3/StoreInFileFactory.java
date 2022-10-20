package PART_3;
import PART_2.*;
import PART_1.*;

/**
 * StoreInFile implementation of Factory
 */
public class StoreInFileFactory implements FactoryMailStore {
    /**
     * It creates a StoreInFile type mail Store
     * @return a Wrapped of Part2 decorator StoreInFile mail Store
     */
    @Override
    public MailStore createProduct() {
        StoreInFile store = new StoreInFile("fitxer.txt");
        try {
            return new StoreDecorator(new StoreDecorator(store, new CipherStrategy()), new ReverseStrategy());
        }catch (Exception e){
            System.out.println("F at creating storeDecorator ");
            return null;
        }
    }
}
