package cn.weicelove.util.composite.service.engine;

import cn.weicelove.util.composite.model.aggregates.TreeRich;
import cn.weicelove.util.composite.model.vo.EngineResult;

import java.util.Map;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/4 16:58
 */
public interface IEngine {

    EngineResult process(final Long treeId, final String userId, TreeRich
            treeRich, final Map<String, String> decisionMatter);
}
