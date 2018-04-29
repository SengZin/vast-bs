package pers.vast.service.common.entity;

import lombok.*;

import java.util.Date;

/**
 * 标签关系
 * Created by sengzin on 2018/4/22.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TagRelPo {
    private Long id;
    // 业务 id
    private String bizId;

    private Long tagId;

    private Long createPerson;

    private Date createTime;

}
