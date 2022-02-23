package org.example.context;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/23 16:55
 */
public class Teacher {

    private String name;

    private String idCard;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
