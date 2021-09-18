package com.junit_demo.app.java8.model;

public class Apple {
        private Boolean beRed;
        private Integer heavy;

        public Apple(Boolean beRed, Integer heavy) {
            this.beRed = beRed;
            this.heavy = heavy;
        }

        public Boolean getBeRed() {
            return beRed;
        }

        public void setBeRed(Boolean red) {
            beRed = red;
        }

        public Integer getHeavy() {
            return heavy;
        }

        public void setHeavy(Integer heavy) {
            this.heavy = heavy;
        }
    }