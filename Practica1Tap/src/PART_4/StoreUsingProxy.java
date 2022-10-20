
package PART_4;

import PART_1.MailStore;
import PART_1.MailSystem;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;


@Config(store = "PART_3.RedisMailStore", log = true)

/**
 * Mail Store using Proxy
 */
public class StoreUsingProxy extends MailSystem {

    /**
     * constructor that just calls GetReflectionClass()
     */
    public StoreUsingProxy() {
        super(getReflectionClass());
    }

    /**
        It calls RedissTORE.getInstance() or StoreInmemory/StoreInFile() constructors
        depending on the reflected annotation.

        It also calls Proxy or not when creating it depending on log value from config annotation.
     */
    public static MailStore getReflectionClass() {

        try {

            Class<StoreUsingProxy> obj = StoreUsingProxy.class;

            Annotation annotation= obj.getAnnotation(Config.class);
            Config con= (Config) annotation;
            Class classe = Class.forName(con.store());
            System.out.println(classe.toString());
            if (classe.toString().equals("class PART_3.RedisMailStore")){
               java.lang.reflect.Method a;
               a = classe.getMethod("getInstance");

               if (con.log()){
                   return (MailStore) Proxy.newInstance(a.invoke(classe));
               }else{
                   return (MailStore) a.invoke(classe);
               }

            }else{
                if (con.log()){
                    return (MailStore) Proxy.newInstance(classe.getConstructor().newInstance());
                }else{
                    return  (MailStore) classe.getConstructor().newInstance();
                }

            }


        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return null;
    }
}
