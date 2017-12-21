<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var maxlength=10;
    $(function () {
        $("#str").keyup(function () {
            loadCustomer();
            $("#customerId").val("");
        });
        $("#customerSelect").change(function(){
            $("#customerNameOrTel").val($(this).find("option:selected").html());
            $("#customerId").val($(this).find("option:selected").val());
        });
        initPage(${orderSearch.pageNum},${orderSearch.count},${orderSearch.pageSize});
    });
    var loadCustomer=function(){
        var str = $("#str").val();
        if ("" != str) {
            $.post("/ajax/customer/saerchlikename",
                {'customerNameOrTel': str},
                function (data) {
                    if (null != data) {
                        var tmp="<option value=\"1\">个人</option>";
                        for (var i = 0; i < data.length; i++) {
                            var str=data[i].name;
                            if(null!=data[i].area&&""!=data[i].area.trim()){
                                str=data[i].area+"-"+str;
                            }
                            if(null!=data[i].telephone&&""!=data[i].telephone.trim()){
                                str=str+"-"+data[i].telephone;
                            }
                            tmp += "<option value='" + data[i].id + "'>" +str+ "</option>";
                        }
                        ;
                        $("#customerSelect").html(tmp);
                    }
                }
            );
        } else {
            $("#carIdSelect").hide().html("");
        }

    }

    var topage=function(page){
        $("#pageNum").val(page);
        $("#searchFrom").submit();
    }
    
    var initPage=function(page,count,pageSize){
        var length;
        if(count%pageSize>0){
            length=parseInt(count/pageSize)+1;
        }else{
            length=count/pageSize;
        }
        if(length>1){
            var str=""
            if(page==1){
                str=str+"<li class=\"active\"><a>«</a></li>";
            }else{
                str=str+"<li><a onclick=\"topage("+(page-1)+")\">«</a></li>";
            }
            if(length>maxlength&&(maxlength/2<page)){
                str=str+"<li class=\"active\"><a>...</a></li>";
            }
            var startnum;
            if(page<=maxlength/2){
                startnum=1;
            }else if(page>=length-maxlength/2){
                startnum=length-maxlength+1;
            }else {
                startnum=page-maxlength/2+1;
            }
            for(var i=0;i<length&&i<maxlength;i++){
                if(page==startnum+i){
                    str=str+"<li class=\"active\"><a>"+(startnum+i)+"</a></li>";

                }else{
                    str=str+"<li><a onclick=\"topage("+(startnum+i)+")\">"+(startnum+i)+"</a></li>";
                }
            }
            if(length>maxlength&&(length-maxlength/2>page)){
                str=str+"<li class=\"active\"><a>...</a></li>";
            }
            if(page==length){
                str=str+"<li class=\"active\"><a>»</a></li>";
            }else {
                str=str+"<li><a onclick=\"topage("+(page+1)+")\">»</a></li>";
            }
            $("#pageUl").html(str);
        }
    }
    var clearAll=function () {
        $("#str").val("");
        $("#pageNum").val("");
        $("#productName").val("");
        $("#startDate").val("");
        $("#endDate").val("");
        $("#customerId").val("");
        $("#customerSelect").html("<option value=\"1\">个人</option>");
    }
</script>

<div>
    <div class="btn-toolbar">
        <form id="searchFrom" class="form-inline" action="/order/search" method="post">
            <input id="str" type="text" class="input-small" placeholder="客户名"  style="width:90px;">
            <input id="pageNum" type="hidden" name="pageNum">
            <c:choose>
                <c:when test="${null!=orderSearch&&null!=orderSearch.customerId}">
                    <input id="customerNameOrTel" type="hidden" name="customerNameOrTel" value="${orderSearch.customerNameOrTel}">
                    <input id="customerId" type="hidden" name="customerId" value="${orderSearch.customerId}">
                    <select style="width: 280px;" id="customerSelect">
                        <option value="${orderSearch.customerId}">${orderSearch.customerNameOrTel}</option>
                    </select>
                </c:when>
                <c:otherwise>
                    <input id="customerNameOrTel" type="hidden" name="customerNameOrTel">
                    <input id="customerId" type="hidden" name="customerId">
                    <select style="width: 280px;" id="customerSelect">
                        <option value="1">个人</option>
                    </select>
                </c:otherwise>
            </c:choose>
            <input type="text" id="productName" name="productName" value="${orderSearch.productName}" class="input-small" placeholder="商品名"  style="width: 80px;">
            <input type="text"id="startDate" name="startDate" value="${orderSearch.startDate}" class="input-small" placeholder="开始日期"  style="width: 70px;">
            <input type="text"id="endDate" name="endDate" value="${orderSearch.endDate}" class="input-small" placeholder="结束日期"  style="width: 70px;">
            <select name="retreat" style="width: 95px;"><option value="false">全部</option><option value="1" <c:if test="${orderSearch.retreat}"> selected = "selected"</c:if>>有退换货</option></select>
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
                <th>序号</th>
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
                    <td>${i.index+1}</td>
                    <td>${order.createTime}</td>
                    <td>${stringUtil.toCutString(order.customerVO.name,12)}</td>
                    <td>${stringUtil.toCutString(order.productNames,50)}</td>
                    <td>${stringUtil.toCutString(order.remarks,16)}</td>
                    <td><a href="/order/${order.id}">详情</a></td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
    <div class="pagination">
        <ul id="pageUl">
        </ul>
    </div>
</div>