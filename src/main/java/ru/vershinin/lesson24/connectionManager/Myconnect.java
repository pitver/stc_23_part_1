package ru.vershinin.lesson24.connectionManager;


import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, PARAMETER, TYPE, METHOD})
@Qualifier
/**
 * Myconnect
 *
 * @author Вершинин Пётр
 */
public @interface Myconnect {
}
