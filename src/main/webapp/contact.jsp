<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:directive.page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Us | R.E.X</title>
<jsp:directive.include file="views/head.inc.html" />
<style type="text/css">
/* Rating Star Widgets Style */
.rating-stars ul {
	list-style-type: none;
	padding: 0;
	-moz-user-select: none;
	-webkit-user-select: none;
}

.rating-stars ul>li.star {
	display: inline-block;
}

/* Idle State of the stars */
.rating-stars ul>li.star>i.fa {
	font-size: 1.5em; /* Change the size of the stars */
	color: #ccc; /* Color on idle state */
}

/* Hover state of the stars */
.rating-stars ul>li.star.hover>i.fa {
	color: #FFCC36;
}

/* Selected state of the stars */
.rating-stars ul>li.star.selected>i.fa {
	color: #FF912C;
}

ul.social { /* UI - Social Icons List */
	list-style: none;
	padding: 0;
}

ul.social li { /* UI - Social Icons List Elements */
	display: inline;
	margin: 0 5px;
}
/* footer social icons */
.social li a { /* UI - Social Icons Circle Style */
	display: inline-block;
	position: relative;
	margin: 0 auto 0 auto;
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	border-radius: 50%;
	text-align: center;
	width: 50px;
	height: 50px;
	font-size: 20px;
	/*background-color: #800000;*/
}

.social li i { /* UI - Circle Social Icons */
	margin: 0;
	line-height: 50px;
	text-align: center;
}

.social li a:hover /*, .triggeredHover*/ {
	/* UI - Social Icons */
	-moz-transform: rotate(360deg);
	-webkit-transform: rotate(360deg);
	-ms-transform: rotate(360deg);
	transform: rotate(360deg);
	-webkit-transition: all 0.8s;
	-moz-transition: all 0.8s;
	-o-transition: all 0.8s;
	-ms-transition: all 0.8s;
	transition: all 0.8s;
}

