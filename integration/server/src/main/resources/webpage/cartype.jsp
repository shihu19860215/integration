<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var delCar=function(){
        if(confirm('确定要删除嘛?')){
            return true;
        }else{
            return false;
        }
    }

    var searchSubmit=function(){
        $("#formcartype").attr("action","carType!search").submit();
    }
    var addSubmit=function(){
        $("#formcartype").submit();
    }

</script>

<div>

    <p class="text-error"></p>
    <div class="row-fluid">
        <div class="block span12">
            <ul class="breadcrumb">
                <li class="active">品牌车型</li>
            </ul>
            <div class="block-body">
                <div id="cartypes" class="row-fluid" style="text-align: center;">
                    <div class="pull-left span4 unstyled">
                        <c:forEach items="${list1}" varStatus="i" var="item" >
                            <p><a href="car/${item.id}">${item.name}</a></p>
                        </c:forEach>
                    </div>
                    <div class="pull-left span4 unstyled">
                        <c:forEach items="${list2}" varStatus="i" var="item" >
                            <p><a href="car/${item.id}">${item.name}</a></p>
                        </c:forEach>

                    </div>
                    <div>
                        <c:forEach items="${list3}" varStatus="i" var="item" >
                            <p><a href="car/${item.id}">${item.name}</a></p>
                        </c:forEach>


                    </div>
                </div>
                <div class="clearfix"></div>
            </div>

        </div>
    </div>

</div>