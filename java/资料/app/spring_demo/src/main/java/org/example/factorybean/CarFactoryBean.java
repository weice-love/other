package org.example.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/17 17:35
 */
public class CarFactoryBean implements FactoryBean<Car> {

    private String carInfo;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        String carInfo = this.getCarInfo();
        String[] split = carInfo.split(",");
        car.setBrand(split[0]);
        car.setMaxSpeed(Integer.parseInt(split[1]));
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }
}
