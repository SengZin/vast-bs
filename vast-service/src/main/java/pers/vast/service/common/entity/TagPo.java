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
    private Long id;
    // 标签名
    private String name;
    // 业务范围
    private Integer scope;

    private Long createPerson;

    private Date createTime;

}
