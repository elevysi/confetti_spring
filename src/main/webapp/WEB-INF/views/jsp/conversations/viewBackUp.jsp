<%@ include file="../../layout/taglib.jsp"%>

<h2>Conversation</h2>

<fmt:parseNumber var="starter" value="0" />

<div class="row">
	<div class="panel panel-profile">
		<div class="panel-heading overflow-h">
			<h2 class="panel-title heading-sm pull-left"><i class="fa fa-comments-o"></i> Messages</h2>
			<a href="<c:url value='/conversations/add/?uuid=${conversation.uuid}'/>" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-xs pull-right">New Message</a>
		</div>
		<div id="scrollbar4" class="panel-body no-padding mCustomScrollbar _mCS_5 mCS-autoHide" data-mcs-theme="minimal-dark" style="position: relative; overflow: visible;">
			<div id="mCSB_5" class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside" tabindex="0">
				<div id="mCSB_5_container" class="mCSB_container" style="position: relative; top: 0px; left: 0px;" dir="ltr">
						<c:forEach items="${conversation.conversationMessages}" var="message">
							<div class="comment">
									<c:if test="${not empty message.profile.profilePicture && fn:length(message.profile.profilePicture) >= 1}">
										<img src="<c:url value='/uploads/download?key=${message.profile.profilePicture.iterator().next().keyIdentification}'/>" alt="" class="mCS_img_loaded rounded-x">
									</c:if>
								
								<div class="overflow-h">
									<strong>
										<a href="<c:url value="/profile?username=${message.profile.name}" />"><c:out value="${message.profile.name}"></c:out></a>
										<small class="pull-right">
											
											<ul class="list-inline comment-list">
												<li>25m</li>
												<li><i class="fa fa-comments"></i> <a href="">Hide</a></li>
												<li><i class="fa fa-trash"></i> <a href="">Report</a></li>
											</ul>
										
										</small>
									</strong>
									
									<p>
										<c:out value="${message.message}" escapeXml="false"/>
									</p>
									
								</div>
							</div>
						</c:forEach>
				</div>
			</div>
			<div id="mCSB_5_scrollbar_vertical" class="mCSB_scrollTools mCSB_5_scrollbar mCS-minimal-dark mCSB_scrollTools_vertical" style="display: block;"><div class="mCSB_draggerContainer">
				<div id="mCSB_5_dragger_vertical" class="mCSB_dragger" style="position: absolute; min-height: 50px; display: block; height: 162px; max-height: 286px; top: 0px;" oncontextmenu="return false;">
					<div class="mCSB_dragger_bar" style="line-height: 50px;"></div>
				</div>
				<div class="mCSB_draggerRail"></div></div>
			</div>
		</div>
	</div>
</div>