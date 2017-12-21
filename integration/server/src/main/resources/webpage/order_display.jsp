<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var saveRemarks=function () {
        if (confirm('确定要修改备注嘛?')) {
            $.post("/ajax/order/saveremarks",
                {
                    "remarks":$("#remarks").val(),
                    "id":${order.id}
                },
                function (data) {
                    if(null!=data){
                        if(1==data.state){
                            alert("保存成功");
                        }
                    };
                }
            );
        } else {
            return false;
        }
        
    }

</script>


<div>
    <p class="block-heading" data-toggle="collapse" data-target="#chart-container">订单详情</p>
    <h3>客户名:${null!=order.customerVO.area?order.customerVO.area.concat("-"):""}${order.customerVO.name}${null!=order.customerVO.telephone?"-".concat(order.customerVO.telephone):""}</h3>
    <c:if test="${null!=order.orderProductList&&order.orderProductList.size()>0}">
    <div class="well">
        <ul class="breadcrumb" style="width: 58px;">
            <li class="active">专用商品</li>
        </ul>
        <table class="table">
            <thead>
            <tr>
                <th>商品名</th>
                <th>型号</th>
                <th>个数</th>
                <th>通用车型</th>
                <th>单价</th>
                <th>价格</th>
                <th>销售</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${order.orderProductList}" var="orderProduct">
                <tr>
                    <td>${orderProduct.product.name}</td>
                    <td>${orderProduct.product.version}${orderProduct.product.remark}</td>
                    <td>
                        <span>${orderProduct.num}</span>
                    </td>
                    <th><c:forEach items="${orderProduct.product.carVOs}" var="car">${car.name} </c:forEach></th>
                    <td>${orderProduct.price}</td>
                    <td>${orderProduct.num*orderProduct.price}</td>
                    <td ><c:choose><c:when test="${orderProduct.sell}">销售</c:when><c:otherwise>退换</c:otherwise></c:choose>
                     </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>
    </c:if>
    <c:if test="${null!=order.otherProductVOList&&order.otherProductVOList.size()>0}">
    <div class="well">
        <ul class="breadcrumb" style="width: 58px;">
            <li class="active">其他商品</li>
        </ul>
        <table class="table">
            <thead>
            <tr>
                <th>商品名</th>
                <th>数量</th>
                <th>单价</th>
                <th>价格</th>
                <th>销售</th>
            </tr>
            </thead>
            <tbody id="tbody2">
            <c:forEach items="${order.otherProductVOList}" varStatus="index" var="otherProductVO">
                <tr>
                    <td>${otherProductVO.name}</td>
                    <td>${otherProductVO.num}${otherProductVO.unit}</td>
                    <td>${otherProductVO.price}</td>
                    <td>${otherProductVO.num*otherProductVO.price}</td>
                    <td ><c:choose><c:when test="${otherProductVO.sell}">销售</c:when><c:otherwise>退换</c:otherwise></c:choose>
                    </td>
                </tr>

            </c:forEach>

            </tbody>

        </table>
    </div>
    </c:if>
    <div class="btn-toolbar">
        <c:if test="${null!=order.total}"><h1 style="font-size: 20px">总价:${order.total}</h1></c:if>
        <h1 style="font-size: 20px">备注:</h1>
    </div>
    <form class="bs-docs-example form-inline">
        <textarea id="remarks" rows="3" style="width: 60%">${order.remarks}</textarea>
    </form>

    <div class="btn-toolbar">
        <button class="btn btn-primary" onclick="saveRemarks()"> 保存备注</button>
        <div class="btn-group">
        </div>
    </div>

</div>