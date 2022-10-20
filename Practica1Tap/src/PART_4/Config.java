package PART_4;


import PART_1.MailBox;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

/**
 *  Config Annotation interface
 */
public @interface Config {

    boolean log() default false;
    String store() default "PART_3.RedisMailStore";

}

