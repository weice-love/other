package com.junit_demo.app.provider;

import com.alibaba.fastjson.JSON;
import com.junit_demo.app.annotion.FileSource;
import com.junit_demo.app.model.Student;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/9/14 15:05
 */
public class JsonFileArgumentProvider implements ArgumentsProvider , AnnotationConsumer<FileSource> {

    private FileSource annotation;

    private InputStream inputStream;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        String content = null;
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream))) {
            content = bf.readLine();
        } catch (Exception e) {
            System.out.println("读入文件出错");
        }
        System.out.println("读入的文件内容: " + content);
        if (content != null && content.trim().length() >0) {
            Student student = JSON.parseObject(content, Student.class);
            return Stream.of(() -> new Object[] {student});
        }
        return Stream.of(() -> new Object[] {new Student("default")});
    }

    @Override
    public void accept(FileSource fileSource) {
        this.annotation = fileSource;
        String resource = fileSource.resource();
        // todo 暂时这么处理
        this.inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
    }
}
