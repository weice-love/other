package cn.weicelove.util.composite.model.vo;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/4 16:57
 */
public class TreeRoot {

    private Long treeId;

    private Long treeRootNodeId;

    private String treeName;

    public Long getTreeRootNodeId() {
        return treeRootNodeId;
    }

    public void setTreeRootNodeId(Long treeRootNodeId) {
        this.treeRootNodeId = treeRootNodeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getTreeName() {
        return treeName;
    }
}
