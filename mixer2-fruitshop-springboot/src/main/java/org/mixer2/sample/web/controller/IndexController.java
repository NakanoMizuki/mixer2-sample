package org.mixer2.sample.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mixer2.sample.dto.Category;
import org.mixer2.sample.dto.Item;
import org.mixer2.sample.service.CategoryService;
import org.mixer2.sample.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(IndexController.class);

    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected ItemService itemService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<Category> categoryList = categoryService.getCategoryList();
        List<Item> oneItemByOneCategory = itemService.getOneItemByOneCategory();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("oneItemByOneCategory", oneItemByOneCategory);
        return "index";
    }

    /**
     * sample for use normal jsp
     * @return
     */
    @RequestMapping(value = "/bar", method = RequestMethod.GET)
    public String bar() {
        return "foo";
    }

    
    /**
     * 強制的にøut of memoryを発生させる
     * @return
     */
    @RequestMapping(value = "/oom")
    @SuppressWarnings("rawtypes")
    public String forceOutOfMemory() {
        List<Map> list = new ArrayList<Map>();
        while (true) {
            Map map = new HashMap(1000);
            list.add(map);
        }
    }
}
