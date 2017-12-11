<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

    $(function () {
        $("#customerName").keyup(function () {
            loadCustomer();
        });
    });
    var loadCustomer=function(){
        var customerName = $("#customerName").val();
        if ("" != customerName) {
            $.post("/ajax/customer/saerchlikename",
                {'customerName': customerName},
                function (data) {
                    if (null != data) {
                        var tmp="<option value='1'>个人</option>";
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



    var delOrderProduct = function (obj) {
        $(obj).parents("tr").remove();
        return false;
    }

    var addCount = function (obj) {
        var spanObj=$(obj).next();
        var max=parseInt(spanObj.attr("max"));
        var now=parseInt(spanObj.html());
        if(now<max){
            spanObj.html(now+1)
        }else {
            alert("库存达到最大;");
        }
    }

    var subCount = function (obj) {
        var spanObj=$(obj).prev();
        var max=parseInt(spanObj.attr("max"));
        var now=parseInt(spanObj.html());
        if(now>1){
            spanObj.html(now-1)
        }
    }

    var addOrder = function (carId) {
        $.post("/ajax/order/add",
            getPostData(),
            function (data) {
                if(null!=data&&"success"==data){
                    location = "/order/list";
                };
            }
        );
        return false;
    }

    var saveOrder=function(){
        $.post("/ajax/order/save",
            getPostData(),
            function (data) {
                if(null!=data&&"success"==data){
                    alert("暂存成功");
                };
            }
        );

    }

    var getPostData=function () {
        var result={};
        if(""!=$("#customerId").val()){
            result.customerVO={}
            result["customerVO.id"]=$("#customerId").val();
        }
        var trObjs=$("#tbody tr");
        var productNames="";
        for(var i=0;i<trObjs.length;i++){
            var trObj=$(trObjs[i]);
            var orderProductVOList='orderProductVOList['+i+']';
            result[orderProductVOList+'.productId']=trObj.find("#productId").val();
            result[orderProductVOList+'.num']=trObj.find("span").html();
            productNames=
            result[orderProductVOList+'.num']=trObj.find("span").html();
            if(""!=trObj.find("#price").val()){
                result[orderProductVOList+'.price']=trObj.find("#price").val();
            }
            result[orderProductVOList+'.sell']=trObj.find("select").val();
        }

       // result.total;
        if(""!=$("#remarks").val()){
            result.remarks=$("#remarks").val();
        }
        return result;
    }

</script>


<div>

    <div class="btn-toolbar">
        <input type="text" id="customerName" class="input-small" placeholder="客户名">
        <c:choose>
            <c:when test="${null!=order.customerVO}">
                <select id="customerId">
                    <option value="${order.customerVO.id}">${order.customerVO.name}</option>
                </select>
            </c:when>
            <c:otherwise>
                <select id="customerId">
                    <option value="1">个人</option>
                </select>
            </c:otherwise>
        </c:choose>
        <div class="btn-group">
        </div>
    </div>

    <div class="btn-toolbar">
        <button class="btn btn-primary" onclick="saveOrder()">暂存</button>
        <div class="btn-group">
        </div>
    </div>
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
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${order.orderProductVOList}" varStatus="index" var="orderProductVO">
                <tr>
                    <input type="hidden" id="productId" value="${productList[index.index].id}"/>
                    <td>${productList[index.index].name}</td>
                    <td>${productList[index.index].version}</td>
                    <td>
                        <c:if test="${null!=user && user.hasAuthority('ProductAjaxController:addNum')}">
                            <a onclick="addCount(this)">
                                <i class="icon-plus"></i>
                            </a>
                        </c:if>
                        <span max="${productList[index.index].num}">${orderProductVO.num}</span>
                        <c:if test="${null!=user && user.hasAuthority('ProductAjaxController:subNum')}">
                            <a onclick="subCount(this)">
                                <i class="icon-minus"></i>
                            </a>
                        </c:if>
                    </td>
                    <th id="carstrs"><c:forEach items="${productList[index.index].carVOs}" var="car">${car.name} </c:forEach></th>
                    <td><input id="price" type="text" value="${orderProductVO.price}" style="width: 40px;"/></td>
                    <td ><select style="width: 65px;">
                        <option value="true">售货</option>
                        <option value="false"<c:if test="${!orderProductVO.sell}"> selected = "selected"</c:if>>换货</option>
                    </select>
                     </td>
                    <td>
                            <a onclick="delOrderProduct(this)" role="button"
                               data-toggle="modal">
                                <i class="icon-remove"></i>
                            </a>
                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="btn-toolbar">
        <h1 style="font-size: 25px">备注:</h1>
    </div>
    <form class="bs-docs-example form-inline">
        <textarea id="remarks" rows="3" style="width: 60%">${order.remarks}</textarea>
    </form>

    <div class="btn-toolbar pull-right">
        <button class="btn btn-primary" onclick="addOrder()"> 确认订单</button>
        <div class="btn-group">
        </div>
    </div>

</div>