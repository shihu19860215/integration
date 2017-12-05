<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
    var addCount = function (productId, o) {
        $.post("productAjax!addCount",
            {'productId': productId},
            function (data) {
                $(o).next().html(" " + data.result.count + " ");
            }
        );
    }

    var subCount = function (productId, o) {
        $.post("productAjax!subCount",
            {'productId': productId},
            function (data) {
                $(o).prev().html(" " + data.result.count + " ");
            }
        );
    }

    var toSearchUpdatePage = function (id) {
        $("#productId").val(id);
        $("#productSerachForm").attr("action", "productSerach!toSearchUpdatePage").submit();
    }

    var delProduct = function (id) {
        if (confirm('确定要删除嘛?')) {
            $("#productId").val(id);
            $("#productSerachForm").attr("action", "productSerach!delProduct").submit();
        } else {
            return false;
        }
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
        $("#carCount").html("共" + $("#productSerachForm").next().find("tbody tr").length + "种");
        var spans = $("#productSerachForm").next().find("tbody tr span");
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
</script>

<div>
    <div class="btn-toolbar">
        <form id="productSerachForm" class="form-inline" action="/search/product" method="post">
            <input type="hidden" id="sort" name="sort" value="${null==sort?'pNameAsc':sort}">
            <input type="text" name="carName" value="${carName}" class="input-small" placeholder="车型">
            <input type="text" name="productName" value="${productName}" class="input-small" placeholder="商品名">
            <input type="text" name="productVersion" value="${productVersion}" class="input-small" placeholder="型号">
            <input type="submit" onclick="return tosearch()" class="btn" value="搜索"></input>
            <input type="reset" onclick="return clearAll()" class="btn" value="清除"></input>
        </form>
        <div class="btn-group">
        </div>
    </div>
    <div class="well">
        <!--
        <form id="productSerachForm" action="" method="post">
            <input type="hidden" name="carName" value="${carName}">
            <input type="hidden" name="productName" value="${productName}">
            <input type="hidden" name="productVersion" value="${productVersion}">
            <input type="hidden" name="sort" value="${''==sort?'pNameAsc':sort}">
        </form>-->
        <table class="table">

            <thead>
            <tr>
                <th onclick="sortCar()" width="40%">通用车型</th>
                <th onclick="sortProduct()" width="15%">商品名</th>
                <th onclick="sortCount()" width="10%">个数</th>
                <th onclick="sortVersion()" width="10%">型号</th>
                <th width="15%">备注</th>
                <th width="10%">价格</th>
                <!--
                <th>批发价</th>
                <th>零售价</th> -->
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" varStatus="i" var="product">
                <tr>
                    <th><c:forEach items="${product.carVOs}" var="carVO">
                            ${carVO.name}/
                        </c:forEach></th>
                    <td>${product.name}</td>
                    <td>
                        <a href="javascript:void(0)" onclick="addCount(${product.id},this)"><i class="icon-plus"></i></a>
                        <span>${product.num}</span>
                        <a href="javascript:void(0)" onclick="subCount(${product.id},this)"><i class="icon-minus"></i></a>
                    </td>
                    <td>${product.version}</td>
                    <td>${product.ownerprice}</td>
                    <td>${product.remark}</td>
                    <!-- <td><s:property value="otherprice"/></td>-->
                    <td>
                        <a onclick="toSearchUpdatePage(${product.id})" href="javascript:void(0);"><i
                                class="icon-pencil"></i></a>
                        <a onclick="delProduct(${product.id})" href="javascript:void(0);" role="button" data-toggle="modal"
                           onclick="return confirm('确定要删除嘛?')">
                            <i class="icon-remove"></i>
                        </a>
                    </td>
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