package com.junit_demo.app.provider;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.SyncReadListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.junit_demo.app.annotion.ExcelSource;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/9/14 15:05
 */
public class ExcelArgumentProvider implements ArgumentsProvider , AnnotationConsumer<ExcelSource> {

    private ExcelSource annotation;

    private InputStream inputStream;

    private Class<?> clazz;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        ExcelReaderBuilder read = EasyExcel.read(inputStream, annotation.getClass(), new SyncReadListener());
        List<Object> objects = EasyExcel.read(inputStream).head(annotation.clazz()).sheet().doReadSync();
        return Stream.of(() -> new Object[] {objects});
    }


    @Override
    public void accept(ExcelSource excelSource) {
        this.annotation = excelSource;
        String resource = excelSource.resource();
        this.clazz = excelSource.clazz();
//        this.inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        try {
            // todo 区分绝对路径还是相对路径
            this.inputStream = new FileInputStream(resource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
