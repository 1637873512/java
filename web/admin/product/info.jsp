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
	    <script type="text/javascript" src="../../js/jquery-2.2.3.min.js"></script>
	    <script type="text/javascript" src="../../js/layer/layer.js"></script>
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
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
				$.ajax({
                    url:"/EasyBuyProductCategory/ajax.do",
                    type:"post",
                    data:"type=1&parentId=0",
                    dataType:"json",
                    success:function (msg) {
                        $(msg).each(function () {
							$("#pc1").append("<option value='"+this.id+"'>"+this.name+"</option>");
                        });
						pc2();
                    },
                    error:function () {
                        alert("ajax请求失败");
                    }
                });
            }
            function pc2() {
			    var pc1 =$("#pc1").val()
                $.ajax({
                    url:"/EasyBuyProductCategory/ajax.do",
                    type:"post",
                    data:"type=2&parentId="+pc1,
                    dataType:"json",
                    success:function (msg) {
                        $("#pc2").html("<option value='0'>请选择二级分类</option>");
                        $(msg).each(function () {
							$("#pc2").append("<option value='"+this.id+"' selected>"+this.name+"</option>");
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
                $.ajax({
                    url:"/EasyBuyProductCategory/ajax.do",
                    type:"post",
                    data:"type=3&parentId="+pc2,
                    dataType:"json",
                    success:function (msg) {
                        $("#pc3").html("<option value='0'>请选择三级分类</option>");
                        $(msg).each(function () {
							$("#pc3").append("<option value='"+this.id+"'>"+this.name+"</option>");  w
                        });
                    },
                    error:function () {
                        alert("ajax请求失败");
                    }
                });
            }
	    </script>
	</head>
	<body>
		<section class="content-header">
        <h1>&nbsp</h1>
        <ol class="breadcrumb">
            <li><a href="right.aspx"><i class="fa fa-dashboard"></i>Home</a></li>
            <li><a href="#">商品管理</a></li>
            <li class="active">添加商品</li>
        </ol>
	    </section>
	    <section class="content">
	        <div class="row">
	            <div class="col-md-12">
	                <div class="box box-info">
	                    <div class="box-header with-border">
	                        <h3 class="box-title">添加商品</h3>
	                    </div>
	                    <form id="form1" class="form-horizontal" enctype="multipart/form-data" method="post" action="/EasyBuyProduct/add.do">
	                        <div class="box-body">
	                            <div class="form-group">
	                                <label for="txtALoginName" class="col-sm-2 control-label">商品名称：</label>
	                                <div class="col-sm-10">
	                                	<input type="text" id="txtALoginName" name="name" class="form-control" placeholder="请输入商品名"/>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label for="txtAPassWord" class="col-sm-2 control-label">商品描述：</label>
	                                <div class="col-sm-10">
	                                	<input type="text" id="txtAPassWord" name="description" class="form-control" placeholder="请输入商品描述"/>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label for="txtreAPassWord" class="col-sm-2 control-label">价格：</label>
	                                <div class="col-sm-10">
	                                	<input type="text" id="txtreAPassWord" name="price" class="form-control" placeholder="请输入商品价格"/>
	                                </div>
	                            </div>
								<div class="form-group">
									<label for="txtreAPassWord" class="col-sm-2 control-label">库存：</label>
									<div class="col-sm-10">
										<input type="text" id="txtreAPassWord1" name="stock" class="form-control" placeholder="请输入库存"/>
									</div>
								</div>
								<div class="form-group">
									<label for="txtreAPassWord" class="col-sm-2 control-label">一级分类：</label>
									<div class="col-sm-10">
										<select id="pc1" name="pc1.id" class="form-control">
											<option value="0">请选择一级分类</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="txtreAPassWord" class="col-sm-2 control-label">二级分类：</label>
									<div class="col-sm-10">
										<select id="pc2" name="pc2.id" class="form-control">
											<option value="0">请选择二级分类</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="txtreAPassWord" class="col-sm-2 control-label">三级分类：</label>
									<div class="col-sm-10">
										<select id="pc3" name="pc3.id" class="form-control">
											<option value="0">请选择三级分类</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="txtreAPassWord" class="col-sm-2 control-label">商品图片：</label>
									<div class="col-sm-10">
										<input type="file" id="tp" name="tp" class="form-control"/>
									</div>
								</div>
	                        </div>
	                        <div class="box-footer">
	                            <div class="col-sm-offset-2 col-sm-8">
	                            	<input type="submit" id="btnok" value="保 存" class="btn btn-block btn-info btn-lg"/>
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
