<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
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

    <!-- Google Fonts -->
    <link href="${contextPath}/static/fonts/open-sans/open-sans.css" rel="stylesheet">
    <link href="${contextPath}/static/fonts/nunito/nunito.css" rel="stylesheet">
    <link href="${contextPath}/static/fonts/poppins/poppins.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="${contextPath}/static/css/style.css" rel="stylesheet">

    <!-- Vendor JS Files -->
    <script src="${contextPath}/static/vendor/apexcharts/apexcharts.min.js" />
    <script src="${contextPath}/static/vendor/bootstrap/js/bootstrap.bundle.min.js" />
    <script src="${contextPath}/static/vendor/chart.js/chart.umd.js" />
    <script src="${contextPath}/static/vendor/echarts/echarts.min.js" />
    <script src="${contextPath}/static/vendor/quill/quill.js" />
    <script src="${contextPath}/static/vendor/simple-datatables/simple-datatables.js" />
    <script src="${contextPath}/static/vendor/tinymce/tinymce.min.js" />
    <script src="${contextPath}/static/vendor/php-email-form/validate.js" />

    <!-- Template Main JS File -->
    <script src="${contextPath}/static/js/main.js" />

</head>
<body>

<div class="container">

    <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

                    <div class="d-flex justify-content-center py-4">
<%--                        <a href="index.html" class="logo d-flex align-items-center w-auto">--%>
<%--                            <img src="assets/img/logo.png" alt="">--%>
<%--                            <span class="d-none d-lg-block">NiceAdmin</span>--%>
<%--                        </a>--%>
                    </div><!-- End Logo -->

                    <div class="card mb-3">

                        <div class="card-body">

                            <div class="pt-4 pb-2">
                                <h5 class="card-title text-center pb-0 fs-4">Create an Account</h5>
                                <p class="text-center small">Enter your personal details to create account</p>
                            </div>

                            <form class="row g-3 needs-validation" novalidate>
                                <div class="col-12">
                                    <label for="name" class="form-label">Your Name</label>
                                    <input type="text" name="name" class="form-control" id="name" required>
                                    <div class="invalid-feedback">Please, enter your name!</div>
                                </div>

                                <div class="col-12">
                                    <label for="email" class="form-label">Your Email</label>
                                    <input type="email" name="email" class="form-control" id="email" required>
                                    <div class="invalid-feedback">Please enter a valid Email adddress!</div>
                                </div>

                                <div class="col-12">
                                    <label for="loginId" class="form-label">Username</label>
                                    <div class="input-group has-validation">
                                        <span class="input-group-text" id="inputGroupPrepend">@</span>
                                        <input type="text" name="loginId" class="form-control" id="loginId" required>
                                        <div class="invalid-feedback">Please choose a LOGIN ID.</div>
                                    </div>
                                </div>

                                <div class="col-12">
                                    <label for="password" class="form-label">Password</label>
                                    <input type="password" name="password" class="form-control" id="password" required>
                                    <div class="invalid-feedback">Please enter your password!</div>
                                </div>

                                <div class="col-12">
                                    <label for="address" class="form-label">Username</label>
                                    <div class="input-group has-validation">
                                        <label for="email" class="form-label">Your Address</label>
                                        <input type="text" name="address" class="form-control" id="address" required>
                                        <div class="invalid-feedback">Please enter a valid adddress!</div>
                                    </div>
                                </div>

                                <div class="col-12">
                                    <label for="phoneNumber" class="form-label">Your Email</label>
                                    <input type="text" name="phoneNumber" class="form-control" id="phoneNumber" required>
                                    <div class="invalid-feedback">Please enter a valid phone number!</div>
                                </div>

                                <div class="col-12">
                                    <div class="form-check">
                                        <input class="form-check-input" name="terms" type="checkbox" value="" id="acceptTerms" required>
                                        <label class="form-check-label" for="acceptTerms">I agree and accept the <a href="#">terms and conditions</a></label>
                                        <div class="invalid-feedback">You must agree before submitting.</div>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <button class="btn btn-primary w-100" type="submit">Create Account</button>
                                </div>
                                <div class="col-12">
                                    <p class="small mb-0">Already have an account? <a href="${contextPath}/login">Log in</a></p>
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
