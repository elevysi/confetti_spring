<%@ include file="../../layout/taglib.jsp"%>

<c:url var="baseUrl" value="/public/plays?page=" />
<tiles:putAttribute name="basePaginationUrl" value="${baseUrl}" cascade="true"/>

<!-- Blog Thumb v4 -->
				<div class="margin-bottom-50">
					<h2 class="title-v4">Plays Confettis</h2>
					<%@ include file="../nullLayouts/indexplaysajax.jsp"%>	
					
				</div>
				<!-- End Blog Thumb v4 -->

<tiles:insertAttribute name="pagination" ignore="true"/>
