<%@ include file="../../layout/taglib.jsp"%>

<div class="row">
	<div class="panel panel-profile">
		<div class="panel-heading overflow-h">
			<h2 class="panel-title heading-sm pull-left"><i class="fa fa-comments-o"></i> Messages</h2>
			<a href="<c:url value='/conversations/add/'/>" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-xs pull-right">New Message</a>
		</div>
		<div id="scrollbar4" class="panel-body no-padding mCustomScrollbar _mCS_5 mCS-autoHide" data-mcs-theme="minimal-dark" style="position: relative; overflow: visible;"><div id="mCSB_5" class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside" tabindex="0"><div id="mCSB_5_container" class="mCSB_container" style="position: relative; top: 0px; left: 0px;" dir="ltr">
		
		<c:forEach items="${conversations}" var="conversation">
			<div class="comment">
				<fmt:parseNumber var="countPic" value="0" />
				<fmt:parseNumber var="starter" value="0" />
				
					<c:forEach items="${conversation.correspondents}" var="correspondent">
						<c:if test="${countPic == starter}">
							<c:if test="${not empty correspondent.owningCorrespondentProfile.profilePicture && fn:length(correspondent.owningCorrespondentProfile.profilePicture) >= 1}">
								<img src="<c:url value='/uploads/download?key=${correspondent.owningCorrespondentProfile.profilePicture.iterator().next().keyIdentification}'/>" alt="" class="mCS_img_loaded rounded-x">
								
							</c:if>
						</c:if>
						<fmt:parseNumber var="count" value="${countPic + 1}" />
					</c:forEach>
				
				
				
				<div class="overflow-h">
					<strong>
						<fmt:parseNumber var="count" value="0" />
						<c:if test="${not empty conversation.correspondents && not empty conversation.correspondents.iterator().hasNext()}">
						<c:forEach items="${conversation.correspondents}" var="correspondent">
							<c:if test="${correspondent.owningCorrespondentProfile.name !=  actingProfile.name}">
								<c:if test="${count > 0}">
									<c:out value=", "></c:out>
								</c:if>
								<a href="<c:url value="/profile?username=${correspondent.owningCorrespondentProfile.name}" />"><c:out value="${correspondent.owningCorrespondentProfile.name}"></c:out></a>
							</c:if>
							<fmt:parseNumber var="count" value="${count + 1}" />
						</c:forEach>
							
						</c:if>
						<small class="pull-right"> 25m</small>
					</strong>
					
					<p>
					
						<c:if test="${not empty conversation.conversationMessages && not empty conversation.conversationMessages.iterator().hasNext()}">
							<c:out value="${conversation.conversationMessages.iterator().next().message}" escapeXml="false"/>
						</c:if>
					
					</p>
					<ul class="list-inline comment-list">
						<li><i class="fa fa-comments"></i> <a href="<c:url value='/conversations/view/${conversation.uuid}/' />">Read</a></li>
						<li><i class="fa fa-trash"></i> <a href="<c:url value='/conversations/view/${conversation.uuid}/' />">Delete</a></li>
					</ul>
				</div>
			</div>
		</c:forEach>
		
	</div></div><div id="mCSB_5_scrollbar_vertical" class="mCSB_scrollTools mCSB_5_scrollbar mCS-minimal-dark mCSB_scrollTools_vertical" style="display: block;"><div class="mCSB_draggerContainer"><div id="mCSB_5_dragger_vertical" class="mCSB_dragger" style="position: absolute; min-height: 50px; display: block; height: 162px; max-height: 286px; top: 0px;" oncontextmenu="return false;"><div class="mCSB_dragger_bar" style="line-height: 50px;"></div></div><div class="mCSB_draggerRail"></div></div></div></div>
		</div>
</div>