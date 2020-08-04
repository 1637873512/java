package com.qhit.servlet;

import com.qhit.dao.EasyBuyProductDao;
import com.qhit.entity.EasyBuyProduct;
import com.qhit.entity.EasyBuyProductCategory;
import com.qhit.entity.EasyBuyUser;
import com.qhit.service.EasyBuyProductService;
import com.qhit.until.Page;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2020/1/2.
 */
@Controller
@RequestMapping("EasyBuyProduct")
public class EasyBuyProductController {
    @Resource
    private EasyBuyProductService ps;

    //分页
    @RequestMapping(value = "/page.do",method = RequestMethod.GET)
    public String page(Model model, String nowPage, String name){
        int num = ps.getCount(name);
        Page page = new Page();
        if (nowPage!=null){
            int index = Integer.parseInt(nowPage);
            page.setNowPage(index);
        }
        page.setCount(num);
        List<EasyBuyProduct> list = ps.getPage((page.getNowPage()-1)*page.getPageSize(),page.getPageSize(),name);
        model.addAttribute("list",list);
        model.addAttribute("page",page);
        model.addAttribute("name",name);
        return "product/list";
    }

    //删除
    @RequestMapping(value = "/del.do",method = RequestMethod.GET)
    public String del(int id,String nowPage,String name){
        int num = ps.del(id);
        return "redirect:/EasyBuyProduct/page.do?nowPage="+nowPage+"&name="+name;
    }

    //单查
    @RequestMapping(value = "/getOne.do",method = RequestMethod.GET)
    public String getOne(Model model,String nowPage,String name,int id){
        EasyBuyProduct product = ps.getOne(id);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("name",name);
        model.addAttribute("product",product);
        return "product/update";
    }



    //修改
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    public String update(String nowPage,String name,@Validated EasyBuyProduct pro,BindingResult result, @RequestParam("tp")MultipartFile[] files, HttpServletRequest req) throws IOException {
        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            String path = req.getSession().getServletContext().getRealPath("/admin/upload");
            String  fileName=multipartFile.getOriginalFilename();
            pro.setFileName(fileName);
            File f = new File(path+"\\"+fileName);
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), f);
        }
        int num=ps.updateEasyBuyProduct(pro);
        return "redirect:/EasyBuyProduct/page.do?nowPage="+nowPage+"&name="+name;
    }
    //添加
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    public String add(@Validated EasyBuyProduct pro,BindingResult result, @RequestParam("tp")MultipartFile[] files, HttpServletRequest req) throws IOException {
        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            String path = req.getSession().getServletContext().getRealPath("/admin/upload");
            String  fileName=multipartFile.getOriginalFilename();
            pro.setFileName(fileName);
            File f = new File(path+"\\"+fileName);
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), f);
        }
          int num=ps.addEasyBuyProduct(pro);
            return "redirect:/EasyBuyProduct/page.do";
    }
}
