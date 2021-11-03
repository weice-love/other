package com.junit_demo.app.annotion;

import com.junit_demo.app.provider.ExcelArgumentProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ArgumentsSource(ExcelArgumentProvider.class)
public @interface ExcelSource {

    String resource() ;

    Class<?> clazz();
}
