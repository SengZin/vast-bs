package pers.vast.service.common.manager;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.vast.service.common.dao.TagMapper;
import pers.vast.service.common.entity.TagPo;
import pers.vast.service.common.entity.TagRelPo;
import pers.vast.service.common.entity.TagScope;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by sengzin on 2018/4/22.
 */
@Service
public class TagManager {

    @Autowired
    private TagMapper mapper;

    /**
     * 打标签
     */
    public void tagging(TagScope tagScope, String bizId, Long operator, Collection<String> tagNames) {
        Preconditions.checkNotNull(tagScope,"tag scope must not null");
        Preconditions.checkNotNull(bizId,"biz id must not null");
        Preconditions.checkNotNull(tagNames,"tag names must not null");
        Date createTime = new Date();
        tagNames.forEach(name -> {
            TagPo tag = mapper.select(tagScope.getId(), name);
            if (tag == null) {
                // 没有标签就先创建
                tag = TagPo.builder()
                        .scope(tagScope.getId())
                        .name(name)
                        .createPerson(operator)
                        .createTime(createTime).build();
                mapper.insert(tag);
            }
            // 记录标签关系
            mapper.insertRel(TagRelPo.builder()
                    .bizId(bizId)
                    .tagId(tag.getId())
                    .createPerson(operator)
                    .createTime(createTime).build());
        });
    }

    /**
     * 标签名称列表
     */
    public List<String> listName(TagScope tagScope, String bizId) {
        return mapper.listName(bizId, tagScope.getId());
    }

    /**
     * 解绑标签
     */
    public void unbind(String bizId, Long operator, Collection<Long> tagIds) {
        tagIds.forEach(tagId -> mapper.updateRelEnable(bizId, tagId, 0, operator));

    }

}
