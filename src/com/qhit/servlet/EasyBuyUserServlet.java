package com.qhit.servlet;

import com.qhit.dao.EasyBuyUserDao;
import com.qhit.entity.EasyBuyUser;
import com.qhit.service.EasyBuyUserService;
import com.qhit.service.Impl.EasyBuyUserServiceImpl;
import com.qhit.until.MD5;
import com.qhit.until.Page;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ASUS on 2019/12/2.
 */
public class EasyBuyUserServlet extends HttpServlet {
    EasyBuyUserService us;
    //重写init方法
    @Override
    public void init() throws ServletException {
        super.init();
        //获取配置文件中的service对象
        us = (EasyBuyUserService)WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("ud");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("utf-8");
       String opr = req.getParameter("opr");
        if("login".equals(opr)){
           String user = req.getParameter("txtUserName");
           String pwd = req.getParameter("txtPwd");
           String jizhu = req.getParameter("jizhu");
           String password = MD5.getMd5(pwd);
            //调用登录的方法
            EasyBuyUser u = us.login(user,password);
            if (u!=null){
                req.getSession().setAttribute("user",user);
                if ("1".equals(jizhu)){
                    Cookie userCookie = new Cookie("user",user);
                    Cookie pwdCookie = new Cookie("pwd",pwd);
                    Cookie jizhuCookie = new Cookie("jizhu",jizhu);
                    resp.addCookie(userCookie);
                    resp.addCookie(pwdCookie);
                    resp.addCookie(jizhuCookie);
                }
                req.getRequestDispatcher("/admin/index.jsp").forward(req,resp);
            }else {
                req.getSession().setAttribute("error","用户名或者密码错误");
                resp.sendRedirect("/admin/login.jsp");
            }
        }else if("page".equals(opr)){
            String nowPage = req.getParameter("nowPage");
            String name = req.getParameter("name");
            int num = us.getCount(name);
            Page page = new Page();
            if (nowPage!=null){
                int index = Integer.parseInt(nowPage);
                page.setNowPage(index);
            }
            page.setCount(num);
            List<EasyBuyUser> list = us.getPage((page.getNowPage()-1)*page.getPageSize(),page.getPageSize(),name);
            req.setAttribute("list",list);
            req.setAttribute("page",page);
            req.setAttribute("name",name);
            req.getRequestDispatcher("/admin/admin/list.jsp").forward(req,resp);
        }else if ("del".equals(opr)){
            int id = Integer.parseInt(req.getParameter("id"));
            int num = us.del(id);
            String nowPage = req.getParameter("nowPage");
            String name = req.getParameter("name");
            resp.sendRedirect("EasyBuyUserServlet?opr=page&name="+name+"&nowPage="+nowPage);
        }else if ("add".equals(opr)){
            String loginName = req.getParameter("txtALoginName");
            String userName = req.getParameter("txtreAUsername");
            String password = req.getParameter("txtAPassWord");
            int sex = Integer.parseInt(req.getParameter("txtASex"));
            String identityCode = req.getParameter("identityCode");
            String email = req.getParameter("email");
            String mobile = req.getParameter("mobile");
            int type = Integer.parseInt(req.getParameter("type"));
            EasyBuyUser user = new EasyBuyUser();
            user.setLoginName(loginName);
            user.setUserName(userName);
            user.setPassword(password);
            user.setSex(sex);
            user.setIdentityCode(identityCode);
            user.setEmail(email);
            user.setMobile(mobile);
            user.setType(type);
            int num = us.add(user);
            resp.sendRedirect("EasyBuyUserServlet?opr=page");
        }else if ("getOne".equals(opr)){
            int id = Integer.parseInt(req.getParameter("id"));
            String nowPage = req.getParameter("nowPage");
            String name = req.getParameter("name");
            EasyBuyUser user = us.getOne(id);
            req.setAttribute("nowPage",nowPage);
            req.setAttribute("name",name);
            req.setAttribute("user",user);
            req.getRequestDispatcher("/admin/admin/update.jsp").forward(req,resp);
        }else if ("update".equals(opr)){
            int id = Integer.parseInt(req.getParameter("id"));
            String userName = req.getParameter("txtreAUsername");
            String nowPage = req.getParameter("nowPage");
            String name = req.getParameter("name");
            int sex =Integer.parseInt(req.getParameter("sex")) ;
            String identityCode = req.getParameter("identityCode");
            String email = req.getParameter("email");
            String mobile = req.getParameter("mobile");
            EasyBuyUser user = new EasyBuyUser();
            user.setId(id);
            user.setUserName(userName);
            user.setSex(sex);
            user.setIdentityCode(identityCode);
            user.setMobile(mobile);
            user.setEmail(email);
            int num = us.updateEasyBuyUser(user);
            resp.sendRedirect("EasyBuyUserServlet?opr=page&name="+name+"&nowPage="+nowPage);
        }else if ("changePassword".equals(opr)){
            String username = req.getParameter("txtALoginName");
            String password = req.getParameter("txtAPassWord");
            resp.sendRedirect("EasyBuyUserServlet?opr=page");
        }
    }
}
