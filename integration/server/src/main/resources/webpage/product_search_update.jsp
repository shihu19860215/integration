<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<script type="text/javascript">
    $(function () {
        $("#str").keyup(function () {
            loadCar();
        });
    });
    var loadCar = function () {
        var name = $("#name").val();
        if ("" != name) {
            $.post("/ajax/car/saerchcarbyname",
                {'str': $("#str").val()},
                function (data) {
                    if (null != data) {
                        var simpleCars = data;
                        var tmp = "";
                        for (var i = 0; i < simpleCars.length; i++) {
                            tmp += "<option value='" + simpleCars[i].id + "'>" + simpleCars[i].name + "</option>";
                        }
                        ;
                        $("#carIdSelect").show().html(tmp);
                    } else {
                    }
                }
            );
        } else {
            $("#carIdSelect").hide().html("");
        }
    }


    var addGeneralCar = function (o) {
        var addId = $("#carIdSelect").val();
        var carId = $("#carId").val();
        if (carId == addId) {
            alert("不能添加当前商品车型为通用车型");
            return false;
        }
        var carIdObjs = $("#generalCar").find("input[name='carIds']");
        if (carIdObjs.length > 0) {
            for (var i = 0; i < carIdObjs.length; i++) {
                if (addId == carIdObjs[i].value) {
                    alert("已经过了，不要重复添加");
                    return false;
                }
            }
        }
        $("#generalCar").append("<p>" + $("#carIdSelect").find("option:selected").text() + "<a href='javascript:void(0)' onclick='delGeneralCar(this)'><i class='icon-remove'></i></a><input name='carIds' type='hidden' value='" + $("#carIdSelect").val() + "'></p>");
    }

    var delGeneralCar = function (o) {
        $(o).parent().remove();
    }

    var formSubmit = function () {
        var carIdsInput=$("#generalCar input");
        var carIds="";
        carIdsInput.each(function(index, obj){
            if(index!=(carIdsInput.size()-1)){
                carIds=carIds+obj.value+",";
            }else{
                carIds=carIds+obj.value;
            }
        });
        $.post("/ajax/product/update",
            {
                'id': $("#id").val(),
                'name': $("#name").val(),
                'num': $("#num").val(),
                'version': $("#version").val(),
                'ownerprice': $("#ownerprice").val(),
                'otherprice': $("#otherprice").val(),
                'remark': $("#remark").val(),
                'carIds':carIds
            },
            function (data) {
                if (null != data) {
                    if("success"==data){
                        alert("更新成功");
                        $("#tab").submit();
                        return;
                    }
                }
                alert("更新失败");
            }
        );
    }
</script>

<div>
    <h1 class="page-title">${car.name}</h1>
    <div class="btn-toolbar">
        <button class="btn btn-primary" onclick="formSubmit()"><i class="icon-save"></i> Save</button>
        <div class="btn-group">
        </div>
    </div>
    <div class="well">
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane active in" id="home">
                <form id="tab" action="/search/product" method="post">
                    <input type="hidden" name="carName" value="${carName}"/>
                    <input type="hidden" name="productName" value="${productName}"/>
                    <input type="hidden" name="productVersion" value="${productVersion}"/>
                    <input type="hidden" name="sort" value="${sort}"/>
                    <input type="hidden" id="id" name="id" value="${product.id}"/>
                    <label>商品名</label>
                    <input type="text" id="name" name="name" value="${product.name}" class="input-xlarge">
                    <label>个数</label>
                    <input type="text" id="num" name="num" value="${product.num}" class="input-xlarge">
                    <label>型号</label>
                    <input type="text" id="version" name="version" value="${product.version}" class="input-xlarge">
                    <label>批发价</label>
                    <input type="text" id="ownerprice" name="ownerprice" value="${product.ownerprice}" class="input-xlarge">
                    <label>零售价</label>
                    <input type="text" id="otherprice" name="otherprice" value="${product.otherprice}" class="input-xlarge">
                    <label>备注</label>
                    <input type="text" id="remark" name="remark" value="${product.remark}" class="input-xlarge">
                    <label>通用车型：</label>
                    <div id="generalCar">
                        <c:forEach items="${product.carVOs}" var="item">
                                <p>${item.name}<a href="javascript:void(0)" onclick="delGeneralCar(this)"><i
                                        class="icon-remove"></i></a>
                                    <input id="carIds" name="carIds" type="hidden" value="${item.id}"></p>
                        </c:forEach>
                    </div>
                    <input type="text" id="str" value="" class="input-xlarge"/>
                    <select id="carIdSelect" class="input-xlarge" style="display: none;">
                    </select>
                    <button class="btn" type="button" onclick="addGeneralCar(this)">添加通用车型</button>
                </form>
            </div>
        </div>

    </div>

</div>