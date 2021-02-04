package cn.weicelove.util.composite.service.logic;

import cn.weicelove.util.composite.model.vo.TreeNodeLink;

import java.util.List;
import java.util.Map;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/4 16:59
 */
public interface LogicFilter {

    /**
     * 逻辑决策器
     *
     * @param matterValue 决策值
     * @param treeNodeLineInfoList 决策节点
     * @return 下⼀个节点Id
     */
    Long filter(String matterValue, List<TreeNodeLink>
            treeNodeLineInfoList);
    /**
     * 获取决策值
     *
     * @param decisionMatter 决策物料
     * @return 决策值
     */
    String matterValue(Long treeId, String userId, Map<String, String>
            decisionMatter);
}
