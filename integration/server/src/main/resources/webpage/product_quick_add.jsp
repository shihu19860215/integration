<%@ page contentType="text/html; charset=UTF-8" %>
<script type="text/javascript">
    $(function () {
        $("#str").keyup(function () {
            loadCar();
        });
    });
    var loadCar = function () {
        var str = $("#str").val();
        if ("" != str) {
            $.post("/ajax/car/saerchcarbyname",
                {'str': str},
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
        if(""!=$("#name").val()&&""!=$("#num").val()){
            $("#tab").submit();
        }
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
                <form id="tab" action="/product/quickadd" method="post">
                    <label>商品名</label>
                    <input type="text" id="name" name="name" value="${productVO.name}" class="input-xlarge">
                    <label>个数</label>
                    <input type="text" id="num" name="num" value="${productVO.num}" class="input-xlarge">
                    <label>型号</label>
                    <input type="text" name="version" value="${productVO.version}" class="input-xlarge">
                    <label>批发价</label>
                    <input type="text" name="ownerprice" value="${productVO.ownerprice}" class="input-xlarge">
                    <label>零售价</label>
                    <input type="text" name="otherprice" value="${productVO.otherprice}" class="input-xlarge">
                    <label>备注</label>
                    <input type="text" name="remark" value="${productVO.remark}" class="input-xlarge">
                    <label>通用车型：</label>
                    <div id="generalCar">
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