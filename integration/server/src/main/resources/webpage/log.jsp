<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var maxlength=10;
    $(function () {
        initPage(${logSearch.pageNum},${logSearch.count},${logSearch.pageSize});
    });


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
        $("#pageNum").val("");
        $("#startDate").val("");
        $("#endDate").val("");
    }
</script>

<div>
    <div class="btn-toolbar">
        <form id="searchFrom" class="form-inline" action="/log/search" method="post">
            <input id="pageNum" type="hidden" name="pageNum">
            <input type="text"id="startDate" name="startDate" value="${logSearch.startDate}" class="input-small" placeholder="开始日期:例20170501"  style="width: 140px;">
            <input type="text"id="endDate" name="endDate" value="${logSearch.endDate}" class="input-small" placeholder="结束日期:例20170601"  style="width: 140px;">
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
                <th>日志</th>
            </tr>
            </thead>
            <tbody id="productTbady">
            <c:forEach items="${logList}" varStatus="i" var="log">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${log.createTime}</td>
                    <td>${log.info}</td>
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