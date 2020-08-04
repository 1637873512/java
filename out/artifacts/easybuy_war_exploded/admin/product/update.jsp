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
	    <script type="text/javascript" src="../../js/jquery-2.2.3.min.js"></script>
	    <script type="text/javascript" src="../../js/layer/layer.js"></script>
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	    <script type="text/javascript">
	        function yanzheng() {
	            var username = $("#txtALoginName").val();
	            var userpwd = $("#txtAPassWord").val();
	            var repwd = $("#txtreAPassWord").val();
	            if (username.length <= 0) {
	                $("#txtALoginName").focus();
	                layer.msg("请输入用户名", { icon: 2, skin: 'layer-ext-moon' });
	                return false;
	            }
	            if (userpwd.length <= 0) {
	                $("#txtAPassWord").focus();
	                layer.msg("请输入密码", { icon: 2, skin: 'layer-ext-moon' });
	                return false;
	            }
	            if (userpwd.length < 6) {
	                $("#txtAPassWord").focus();
	                layer.msg("密码至少六位", { icon: 2, skin: 'layer-ext-moon' });
	                return false;
	            }
	            if (userpwd != repwd) {
	                $("#txtreAPassWord").focus();
	                layer.msg("两次密码不一直", { icon: 2, skin: 'layer-ext-moon' });
	                return false;
	            }
	            return true;
	        }
	    </script>
	</head>
	<body>
		<section class="content-header">
        <h1>&nbsp</h1>
        <ol class="breadcrumb">
            <li><a href="right.aspx"><i class="fa fa-dashboard"></i>Home</a></li>
            <li><a href="#">管理员管理</a></li>
            <li class="active">添加管理员</li>
        </ol>
	    </section>
	    <section class="content">
	        <div class="row">
	            <div class="col-md-12">
	                <div class="box box-info">
	                    <div class="box-header with-border">
	                        <h3 class="box-title">编辑商品</h3>
	                    </div>
	                    <form id="form1" class="form-horizontal" action="/EasyBuyProductServlet?opr=update" method="post" enctype="multipart/form-data">
	                        <div class="box-body">
	                            <div class="form-group">
									<input id="pc11" type="hidden" value="${product.getPc1().getId()}"/>
									<input id="pc22" type="hidden" value="${product.getPc2().getId()}"/>
									<input id="pc33" type="hidden" value="${product.getPc3().getId()}"/>
									<input name="id" type="hidden" value="${product.id}"/>
									<input name="nowPage" type="hidden" value="${nowPage}">
									<input name="name" type="hidden" value="${name}">
	                                <label for="txtALoginName" class="col-sm-2 control-label">商品名称：</label>
	                                <div class="col-sm-10">
	                                	<input type="text" id="txtALoginName" name="spname" class="form-control" value="${product.name}"/>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label for="txtAPassWord" class="col-sm-2 control-label">价格：</label>
	                                <div class="col-sm-10">
	                                	<input type="text" id="txtAPassWord" name="price" class="form-control" value="${product.price}"/>
	                                </div>
	                            </div>
								<div class="form-group">
									<label for="txtAPassWord" class="col-sm-2 control-label">库存：</label>
									<div class="col-sm-10">
										<input type="text" id="txtAPassWord9" name="stock" class="form-control" value="${product.stock}"/>
									</div>
								</div>
								<div class="form-group">
									<label for="txtAPassWord" class="col-sm-2 control-label">描述：</label>
									<div class="col-sm-10">
										<input type="text" id="txtAPassWord12" name="description" class="form-control" value="${product.description}"/>
									</div>
								</div>
								<div class="form-group">
									<label for="pc1" class="col-sm-2 control-label">一级分类：</label>
									<div class="col-sm-10">
										<select id="pc1" name="pc1" class="form-control">
											<option value="0">请选择一级分类</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="pc2" class="col-sm-2 control-label">二级分类：</label>
									<div class="col-sm-10">
										<select id="pc2" name="pc2" class="form-control">
											<option value="0">请选择二级分类</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="pc3" class="col-sm-2 control-label">三级分类：</label>
									<div class="col-sm-10">
										<select id="pc3" name="pc3" class="form-control">
											<option value="0">请选择三级分类</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="tp" class="col-sm-2 control-label">商品图片：</label>
									<div class="col-sm-10">
										<img src="/admin/upload/${product.fileName}" width="100px" height="100px">
										<input type="hidden" name="fileName" value="${product.fileName}"/>
										<input type="file" id="tp" name="fileName" class="form-control"/>
									</div>
								</div>
								<div class="form-group">
									<label for="txtAPassWord" class="col-sm-2 control-label">是否删除：</label>
									<div class="col-sm-10">
										<input type="text" id="txtAPassWord1" name="isDelete" class="form-control"
												<c:if test="${product.isDelete==0}">value="正常" </c:if>
											   <c:if test="${product.isDelete==1}">value="已删除" </c:if>
												readonly
										/>
									</div>
								</div>
	                        </div>
	                        <div class="box-footer">
	                            <div class="col-sm-offset-2 col-sm-8">
	                            	<input type="submit" id="btnok" value="保 存" class="btn btn-block btn-info btn-lg" onclick="yanzheng()" />
	                            </div>
	                            <div class="col-sm-2">&nbsp;</div>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </section>
	    <script src="../../js/bootstrap.min.js" type="text/javascript"></script>
	    <script src="../plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	    <script src="../plugins/fastclick/fastclick.js" type="text/javascript"></script>
	    <script src="../dist/js/app.min.js" type="text/javascript"></script>
	</body>
