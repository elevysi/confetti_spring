<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../../layout/taglib.jsp" %>
	<h3>List of Posts</h3>
	<hr />
	
	<table class="table">
		<tr>
			<th>Id</th>
			<th>Title</th>
			<th>Description</th>
			<th>Actions</th>
		</tr>
		
		<c:forEach items="${posts}" var="post">
			<tr>
				<td>
					${post.id}
				</td>
				<td>
					${post.title}
				</td>
				<td>
					${post.description}
				</td>
				<td>
					<a href="<spring:url value='/posts/edit' />" class="btn btn-xs btn-primary"><i class="glyphicon glyphicon-pencil"></i> Edit</a>
					<a href="<spring:url value='/posts/delete' />" class="btn btn-xs btn-danger"><i class="glyphicon glyphicon-trash"></i> Remove</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<hr/>
	<a href="<spring:url value='/blog/posts/add' />" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-plus"></i> New Post</a>