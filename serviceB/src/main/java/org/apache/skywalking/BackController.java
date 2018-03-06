package org.apache.skywalking;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class BackController {
    @RequestMapping("/serviceB/rest")
    @ResponseBody
    String home() {
        return "{\"type\" : \"resultType\", \"value\" : \"123456\"}";
    }
}
