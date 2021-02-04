package cn.weicelove.util.composite.service.engine;

import cn.weicelove.util.composite.model.aggregates.TreeRich;
import cn.weicelove.util.composite.model.vo.EngineResult;
import cn.weicelove.util.composite.model.vo.TreeNode;
import cn.weicelove.util.composite.model.vo.TreeRoot;
import cn.weicelove.util.composite.service.logic.LogicFilter;

import java.util.Map;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/4 16:58
 */
public abstract class EngineBase extends EngineConfig implements IEngine{

    @Override
    public abstract EngineResult process(Long treeId, String userId,
                                         TreeRich treeRich, Map<String, String> decisionMatter);

    protected TreeNode engineDecisionMaker(TreeRich treeRich, Long treeId,
                                           String userId, Map<String, String> decisionMatter) {
        TreeRoot treeRoot = treeRich.getTreeRoot();
        Map<Long, TreeNode> treeNodeMap = treeRich.getTreeNodeMap();
        // 规则树根ID
        Long rootNodeId = treeRoot.getTreeRootNodeId();
        TreeNode treeNodeInfo = treeNodeMap.get(rootNodeId);
        //节点类型[NodeType]；1⼦叶、2果实
        while (treeNodeInfo.getNodeType().equals(1)) {
            String ruleKey = treeNodeInfo.getRuleKey();
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);
            String matterValue = logicFilter.matterValue(treeId, userId,
                    decisionMatter);
            Long nextNode = logicFilter.filter(matterValue,
                    treeNodeInfo.getTreeNodeLinkList());
            treeNodeInfo = treeNodeMap.get(nextNode);

            System.out.println(String.format("决策树引擎=>%s userId：%s treeId：%s treeNode：%s ruleKey：%s matterValue：%s", treeRoot.getTreeName(), userId, treeId,
            treeNodeInfo.getTreeNodeId(), ruleKey, matterValue));
        }
        return treeNodeInfo;
    }
}
