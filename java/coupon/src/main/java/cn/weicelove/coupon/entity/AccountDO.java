package cn.weicelove.coupon.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("account")
public class AccountDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String accountName;

    private String accountPassword;

    private String encryptionType;

    private String loginUrl;


    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;
}
