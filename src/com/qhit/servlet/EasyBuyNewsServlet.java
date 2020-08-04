package com.qhit.servlet;

import com.qhit.entity.EasyBuyNews;
import com.qhit.entity.EasyBuyProduct;
import com.qhit.entity.EasyBuyUser;
import com.qhit.service.EasyBuyNewsService;
import com.qhit.service.EasyBuyUserService;
import com.qhit.service.Impl.EasyBuyNewsServiceImpl;
import com.qhit.until.Page;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2019/12/9.
 */
public class EasyBuyNewsServlet extends HttpServlet {
    EasyBuyNewsService ns ;

    @Override
    public void init() throws ServletException {
        super.init();
        ns = (EasyBuyNewsService) WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("nd");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String opr = req.getParameter("opr");
        if("page".equals(opr)){
            String nowPage = req.getParameter("nowPage");
            String name = req.getParameter("name");
            int num = ns.getCount(name);
            Page page = new Page();
            if (nowPage!=null){
                int index = Integer.parseInt(nowPage);
                page.setNowPage(index);
            }
            page.setCount(num);
            List<EasyBuyNews> list = ns.getPage((page.getNowPage()-1)*page.getPageSize(),page.getPageSize(),name);
            req.setAttribute("list",list);
            req.setAttribute("page",page);
            req.setAttribute("name",name);
            req.getRequestDispatcher("/admin/news/list.jsp").forward(req,resp);
        }else if ("add".equals(opr)){
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            Date date = new Date();
            EasyBuyNews news = new EasyBuyNews();
            news.setTitle(title);
            news.setContent(content);
            news.setCreateTime(date);
            int num = ns.add(news);
            resp.sendRedirect("EasyBuyNewsServlet?opr=page");
        }else if ("del".equals(opr)){
            int id = Integer.parseInt(req.getParameter("id"));
            int num = ns.del(id);
            String nowPage = req.getParameter("nowPage");
            String name = req.getParameter("name");
            resp.sendRedirect("EasyBuyNewsServlet?opr=page&name="+name+"&nowPage="+nowPage);
        }else if ("getOne".equals(opr)){
            int id = Integer.parseInt(req.getParameter("id"));
            String nowPage = req.getParameter("nowPage");
            String name = req.getParameter("name");
            EasyBuyNews news = ns.getOne(id);
            req.setAttribute("nowPage",nowPage);
            req.setAttribute("name",name);
            req.setAttribute("news",news);
            req.getRequestDispatcher("/admin/news/update.jsp").forward(req,resp);
        }else if ("update".equals(opr)){
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String nowPage = req.getParameter("nowPage");
            String name = req.getParameter("name");
            String content = req.getParameter("content");
            Date date = new Date();
            EasyBuyNews news = new EasyBuyNews();
            news.setId(id);
            news.setTitle(title);
            news.setContent(content);
            news.setCreateTime(date);
            int num = ns.updateEasyBuyNews(news);
            resp.sendRedirect("EasyBuyNewsServlet?opr=page&name="+name+"&nowPage="+nowPage);
        }
    }
}
