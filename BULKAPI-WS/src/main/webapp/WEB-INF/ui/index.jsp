<%@page import="sn.intouch.gu.api.ejb.services.ParameterService"%>
<%@page import="sn.intouch.gu.api.ejb.utils.EJBRegistry"%>
<%@page import="sn.intouch.gu.api.ejb.utils.JNDIUtils"%>
<%@page import="sn.intouch.gu.api.ejb.entities.Parametre"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="IE=edge">
<title>BULKAPI UI</title>
<link rel="icon" type="image/png"
	href="<c:url value='/resources/images/favicon-32x32.png'/>" sizes="32x32" />
<link rel="icon" type="image/png"
	href="<c:url value='/resources/mages/favicon-16x16.png'/>"sizes="16x16" />
<link href="<c:url value='/resources/css/typography.css'/>" media='screen'
	rel='stylesheet' type='text/css' />
<link href="<c:url value='/resources/css/reset.css'/>" media='screen' rel='stylesheet'
	type='text/css' />
<link href="<c:url value='/resources/css/screen.css'/>" media='screen' rel='stylesheet'
	type='text/css' />
<link href="<c:url value='/resources/css/reset.css'/>" media='print' rel='stylesheet'
	type='text/css' />
<link href="<c:url value='/resources/css/print.css'/>" media='print' rel='stylesheet'
	type='text/css' />

<script src="<c:url value='/resources/lib/object-assign-pollyfill.js'/>"
	type='text/javascript'></script>
<script src="<c:url value='/resources/lib/jquery-1.8.0.min.js'/>" type='text/javascript'></script>
<script src="<c:url value='/resources/lib/jquery.slideto.min.js'/>"
	type='text/javascript'></script>
<script src="<c:url value='/resources/lib/jquery.wiggle.min.js'/>" type='text/javascript'></script>
<script src="<c:url value='/resources/lib/jquery.ba-bbq.min.js'/>" type='text/javascript'></script>
<script src="<c:url value='/resources/lib/handlebars-4.0.5.js'/>" type='text/javascript'></script>
<script src="<c:url value='/resources/lib/lodash.min.js'/>" type='text/javascript'></script>
<script src="<c:url value='/resources/lib/backbone-min.js'/>" type='text/javascript'></script>
<script src="<c:url value='/resources/swagger-ui.js'/>" type='text/javascript'></script>
<script src="<c:url value='/resources/lib/highlight.9.1.0.pack.js'/>"
	type='text/javascript'></script>
<script src="<c:url value='/resources/lib/highlight.9.1.0.pack_extended.js'/>"
	type='text/javascript'></script>
<script src="<c:url value='/resources/lib/jsoneditor.min.js'/>" type='text/javascript'></script>
<script src="<c:url value='/resources/lib/marked.js'/>" type='text/javascript'></script>
<script src="<c:url value='/resources/lib/swagger-oauth.js'/>" type='text/javascript'></script>

<!-- Some basic translations -->
<!-- <script src='lang/translator.js' type='text/javascript'></script> -->
<!-- <script src='lang/ru.js' type='text/javascript'></script> -->
<!-- <script src='lang/en.js' type='text/javascript'></script> -->

<script type="text/javascript">
	$(function() {
		var url = window.location.search.match(/url=([^&]+)/);
		if (url && url.length > 1) {
			url = decodeURIComponent(url[1]);
		} else {
			<% String urlSer = "";
			ParameterService parameterService = (ParameterService) JNDIUtils
					.lookUpEJB(EJBRegistry.ParameterServiceBean);
			Parametre parametre=parameterService.getParameterByCode("URL_DOC_DIST_BULK");
			if (parametre==null){
				urlSer="http://localhost:9090/bo/bulk/api/v1";
			}else if (parametre.getPrmStringValue()==null){
				urlSer="http://localhost:9090/bo/bulk/api/v1";
			}else{
				urlSer=parametre.getPrmStringValue();
			}
			%>
			console.log("URL_DOC_DIST_BULK: "+"<%=urlSer%>");
			url = "<%=urlSer+"/docs"%>";
		}

		hljs.configure({
			highlightSizeThreshold : 5000
		});

		// Pre load translate...
		if (window.SwaggerTranslator) {
			window.SwaggerTranslator.translate();
		}
		window.swaggerUi = new SwaggerUi(
				{
					url : url,
					dom_id : "swagger-ui-container",
					supportedSubmitMethods : [ 'get', 'post', 'put', 'delete',
							'patch' ],
					onComplete : function(swaggerApi, swaggerUi) {
						if (typeof initOAuth == "function") {
							initOAuth({
								clientId : "your-client-id",
								clientSecret : "your-client-secret-if-required",
								realm : "your-realms",
								appName : "your-app-name",
								scopeSeparator : " ",
								additionalQueryStringParams : {}
							});
						}

						if (window.SwaggerTranslator) {
							window.SwaggerTranslator.translate();
						}
					},
					onFailure : function(data) {
						log("Unable to Load SwaggerUI");
					},
					docExpansion : "none",
					jsonEditor : false,
					defaultModelRendering : 'schema',
					showRequestHeaders : false,
					showOperationIds : false
				});

		window.swaggerUi.load();

		function log() {
			if ('console' in window) {
				console.log.apply(console, arguments);
			}
		}
	});
</script>
</head>

<body class="swagger-section">
	<div id='header'>
		<div class="swagger-ui-wrap">
			<a id="logo" href="http://www.hubsocial.org/"><img class="logo__img"
				alt="swagger" height="30" width="30" src="<c:url value="/resources/images/logo_small.png" />"/><span
				class="logo__title">InTouch</span></a>
			<form id='api_selector'>
				<div class='input'>
					<input placeholder="http://example.com/api" id="input_baseUrl"
						name="baseUrl" type="text" />
				</div>
				<div id='auth_container'></div>
				<div class='input'>
					<a id="explore" class="header__btn" href="#" data-sw-translate>Explore</a>
				</div>
			</form>
		</div>
	</div>

	<div id="message-bar" class="swagger-ui-wrap" data-sw-translate>&nbsp;</div>
	<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
</body>
</html>
