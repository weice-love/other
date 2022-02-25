package org.example.converter;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/25 11:36
 */
public class Student {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
