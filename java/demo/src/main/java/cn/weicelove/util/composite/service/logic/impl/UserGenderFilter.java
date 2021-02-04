package cn.weicelove.util.composite.service.logic.impl;

import cn.weicelove.util.composite.service.logic.BaseFilter;

import java.util.Map;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/4 17:00
 */
public class UserGenderFilter extends BaseFilter {

    @Override
    public String matterValue(Long treeId, String userId, Map<String,
                String> decisionMatter) {
        return decisionMatter.get("gender");
    }
}
