package pers.vast.service.common.entity;

import lombok.*;

import java.util.Date;

/**
 * 标签
 * Created by sengzin on 2018/4/22.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TagPo {
    /** 通用属性 */
    private Long id;
    private Integer enable;
    private Long createPerson;
    private Long updatePerson;
    private Date createTime;
    private Date updateTime;

    /** 标签属性 */
    // 标签名
    private String name;
    // 标签拥有者类型
    private Integer ownerType;
    // 拥有者 id
    private String ownerId;
    // 业务范围
    private Integer scope;
    // 标签颜色
    private String color;

    /** 关系 属性 */
    private TagRelPo rels;
}
