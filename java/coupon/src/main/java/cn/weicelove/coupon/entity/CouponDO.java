package cn.weicelove.coupon.entity;


import cn.weicelove.coupon.constants.CouponEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ice_coupon")
public class CouponDO  extends BaseDO {


    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("group_id")
    private Long groupId;

    @TableField("coupon_name")
    private String couponName;

    @TableField("user_id")
    private Long userId;

    @TableField("used")
    private CouponEnum.UseFlagEnum used;

}
