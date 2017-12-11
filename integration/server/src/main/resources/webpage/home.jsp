<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>新毅汽车用品批发商行</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>

    <link rel="stylesheet" type="text/css" href="/resources/lib/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/stylesheets/theme.css"/>
    <link rel="stylesheet" href="/resources/lib/font-awesome/css/font-awesome.css"/>

    <script src="/resources/lib/jquery-1.8.1.min.js" type="text/javascript"></script>

    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

</head>

<body>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">

            <ul class="nav pull-right">

                <li id="fat-menu" class="dropdown">
                    <c:choose>
                        <c:when test="${null!=user}">
                            <a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="icon-user"></i> ${user.username}
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a tabindex="-1" href="/loginout">Logout</a></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <a href="/login" id="drop3" role="button" class="dropdown-toggle">
                                <i class="icon-user"></i>登陆
                            </a>

                        </c:otherwise>
                    </c:choose>
                </li>

            </ul>
            <a class="brand" href="index.html"><span class="first">Your</span> <span class="second">Company</span></a>
        </div>
    </div>
</div>


<div class="container-fluid">

    <div class="row-fluid">
        <div class="span3">
            <div class="sidebar-nav">
                <div class="nav-header" data-toggle="collapse" data-target="#dashboard-menu"><i class="icon-dashboard"></i>汽车用品</div>
                <ul id="dashboard-menu" class="nav nav-list collapse in">

                    <li <c:if test="${page.index==1}">class="active"</c:if> > <a href="/cartype/list">品牌车型</a></li>

                    <li <c:if test="${page.index==2}">class="active"</c:if> ><a href="/search/product">商品搜索</a></li>
                    <c:if test="${null!=user && user.hasAuthority('ProductController:toQuickAddPage')}">
                    <li <c:if test="${page.index==3}">class="active"</c:if> ><a href="/product/toquickaddpage">快速添加</a></li>
                    </c:if>
                    <li <c:if test="${page.index==4}">class="active"</c:if> ><a href="/order/toaddpage">当前订单</a></li>


                </ul>
                <div class="nav-header" data-toggle="collapse" data-target="#accounts-menu"><i class="icon-briefcase"></i>管理</div>
                <ul id="accounts-menu" class="nav nav-list collapse in">


                    <li <c:if test="${page.index==11}">class="active"</c:if> ><a href="/order/list">订单管理</a></li>
                    <li <c:if test="${page.index==12}">class="active"</c:if> ><a href="/customer/list">客户管理</a></li>


                </ul>
            </div>
        </div>
        <div class="span9">
            <jsp:include page="${page.includePage}.jsp"></jsp:include>



        </div>
    </div>

    <script src="/resources/lib/bootstrap/js/bootstrap.js"></script>

    </div>
</body>
</html>
