package cn.weicelove.util.composite.model.vo;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/4 16:57
 */
public class EngineResult {

    private String userId;

    private Long treeId;

    private Long treeNodeId;

    private String nodeValue;

    public EngineResult(String userId, Long treeId, Long treeNodeId, String nodeValue) {
        this.nodeValue = nodeValue;
        this.treeNodeId = treeNodeId;
        this.treeId = treeId;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreeNodeId() {
        return treeNodeId;
    }

    public void setTreeNodeId(Long treeNodeId) {
        this.treeNodeId = treeNodeId;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    @Override
    public String toString() {
        return "EngineResult{" +
                "userId='" + userId + '\'' +
                ", treeId=" + treeId +
                ", treeNodeId=" + treeNodeId +
                ", nodeValue='" + nodeValue + '\'' +
                '}';
    }
}
