<jsp:directive.page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>Real Estate eXplorer - REX</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="png" href="/resources/img/favicon/Logo.png" />
<link rel="stylesheet" type="text/css"
	href="/resources/css/font-awesome.min.css">
<style>
body {
	background: #081421;
	color: #d3d7de;
	font-family: "Courier new";
	font-size: 18px;
	line-height: 1.5em;
	cursor: default;
}

.code-area {
	position: absolute;
	  width: 320px;
	min-width: 320px;
	top: 50%;
	left: 50%;
	-webkit-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}

.code-area>span {
	display: block;
}

.btn {
	background-color: rgba(255, 255, 255, 0.5);
	color: #000;
	position: absolute;
	bottom: 20px;
	width: 50px;
	height: 50px;
	cursor: pointer;
	transition: all 200ms ease-in-out;
	box-sizing: border-box;
	padding-top: 8px;
}

.btn:hover {
	background-color: rgba(255, 255, 255, 0.8);
}

.btn-left {
	left: 20px;
}

.btn-right {
	right: 20px;
}

@media screen and (max-width: 320px) {
	.code-area {
		font-size: 5vw;
		min-width: auto;
		width: 95%;
		margin: auto;
		padding: 5px;
		padding-left: 10px;
		line-height: 6.5vw;
	}
}
</style>
</head>
<body>
	<div class="code-area">
		<span style="color: #777; font-style: italic;"> // 404 page not
			found. </span> <span> <span style="color: #d65562;"> if </span> (<span
			style="color: #4ca8ef;">!</span><span
			style="font-style: italic; color: #bdbdbd;">found</span>) {
		</span> <span> <span style="padding-left: 15px; color: #2796ec">
				<i style="width: 10px; display: inline-block"></i>throw
		</span> <span> (<span style="color: #a6a61f">"&#x6CC;&#x6C1;&#x627;&#x6BA;
					&#x6A9;&#x6CC;&#x627; &#x6A9;&#x631; &#x631;&#x6C1;&#x627;
					&#x6C1;&#x6D2; &#x61F;&#x61F;&#x61F;"</span>);
		</span> <span style="display: block">}</span>
		</span>
	</div>
	<div class="btn btn-left" onclick="window.location.href='/'">
		<i class="fa fa-fw fa-home fa-2x"></i>
	</div>
	<div class="btn btn-right" onclick="window.history.back()">
		<i class="fa fa-fw fa-chevron-left fa-2x"></i>
	</div>
</body>
</html>