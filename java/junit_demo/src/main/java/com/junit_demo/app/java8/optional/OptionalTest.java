package com.junit_demo.app.java8.optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/11/2 16:39
 */

public class OptionalTest {

    private static final Logger log = LoggerFactory.getLogger(OptionalTest.class);

    @DisplayName("optional使用测试")
    @Test
    public void optionalTest() {
        Optional<User> empty = Optional.empty();
        User user = new User();
        Car car1 = new Car();
        user.setCar(car1);
        Insurance insurance = new Insurance();
        car1.setInsurance(insurance);
        insurance.setName("东方");
        Optional<User> user1 = Optional.of(user);
        // user可为空
        Optional<User> user2 = Optional.ofNullable(user);
        Optional<User> user3 = Optional.ofNullable(null);

        Optional<Optional<Car>> car = user2.map(User::getCar);

        Assertions.assertEquals("东方", user2.flatMap(User::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("Unknown"));
        Assertions.assertEquals("Unknown", user3.flatMap(User::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("Unknown"));
        Assertions.assertEquals("Unknown", user3.flatMap(User::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElseGet(() -> "Unknown"));
        Assertions.assertThrows(NullPointerException.class, () -> user3.flatMap(User::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElseThrow(NullPointerException::new));
    }



    public static class Car {

        private Optional<Insurance> insurance;

        public Optional<Insurance> getInsurance() {
            return insurance;
        }

        public void setInsurance(Insurance insurance) {
            this.insurance = Optional.of(insurance);
        }
    }

    public static class Insurance {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

   public static class User {
        private Optional<Car> car;

        public Optional<Car> getCar() {
            return car;
        }

       public void setCar(Car car) {
           this.car = Optional.of(car);
       }
   }
}
