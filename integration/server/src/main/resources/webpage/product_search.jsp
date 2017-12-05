<%@ page contentType="text/html; charset=UTF-8" %>
<script type="text/javascript">
	var addCount=function(productId,o){
		$.post("productAjax!addCount", 
				{ 'productId': productId},
				function(data) {
					$(o).next().html(" "+data.result.count+" ");
				}
			);
	}
	
	var subCount=function(productId,o){
		$.post("productAjax!subCount", 
				{ 'productId': productId},
				function(data) {
					$(o).prev().html(" "+data.result.count+" ");
				}
			);
	}
	
	var toSearchUpdatePage=function(id){
		$("#productId").val(id);
		$("#productSerachForm").attr("action","productSerach!toSearchUpdatePage").submit();
	}
	
	var delProduct=function(id){
		if(confirm('确定要删除嘛?')){
			$("#productId").val(id);
			$("#productSerachForm").attr("action","productSerach!delProduct").submit();
		}else{
			return false;
		}
	}
	
	var sortCar=function(){
		var sorttmp=parseInt($("#sort").val());
		if(sorttmp<2){
			sorttmp=(sorttmp+1)%2;
		}else{
			sorttmp=sorttmp%2;
		}
		$("#sort").val(sorttmp);
		$("#productSerachForm").submit();
	}
	
	var sortProduct=function(){
		var sorttmp=parseInt($("#sort").val());
		if(sorttmp>=2&&sorttmp<4){
			sorttmp=(sorttmp+1)%2+2;
		}else{
			sorttmp=sorttmp%2+2;
		}
		$("#sort").val(sorttmp);
		$("#productSerachForm").submit();
	}
	
	var sortCount=function(){
		var sorttmp=parseInt($("#sort").val());
		if(sorttmp>=4&&sorttmp<6){
			sorttmp=(sorttmp+1)%2+4;
		}else{
			sorttmp=sorttmp%2+4;
		}
		$("#sort").val(sorttmp);
		$("#productSerachForm").submit();
	}
	
	var sortVersion=function(){
		var sorttmp=parseInt($("#sort").val());
		if(sorttmp>=6){
			sorttmp=(sorttmp+1)%2+6;
		}else{
			sorttmp=sorttmp%2+6;
		}
		$("#sort").val(sorttmp);
		$("#productSerachForm").submit();
	}
	
	var statistics=function(){
		$("#carCount").html("共"+$("#productSerachForm").next().find("tbody tr").length+"种");
		var spans=$("#productSerachForm").next().find("tbody tr span");
		var count=0;
		for(var i=0;i<spans.length;i++){
			count=count+parseInt($(spans[i]).html());
		}
		$("#numCount").html("共"+count+"个");
	}

	var clearAll=function(){
		$(".form-inline input").val("");
	}
	var tosearch=function(){
		var texts=$(".form-inline input");
		var i=0;
		for(;i<texts.length;i++){
			if(""!=$(texts[i]).val())break;
		}
		if(3==i){
			alert("没有搜索条件");
			return false;
		}
	}
</script>

<div>
<div class="btn-toolbar">
	<form class="form-inline" action="productSerach!serach" method="post">
	  <input type="text" name="carName" value="${carName}" class="input-small" placeholder="车型">
	  <input type="text" name="productName" value="${productName}" class="input-small" placeholder="商品名">
	  <input type="text" name="version" value="${version}" class="input-small" placeholder="型号">
	  <button type="submit" onclick="return tosearch()" class="btn">搜索</button>
	  <button onclick="clearAll()" class="btn">清除</button>
	</form>
 <div class="btn-group">
  </div>
</div>
<div class="well">
    <form id="productSerachForm" action="" method="post">
        <input type="hidden" name="carName" value="${carName}">
        <input type="hidden" name="productName" value="${productName}">
        <input type="hidden" name="version" value="${version}">
        <input type="hidden" name="sort" value="${sort}">
    </form>
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
      	<s:iterator value="#request.products">
	        <tr>
	          <th><s:property value="carStr"/></th>
	          <td><s:property value="name"/></td>
	          <td><s:if test="@com.shihu.util.UserUtil@hasAuthority(\"productAjax!addCount\")"><a href="javascript:void(0)" onclick="addCount(${id},this)"><i class="icon-plus"></i></a></s:if><span> <s:property value="num"/> </span><s:if test="@com.shihu.util.UserUtil@hasAuthority(\"productAjax!subCount\")"><a href="javascript:void(0)" onclick="subCount(${id},this)"><i class="icon-minus"></i></a></s:if></td>
	          <td><s:property value="version"/></td>
	          <td><s:property value="remark"/></td>
	          <td><s:property value="ownerprice"/></td>
	          <!-- <td><s:property value="otherprice"/></td>-->
	          <td>
	              <s:if test="@com.shihu.util.UserUtil@hasAuthority(\"product!toUpdatePage\")"><a onclick="toSearchUpdatePage(${id})" href="javascript:void(0);"><i class="icon-pencil"></i></a></s:if>
	              <s:if test="@com.shihu.util.UserUtil@hasAuthority(\"product!delProduct\")"><a onclick="delProduct(${id})" href="javascript:void(0);" role="button" data-toggle="modal" onclick="return confirm('确定要删除嘛?')"><i class="icon-remove"></i></a></s:if>
	          </td>
	        </tr>
      		
      	</s:iterator>
      </tbody>
      
      <thead>
        <tr>
          <th id="carCount"></th>
          <th></th>
          <th id="numCount"></th>
          <th></th>
          <th></th>
          <th></th>
          <th><button onclick="statistics()" class="btn">统计</button></th>
        </tr>
      </thead>
    </table>
</div>

</div>