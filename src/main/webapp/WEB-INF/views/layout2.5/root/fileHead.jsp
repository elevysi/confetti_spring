<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../../layout/taglib.jsp"%>
<meta charset="utf-8">

<c:choose>
	<c:when test="${not empty  pageDescription}">
		<meta name="description" content="<c:out value='${pageDescription}' />" />
		
	</c:when>
	<c:otherwise>
		<meta name="description" content="Blog, Web, Development, Technology, Music, LifeStyle, Motivation">
	</c:otherwise>
</c:choose>

  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

  <!-- Favicon -->
  <link rel="shortcut icon" href="../../favicon.ico">
  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
  <!-- CSS Global Compulsory -->
  <link rel="stylesheet" href="<c:url value='/resources_2_5/vendor/bootstrap/bootstrap.min.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources_2_5/vendor/icon-awesome/css/font-awesome.min.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources_2_5/vendor/icon-line-pro/style.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources_2_5/vendor/icon-hs/style.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources_2_5/vendor/animate.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources_2_5/vendor/hs-megamenu/src/hs.megamenu.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources_2_5/vendor/hamburgers/hamburgers.min.css'/>">

  <!-- CSS Unify -->
  <link rel="stylesheet" href="<c:url value='/resources_2_5/css/unify-core.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources_2_5/css/unify-components.css'/>">
  <link rel="stylesheet"href=" <c:url value='/resources_2_5/css/unify-globals.css'/>">

  <!-- CSS Customization -->
  <link rel="stylesheet" href="<c:url value='/resources_2_5/css/custom.css'/>">
