package cn.llman.controller;

import cn.llman.beans.vo.ResultBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @date 2018/11/19
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResultBean<String> hello() {
        return new ResultBean<String>("Hello, world!");
    }

}
