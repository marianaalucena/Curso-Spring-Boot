package io.github.marianaalucena;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE}) //@Development so pode ser inserido numa classe
@Retention(RetentionPolicy.RUNTIME)
@Autowired
@Qualifier("Gato")
public @interface Gato {
}
