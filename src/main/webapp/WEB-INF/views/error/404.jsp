<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>404</title>

    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <!-- Favicons -->
    <link href="${contextPath}/static/assets/img/favicon.png" rel="icon">
    <link href="${contextPath}/static/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="${contextPath}/static/fonts/open-sans/open-sans.css" rel="stylesheet">
    <link href="${contextPath}/static/fonts/nunito/nunito.css" rel="stylesheet">
    <link href="${contextPath}/static/fonts/poppins/poppins.css" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="${contextPath}/static/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/static/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="${contextPath}/static/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="${contextPath}/static/assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="${contextPath}/static/assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="${contextPath}/static/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="${contextPath}/static/assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="${contextPath}/static/assets/css/style.css" rel="stylesheet">

    <!-- Vendor JS Files -->
    <script src="${contextPath}/static/assets/vendor/apexcharts/apexcharts.min.js"></script>
    <script src="${contextPath}/static/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${contextPath}/static/assets/vendor/chart.js/chart.umd.js"></script>
    <script src="${contextPath}/static/assets/vendor/echarts/echarts.min.js"></script>
    <script src="${contextPath}/static/assets/vendor/quill/quill.js"></script>
    <script src="${contextPath}/static/assets/vendor/simple-datatables/simple-datatables.js"></script>
    <script src="${contextPath}/static/assets/vendor/tinymce/tinymce.min.js"></script>
    <script src="${contextPath}/static/assets/vendor/php-email-form/validate.js"></script>

    <!-- Template Main JS File -->
    <script src="${contextPath}/static/assets/js/main.js"></script>

</head>
<body>

<main>
<div class="container">

    <section class="section error-404 min-vh-100 d-flex flex-column align-items-center justify-content-center">
        <h1>404</h1>
        <h2>The page you are looking for doesn't exist.</h2>
        <a class="btn" href="#">Back to home</a>
        <img src="${contextPath}/static/assets/img/not-found.svg" class="img-fluid py-5" alt="Page Not Found">
        <div class="credits">
            <!-- All the links in the footer should remain intact. -->
            <!-- You can delete the links only if you purchased the pro version. -->
            <!-- Licensing information: https://bootstrapmade.com/license/ -->
            <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
            Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
        </div>
    </section>

</div>
</main><!-- End #main -->

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

</body>
</html>
