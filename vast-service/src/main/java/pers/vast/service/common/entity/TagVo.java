package pers.vast.service.common.entity;

import lombok.*;

/**
 * 标签
 * Created by sengzin on 2018/4/17.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TagVo {
    private Long id;
    // 标签名
    private String name;
    // 标签颜色
    private String color;
}
