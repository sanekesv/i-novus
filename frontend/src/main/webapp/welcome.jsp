<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="resources/static/js/file-upload.js"></script>

    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="resources/static/css/general.css"/>


</head>
<body>
<div class="container">
    <h1>Upload file for convert XML to JSON</h1>

    <form method="POST" enctype="multipart/form-data" id="fileUploadForm">
        <input type="file" name="file"/>
        <input class="btn" type="submit" value="Submit" id="btnSubmit"/>
    </form>

    <h1>Result</h1>
    <pre id="json"></pre>
</div>
</body>
</html>