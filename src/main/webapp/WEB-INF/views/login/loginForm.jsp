<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>login</title>

    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <!-- Vendor CSS Files -->
    <link href="${contextPath}/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/static/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="${contextPath}/static/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="${contextPath}/static/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="${contextPath}/static/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="${contextPath}/static/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="${contextPath}/static/vendor/simple-datatables/style.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="${contextPath}/static/css/style.css" rel="stylesheet">

    <!-- Google Fonts -->
    <link href="${contextPath}/static/font/open-sans/open-sans.css" rel="stylesheet">
    <link href="${contextPath}/static/font/nunito/nunito.css" rel="stylesheet">
    <link href="${contextPath}/static/font/poppins/poppins.css" rel="stylesheet">

    <!-- Vendor JS Files -->
    <script src="${contextPath}/static/vendor/apexcharts/apexcharts.min.js"></script>
    <script src="${contextPath}/static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${contextPath}/static/vendor/chart.js/chart.umd.js"></script>
    <script src="${contextPath}/static/vendor/echarts/echarts.min.js"></script>
    <script src="${contextPath}/static/vendor/quill/quill.js"></script>
    <script src="${contextPath}/static/vendor/simple-datatables/simple-datatables.js"></script>
    <script src="${contextPath}/static/vendor/tinymce/tinymce.min.js"></script>
    <script src="${contextPath}/static/vendor/php-email-form/validate.js"></script>

    <!-- Template Main JS File -->
    <script src="${contextPath}/static/js/main.js"></script>

</head>
<body>
<div class="container">

    <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

                    <div class="d-flex justify-content-center py-4">
<%--                        <a href="index.html" class="logo d-flex align-items-center w-auto">--%>
                            <img src="${contextPath}/static/img/logo.png" alt="">
                            <span class="d-none d-lg-block">NiceAdmin</span>
<%--                        </a>--%>
                    </div><!-- End Logo -->

                    <div class="card mb-3">

                        <div class="card-body">

                            <div class="pt-4 pb-2">
                                <h5 class="card-title text-center pb-0 fs-4">Login to Your Account</h5>
                                <p class="text-center small">Enter your username & password to login</p>
                            </div>

                            <form class="row g-3 needs-validation" novalidate>

                                <div class="col-12">
                                    <label for="loginId" class="form-label">Username</label>
                                    <div class="input-group has-validation">
                                        <span class="input-group-text" id="inputGroupPrepend">@</span>
                                        <input type="text" name="loginId" class="form-control" id="loginId" required>
                                        <div class="invalid-feedback">Please enter your username.</div>
                                    </div>
                                </div>

                                <div class="col-12">
                                    <label for="yourPassword" class="form-label">Password</label>
                                    <input type="password" name="password" class="form-control" id="yourPassword" required>
                                    <div class="invalid-feedback">Please enter your password!</div>
                                </div>

                                <div class="col-12">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="remember" value="true" id="rememberMe">
                                        <label class="form-check-label" for="rememberMe">Remember me</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <button class="btn btn-primary w-100" type="submit">Login</button>
                                </div>
                                <div class="col-12">
                                    <p class="small mb-0">Don't have account? <a href="${contextPath}/login/regist">Create an account</a></p>
                                </div>
                            </form>

                        </div>
                    </div>

                    <div class="credits">
                        <!-- All the links in the footer should remain intact. -->
                        <!-- You can delete the links only if you purchased the pro version. -->
                        <!-- Licensing information: https://bootstrapmade.com/license/ -->
                        <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
                        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
                    </div>

                </div>
            </div>
        </div>

    </section>

</div>

</body>
</html>
