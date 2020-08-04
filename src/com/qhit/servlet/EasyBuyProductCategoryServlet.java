package com.qhit.servlet;

import com.alibaba.fastjson.JSONArray;
import com.qhit.entity.EasyBuyProductCategory;
import com.qhit.service.EasyBuyNewsService;
import com.qhit.service.EasyBuyProductCategoryService;
import com.qhit.service.Impl.EasyBuyProductCategoryServiceImpl;
import com.qhit.until.Page;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by ASUS on 2019/12/10.
 */
public class EasyBuyProductCategoryServlet extends HttpServlet {
    EasyBuyProductCategoryService ps ;

    @Override
    public void init() throws ServletException {
        super.init();
        ps = (EasyBuyProductCategoryService) WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("pcd");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String opr = req.getParameter("opr");
        if ("ajax".equals(opr)){
            int type = Integer.parseInt(req.getParameter("type"));
            int parentId = Integer.parseInt(req.getParameter("parentId"));
            List<EasyBuyProductCategory> easyBuyProductCategoryList = ps.feilei(type,parentId);
            PrintWriter out = resp.getWriter();
            String jsons = JSONArray.toJSONString(easyBuyProductCategoryList);
            out.write(jsons);
            out.close();
        }
    }
}
