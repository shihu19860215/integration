<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<script type="text/javascript">
    var formSubmit = function () {
        $("#tab").submit();
    }
</script>

<div>
    <div class="btn-toolbar">
        <button class="btn btn-primary" onclick="formSubmit()"><i class="icon-save"></i> Save</button>
        <div class="btn-group">
        </div>
    </div>
    <div class="well">
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane active in" id="home">
                <form id="tab" action="/customer/update" method="post">
                    <input type="hidden" name="id" value="${customerVO.id}" class="input-xlarge">
                    <label>客户名</label>
                    <input type="text" name="name" value="${customerVO.name}" class="input-xlarge">
                    <label>客户电话</label>
                    <input type="text" name="telephone" value="${customerVO.telephone}" class="input-xlarge">
                    <label>备用电话</label>
                    <input type="text" name="telephone2" value="${customerVO.telephone2}" class="input-xlarge">
                    <label>地区</label>
                    <input type="text" name="area" value="${customerVO.area}" class="input-xlarge">
                    <label>地址</label>
                    <input type="text" name="address" value="${customerVO.address}" class="input-xlarge">
                </form>
            </div>
        </div>

    </div>

</div>