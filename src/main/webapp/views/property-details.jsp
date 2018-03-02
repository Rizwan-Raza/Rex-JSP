<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/functions" prefix="cf"%>
<%@page import="com.rex.model.CommonModel"%>
<jsp:directive.page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
<!DOCTYPE html>
<html>
<head>
<title>Property Details | R.E.X</title>
<base href="../">
<jsp:directive.include file="head.inc.html" /></head>
<style>
.carousel .user-dp {
	left: 50%;
	margin-left: -100px;
}
</style>
<body class="bg-default">
	<%
		if (request.getAttribute("prop_id") != null) {
	%>
	<jsp:directive.include file="../resources/js/detecter.jspf" />
	<jsp:directive.include file="nav.jspf" />
	<jsp:directive.include file="modals/error.inc.html" />
	<jsp:directive.include file="modals/success.inc.html" />
	<jsp:directive.include file="modals/image.inc.html" />
	<jsp:directive.include file="modals/wait.inc.html" />
	<c:choose>
		<c:when test="${log ne null}">
			<jsp:directive.include file="modals/profile-picture.jspf" />
			<jsp:directive.include file="modals/change-password.inc.html" />
			<jsp:directive.include file="modals/logout.inc.html" />
			<script type="text/javascript" src="resources/js/active.js"></script>
		</c:when>
		<c:otherwise>
			<jsp:directive.include file="client/modals/login.inc.html" />
			<jsp:directive.include file="client/modals/auth-error.inc.html" />
			<jsp:directive.include file="client/modals/active-error.inc.html" />

			<jsp:directive.include file="admin/modals/login.inc.html" />
			<jsp:directive.include file="admin/modals/auth-error.inc.html" />

			<jsp:directive.include file="modals/signup.inc.html" />
			<jsp:directive.include file="modals/signup-success.inc.html" />
			<jsp:directive.include file="modals/forgot-password.inc.html" />

			<script type="text/javascript" src="resources/js/inactive.js"></script>
		</c:otherwise>
	</c:choose>
	<%
		request.setAttribute("props", new CommonModel()
					.getProps(Integer.parseInt((String) request.getAttribute("prop_id")), "SINGLE"));
	%>
	<c:choose>
		<c:when test="${empty props }">
			<div class=" alert alert-danger">
				<i class="fa fa-5x fa-exclamation-triangle text-center d-block"></i>
				<h4 class="alert-heading text-center">Sorry, Something went
					wrong</h4>
				<p class="text-center">
					Try to reload? <a href="javascript:window.location.reload()"
						class="alert-link">Reload Now</a>.
				</p>
			</div>
		</c:when>
		<c:otherwise>
			<c:forEach var="prop" varStatus="propStatus" items="${props}">
				<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<div class="overlays">

						<div class="user-dp user-dp-xl image-triggere mt-4"
							style="background-image: url('${prop.seller.src.replace('\\', '/') }');"
							onclick="showImageModal('${prop.seller.fullname }', '${prop.seller.src.replace('\\', '/') }')">
						</div>
						<div class="container search pt-3 text-light">
							<h1>${prop.title }</h1>
							<p>${prop.address.street },${prop.address.town },
								${prop.address.city }, ${prop.address.state }</p>
						</div>
					</div>
					<ol class="carousel-indicators">
						<c:forEach begin="0" end="${prop.images.size() }" varStatus="loop">
							<c:if test="${loop.index < prop.images.size() }">
								<li data-target="
								#propCarousel-${prop.propID}"
									data-slide-to="${loop.index}"
									${loop.index eq 0 ? 'class=\"active\"' : ''}></li>
							</c:if>
						</c:forEach>
					</ol>
					<div class="carousel-inner">
						<c:forEach var="image" varStatus="imageStatus"
							items="${prop.images}">
							<jsp:text>
								<![CDATA[<div class="carousel-item]]>
							</jsp:text>
							<c:if test="${imageStatus.first }"> active</c:if>" onclick="showImageModal('${prop.title}', '${image}')"
									<jsp:text>
								<![CDATA[ style="background-image: url(']]>${image}<![CDATA[');"></div>]]>
							</jsp:text>
						</c:forEach>
					</div>
					<a class="carousel-control-prev"
						href="#propCarousel-${prop.propID}" role="button"
						data-slide="prev"> <span class="carousel-control-prev-icon"
						aria-hidden="true"></span> <span class="sr-only">Previous</span>
					</a> <a class="carousel-control-next"
						href="#propCarousel-${prop.propID}" role="button"
						data-slide="next"> <span class="carousel-control-next-icon"
						aria-hidden="true"></span> <span class="sr-only">Next</span>
					</a>
				</div>
				<div class="container py-4">
					<div class="card hoverable bg-light p-4 mb-4">
						<h2 class="card-title">Basic Details</h2>
						<hr />
						<div class="card-body pb-0">
							<dl class="row">
								<dt class="col-sm-3">Property Title</dt>
								<dd class="col-sm-9">${prop.title }</dd>
								<dt class="col-sm-3">Property Type</dt>
								<dd class="col-sm-9">${prop.propType }</dd>
								<dt class="col-sm-3">Transaction Type</dt>
								<dd class="col-sm-9">${prop.tranType }</dd>
							</dl>
						</div>
					</div>
					<div class="card hoverable bg-light p-4 mb-4">
						<h2 class="card-title">Location</h2>
						<hr />
						<div class="card-body pb-0">
							<dl class="row">
								<dt class="col-sm-3">Street Number</dt>
								<dd class="col-sm-9">${prop.address.street }</dd>
								<dt class="col-sm-3">Town</dt>
								<dd class="col-sm-9">${prop.address.town }</dd>
								<dt class="col-sm-3">City</dt>
								<dd class="col-sm-9">${prop.address.city }</dd>
								<dt class="col-sm-3">State</dt>
								<dd class="col-sm-9">${prop.address.state }</dd>
							</dl>
						</div>
					</div>
					<div class="card hoverable bg-light p-4 mb-4">
						<h2 class="card-title">Features</h2>
						<hr />
						<div class="card-body pb-0">
							<dl class="row">
								<dt class="col-sm-3">BHK / Number of Bedrooms</dt>
								<dd class="col-sm-9">${prop.bhk }</dd>
								<dt class="col-sm-3">Number of Bathrooms</dt>
								<dd class="col-sm-9">${prop.bath }</dd>
								<dt class="col-sm-3">Age of Construction</dt>
								<dd class="col-sm-9">${prop.age }</dd>
								<dt class="col-sm-3">Furnished</dt>
								<dd class="col-sm-9">${prop.furnished eq '0'? 'No' : 'Yes' }</dd>
							</dl>
						</div>
					</div>
					<div class="card depth bg-light p-4 mb-4">
						<h2 class="card-title">Area and Price</h2>
						<hr />
						<div class="card-body pb-0">
							<dl class="row">
								<dt class="col-sm-3">Covered Area</dt>
								<dd class="col-sm-9">${prop.propArea }</dd>
								<dt class="col-sm-3">Land Area</dt>
								<dd class="col-sm-9">${prop.land }</dd>
								<dt class="col-sm-3">Price</dt>
								<dd class="col-sm-9">
									<i class="fa fa-fw fa-rupee-sign"></i>${prop.price }</dd>
								<dt class="col-sm-3">Price Tag Visible</dt>
								<dd class="col-sm-9">${prop.priceDisplay eq '0' ? 'No' : 'Yes'}</dd>
								<dt class="col-sm-3">Ready to Move</dt>
								<dd class="col-sm-9">${prop.available eq '0' ? 'No' : 'Yes'}</dd>
							</dl>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<jsp:directive.include file="footer.inc.html" />
	<script type="text/javascript" src="resources/js/common.js"></script>
	<%
		} else {
	%>
	<%
		response.sendError(404);
		}
	%>

</body>
</html>