package pers.vast.service.project.entity;

import lombok.*;

import java.util.Date;

/**
 * Created by sengzin on 2017/9/17.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectVo {

    private Long id;

    private String name;

    private String introduction;

    private String announcement;

    private String coverUrl;

    private Long createPerson;

    private String createPersonName;

    private Date createTime;

    private Date updateTime;

    // private Byte status;

}
