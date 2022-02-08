package cn.weicelove.coupon.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseDO implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Version
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
