<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
		<form id="form1" runat="server">
        <section class="content-header">
            <h1>&nbsp;</h1>
            <ol class="breadcrumb">
                <li><a href="right.html"><i class="fa fa-dashboard"></i>Home</a></li>
                <li><a href="#">分类管理</a></li>
                <li class="active">分类列表</li>
            </ol>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">分类列表</h3>
                        </div>
                        <div class="box-body table-responsive no-padding">
                        	<table>
                        		<tr>
                                	<td colspan="4">关键词:<input type="text"/><input type="button" value="搜索" /></td>
                                </tr>
                        	</table>
                            <table id="grid" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
                                
                                <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>用户名</th>
                                        <th>真实姓名</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<tr>
                                        <td>1</td>
                                        <td>admin</td>
                                        <td>admin</td>
                                        <td><button id='editrow' class='btn btn-success btn-sm' type='button'>编辑</button>&nbsp<button id='delrow' class='btn btn-danger btn-sm' type='button'>删除</button></td>
                                    </tr>
                                    <tr>
                                        <td>1</td>
                                        <td>admin</td>
                                        <td>admin</td>
                                        <td><button id='editrow' class='btn btn-success btn-sm' type='button'>编辑</button>&nbsp<button id='delrow' class='btn btn-danger btn-sm' type='button'>删除</button></td>
                                    </tr>
                                    <tr>
                                        <td>1</td>
                                        <td>admin</td>
                                        <td>admin</td>
                                        <td><button id='editrow' class='btn btn-success btn-sm' type='button'>编辑</button>&nbsp<button id='delrow' class='btn btn-danger btn-sm' type='button'>删除</button></td>
                                    </tr>
                                    <tr>
                                        <td>1</td>
                                        <td>admin</td>
                                        <td>admin</td>
                                        <td><button id='editrow' class='btn btn-success btn-sm' type='button'>编辑</button>&nbsp<button id='delrow' class='btn btn-danger btn-sm' type='button'>删除</button></td>
                                    </tr>
                                    <tr>
                                        <td>1</td>
                                        <td>admin</td>
                                        <td>admin</td>
                                        <td><button id='editrow' class='btn btn-success btn-sm' type='button'>编辑</button>&nbsp<button id='delrow' class='btn btn-danger btn-sm' type='button'>删除</button></td>
                                    </tr>
                                </tbody>
                                <tr>
                                	<td colspan="4" align="right">
                                		<ul class="pager">
                                			<li><a href="#">首页</a></li>
                                			<li><a href="#">上一页</a></li>
                                			<li><a href="#">下一页</a></li>
                                			<li><a href="#">尾页</a></li>
                                			<li><span>共1/10页</span></li>
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
