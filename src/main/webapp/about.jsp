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
<style>
dd a span {
	width: 135px;
	display: inline-block
}
</style>
</head>
<body>
	<jsp:directive.include file="resources/js/detecter.jspf" />
	<jsp:directive.include file="views/nav.jspf" />
	<jsp:directive.include file="views/modals/error.inc.html" />
	<jsp:directive.include file="views/modals/success.inc.html" />
	<jsp:directive.include file="views/modals/image.inc.html" />
	<jsp:directive.include file="views/modals/wait.inc.html" />
	<c:choose>
		<c:when test="${log ne null and user ne null}">
			<jsp:directive.include file="views/modals/profile-picture.jspf" />
			<jsp:directive.include file="views/modals/change-password.inc.html" />
			<jsp:directive.include file="views/modals/logout.inc.html" />
			<c:choose>
				<c:when test="${log eq 'client'}">
					<jsp:directive.include file="views/client/modals/edit-profile.jspf" />
					<script type="text/javascript" src="resources/js/client.js"></script>
				</c:when>
				<c:otherwise>
					<jsp:directive.include file="views/admin/modals/edit-profile.jspf" />
					<script type="text/javascript" src="resources/js/admin.js"></script>
				</c:otherwise>
			</c:choose>
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
						alt="Rizwan Raza"
						onclick="showImageModal('Rizwan Raza', 'resources/img/Raza2.jpg')">
					<div class="card-body">
						<h5 class="card-title">Rizwan Raza</h5>
						<p class="card-text">Designer and Developer</p>
						<a href="https://www.facebook.com/RexTerminous"
							class="btn bg-facebook px-2 hoverable" target="_new"><i
							class="fab fa-fw fa-facebook-f"></i></a> <a
							href="https://twitter.com/RexTerminous"
							class="btn bg-twitter px-2 hoverable" target="_new"><i
							class="fab fa-fw fa-twitter"></i></a> <a
							href="https://www.linkedin.com/in/rex-terminous/"
							class="btn bg-linkedin px-2 hoverable" target="_new"><i
							class="fab fa-fw fa-linkedin-in"></i></a> <a
							href="https://plus.google.com/+RizwanRaza365"
							class="btn bg-google-plus px-2 hoverable" target="_new"><i
							class="fab fa-fw fa-google-plus-g"></i></a>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-6 col-lg-3">
				<div class="card text-center hoverable">
					<img class="card-img-top" src="resources/img/Tausif2.jpg"
						alt="Mohd Tausif Raza"
						onclick="showImageModal('Mohd Tausif Raza', 'resources/img/Tausif2.jpg')">
					<div class="card-body">
						<h5 class="card-title">Mohd Tausif Raza</h5>
						<p class="card-text">Guide</p>
						<a href="https://www.facebook.com/tausifraza91"
							class="btn bg-facebook px-2 hoverable" target="_new"><i
							class="fab fa-fw fa-facebook-f"></i></a> <a
							href="https://twitter.com/tausifraza76"
							class="btn bg-twitter px-2 hoverable" target="_new"><i
							class="fab fa-fw fa-twitter"></i></a> <a
							href="https://www.linkedin.com/in/mohd-tausif-raza-a7812256/"
							class="btn bg-linkedin px-2 hoverable" target="_new"><i
							class="fab fa-fw fa-linkedin-in"></i></a> <a
							href="https://plus.google.com/u/0/111293866098336955004"
							class="btn bg-google-plus px-2 hoverable" target="_new"><i
							class="fab fa-fw fa-google-plus-g"></i></a>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<h2 class="text-center my-4">Technologies Used</h2>
		<div id="chartContainer" style="height: 370px; width: 100%;"></div>
		<div class="text-center">The above data is based on GitHub
			Repository.</div>
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
		<hr />
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
						<td>Java</td>
						<td>51</td>
						<td>32.08</td>
						<td>.java</td>
					</tr>
					<tr>
						<td>HTML</td>
						<td>41</td>
						<td>25.79</td>
						<td>.html, .htm</td>
					</tr>
					<tr>
						<td>Java Server Pages</td>
						<td>29</td>
						<td>18.24</td>
						<td>.jsp</td>
					</tr>
					<tr>
						<td>Java Server Pages Fragments</td>
						<td>22</td>
						<td>13.84</td>
						<td>jspf</td>
					</tr>
					<tr>
						<td>JavaScript</td>
						<td>8</td>
						<td>5.03</td>
						<td>.js</td>
					</tr>
					<tr>
						<td>CSS</td>
						<td>3</td>
						<td>1.89</td>
						<td>.css</td>
					</tr>
					<tr>
						<td>INI</td>
						<td>2</td>
						<td>1.26</td>
						<td>.properties</td>
					</tr>
					<tr>
						<td>XML</td>
						<td>1</td>
						<td>1.30</td>
						<td>.xml</td>
					</tr>
					<tr>
						<td>Markdown</td>
						<td>1</td>
						<td>0.63</td>
						<td>.md</td>
					</tr>
					<tr>
						<td>Maven POM</td>
						<td>1</td>
						<td>0.63</td>
						<td>pom.xml</td>
					</tr>
					<tr>
						<td>Tag Library Descriptor</td>
						<td>1</td>
						<td>0.63</td>
						<td>.tld</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4" class="text-center">The above data is based
							on GitHub Repository and Server File System.</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<hr />
		<dl class="row">
			<dt class="col-sm-3">Java Technologies</dt>
			<dd class="col-sm-9">
				<p>
					JavaSE (including Collections, Serialization and Beans)<br>Java
					Servlets<br>Java Server Pages (JSP)<br>Java Server Pages
					Fragments (JSPF)<br>Java Database Connectivity (JDBC )<br>JavaServer
					Pages Standard Tag Library (JSTL)<br>Expression Language (EL)<br>JavaMail
					API
				</p>
			</dd>

			<dt class="col-sm-3">JavaScript Technologies</dt>
			<dd class="col-sm-9">
				<p>
					JavaScript Document Object Model (DOM)<br>JavaScript Browser
					Object Model (BOM)<br>Asynchronous JavaSript and XML (AJAX)<br>jQuery
					and jQuery UI<br>JavaScript Object Notation (JSON)
				</p>
			</dd>

			<dt class="col-sm-3">Web Technologies</dt>
			<dd class="col-sm-9">
				<p>
					HTML5 with Canvas and Semantics Tags<br>CSS3 with Animations
					and Media Query<br>Bootstrap 4.1<br>Font Awesome 5
				</p>
			</dd>

			<dt class="col-sm-3 text-truncate">Persistence and Hosting</dt>
			<dd class="col-sm-9">
				<p>
					eXtensible Markup Language (XML)<br>OpenShift Hosting Server
					Starter 3 Pro<br>MySQL Database 5.7<br> <a href="mysql/">JSPMyAdmin</a>
					(Self Made from GitHub's OpenSource Code)<br> <a
						href="props.jsp">Properties Files</a> for Credentials and
					Initializers<br>Google Gmail IMAP Server
				</p>
			</dd>
			<dt class="col-sm-3 text-truncate">Dev. and Build Tools</dt>
			<dd class="col-sm-9">
				<p>
					Java Development Kit 9 (JDK 9)<br>Maven Build Tool<br>Eclipse
					Oxygen IDE<br>MySQL WorkBench<br>GitHub Repository<br>Apache
					Tomcat Server 9
				</p>
			</dd>
			<dt class="col-sm-3 text-truncate">References and Helps</dt>
			<dd class="col-sm-9">
				<p>
					<a href="https://stackoverflow.com/" target="_new"><span>StackOverflow</span>
						https://stackoverflow.com/</a><br> <a
						href="https://www.w3schools.com/" target="_new"><span>W3Schools</span>
						https://www.w3schools.com/</a><br> <a href="https://github.com/"
						target="_new"><span>Github</span> https://github.com/</a><br>
					<a href="https://codepen.io/" target="_new"><span>CodePen</span>
						https://codepen.io/</a><br> <a href="https://bootsnipp.com/"
						target="_new"><span>Bootsnipp</span> https://bootsnipp.com/</a><br>
					<a href="https://docs.oracle.com/en/"><span>Oracle Help
							Center</span> https://docs.oracle.com/en/</a><br> <a
						href="https://getbootstrap.com/docs/4.0/getting-started/introduction/"
						target="_new"><span>Bootstrap Docs</span>
						https://getbootstrap.com/docs/4.0/</a><br> <a
						href="https://www.magicbricks.com/" target="_new"><span>MagicBricks</span>
						https://www.magicbricks.com/</a> <br> <a
						href="https://www.commonfloor.com/" target="_new"><span>commonfloor</span>
						https://www.commonfloor.com/ </a> <br> <a
						href="http://rex.esy.es/" target="_new"><span>R.E.X</span>
						http://rex.esy.es/</a> <br> <a href="http://rex.esy.es/pl/"
						target="_new"><span>ProLog Academy</span>
						http://rex.esy.es/pl/</a>
				</p>
			</dd>
			<dt class="col-sm-3 text-truncate">GitHub Source Code</dt>
			<dd class="col-sm-9">
				<a href="https://github.com/Rizwan-Raza/Rex-JSP" target="_new">https://github.com/Rizwan-Raza/Rex-JSP</a>
			</dd>
		</dl>
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
						y : 60.8,
						name : "Java",
						exploded : true
					}, {
						y : 21.3,
						name : "HTML"
					}, {
						y : 12.8,
						name : "JavaScript"
					}, {
						y : 5.1,
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