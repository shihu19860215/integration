<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var addProduct = function (carId) {
        location = "/product/toaddpage/"+carId;
    }

    var delProduct = function () {
        if (confirm('确定要删除嘛?')) {
            return true;
        } else {
            return false;
        }
    }

    var addCount = function (productId, o) {
        $.post("/ajax/product/add",
            {'productId': productId},
            function (data) {
                $(o).next().html(" " + data.count + " ");
                //location = location;
            }
        );
    }

    var subCount = function (productId, o) {
        $.post("/ajax/product/sub",
            {'productId': productId},
            function (data) {
                $(o).prev().html(" " + data.count + " ");
                //location = location;
            }
        );
    }
</script>


<div>
    <ul class="breadcrumb">
        <li><a href="/cartype/list">品牌</a> <span class="divider">/</span></li>
        <li><a href="/car/list?carTypeId=${carType.id}">${carType.name}</a> <span
                class="divider">/</span></li>
        <li class="active">${car.name}</li>
    </ul>
    <div class="btn-toolbar">
        <button class="btn btn-primary" onclick="addProduct(${car.id})"><i class="icon-plus"></i> 添加</button>
        <div class="btn-group">
        </div>
    </div>
    <div class="well">
        <table class="table">
            <thead>
            <tr>
                <th>商品名</th>
                <th>个数</th>
                <th>型号</th>
                <th>备注</th>
                <th>批发价</th>
                <th>零售价</th>
                <th>通用车型</th>
                <th style="width: 26px;"></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${productList}" varStatus="i" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td><a href="javascript:void(0)" onclick="addCount(${item.id},this)">
                        <i class="icon-plus"></i>
                    </a>
                        <span> ${item.num}</span>
                        <a href="javascript:void(0)" onclick="subCount(${item.id},this)">
                            <i class="icon-minus"></i>
                        </a>
                    </td>
                    <td>${item.version}</td>
                    <td>${item.remark}</td>
                    <td>${item.ownerprice}</td>
                    <td>${item.otherprice}</td>
                    <th><c:forEach items="${item.carVOs}" var="car">${car.name} </c:forEach></th>
                    <td>
                        <a href="/product/toupdatepage/${item.id}?carId=${car.id}"><i class="icon-pencil"></i></a>
                        <a href="/product/del/${item.id}?carId=${car.id}&carTypeId=${carType.id}" role="button" data-toggle="modal">
                            <i class="icon-remove" onclick="return confirm('确定要删除嘛?')"></i>
                        </a>
                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>


</div>