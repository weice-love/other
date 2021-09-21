package com.junit_demo.app.provider;

import com.alibaba.fastjson.JSON;
import com.junit_demo.app.annotion.ListSource;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/9/14 15:05
 */
public class ListArgumentProvider implements ArgumentsProvider , AnnotationConsumer<ListSource> {

    private ListSource annotation;

    private InputStream inputStream;

    private Class<?> clazz;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        String content = null;
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream))) {
            content = bf.readLine();
        } catch (Exception e) {
            System.out.println("读入文件("+this.annotation.resource()+")出错");
        }
        System.out.println("读入的文件内容: " + content);
        if (content != null && content.trim().length() >0) {
            List<?> result = JSON.parseArray(content, this.clazz);
            return Stream.of(() -> new Object[] {result});
        }
        return Stream.of(() -> new Object[] {});
    }

    @Override
    public void accept(ListSource listSource) {
        this.annotation = listSource;
        String resource = listSource.resource();
        this.clazz = listSource.clazz();
        this.inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
    }
}