.social a { /* UI - Circle Social Icons */
	color: #fff;
	-webkit-transition: all 0.8s;
	-moz-transition: all 0.8s;
	-o-transition: all 0.8s;
	-ms-transition: all 0.8s;
	transition: all 0.8s;
}
/* End of Footer Social Icons */
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
		<h1 class="text-center">Contact Us</h1>
		<br>
		<div class="row">
			<div class="col-sm-6 col-md-6 col-lg-4">
				<div class="card text-center hoverable">
					<img class="card-img-top" src="resources/img/Raza2.jpg"
						alt="Rizwan Raza"
						onclick="showImageModal('Rizwan Raza', 'resources/img/Raza2.jpg')">
					<div class="card-body">
						<h5 class="card-title">Rizwan Raza</h5>
						<p class="card-text">Designer and Developer</p>
						<a href="https://www.facebook.com/RexTerminous"
							class="btn bg-facebook px-2 hoverable"><i
							class="fab fa-fw fa-facebook-f"></i></a> <a
							href="https://twitter.com/RexTerminous"
							class="btn bg-twitter px-2 hoverable"><i
							class="fab fa-fw fa-twitter"></i></a> <a
							href="https://www.linkedin.com/in/rex-terminous/"
							class="btn bg-linkedin px-2 hoverable"><i
							class="fab fa-fw fa-linkedin-in"></i></a> <a
							href="https://plus.google.com/+RizwanRaza365"
							class="btn bg-google-plus px-2 hoverable"><i
							class="fab fa-fw fa-google-plus-g"></i></a>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-6 col-lg-8">
				<form role="form">
					<p>Leave a quick review, suggestion, general feedback or bug
						report to the developer.</p>
					<div class="form-group">
						<input type="text" class="form-control" id="name" name="name"
							placeholder="Name" required>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="email" name="email"
							placeholder="Email" required>
					</div>
					<div class="form-group">
						<input type="tel" class="form-control" id="mobile" name="mobile"
							placeholder="Mobile Number *" required>
					</div>
					<div class="form-group">
						<select class="form-control custom-select" name="type">
							<option value="gen">General Feedback</option>
							<option value="Residential">Bug Report</option>
							<option value="Commercial">Suggestion</option>
							<option value="Industrial">Review and Rating</option>
							<option value="Industrial">Other</option>
						</select>
					</div>
					<div class="form-group">
						<textarea class="form-control" id="message" placeholder="Message"
							maxlength="140" rows="7"></textarea>
						<span id="characterLeft" class="help-block ">You have
							reached the limit</span>
					</div>
					<div class="row">
						<div class="col-sm-2">
							<label>Rating</label>
						</div>
						<div class='col-sm-6 rating-stars text-center'>
							<ul id='stars'>
								<li class='star' title='Poor' data-value='1'
									data-toggle="tooltip"><i class='fa fa-star fa-fw'></i></li>
								<li class='star' title='Fair' data-value='2'
									data-toggle="tooltip"><i class='fa fa-star fa-fw'></i></li>
								<li class='star' title='Good' data-value='3'
									data-toggle="tooltip"><i class='fa fa-star fa-fw'></i></li>
								<li class='star' title='Excellent' data-value='4'
									data-toggle="tooltip"><i class='fa fa-star fa-fw'></i></li>
								<li class='star' title='WOW!!!' data-value='5'
									data-toggle="tooltip"><i class='fa fa-star fa-fw'></i></li>
							</ul>
							<div class='success-box'>
								<div class='clearfix'></div>
								<div class='text-message'></div>
								<div class='clearfix'></div>
							</div>
						</div>
						<div class="col-sm-4">
							<button type="button" id="submit" name="submit"
								class="btn btn-primary float-right">Submit Form</button>
						</div>
					</div>

				</form>
			</div>
		</div>
		<hr />
		<h2 class="text-center my-4">Find Us Here</h2>
		<iframe
			src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d14016.387855079322!2d77.2891295!3d28.5668509!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x8a9dc3e7205faa11!2sProlog+Academy!5e0!3m2!1sen!2sin!4v1461454102691"
			width="100%" height="300" style="border: 0"></iframe>
	</div>
	<div class="bg-default">
		<div class="container text-center py-4">
			<h2 class="my-4">Follow Us</h2>
			<p>Connect with us through different social media, we are
				available everywhere, where you want. Just Simply follow us.</p>
			<ul class="social mx-auto">
				<li><a href="https://www.facebook.com/RexTerminous"
					class="bg-facebook" title="Facebook" data-toggle="tooltip"> <i
						class="fab fa-facebook-f"></i>
				</a></li>
				<li><a href="https://twitter.com/RexTerminous"
					class="bg-twitter" title="Twitter" data-toggle="tooltip"> <i
						class="fab fa-twitter"></i>
				</a></li>
				<li><a href="https://plus.google.com/+RizwanRaza365"
					class="bg-google-plus" title="Google +" data-toggle="tooltip">
						<i class="fab fa-google-plus-g"></i>
				</a></li>
				<li><a href="https://www.linkedin.com/in/rex-terminous/"
					class="bg-linkedin" title="Linkedin" data-toggle="tooltip"> <i
						class="fab fa-linkedin-in"></i>
				</a></li>
				<li><a href="https://www.tumblr.com/blog/rex-terminous"
					class="bg-tumblr" title="Tumblr" data-toggle="tooltip"> <i
						class="fab fa-tumblr"></i>
				</a></li>
				<li><a href="https://www.instagram.com/rex.terminous"
					class="bg-instagram" title="Instagram" data-toggle="tooltip"> <i
						class="fab fa-instagram"></i>
				</a></li>
				<li><a href="https://in.pinterest.com/RexTerminous/"
					class="bg-pinterest" title="Pinterest" data-toggle="tooltip"> <i
						class="fab fa-pinterest-p"></i>
				</a></li>
				<li><a href="https://github.com/Rizwan-Raza" class="bg-github"
					title="GitHub" data-toggle="tooltip"> <i class="fab fa-github"></i>
				</a></li>
				<li><a
					href="https://stackoverflow.com/users/5148111/rizwan-raza"
					class="bg-stack-overflow" title="StackOverflow"
					data-toggle="tooltip"> <i class="fab fa-stack-overflow"></i>
				</a></li>
				<li><a href="https://www.freecodecamp.org/rizwan-raza"
					class="bg-free-code-camp" title="freeCodeCamp"
					data-toggle="tooltip"> <i class="fab fa-free-code-camp"></i>
				</a></li>
				<li><a href="https://rexterminous.deviantart.com/"
					class="bg-devientart" title="DeviantArt" data-toggle="tooltip">
						<i class="fab fa-deviantart"></i>
				</a></li>
				<li><a href="http://rraza.blogspot.in/" class="bg-blogger"
					title="Blogger" data-toggle="tooltip"> <i
						class="fab fa-blogger-b"></i>
				</a></li>
				<li><a href="mailto:rizwan.raza987@gmail.com" class="bg-dark"
					title="Mail" data-toggle="tooltip"> <i class="fa fa-envelope"></i>
				</a></li>
				<li><a href="tel:9718666289" class="bg-success" title="Call"
					data-toggle="tooltip"> <i class="fa fa-phone"></i>
				</a></li>
			</ul>
		</div>
	</div>
	<jsp:directive.include file="views/footer.inc.html" />
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#characterLeft').text('140 characters left');
							$('#message')
									.keydown(
											function() {
												var max = 140;
												var len = $(this).val().length;
												if (len >= max) {
													$('#characterLeft')
															.text(
																	'You have reached the limit');
													$('#characterLeft')
															.addClass('red');
													$('#btnSubmit').addClass(
															'disabled');
												} else {
													var ch = max - len;
													$('#characterLeft')
															.text(
																	ch
																			+ ' characters left');
													$('#btnSubmit')
															.removeClass(
																	'disabled');
													$('#characterLeft')
															.removeClass('red');
												}
											});
							/* 1. Visualizing things on Hover - See next part for action on click */
							$('#stars li')
									.on(
											'mouseover',
											function() {
												var onStar = parseInt($(this)
														.data('value'), 10); // The star currently mouse on

												// Now highlight all the stars that's not after the current hovered star
												$(this)
														.parent()
														.children('li.star')
														.each(
																function(e) {
																	if (e < onStar) {
																		$(this)
																				.addClass(
																						'hover');
																	} else {
																		$(this)
																				.removeClass(
																						'hover');
																	}
																});

											})
									.on(
											'mouseout',
											function() {
												$(this)
														.parent()
														.children('li.star')
														.each(
																function(e) {
																	$(this)
																			.removeClass(
																					'hover');
																});
											});

							/* 2. Action to perform on click */
							$('#stars li')
									.on(
											'click',
											function() {
												var onStar = parseInt($(this)
														.data('value'), 10); // The star currently selected
												var stars = $(this).parent()
														.children('li.star');

												for (i = 0; i < stars.length; i++) {
													$(stars[i]).removeClass(
															'selected');
												}

												for (i = 0; i < onStar; i++) {
													$(stars[i]).addClass(
															'selected');
												}

												// JUST RESPONSE (Not needed)
												var ratingValue = parseInt($(
														'#stars li.selected')
														.last().data('value'),
														10);
												var msg = "";
												if (ratingValue > 1) {
													msg = "Thanks! You rated this "
															+ ratingValue
															+ " stars.";
												} else {
													msg = "We will improve ourselves. You rated this "
															+ ratingValue
															+ " stars.";
												}
												responseMessage(msg);

											});

						});

		function responseMessage(msg) {
			$('.success-box').fadeIn(200);
			$('.success-box div.text-message').html("<span>" + msg + "</span>");
		}
	</script>
	<script type="text/javascript" src="resources/js/common.js"></script>
</body>
</html>