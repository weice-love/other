package org.example.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/25 11:28
 */
public class String2StudentConverter implements Converter<String, Student> {
    @Override
    public Student convert(String source) {
        Student student = new Student();
        student.setName(source);
        return student;
    }
}
