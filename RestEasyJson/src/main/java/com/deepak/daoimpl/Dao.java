package com.deepak.daoimpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Repository;

/**
 * This annotation represents data access object component. By annotating any
 * class with this, indicates that an annotated class is a "component". Such
 * classes are considered as candidates for auto-detection when using
 * annotation-based configuration and class path scanning. Other class-level
 * annotations may be considered as identifying a component as well.
 * 
 * @author Auto Generated By HeadStart
 * @version 1.0
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repository
@Inherited
public @interface Dao {

}