</html>
<script type="text/javascript">
    $(function () {
        pc1();
        $("#pc1").change(function () {
            pc2();
        })
        $("#pc2").change(function () {
            pc3();
        })
    })
    function pc1() {
        var pc11 = $("#pc11").val();
        $.ajax({
            url:"/EasyBuyProductCategoryServlet",
            type:"post",
            data:"opr=ajax&type=1&parentId=0",
            dataType:"json",
            success:function (msg) {
                $(msg).each(function () {
                    if (this.id == pc11){
                        $("#pc1").append("<option value='"+this.id+"' selected>"+this.name+"</option>");
                    }else {
                        $("#pc1").append("<option value='"+this.id+"'>"+this.name+"</option>");
                    }
                });
                pc2();
            },
            error:function () {
                alert("ajax请求失败");
            }
        });
    }
    function pc2() {
        var pc22 = $("#pc22").val()
        var pc1 =$("#pc1").val()
        $.ajax({
            url:"/EasyBuyProductCategoryServlet",
            type:"post",
            data:"opr=ajax&type=2&parentId="+pc1,
            dataType:"json",
            success:function (msg) {
                $("#pc2").html("<option value='0'>请选择二级分类</option>");
                $(msg).each(function () {
                    if(this.id == pc22){
                        $("#pc2").append("<option value='"+this.id+"' selected>"+this.name+"</option>");
                    }else {
                        $("#pc2").append("<option value='"+this.id+"'>"+this.name+"</option>");
                    }
                });
                pc3();
            },
            error:function () {
                alert("ajax请求失败");
            }
        });
    }
    function pc3() {
        var pc2 = $("#pc2").val();
        var pc33 = $("#pc33").val()
        $.ajax({
            url:"/EasyBuyProductCategoryServlet",
            type:"post",
            data:"opr=ajax&type=3&parentId="+pc2,
            dataType:"json",
            success:function (msg) {
                $("#pc3").html("<option value='0'>请选择三级分类</option>");
                $(msg).each(function () {
                    if(this.id==pc33){
                        $("#pc3").append("<option value='"+this.id+"' selected>"+this.name+"</option>");
                    }else {
                        $("#pc3").append("<option value='"+this.id+"'>"+this.name+"</option>");
                    }
                });
            },
            error:function () {
                alert("ajax请求失败");
            }
        });
    }
</script>