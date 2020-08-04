<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <title></title>
	    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	    <!-- Tell the browser to be responsive to screen width -->
	    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport" />
	    <meta name="apple-mobile-web-app-capable" content="yes" />
	    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
	    <meta name="apple-touch-fullscreen" content="yes" />
	    <link rel="stylesheet" href="../../css/bootstrap.min.css" />
	    <!-- Font Awesome -->
	    <link rel="stylesheet" href="../../css/font-awesome.min.css" />
	    <!-- Ionicons -->
	    <link rel="stylesheet" href="../../css/ionicons.min.css" />
	    <!-- Theme style -->
	    <link rel="stylesheet" href="../dist/css/AdminLTE.min.css" />
	    <link rel="stylesheet" href="../dist/css/skins/_all-skins.min.css" />
	    <link rel="stylesheet" href="../../css/dataTables.bootstrap.min.css" />
	    <script type="text/javascript" src="../../js/jquery-2.2.3.min.js"></script>
	    <script type="text/javascript" src="../../js/layer/layer.js"></script>
	</head>
	<body>
		<form id="form1" runat="server" method="post" action="/EasyBuyProductServlet?opr=page">
        <section class="content-header">
            <h1>&nbsp;</h1>
            <ol class="breadcrumb">
                <li><a href="right.html"><i class="fa fa-dashboard"></i>Home</a></li>
                <li><a href="#">商品管理</a></li>
                <li class="active">商品列表</li>
            </ol>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">商品列表</h3>
                        </div>
                        <div class="box-body table-responsive no-padding">
                        	<table>
                        		<tr>
                                	<td colspan="4">关键词:<input type="text" name="name" value="${name}" /><input type="submit" /></td>
                                </tr>
                        	</table>
                            <table id="grid" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
                                
                                <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>商品名字</th>
                                        <th>价格</th>
                                        <th>一级分类名称</th>
                                        <th>二级分类名称</th>
                                        <th>三级分类名称</th>
                                        <th>商品图片</th>
                                        <th>是否删除</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${list}" var="product">
                                    <tr>
                                        <td>${product.id}</td>
                                        <td>${product.name}</td>
                                        <td>${product.price}</td>
                                        <td>${product.pc1.name}</td>
                                        <td>${product.pc2.name}</td>
                                        <td>${product.pc3.name}</td>
                                        <td><img src="/admin/upload/${product.fileName}" width="100px" height="100px"></td>
                                        <td>
                                            <c:if test="${product.isDelete ==0}">正常</c:if>
                                            <c:if test="${product.isDelete ==1}">已删除</c:if>
                                        </td>
                                        <td><button id='editrow' class='btn btn-success btn-sm' type='button' onclick="update(${product.id},${page.nowPage},'${name}')">编辑</button>&nbsp
                                            <button id='delrow' class='btn btn-danger btn-sm' type='button' onclick="del(${product.id},${page.nowPage},'${name}')">删除</button>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                                <tr>
                                	<td colspan="4" align="right">
                                		<ul class="pager">
                                            <li><a href="/EasyBuyProduct/page.do?nowPage=1&name=${name}">首页</a></li>
                                            <li><a
                                                    <c:if test="${page.nowPage>1}">href="/EasyBuyProduct/page.do?nowPage=${page.nowPage-1}&name=${name}"</c:if>
                                            >上一页</a></li>
                                            <li><a
                                                    <c:if test="${page.nowPage<page.pageCount}">href="/EasyBuyProduct/page.do?nowPage=${page.nowPage+1}&name=${name}"</c:if>
                                            >下一页</a></li>
                                            <li><a href="/EasyBuyProduct/page.do?nowPage=${page.pageCount}&name=${name}">尾页</a></li>
                                            <li><span>共${page.nowPage}/${page.pageCount}页</span></li>
                                		</ul>
                                	</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script type="text/javascript" src="../../js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <script type="text/javascript" src="../plugins/fastclick/fastclick.js"></script>
        <script type="text/javascript" src="../dist/js/app.min.js"></script>
    </form>
	</body>
</html>
<script type="text/javascript">
function del(id,nowPage,name) {
var bo = confirm("确认删除此商品吗？")
if (bo){
location.href="/EasyBuyProduct/del.do?id="+id+"&name="+name+"&nowPage="+nowPage;
}
}
function update(id,nowPage,name) {
var bo = confirm("确认修改此商品吗？")
if (bo){
location.href="/EasyBuyProduct/getOne.do?id="+id+"&name="+name+"&nowPage="+nowPage;
}
}

</script>