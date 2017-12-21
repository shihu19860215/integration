<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var addCount = function (productId, o) {
        $.post("/ajax/product/add",
            {'productId': productId},
            function (data) {
                $(o).next().html(" " + data.count + " ");
                //location = location;
            }
        );
        return false;
    }

    var subCount = function (productId, o) {
        $.post("/ajax/product/sub",
            {'productId': productId},
            function (data) {
                $(o).prev().html(" " + data.count + " ");
                //location = location;
            }
        );
        return false;
    }

    var toSearchUpdatePage = function (id) {
        $("#productSerachForm").attr("action", "/search/product/toupdatepage/" + id).submit();
    }

    var delProduct = function (id) {
        if (confirm('确定要删除嘛?')) {
            $.post("/ajax/product/del/" + id,
                {},
                function (data) {
                    if (null != data) {
                        if ("success" == data) {
                            alert(" 删除成功");
                            $("#productSerachForm").submit();
                            return;
                        }
                    }
                    alert("删除失败");
                }
            );

        }
        return false;
    }

    var sortCar = function () {
        var sorttmp = $("#sort").val();
        if (sorttmp == "cNameAsc") {
            sorttmp = "cNameDesc";
        } else {
            sorttmp = "cNameAsc";
        }
        $("#sort").val(sorttmp);
        $("#productSerachForm").submit();
    }

    var sortProduct = function () {
        var sorttmp = $("#sort").val();
        if (sorttmp == "pNameAsc") {
            sorttmp = "pNameDesc";
        } else {
            sorttmp = "pNameAsc";
        }
        $("#sort").val(sorttmp);
        $("#productSerachForm").submit();
    }

    var sortCount = function () {
        var sorttmp = $("#sort").val();
        if (sorttmp == "pNumAsc") {
            sorttmp = "pNumDesc";
        } else {
            sorttmp = "pNumAsc";
        }
        $("#sort").val(sorttmp);
        $("#productSerachForm").submit();
    }

    var sortVersion = function () {
        var sorttmp = $("#sort").val();
        if (sorttmp == "pVersionAsc") {
            sorttmp = "pVersionDesc";
        } else {
            sorttmp = "pVersionAsc";
        }
        $("#sort").val(sorttmp);
        $("#productSerachForm").submit();
    }

    var statistics = function () {
        $("#carCount").html("共" + $("#productTbady").find("tr").length + "种");
        var spans = $("#productTbady").find("tr span");
        var count = 0;
        for (var i = 0; i < spans.length; i++) {
            count = count + parseInt($(spans[i]).html());
        }
        $("#numCount").html("共" + count + "个");
    }

    var clearAll = function () {
        $(".form-inline .input-small").val("");
        return false;
    }
    var tosearch = function () {
        var texts = $(".form-inline .input-small");
        var i = 0;
        for (; i < texts.length; i++) {
            if ("" != $(texts[i]).val()) break;
        }
        if (3 == i) {
            alert("没有搜索条件");
            return false;
        }
    }

    var addToOrder = function (productId, obj) {
        $.post("/ajax/order/addproduct/" + productId,
            {},
            function (data) {
                if (null != data && "success" == data) {
                    $(obj).remove();
                } else {
                    alert("加入订单失败");
                }
            }
        );
        return false;
    }
</script>

<div>
    <div class="btn-toolbar">
        <form id="productSerachForm" class="form-inline" action="/search/product" method="post">
            <input type="hidden" id="productId" name="productId">
            <input type="hidden" id="sort" name="sort" value="${null==sort?'pNameAsc':sort}">
            <input type="text" name="carName" value="${carName}" class="input-small" placeholder="车型">
            <input type="text" name="productName" value="${productName}" class="input-small" placeholder="商品名">
            <input type="text" name="productVersion" value="${productVersion}" class="input-small" placeholder="型号">
            <input type="text" name="productRemark" value="${productRemark}" class="input-small" placeholder="备注">
            <input type="submit" onclick="return tosearch()" class="btn" value="搜索"></input>
            <input type="reset" onclick="return clearAll()" class="btn" value="清除"></input>
        </form>
        <div class="btn-group">
        </div>
    </div>
    <div class="well">
        <table class="table">

            <thead>
            <tr>
                <th onclick="sortCar()" width="40%">通用车型</th>
                <th onclick="sortProduct()" width="10%">商品名</th>
                <th onclick="sortCount()" width="8%">个数</th>
                <th onclick="sortVersion()" width="10%">型号</th>
                <th width="15%">备注</th>
                <th width="7%">价格</th>
                <c:if test="${null!=user}">
                    <th width="10%">操作</th>
                </c:if>
            </tr>
            </thead>
            <tbody id="productTbady">
            <c:forEach items="${products}" varStatus="i" var="product">
                <tr>
                    <th><c:forEach items="${product.carVOs}" var="carVO">
                        ${carVO.name}/
                    </c:forEach></th>
                    <td>${product.name}</td>
                    <td>
                        <c:if test="${null!=user && user.hasAuthority('ProductAjaxController:addNum')}">
                            <a onclick="addCount(${product.id},this)"><i
                                    class="icon-plus"></i></a>
                        </c:if>
                        <span>${product.num}</span>
                        <c:if test="${null!=user && user.hasAuthority('ProductAjaxController:subNum')}">
                            <a  onclick="subCount(${product.id},this)"><i
                                    class="icon-minus"></i></a>
                        </c:if>
                    </td>
                    <td>${product.version}</td>
                    <td>${product.remark}</td>
                    <td>${product.ownerprice}</td>
                    <!-- <td><s:property value="otherprice"/></td>-->
                    <c:if test="${null!=user}">
                        <td>

                            <c:if test="${user.hasAuthority('ProductSearchController:toUpdatePage')}">
                                <a onclick="toSearchUpdatePage(${product.id})" ><i
                                        class="icon-pencil"></i></a>
                            </c:if>
                            <c:if test="${user.hasAuthority('ProductAjaxController:del')}">
                                <a onclick="delProduct(${product.id})" role="button"
                                   data-toggle="modal"
                                   onclick="return confirm('确定要删除嘛?')">
                                    <i class="icon-remove"></i>
                                </a>
                            </c:if>
                            <c:if test="${product.num>0&&user.hasAuthority('OrderAjaxController:addProduct')}">
                            <a onclick="addToOrder(${product.id},this)"  role="button"
                               data-toggle="modal">
                                <i class="icon-shopping-cart"></i>
                            </a>
                            </c:if>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>

            <thead>
            <tr>
                <th id="carCount"></th>
                <th></th>
                <th id="numCount"></th>
                <th></th>
                <th></th>
                <th></th>
                <th>
                    <button onclick="statistics()" class="btn">统计</button>
                </th>
            </tr>
            </thead>
        </table>
    </div>

</div>