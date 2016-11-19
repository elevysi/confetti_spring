<%@ include file="../taglib.jsp"%>
<div class="row">
	<c:if test="${sessionMessage.msgText != null}">
		<div class="${sessionMessage.msgClass}">
			<button class="close" aria-hidden="true" data-dismiss="alert"
				type="button">×</button>
			<c:out value="${sessionMessage.msgText}"></c:out>
		</div>
	</c:if>
	<tiles:insertAttribute name="bodyContent" />
</div>
