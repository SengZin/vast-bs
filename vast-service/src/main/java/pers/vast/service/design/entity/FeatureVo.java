package pers.vast.service.design.entity;

import lombok.*;
import pers.vast.service.common.entity.TagVo;

import java.util.Collection;

/**
 * 单位特性
 * Created by sengzin on 2018/4/17.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FeatureVo {
    private Long id;
    // 特性名称
    private String name;
    // 特性标签
    private Collection<TagVo> tags;
}
