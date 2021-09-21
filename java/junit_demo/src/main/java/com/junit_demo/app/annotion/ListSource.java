package com.junit_demo.app.annotion;

import com.junit_demo.app.provider.ListArgumentProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ArgumentsSource(ListArgumentProvider.class)
public @interface ListSource {

    String resource() ;

    Class<?> clazz();
}
