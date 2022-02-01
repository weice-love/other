package cn.weicelove.coupon.model;

import cn.weicelove.coupon.constants.CouponEnum;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CouponResp implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String couponName;

    private String groupName;

    private Integer groupId;

    private Long userId;

    private String userName;

    private CouponEnum.UseFlagEnum used;

    private LocalDateTime createTime;
}
