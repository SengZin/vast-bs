package pers.vast.service.common.manager;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.vast.service.common.dao.TagMapper;
import pers.vast.service.common.entity.OwnerType;
import pers.vast.service.common.entity.TagPo;
import pers.vast.service.common.entity.TagRelPo;
import pers.vast.service.common.entity.TagScope;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by sengzin on 2018/4/22.
 */
@Service
public class TagManager {

    @Autowired
    private TagMapper mapper;

    /**
     * 新建标签
     */
    public void create(OwnerType ownerType, String ownerId, TagScope scope, Long operator, Collection<TagPo> tagPos) {
        Preconditions.checkNotNull(ownerType,"ownerType must not null");
        Preconditions.checkNotNull(ownerId,"ownerId must not null");
        Preconditions.checkNotNull(scope,"scope must not null");
        Preconditions.checkNotNull(tagPos,"tagPos must not null");
        Date createTime = new Date();
        tagPos.forEach(tagPo -> {
            // 添加标签定义
            String name = tagPo.getName();
            TagPo tag = mapper.select(ImmutableMap.of("ownerType", ownerType.getId(), "ownerId", ownerId, "scope", scope.getId(), "name", name));
            Preconditions.checkState(tag == null || tag.getEnable() == 0,"标签“%s”已经存在，新建失败", name);
            if (tag == null) {
                // 没有相同的标签
                tagPo.setOwnerType(ownerType.getId());
                tagPo.setOwnerId(ownerId);
                tagPo.setScope(scope.getId());
                tagPo.setCreatePerson(operator);
                tagPo.setCreateTime(createTime);
                mapper.insert(tagPo);
            } else {
                // 有失效的同名标签，恢复状态
                mapper.updateEnable(tag.getId(), "1");
            }
        });
    }

    /**
     * 更新标签
     */
    public void update(OwnerType ownerType, String ownerId, TagScope scope, Long operator, Collection<TagPo> tagPos) {
        Preconditions.checkNotNull(ownerType,"ownerType must not null");
        Preconditions.checkNotNull(ownerId,"ownerId must not null");
        Preconditions.checkNotNull(scope,"scope must not null");
        Preconditions.checkNotNull(tagPos,"tagPos must not null");
        tagPos.forEach(updatePo -> {
            String name = updatePo.getName();
            Long id = updatePo.getId();
            TagPo po = mapper.select(ImmutableMap.of("id", id));
            Preconditions.checkNotNull(po,"未查到对应标签：%s（%s）", name, id);
            Preconditions.checkState(Objects.equals(ownerType.getId(), po.getOwnerType()), "标签“%s（%s）”ownerType不符合，不可修改", name, id);
            Preconditions.checkState(Objects.equals(ownerId, po.getOwnerId()), "标签“%s（%s）”ownerId不符合，不可修改", name, id);
            Preconditions.checkState(Objects.equals(scope.getId(), po.getScope()), "标签“%s（%s）”scope不符合，不可修改", name, id);
            Preconditions.checkState(po.getEnable() == 1, "标签“%s（%s）”状态无效，不可修改", name, id);
            mapper.update(id, updatePo.getName(), updatePo.getColor(), operator);
        });
    }

    /**
     * 标签列表
     */
    public List<TagPo> list(OwnerType ownerType, String ownerId, TagScope scope) {
        return mapper.list(ImmutableMap.of("ownerType", ownerType.getId(), "ownerId", ownerId, "scope", scope.getId(), "enable", "1"));
    }

    /**
     * 删除标签
     */
    public void delete(OwnerType ownerType, String ownerId, TagScope scope, Long operator, Collection<Long> ids) {
        ids.forEach(id -> {
            TagPo po = mapper.select(ImmutableMap.of("id", id));
            Preconditions.checkNotNull(po,"未查到对应标签：（%s）", id);
            Preconditions.checkState(Objects.equals(ownerType.getId(), po.getOwnerType()), "标签“%s（%s）”ownerType不符合，不可修改", po.getName(), id);
            Preconditions.checkState(Objects.equals(ownerId, po.getOwnerId()), "标签“%s（%s）”ownerId不符合，不可修改", po.getName(), id);
            Preconditions.checkState(Objects.equals(scope.getId(), po.getScope()), "标签“%s（%s）”scope不符合，不可修改", po.getId(), id);
            mapper.updateEnable(id, "0");
            mapper.updateRelEnableByTagId(id, "0");
        });
    }

    /**
     * 重打标签
     * 只绑定本次传入的标签，未传入则表示解绑
     */
    public void retag(OwnerType ownerType, String ownerId, TagScope scope, Long operator, Collection<Long> tagIds, String bizId) {
        Preconditions.checkNotNull(ownerType,"ownerType must not null");
        Preconditions.checkNotNull(ownerId,"ownerId must not null");
        Preconditions.checkNotNull(scope,"scope must not null");
        Preconditions.checkNotNull(tagIds,"tagPos must not null");
        // 先把该标签绑定的标签全删掉
        mapper.updateRelEnableByBizId(bizId, "0");
        // 重新绑定标签
        Date createTime = new Date();
        tagIds.forEach(tagId -> {
            TagPo po = mapper.select(ImmutableMap.of("id", tagId));
            Preconditions.checkNotNull(po,"未查到对应标签：（%s）", tagId);
            String name = po.getName();
            Preconditions.checkState(Objects.equals(ownerType.getId(), po.getOwnerType()), "标签“%s（%s）”ownerType不符合，不可使用", name, tagId);
            Preconditions.checkState(Objects.equals(ownerId, po.getOwnerId()), "标签“%s（%s）”ownerId不符合，不可使用", name, tagId);
            Preconditions.checkState(Objects.equals(scope.getId(), po.getScope()), "标签“%s（%s）”scope不符合，不可使用", name, tagId);
            Preconditions.checkState(po.getEnable() == 1, "标签“%s（%s）”状态无效，不可使用", name, tagId);
            TagRelPo relPo = mapper.selectRel(ImmutableMap.of("bizId", bizId, "tagId", tagId));
            if (relPo == null) {
                // 新建关系
                relPo = TagRelPo.builder()
                        .bizId(bizId)
                        .tagId(tagId)
                        .createPerson(operator)
                        .createTime(createTime).build();
                mapper.insertRel(relPo);
            } else {
                // 已经有关系，更新状态为有效
                mapper.updateRelEnableById(relPo.getId(), "1");
            }
        });
    }

    /**
     * 获取标签列表
     */
    public List<TagPo> list(OwnerType ownerType, String ownerId, TagScope scope, String bizId) {
        return mapper.listWithJoin(ImmutableMap.of("ownerType", ownerType.getId(), "ownerId", ownerId, "scope", scope.getId(), "enable", "1", "bizId", bizId));
    }

    /**
     * 解绑标签
     */
    public void unbind(String bizId, Long operator, Collection<Long> tagIds) {
        tagIds.forEach(tagId -> mapper.updateRelEnable(bizId, tagId, 0, operator));

    }

}
