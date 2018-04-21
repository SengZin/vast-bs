package pers.vast.entity;

import lombok.*;

import java.util.Collection;

/**
 * Created by sengzin on 2017/9/17.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CollectionVo {

    private Collection rows;

    private int total;

}
