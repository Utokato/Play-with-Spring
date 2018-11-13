package cn.llman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author
 * @date 2018/11/12
 */
@Controller
public class HelloController {

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index.html");
    }

}
