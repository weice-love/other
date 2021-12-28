package cn.weicelove.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> @author     :  清风
 * <p> description :  基础DO类
 * <p> create date :  2021/12/28 20:46
 */
public class BaseDO implements Serializable {
    private static final long serialVersionUID = 2105518036548964907L;
    /**
     * 该注解需要保留，用于 mybatis 回显 ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;


    /**
     * 创建时间 插入自动填充
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;


    /**
     * 乐观锁
     */
    @Version
    private Integer version;
}
