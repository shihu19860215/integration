<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

    $(function () {
        $("#cutomerStr").keyup(function () {
            loadCustomer();
        });
        $("#customerId").change(function(){
            $("#customerName").val($(this).find("option:selected").html());
        });
    });
    var loadCustomer=function(){
        var cutomerStr = $("#cutomerStr").val();
        if ("" != cutomerStr) {
            $.post("/ajax/customer/saerchlikename",
                {'customerName': cutomerStr},
                function (data) {
                    if (null != data) {
                        var tmp="<option>全部</option>";
                        for (var i = 0; i < data.length; i++) {
                            tmp += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        ;
                        $("#customerId").html(tmp);
                    }
                }
            );
        } else {
            $("#carIdSelect").hide().html("");
        }

    }

</script>

<div>
    <div class="btn-toolbar">
        <form class="form-inline" action="/order/search" method="post">
            <input id="cutomerStr" type="text" class="input-small" placeholder="客户名"  style="width: 60px;">

            <c:choose>
                <c:when test="${null!=orderSearch&&null!=orderSearch.customerId}">
                    <input id="customerName" type="hidden" name="customerName" value="${orderSearch.customerName}">
                    <select name="customerId" style="width: 120px;" id="customerId">
                        <option value="${orderSearch.customerId}">${orderSearch.customerName}</option>
                    </select>
                </c:when>
                <c:otherwise>
                    <input id="customerName" type="hidden" name="customerName">
                    <select style="width: 120px;" id="customerId">
                        <option>全部</option>
                    </select>
                </c:otherwise>
            </c:choose>
            <input type="text" name="productName" value="${orderSearch.productName}" class="input-small" placeholder="商品名"  style="width: 80px;">
            <input type="text" name="startDate" value="${orderSearch.startDate}" class="input-small" placeholder="开始日期:例20170501"  style="width: 140px;">
            <input type="text" name="endDate" value="${orderSearch.endDate}" class="input-small" placeholder="结束日期:例20170601"  style="width: 140px;">
            <input type="submit" class="btn" value="搜索"></input>
            <input type="reset" onclick="return clearAll()" class="btn" value="清除"></input>
        </form>
        <div class="btn-group">
        </div>
    </div>
    <div class="well">
        <table class="table">

            <thead>
            <tr>
                <th>时间</th>
                <th>客户名</th>
                <th>销售商品</th>
                <th>备注</th>
                <th></th>
            </tr>
            </thead>
            <tbody id="productTbady">
            <c:forEach items="${orderList}" varStatus="i" var="order">
                <tr>
                    <td>${order.createTime}</td>
                    <td>${order.customerVO.name}</td>
                    <td>${order.productNames}</td>
                    <td>${order.remarks}</td>
                    <td><a href="/order/${order.id}">详情</a></td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
    <div class="pagination">
        <ul>
            <li><a href="#">«</a></li>
            <li class="active"><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">»</a></li>
        </ul>
    </div>
</div>