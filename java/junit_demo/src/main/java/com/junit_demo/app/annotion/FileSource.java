package com.junit_demo.app.annotion;

import com.junit_demo.app.provider.JsonFileArgumentProvider;
import org.apiguardian.api.API;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ArgumentsSource(JsonFileArgumentProvider.class)
public @interface FileSource {

    String resource() ;

    String[] files() default {};

    String encoding() default "UTF-8";

    String lineSeparator() default "\n";

    char delimiter() default '\u0000';

    String delimiterString() default "";

    int numLinesToSkip() default 0;

    String emptyValue() default "";

    String[] nullValues() default {};

    @API(
            status = API.Status.EXPERIMENTAL,
            since = "5.7"
    )
    int maxCharsPerColumn() default 4096;
}
