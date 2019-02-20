package pers.vast.resource.others;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.util.XMLConverUtil;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

/**
 * 微信推送消息接收接口
 * create by lichengzhen in 19-2-20
 */
@Slf4j
@RestController
@RequestMapping(value = "/wechat")
public class WechatController {
    private ObjectMapper om = new ObjectMapper();
    /**
     *
     * @return 返回的不是 success 或 "" 的话公众号上会提示用户 “公众号暂时无法提供服务”
     */
    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    public String get(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        log.info("{} GET 请求", uuid);
        Map<String, String[]> params = request.getParameterMap();
        log.info("{} params = {}", uuid, om.writerWithDefaultPrettyPrinter().writeValueAsString(params));
        return "success";
    }

    /**
     *
     * @return 返回的不是 success 或 "" 的话公众号上会提示用户 “公众号暂时无法提供服务”
     */
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    public String post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        log.info("{} POST 请求", uuid);
        Map<String, String[]> params = request.getParameterMap();
        log.info("{} params = {}", uuid, om.writerWithDefaultPrettyPrinter().writeValueAsString(params));

        ServletInputStream inputStream = request.getInputStream();
        if (inputStream != null) {
            EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, inputStream);
            log.info("{} body = {}", uuid, om.writerWithDefaultPrettyPrinter().writeValueAsString(eventMessage));
        } else {
            log.info("{} body = null");
        }
        return "success";
    }

}