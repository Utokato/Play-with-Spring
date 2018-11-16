package cn.llman.controller;

import cn.llman.beans.vo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @date 2018/11/15
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResultBean<String> hello() {
        return new ResultBean<String>("hello,a!");
    }

}
