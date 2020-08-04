package com.qhit.servlet;

import com.qhit.entity.EasyBuyNews;
import com.qhit.entity.EasyBuyProduct;
import com.qhit.entity.EasyBuyProductCategory;
import com.qhit.entity.EasyBuyUser;
import com.qhit.service.EasyBuyNewsService;
import com.qhit.service.EasyBuyProductService;
import com.qhit.service.Impl.EasyBuyProductServiceImpl;
import com.qhit.until.Page;
import com.qhit.until.Tool;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by ASUS on 2019/12/9.
 */
public class EasyBuyProductServlet extends HttpServlet {
    EasyBuyProductService ps ;

    @Override
    public void init() throws ServletException {
        super.init();
        ps = (EasyBuyProductService) WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("pd");
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
            int num = ps.getCount(name);
            Page page = new Page();
            if (nowPage!=null){
                int index = Integer.parseInt(nowPage);
                page.setNowPage(index);
            }
            page.setCount(num);
            List<EasyBuyProduct> list = ps.getPage((page.getNowPage()-1)*page.getPageSize(),page.getPageSize(),name);
            req.setAttribute("list",list);
            req.setAttribute("page",page);
            req.setAttribute("name",name);
            req.getRequestDispatcher("/admin/product/list.jsp").forward(req,resp);
        }else if ("del".equals(opr)){
            int id = Integer.parseInt(req.getParameter("id"));
            int num = ps.del(id);
            String nowPage = req.getParameter("nowPage");
            String name = req.getParameter("name");
            resp.sendRedirect("EasyBuyProductServlet?opr=page&name="+name+"&nowPage="+nowPage);
        }else if ("getOne".equals(opr)){
            int id = Integer.parseInt(req.getParameter("id"));
            String nowPage = req.getParameter("nowPage");
            String name = req.getParameter("name");
            EasyBuyProduct product = ps.getOne(id);
            req.setAttribute("nowPage",nowPage);
            req.setAttribute("name",name);
            req.setAttribute("product",product);
            req.getRequestDispatcher("/admin/product/update.jsp").forward(req,resp);
        }else if ("update".equals(opr)){
            Map<String,String> map1 = new HashMap<String, String>();
            boolean bo1 = ServletFileUpload.isMultipartContent(req);
            if (bo1) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload sfu = new ServletFileUpload(factory);
                //处理文件名的中文乱码
                sfu.setHeaderEncoding("utf-8");
                try {
                    List<FileItem> items = sfu.parseRequest(req);
                    for (FileItem item:items) {
                        if (item.isFormField()){
                            String name = item.getFieldName();
                            String value = item.getString("utf-8");
                            map1.put(name,value);
                        }else {
                            String fileName = item.getName();
                            if (fileName != null&& fileName!=""){
                                String path = req.getServletContext().getRealPath("/admin/upload");
                                File file = new File(path+"/"+fileName);
                                if (!file.exists()){
                                    file.getParentFile().mkdirs();
                                    boolean bool = file.createNewFile();
                                }
                                item.write(file);
                                map1.put("fileName",fileName);
                            }
                        }
                    }
                } catch (FileUploadException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            EasyBuyProduct pro = new EasyBuyProduct();
            EasyBuyProductCategory pc1 = new EasyBuyProductCategory();
            EasyBuyProductCategory pc2 = new EasyBuyProductCategory();
            EasyBuyProductCategory pc3 = new EasyBuyProductCategory();
            pro.setName(map1.get("spname"));
            pro.setId(Integer.parseInt(map1.get("id")));
            pro.setDescription(map1.get("description"));
            pro.setPrice(Double.parseDouble(map1.get("price")));
            pro.setStock(Integer.parseInt(map1.get("stock")));
            pc1.setId(Integer.parseInt(map1.get("pc1")));
            pc2.setId(Integer.parseInt(map1.get("pc2")));
            pc3.setId(Integer.parseInt(map1.get("pc3")));
            pro.setPc1(pc1);
            pro.setPc2(pc2);
            pro.setPc3(pc3);
            pro.setFileName(map1.get("fileName"));
            String name = map1.get("name");
            int nowPage =Integer.parseInt(map1.get("nowPage"));
            int num = ps.updateEasyBuyProduct(pro);
            resp.sendRedirect("EasyBuyProductServlet?opr=page&name="+name+"&nowPage="+nowPage);
        }else if ("add".equals(opr)){
            Map<String,String> map = new HashMap<String, String>();
            boolean bo = ServletFileUpload.isMultipartContent(req);
            if (bo){
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload sfu = new ServletFileUpload(factory);
                //处理文件名的中文乱码
                sfu.setHeaderEncoding("utf-8");
                try {
                List<FileItem> items = sfu.parseRequest(req);
                for (FileItem item:items) {
                    if (item.isFormField()){
                        String name = item.getFieldName();
                        String value = item.getString("utf-8");
                        map.put(name,value);
                    }else {
                        String fileName = item.getName();
                        String path = req.getServletContext().getRealPath("/admin/upload");
                        File file = new File(path+"/"+fileName);

                        if (!file.exists()){
                            file.getParentFile().mkdirs();
                            boolean bool = file.createNewFile();
                        }
                        item.write(file);
                        map.put("fileName",fileName);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
            EasyBuyProduct pro = new EasyBuyProduct();
            EasyBuyProductCategory pc1 = new EasyBuyProductCategory();
            EasyBuyProductCategory pc2 = new EasyBuyProductCategory();
            EasyBuyProductCategory pc3 = new EasyBuyProductCategory();
             pro.setName(map.get("name"));
             pro.setDescription(map.get("description"));
             pro.setPrice(Double.parseDouble(map.get("price")));
             pro.setStock(Integer.parseInt(map.get("stock")));
             pc1.setId(Integer.parseInt(map.get("pc1")));
             pc2.setId(Integer.parseInt(map.get("pc2")));
             pc3.setId(Integer.parseInt(map.get("pc3")));
             pro.setPc1(pc1);
             pro.setPc2(pc2);
             pro.setPc3(pc3);
             pro.setFileName(map.get("fileName"));
             pro.setIsDelete(0);
            int num = ps.addEasyBuyProduct(pro);
            resp.sendRedirect("/EasyBuyProductServlet?opr=page");
        }
    }
}
