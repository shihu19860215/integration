<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var saveRemarks=function () {
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
        
    }

</script>


<div>
    <p class="block-heading" data-toggle="collapse" data-target="#chart-container">订单详情</p>
    <h3>客户名:${order.customerVO.name}</h3>
    <div class="well">
        <table class="table">
            <thead>
            <tr>
                <th>商品名</th>
                <th>型号</th>
                <th>个数</th>
                <th>通用车型</th>
                <th>单价</th>
                <th>销售</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${order.orderProductList}" var="orderProduct">
                <tr>
                    <td>${orderProduct.product.name}</td>
                    <td>${orderProduct.product.version}</td>
                    <td>
                        <span>${orderProduct.num}</span>
                    </td>
                    <th><c:forEach items="${orderProduct.product.carVOs}" var="car">${car.name} </c:forEach></th>
                    <td>${orderProduct.price}</td>
                    <td ><c:choose><c:when test="${orderProduct.sell}">销售</c:when><c:otherwise>换货</c:otherwise></c:choose>
                    </select>
                     </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="btn-toolbar">
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