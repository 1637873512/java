package com.qhit.servlet;

import com.alibaba.fastjson.JSONArray;
import com.qhit.entity.EasyBuyProductCategory;
import com.qhit.service.EasyBuyProductCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by ASUS on 2020/1/2.
 */
@Controller
@RequestMapping("EasyBuyProductCategory")
public class EasyBuyProductCategoryController {
    @Resource
    private EasyBuyProductCategoryService ps;

    @RequestMapping(value = "/ajax.do",method = RequestMethod.POST)
    @ResponseBody
    public Boolean ajax(int type, int parentId, HttpServletResponse resp){
        resp.setCharacterEncoding("utf-8");
        List<EasyBuyProductCategory> easyBuyProductCategoryList = ps.feilei(type,parentId);
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsons = JSONArray.toJSONString(easyBuyProductCategoryList);
        out.write(jsons);
        out.close();
        return true;
    }
}
