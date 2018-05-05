package pers.vast.resource.design;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 单位接口
 * Created by sengzin on 2018/5/2.
 */
@RestController
@RequestMapping(value = "/unit")
public class UnitResource {

    /**
     * 创建单位
     */
    @RequestMapping(method = RequestMethod.POST)
    public void create() {
        // 创建时刻携带能力值，一并保存
    }

    /**
     * 更新单位
     */
    @RequestMapping(method = RequestMethod.PUT)
    public void update() {
        // 能力数值一次全更新，直接覆盖老数据
    }

    /**
     * 查单个单位
     */
    @RequestMapping(method = RequestMethod.GET)
    public void get() {

    }

    /**
     * 删除单位，先逻辑删除、再强删除
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete() {

    }

    /**
     * 单位列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list() {

    }

}
