package cn.weicelove.coupon.constants;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public interface CouponEnum {


    enum UseFlagEnum implements IEnum<Integer> {
        UN_USED(0, "未使用"),
        USED(1, "已使用");


        @Getter
//        @JsonValue
        private final Integer code;

        @Getter
        private final String desc;

        UseFlagEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        @Override
        public Integer getValue() {
            return this.code;
        }


        @Override
        public String toString() {
            return this.code.toString();
        }
    }

}
