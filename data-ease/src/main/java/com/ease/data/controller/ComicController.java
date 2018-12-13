package com.ease.data.controller;


import com.ease.data.config.result.RestResult;
import com.ease.data.config.result.ResultGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;

/**
 * @author Wagic
 * @version 1.0 Wagic 2018-02-11 created
 */
@Controller
@Validated
@RequestMapping("/comic")
public class ComicController {

    private static final Logger logger = LogManager.getLogger(ComicController.class);



    @RequestMapping("/getComicListById")
    @ResponseBody
    public RestResult<Model> test(@NotNull(message = "漫画id不能为空") String comicId, Model model) {

        return ResultGenerator.createSucResult(model);
    }
}
