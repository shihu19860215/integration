<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var delCar = function () {
        if (confirm('确定要删除嘛?')) {
            return true;
        } else {
            return false;
        }
    }

    var searchSubmit = function () {
        $("#formcar").attr("action", "/car/search").submit();
    }
    var addSubmit = function () {
        $("#formcar").submit();
    }

</script>

<div>


        <div class="input-append">
            <form id="formcar" action="/car/add" method="post">
                <input type="hidden" name="carTypeId" value="${carType.id}"/>
                <input class="span2" name="name" type="text"/>
                <button onclick="addSubmit()" class="btn" type="button"> 添加</button>
                <input class="span2" name="searchStr" value="${searchStr}" type="text"/>
                <button onclick="searchSubmit()" class="btn" type="button"> 搜索</button>
            </form>
        </div>
    <p class="text-error">${errorInfo}</p>
    <div class="row-fluid">
        <div class="block span12">
            <ul class="breadcrumb">
                <li><a href="/cartype/list">品牌车型</a> <span class="divider">/</span></li>
                <li class="active">${carType.name}</li>
            </ul>
            <div class="block-body">
                <div id="cars" class="row-fluid" style="text-align: center;">
                    <div class="pull-left span4 unstyled">
                        <c:forEach items="${carType.carVOs}" varStatus="i" var="item">
                            <c:if test="${ i.count%3==1}">
                                <p>
                                    <a href="/product/list?carId=${item.id}&carTypeId=${carType.id}">${item.name}</a>
                                    <a href="/car/del/${item.id}?carTypeId=${carType.id}" onclick="return delCar()"><i class="icon-remove"></i></a>
                                </p>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="pull-left span4 unstyled">
                        <c:forEach items="${carType.carVOs}" varStatus="i" var="item">
                            <c:if test="${i.count%3==2}">
                                <p>
                                    <a href="/product/list?carId=${item.id}&carTypeId=${carType.id}">${item.name}</a>
                                    <a href="/car/del/${item.id}?carTypeId=${carType.id}" onclick="return delCar()"><i class="icon-remove"></i></a>
                                </p>
                            </c:if>
                        </c:forEach>

                    </div>
                    <div>
                        <c:forEach items="${carType.carVOs}" varStatus="i" var="item">
                            <c:if test="${i.count%3==0}">
                                <p>
                                    <a href="/product/list?carId=${item.id}&carTypeId=${carType.id}">${item.name}</a>
                                    <a href="/car/del/${item.id}?carTypeId=${carType.id}" onclick="return delCar()"><i class="icon-remove"></i></a>
                                </p>
                            </c:if>
                        </c:forEach>


                    </div>
                </div>
                <div class="clearfix"></div>
            </div>

        </div>
    </div>

</div>