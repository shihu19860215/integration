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
    var delOtherProduct=function(obj){
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

    var addOtherProduct=function () {
        $("#tbody2").append("<tr><td><input type=\"text\" name=\"otherProductName\" style=\"width:300px;\"></td>" +
            "                    <td><input type=\"text\" name=\"otherProductNum\" style=\"width:30px;\"></td>" +
            "                    <td><input type=\"text\" name=\"otherProductUnit\" style=\"width:30px;\"></td>" +
            "                    <td><input type=\"text\" name=\"otherProductPrice\" style=\"width:50px;\"></td>" +
            "                    <td>" +
            "                        <a onclick=\"delOtherProduct(this)\" role=\"button\"" +
            "                           data-toggle=\"modal\">" +
            "                            <i class=\"icon-remove\"></i>" +
            "                        </a>" +
            "                    </td></tr>");
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
                if(null!=data){
                    if(1==data.state){
                        location = "/order/search";
                    }else {
                        alert(data.info);
                    }
                };
            }
        );
        return false;
    }

    var saveOrder=function(){
        $.post("/ajax/order/save",
            getPostData(),
            function (data) {
                if(null!=data){
                    if(1==data.state){
                        alert("暂存成功");
                    }
                };
            }
        );
        return false;
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
            productNames=productNames+trObj.find("#productName").html()+",";
            result[orderProductVOList+'.num']=trObj.find("span").html();
            if(""!=trObj.find("#price").val()){
                result[orderProductVOList+'.price']=trObj.find("#price").val();
            }
            result[orderProductVOList+'.sell']=trObj.find("select").val();
        }

        trObjs=$("#tbody2 tr");
        for(var i=0;i<trObjs.length;i++){
            var trObj=$(trObjs[i]);
            var otherProductVOList='otherProductVOList['+i+']';
            var name=trObj.find("input[name='otherProductName']").val();
            var num=trObj.find("input[name='otherProductNum']").val();
            var unit=trObj.find("input[name='otherProductUnit']").val();
            var price=trObj.find("input[name='otherProductPrice']").val();
            if(null!=name&&name!=""&&null!=num&&""!=num&&null!=unit&&""!=unit){
                result[otherProductVOList+'.name']= name;
                result[otherProductVOList+'.num']=num;
                result[otherProductVOList+'.unit']=unit;
                result[otherProductVOList+'.price']=price;
                productNames=productNames+name+",";
            }

        }
        result.productNames=productNames.substr(0,productNames.length-1);
        var total=countTotal();
        if(total>0){
            result.total=total;
        }
        if(""!=$("#remarks").val()){
            result.remarks=$("#remarks").val();
        }
        return result;
    }

    var showTotal=function () {
        if(countTotal()<0){
            alert("数量价格非法或为空");
        }
    }

    var  countTotal=function(){;
        var count=0;
        var trObjs=$("#tbody tr")
        for(var i=0;i<trObjs.length;i++){
            var trObj=$(trObjs[i]);
            var num=parseInt(trObj.find("span").html());
            var price=parseInt(trObj.find("#price").val());
            if(!isNaN(num)&&num>0&&!isNaN(price)&&price>0){
                count=count+num*price;
            }else {
                $("#totalH1").html("总价:");
                return -1;
            }
        }
        trObjs=$("#tbody2 tr");
        for(var i=0;i<trObjs.length;i++){
            var trObj=$(trObjs[i]);
            var num=parseInt(trObj.find("input[name='otherProductNum']").val());
            var price=parseInt(trObj.find("input[name='otherProductPrice']").val());
            if(!isNaN(num)&&num>0&&!isNaN(price)&&price>0){
                count=count+num*price;
            }else {
                $("#totalH1").html("总价:");
                return -1;
            }
        }
        $("#totalH1").html("总价:"+count);
        return count;
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
                <th>销售</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${order.orderProductVOList}" varStatus="index" var="orderProductVO">
                <tr>
                    <input type="hidden" id="productId" value="${productList[index.index].id}"/>
                    <td id="productName">${productList[index.index].name}</td>
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

    <div class="well">
        <ul class="breadcrumb" style="width: 58px;">
            <li class="active">其他商品</li>
        </ul>
        <table class="table">
            <thead>
            <tr>
                <th>商品名</th>
                <th>个数</th>
                <th>单位</th>
                <th>单价</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody2">
            <c:forEach items="${order.otherProductVOList}" varStatus="index" var="otherProductVO">
                <tr>
                    <td><input type="text" name="otherProductName" value="${otherProductVO.name}" style="width:300px;"></td>
                    <td><input type="text" name="otherProductNum" value="${otherProductVO.num}" style="width:30px;"></td>
                    <td><input type="text" name="otherProductUnit" value="${otherProductVO.unit}" style="width:30px;"></td>
                    <td><input type="text" name="otherProductPrice" value="${otherProductVO.price}" style="width:50px;"></td>
                    <td>
                        <a onclick="delOtherProduct(this)" role="button"
                           data-toggle="modal">
                            <i class="icon-remove"></i>
                        </a>
                    </td>
                </tr>

            </c:forEach>

            </tbody>

            <thead>
            <tr>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th style="width:100px;" onclick="addOtherProduct()"><button>添加其他商品</button></th>
            </tr>
            </thead>
        </table>
    </div>
    <div class="btn-toolbar">
        <h1 id="totalH1" style="font-size: 25px">总价:${order.total}</h1>
        <button onclick="showTotal()">计算总价</button><br><br>
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