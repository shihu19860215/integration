<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var addCustomer = function () {
        location = "/customer/toaddpage";
    }
</script>


<div>
    <ul class="breadcrumb">
        <li>客户列表</li>
    </ul>

    <div class="btn-toolbar">
        <button class="btn btn-primary" onclick="addCustomer()"><i class="icon-plus"></i> 添加</button>
        <div class="btn-group">
        </div>
    </div>

    <div class="well">
        <table class="table">
            <thead>
            <tr>
                <th>客户名</th>
                <th>客户电话</th>
                <th>备用电话</th>
                <th>地区</th>
                <th>地址</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="customerVO">
                <tr>
                    <td>${customerVO.name}</td>
                    <td>${customerVO.telephone}</td>
                    <td>${customerVO.telephone2}</td>
                    <td>${customerVO.area}</td>
                    <td>${customerVO.address}</td>
                    <td>
                            <a href="/customer/toupdatepage/${customerVO.id}"><i
                                    class="icon-pencil"></i></a>
                            <a href="/customer/del/${customerVO.id}" role="button"
                               data-toggle="modal">
                                <i class="icon-remove" onclick="return confirm('确定要删除嘛?')"></i>
                            </a>
                    </td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>


</div>