package com.qhit.servlet;

import com.qhit.entity.EasyBuyUser;

import com.qhit.service.EasyBuyUserService;
import com.qhit.until.MD5;

import com.qhit.until.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ASUS on 2019/12/31.
 */
@Controller
@RequestMapping("EasyBuyUser")
public class EasyBuyUserController{
    @Resource
    private EasyBuyUserService us;

    //登录
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String login(String txtUserName, String txtPwd, String jizhu, HttpServletResponse resp, HttpSession session){
        String pwd = MD5.getMd5(txtPwd);
        EasyBuyUser u = us.login(txtUserName,pwd);
        if (u!=null){
            session.setAttribute("user",txtUserName);
            if ("1".equals(jizhu)){
                Cookie userCookie = new Cookie("user",txtUserName);
                Cookie pwdCookie = new Cookie("pwd",pwd);
                Cookie jizhuCookie = new Cookie("jizhu",jizhu);
                resp.addCookie(userCookie);
                resp.addCookie(pwdCookie);
                resp.addCookie(jizhuCookie);
            }
            return "index";
        }else {
            session.setAttribute("error","用户名或者密码错误");
            return "login";
        }
    }
    //分页
    @RequestMapping(value = "/page.do",method = RequestMethod.GET)
    public String page(Model model,String nowPage,String name){
        int num = us.getCount(name);
        Page page = new Page();
        if (nowPage!=null){
            int index = Integer.parseInt(nowPage);
            page.setNowPage(index);
        }
        page.setCount(num);
        List<EasyBuyUser> list = us.getPage((page.getNowPage()-1)*page.getPageSize(),page.getPageSize(),name);
        model.addAttribute("list",list);
        model.addAttribute("page",page);
        model.addAttribute("name",name);
        return "admin/list";
    }
    //删除
    @RequestMapping(value = "/del.do",method = RequestMethod.GET)
    public String del(int id,String nowPage,String name){
        int num = us.del(id);
        return "redirect:/EasyBuyUser/page.do?nowPage="+nowPage+"&name="+name;
    }

    //添加
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    public String add(EasyBuyUser user){
        int num = us.add(user);
        return "redirect:/EasyBuyUser/page.do";
    }
    //单查
    @RequestMapping(value = "/getOne.do",method = RequestMethod.GET)
    public String getOne(Model model,String nowPage,String name,int id){
        EasyBuyUser user = us.getOne(id);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("name",name);
        model.addAttribute("user",user);
        return "admin/update";
    }

    //修改
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    public String update(EasyBuyUser user,String nowPage,String name){
       int num = us.updateEasyBuyUser(user);
        return "redirect:/EasyBuyUser/page.do?nowPage="+nowPage+"&name="+name;
    }


    //修改密码
    @RequestMapping(value = "/change.do",method = RequestMethod.POST)
    public String changePassword(String txtALoginName ,String txtAPassWord){
        int num = us.changePasswprd(txtALoginName,txtAPassWord);
        return "redirect:/EasyBuyUser/page.do";
    }
}
