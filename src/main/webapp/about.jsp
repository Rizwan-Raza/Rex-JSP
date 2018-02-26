<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:directive.page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About Us | R.E.X</title>
<jsp:directive.include file="views/head.inc.html" />
</head>
<body>
	<jsp:directive.include file="resources/js/detecter.jspf" />
	<jsp:directive.include file="views/nav.jspf" />
	<jsp:directive.include file="views/modals/error.inc.html" />
	<jsp:directive.include file="views/modals/success.inc.html" />
	<jsp:directive.include file="views/modals/image.inc.html" />
	<jsp:directive.include file="views/modals/wait.inc.html" />
	<c:choose>
		<c:when test="${log ne null}">
			<jsp:directive.include file="views/modals/profile-picture.jspf" />
			<jsp:directive.include file="views/modals/change-password.inc.html" />
			<jsp:directive.include file="views/modals/logout.inc.html" />
			<script type="text/javascript" src="resources/js/active.js"></script>
		</c:when>
		<c:otherwise>
			<jsp:directive.include file="views/client/modals/login.inc.html" />
			<jsp:directive.include file="views/client/modals/auth-error.inc.html" />
			<jsp:directive.include
				file="views/client/modals/active-error.inc.html" />

			<jsp:directive.include file="views/admin/modals/login.inc.html" />
			<jsp:directive.include file="views/admin/modals/auth-error.inc.html" />

			<jsp:directive.include file="views/modals/signup.inc.html" />
			<jsp:directive.include file="views/modals/signup-success.inc.html" />
			<jsp:directive.include file="views/modals/forgot-password.inc.html" />

			<script type="text/javascript" src="resources/js/inactive.js"></script>
		</c:otherwise>
	</c:choose>
	<div class="container py-4">
		<h1 class="text-center">About Us</h1>
		<br>
		<div class="media">
			<img class="mr-3 border p-1" src="resources/img/Logo.png" alt="Rex"
				width="100">
			<div class="media-body">
				<h5 class="mt-0">Real Estate eXplorer by Prolog Academy</h5>
				<p>We welcome you in advance for showing your interest with us.
					ProLog Academy is founded by IT industry experts and the faculty is
					expertise in multiple domains. ProLog Academy management and
					teaching faculty includes either working IT professionals/expertise
					in IT industry. All of them are highly experienced in
					designing/development of software solutions in their respective
					domains.</p>
			</div>
		</div>
		<hr>
		<h2 class="text-center my-4">Contributors</h2>
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-sm-6 col-md-6 col-lg-3">
				<div class="card text-center hoverable">
					<img class="card-img-top" src="resources/img/Raza2.jpg"
						alt="Rizwan Raza">
					<div class="card-body">
						<h5 class="card-title">Rizwan Raza</h5>
						<p class="card-text">Designer and Developer</p>
						<a href="https://www.facebook.com/RexTerminous"
							class="btn btn-primary"><i class="fa fa-fw fa-facebook"></i></a>
						<a href="https://twitter.com/rex.terminous"
							class="btn btn-primary"><i class="fa fa-fw fa-twitter"></i></a> <a
							href="https://www.linkedin.com/in/rraza/" class="btn btn-primary"><i
							class="fa fa-fw fa-linkedin"></i></a>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-6 col-lg-3">
				<div class="card text-center hoverable">
					<img class="card-img-top" src="resources/img/Tausif2.jpg"
						alt="Rizwan Raza">
					<div class="card-body">
						<h5 class="card-title">Mohd Tausif Raza</h5>
						<p class="card-text">Guide</p>
						<a href="https://www.facebook.com/tausifraza91"
							class="btn btn-primary"><i class="fa fa-fw fa-facebook"></i></a>
						<a href="https://twitter.com/rex.terminous"
							class="btn btn-primary"><i class="fa fa-fw fa-twitter"></i></a> <a
							href="https://www.linkedin.com/in/rraza/" class="btn btn-primary"><i
							class="fa fa-fw fa-linkedin"></i></a>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<h2 class="text-center my-4">Technologies Used</h2>
		<div id="chartContainer" style="height: 370px; width: 100%;"></div>

		<!-- <div id="accordionTech">
			<div class="card mb-2">
				<div class="card-header p-0" id="headingTechOne">
					<div class="collapsed progress" data-toggle="collapse"
						data-target="#collapseTechOne" aria-expanded="false"
						aria-controls="collapseTechOne">
						<div class="progress-bar" role="progressbar" style="width: 54.5%"
							aria-valuenow="54.5" aria-valuemin="0" aria-valuemax="100">JavaScript
							- 54.5%</div>
						<div class="progress-bar bg-success" role="progressbar"
							style="width: 22.2%" aria-valuenow="22.2" aria-valuemin="0"
							aria-valuemax="100">Java - 22.2%</div>
						<div class="progress-bar bg-danger" role="progressbar"
							style="width: 19.2%" aria-valuenow="19.2" aria-valuemin="0"
							aria-valuemax="100">HTML - 19.2%</div>
						<div class="progress-bar bg-info" role="progressbar"
							style="width: 4.1%" aria-valuenow="4.1 aria-valuemin="
							0"
							aria-valuemax="100">4.1%</div>
					</div>
				</div>
				<div id="collapseTechOne" class="collapse"
					aria-labelledby="headingTechOne" data-parent="#accordionTech">
					<div class="card-body m-0 p-0">
						<table class="table table-sm table-striped m-0">
							<tbody>
								<tr>
									<td>Java
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div> -->
		<div class="table-responsive mt-3">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Language Name</th>
						<th>N0. of files</th>
						<th>Percentage</th>
						<th>Extension</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>HTML</td>
						<td>85</td>
						<td>33.07</td>
						<td>.html, .htm</td>
					</tr>
					<tr>
						<td>Java Server Pages</td>
						<td>46</td>
						<td>17.89</td>
						<td>.jsp, jspf</td>
					</tr>
					<tr>
						<td>Java</td>
						<td>41</td>
						<td>15.95</td>
						<td>.java</td>
					</tr>
					<tr>
						<td>JavaScript</td>
						<td>28</td>
						<td>10.89</td>
						<td>.js</td>
					</tr>
					<tr>
						<td>CSS</td>
						<td>22</td>
						<td>8.56</td>
						<td>.css</td>
					</tr>
					<tr>
						<td>INI</td>
						<td>17</td>
						<td>6.61</td>
						<td>.prefs, .properties, .tld</td>
					</tr>
					<tr>
						<td>XML</td>
						<td>13</td>
						<td>5.05</td>
						<td>.xml</td>
					</tr>
					<tr>
						<td>Markdown</td>
						<td>3</td>
						<td>1.16</td>
						<td>.md</td>
					</tr>
					<tr>
						<td>Maven POM</td>
						<td>2</td>
						<td>0.77</td>
						<td>pom.xml</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4" class="text-center">The above data is based
							on GitHub.</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
	<jsp:directive.include file="views/footer.inc.html" />

	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
	<script>
		window.onload = function() {

			var chart = new CanvasJS.Chart("chartContainer", {
				exportEnabled : true,
				animationEnabled : true,
				legend : {
					cursor : "pointer",
					itemclick : explodePie
				},
				data : [ {
					type : "pie",
					showInLegend : true,
					toolTipContent : "{name}: <strong>{y}%</strong>",
					indexLabel : "{name} - {y}%",
					dataPoints : [ {
						y : 54.5,
						name : "JavaScript"
					}, {
						y : 22.2,
						name : "Java",
						exploded : true
					}, {
						y : 19.2,
						name : "HTML"
					}, {
						y : 4.1,
						name : "CSS"
					} ]
				} ]
			});
			chart.render();
		}

		function explodePie(e) {
			if (typeof (e.dataSeries.dataPoints[e.dataPointIndex].exploded) === "undefined"
					|| !e.dataSeries.dataPoints[e.dataPointIndex].exploded) {
				e.dataSeries.dataPoints[e.dataPointIndex].exploded = true;
			} else {
				e.dataSeries.dataPoints[e.dataPointIndex].exploded = false;
			}
			e.chart.render();

		}
	</script>
	<script type="text/javascript" src="resources/js/common.js"></script>
</body>
</html>