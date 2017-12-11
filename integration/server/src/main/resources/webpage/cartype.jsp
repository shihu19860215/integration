<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var delCarType = function () {
        if (confirm('确定要删除嘛?')) {
            return true;
        } else {
            return false;
        }
    }

    var searchSubmit = function () {
        $("#formcartype").attr("action", "/cartype/search").submit();
    }
    var addSubmit = function () {
        $("#formcartype").submit();
    }

</script>

<div>


    <div class="input-append">
        <form id="formcartype" action="/cartype/add" method="post">
            <c:if test="${null!=user && user.hasAuthority('CarTypeController:add')}">
                <input class="span2" name="name" type="text"/>
                <button onclick="addSubmit()" class="btn" type="button"> 添加</button>
            </c:if>
            <input class="span2" name="searchStr" value="${searchStr}" type="text"/>
            <button onclick="searchSubmit()" class="btn" type="button"> 搜索</button>
        </form>
    </div>
    <p class="text-error">${page.errorInfo}</p>
    <div class="row-fluid">
        <div class="block span12">
            <ul class="breadcrumb">
                <li class="active">品牌车型</li>
            </ul>
            <div class="block-body">
                <div id="cartypes" class="row-fluid" style="text-align: center;">
                    <div class="pull-left span4 unstyled">
                        <c:forEach items="${carTypeList}" varStatus="i" var="item">
                            <c:if test="${ i.count%3==1}">
                                <p>
                                    <a href="/car/list?carTypeId=${item.id}">${item.name}</a>
                                    <c:if test="${null!=user && user.hasAuthority('CarTypeController:delete')}">
                                        <a href="/cartype/del/${item.id}" onclick="return delCarType()"><i
                                                class="icon-remove"></i></a>
                                    </c:if>
                                </p>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="pull-left span4 unstyled">
                        <c:forEach items="${carTypeList}" varStatus="i" var="item">
                            <c:if test="${ i.count%3==2}">
                                <p>
                                    <a href="/car/list?carTypeId=${item.id}">${item.name}</a>
                                    <c:if test="${null!=user && user.hasAuthority('CarTypeController:delete')}">
                                        <a href="/cartype/del/${item.id}" onclick="return delCarType()"><i
                                                class="icon-remove"></i></a>
                                    </c:if>
                                </p>
                            </c:if>
                        </c:forEach>

                    </div>
                    <div>
                        <c:forEach items="${carTypeList}" varStatus="i" var="item">
                            <c:if test="${ i.count%3==0}">
                                <p>
                                    <a href="/car/list?carTypeId=${item.id}">${item.name}</a>
                                    <c:if test="${null!=user && user.hasAuthority('CarTypeController:delete')}">
                                        <a href="/cartype/del/${item.id}" onclick="return delCarType()"><i
                                                class="icon-remove"></i></a>
                                    </c:if>
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