package cn.weicelove.util.composite.model.vo;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/4 16:57
 */
public class TreeNodeLink {

    private Long nodeIdFrom;

    private Integer ruleLimitType;

    private String ruleLimitValue;

    private Long nodeIdTo;

    public Integer getRuleLimitType() {
        return ruleLimitType;
    }

    public void setRuleLimitType(Integer ruleLimitType) {
        this.ruleLimitType = ruleLimitType;
    }

    public String getRuleLimitValue() {
        return ruleLimitValue;
    }

    public void setRuleLimitValue(String ruleLimitValue) {
        this.ruleLimitValue = ruleLimitValue;
    }

    public Long getNodeIdTo() {
        return nodeIdTo;
    }

    public void setNodeIdTo(Long nodeIdTo) {
        this.nodeIdTo = nodeIdTo;
    }

    public void setNodeIdFrom(Long nodeIdFrom) {
        this.nodeIdFrom = nodeIdFrom;
    }

    public Long getNodeIdFrom() {
        return nodeIdFrom;
    }
}
