package com.arch.seckill.controller;

import com.arch.seckill.dto.SeckillDto;
import com.arch.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by chenxiaobian on 16/12/22.
 */
@Controller
@RequestMapping("/seckill")
public class SecKillController {

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<SeckillDto> seckills = seckillService.queryAll();
        model.addAttribute("seckills", seckills);
        return "list";
    }


}
