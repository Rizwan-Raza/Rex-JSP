<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<jsp:directive.include file="../views/modals/seller.jspf" />

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
				<div id="lPropCarousel-${prop.propID}" class="carousel slide"
					data-ride="carousel">
					<div class="overlays">

						<div class="user-dp user-dp-xl image-triggerer mt-4"
							style="background-image: url('${prop.seller.src.replace('\\', '/') }');"
							onclick="showSeller('${prop.seller.fname }','${prop.seller.lname }', '${prop.seller.email }', '${prop.seller.gender }', '${prop.seller.contact }', '${prop.seller.src }', 'Seller')">
						</div>
						<div class="container search py-3 text-light">
							<h1>${prop.title }</h1>
							<p class="mb-0">${prop.address.street },${prop.address.town },
								${prop.address.city }, ${prop.address.state }</p>
							<small class="text-muted">Posted: <jsp:text>
									<![CDATA[<span data-toggle="tooltip" data-placement="auto" title="]]>
								</jsp:text> <fmt:formatDate value="${prop.time }"
									pattern="MMM d, y 'at' h:mm a" /> <jsp:text>
									<![CDATA[">]]>
								</jsp:text>${cf:daysUntilToday(prop.time)} <jsp:text>
									<![CDATA[</span>]]>
								</jsp:text></small>
							<c:if test="${prop.edit ne null}">
								<br>
								<small class="text-muted">Edited: <jsp:text>
										<![CDATA[<span data-toggle="tooltip" data-placement="auto" title="]]>
									</jsp:text> <fmt:formatDate value="${prop.edit }"
										pattern="MMM d, y 'at' h:mm a" /> <jsp:text>
										<![CDATA[">]]>
									</jsp:text>${cf:daysUntilToday(prop.edit)} <jsp:text>
										<![CDATA[</span>]]>
									</jsp:text></small>
							</c:if>
						</div>
					</div>
					<ol class="carousel-indicators">
						<c:forEach begin="0" end="${prop.images.size() }" varStatus="loop">
							<c:if test="${loop.index < prop.images.size() }">
								<li data-target="
								#lPropCarousel-${prop.propID}"
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
						href="#lPropCarousel-${prop.propID}" role="button"
						data-slide="prev"> <span class="carousel-control-prev-icon"
						aria-hidden="true"></span> <span class="sr-only">Previous</span>
					</a> <a class="carousel-control-next"
						href="#lPropCarousel-${prop.propID}" role="button"
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
					<div class="card hoverable bg-light p-4 mb-4">
						<h2 class="card-title">Area and Price</h2>
						<hr />
						<div class="card-body pb-0">
							<dl class="row">
								<dt class="col-sm-3">Covered Area</dt>
								<dd class="col-sm-9">${prop.propArea }</dd>
								<dt class="col-sm-3">Land Area</dt>
								<dd class="col-sm-9">${prop.land }</dd>
								<dt class="col-sm-3">Price</dt>
								<dd class="col-sm-9">&#8377; ${prop.price }</dd>
								<dt class="col-sm-3">Price Tag Visible</dt>
								<dd class="col-sm-9">${prop.priceDisplay eq '0' ? 'No' : 'Yes'}</dd>
								<dt class="col-sm-3">Ready to Move</dt>
								<dd class="col-sm-9">${prop.available eq '0' ? 'No' : 'Yes'}</dd>
							</dl>
						</div>
					</div>
					<div class="card hoverable bg-light p-4 mb-4">
						<h2 class="card-title">Amenities</h2>
						<hr />
						<div class="card-body pb-0">
							<dl class="row">
								<dt class="col-sm-3">Internet / Wi-Fi</dt>
								<dd class="col-sm-9">${prop.amens.contains('net') ? 'Yes' : 'No'}</dd>
								<dt class="col-sm-3">Air-Conditioned</dt>
								<dd class="col-sm-9">${prop.amens.contains('air') ? 'Yes' : 'No'}</dd>
								<dt class="col-sm-3">RO Water System</dt>
								<dd class="col-sm-9">${prop.amens.contains('ro') ? 'Yes' : 'No'}</dd>
								<dt class="col-sm-3">Gas Supply</dt>
								<dd class="col-sm-9">${prop.amens.contains('gas') ? 'Yes' : 'No'}</dd>
								<dt class="col-sm-3">Water Supply</dt>
								<dd class="col-sm-9">${prop.amens.contains('water') ? 'Yes' : 'No'}</dd>
							</dl>
						</div>
					</div>
					<div class="card hoverable bg-light p-4 mb-4">
						<h2 class="card-title">Distance from Key Facility</h2>
						<hr />
						<div class="card-body pb-0">
							<dl class="row">
								<dt class="col-sm-3">Hospital</dt>
								<dd class="col-sm-9">${prop.hospital}</dd>
								<dt class="col-sm-3">School</dt>
								<dd class="col-sm-9">${prop.school}</dd>
								<dt class="col-sm-3">Railway Station</dt>
								<dd class="col-sm-9">${prop.rail}</dd>
							</dl>
						</div>
					</div>
					<div class="card hoverable bg-light p-4 mb-4">
						<h2 class="card-title">Property Snapshot</h2>
						<hr />
						<div class="card-body pb-0">
							<dl class="row">
								<dt class="col-sm-3">Available Units</dt>
								<dd class="col-sm-9">${prop.units}</dd>
								<c:if test="${prop.floor ne '-5' }">
									<dt class="col-sm-3">Floor Number</dt>
									<dd class="col-sm-9">
										<c:choose>
											<c:when test="${prop.floor eq '0' }">Ground Floor</c:when>
											<c:otherwise>${prop.floor}<c:choose>
													<c:when test="${prop.floor % 10 eq 1 }">st</c:when>
													<c:when test="${prop.floor % 10 eq 2 }">nd</c:when>
													<c:when test="${prop.floor % 10 eq 3 }">rd</c:when>
													<c:otherwise>th</c:otherwise>
												</c:choose> Floor
										</c:otherwise>
										</c:choose>
									</dd>
								</c:if>
								<dt class="col-sm-3">Total Floors</dt>
								<dd class="col-sm-9">${prop.totalFloors}</dd>
								<c:if test="${not empty prop.desc}">
									<dt class="col-sm-3">Brief Description</dt>
									<dd class="col-sm-9">${prop.desc}</dd>
								</c:if>
								<c:if test="${not empty prop.tnc}">
									<dt class="col-sm-3">Terms and Conditions</dt>
									<dd class="col-sm-9">${prop.tnc}</dd>
								</c:if>
							</dl>
						</div>
					</div>
					<div class="card hoverable bg-light p-4 mb-4">
						<h2 class="card-title">Images</h2>
						<hr />
						<div class="card-body pb-0 row" id="prop-img-holder">
							<c:forEach var="image" varStatus="imageStatus"
								items="${prop.images}">
								<div
									class="col-${fn:substringBefore(12/prop.images.size(), '.') }">
									<div class="buy-prop-image image-triggerer hoverable"
										style="background-image: url('${image}')"
										onclick="showImageModal('${prop.title }', '${image}')"></div>
								</div>
							</c:forEach>
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