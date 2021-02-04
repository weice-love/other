package cn.weicelove.util.composite.service.engine.impl;

import cn.weicelove.util.composite.model.aggregates.TreeRich;
import cn.weicelove.util.composite.model.vo.EngineResult;
import cn.weicelove.util.composite.model.vo.TreeNode;
import cn.weicelove.util.composite.service.engine.EngineBase;

import java.util.Map;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/4 16:59
 */
public class TreeEngineHandle extends EngineBase {

    @Override
    public EngineResult process(Long treeId, String userId, TreeRich
            treeRich, Map<String, String> decisionMatter) {
        // 决策流程
        TreeNode treeNode = engineDecisionMaker(treeRich, treeId, userId,
                decisionMatter);
        // 决策结果
        return new EngineResult(userId, treeId, treeNode.getTreeNodeId(),
                treeNode.getNodeValue());
    }
}
