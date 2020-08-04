package com.qhit.servlet;

import com.qhit.entity.EasyBuyNews;
import com.qhit.entity.EasyBuyUser;
import com.qhit.service.EasyBuyNewsService;
import com.qhit.service.EasyBuyUserService;
import com.qhit.until.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ASUS on 2020/1/1.
 */
@Controller
@RequestMapping("EasyBuyNews")
public class EasyBuyNewsController {

    @Resource
    private EasyBuyNewsService ns;

    //分页
    @RequestMapping(value = "/page.do",method = RequestMethod.GET)
    public String page(Model model, String nowPage, String name){
        int num = ns.getCount(name);
        Page page = new Page();
        if (nowPage!=null){
            int index = Integer.parseInt(nowPage);
            page.setNowPage(index);
        }
        page.setCount(num);
        List<EasyBuyNews> list = ns.getPage((page.getNowPage()-1)*page.getPageSize(),page.getPageSize(),name);
        model.addAttribute("list",list);
        model.addAttribute("page",page);
        model.addAttribute("name",name);
        return "news/list";
    }

    //添加
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    public String add(EasyBuyNews news){
        int num = ns.add(news);
        return "redirect:/EasyBuyNews/page.do";
    }

    //删除
    @RequestMapping(value = "/del.do",method = RequestMethod.GET)
    public String del(int id,String nowPage,String name){
        int num = ns.del(id);
        return "redirect:/EasyBuyNews/page.do?nowPage="+nowPage+"&name="+name;
    }

    //单查
    @RequestMapping(value = "/getOne.do",method = RequestMethod.GET)
    public String getOne(Model model,String nowPage,String name,int id){
        EasyBuyNews news = ns.getOne(id);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("name",name);
        model.addAttribute("news",news);
        return "news/update";
    }

    //修改
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    public String update(EasyBuyNews news,String nowPage,String name){
        int num = ns.updateEasyBuyNews(news);
        return "redirect:/EasyBuyNews/page.do?nowPage="+nowPage+"&name="+name;
    }
}
