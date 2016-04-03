<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#macro mainTemplate title="MainTemplate">
    <@spring.url value="/resources/css/" var="css_res"/>
    <@spring.url value="/resources/js/" var="js_res"/>
    <@spring.url value="/resources/image/" var="img_res"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>Book Store</title>
    <link rel="stylesheet" href="${css_res}style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="${css_res}modal.css" type="text/css">

    <!--[if lte IE 6]>
    <link rel="stylesheet" href="${css_res}ie6.css" type="text/css" media="all">
    <![endif]-->

    <!-- JS -->
    <script src="${js_res}jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="${js_res}simpleCart.min.js" type="text/javascript"></script>
    <script src="${js_res}store/modal.js" type="text/javascript"></script>
    <script src="${js_res}store/catalog.js" type="text/javascript"></script>
    <script src="${js_res}store/cart.js" type="text/javascript"></script>
    <!-- End JS -->


    <link rel="shortcut icon" href="${img_res}favicon.ico" type="image/ico">
</head>
<body>
<!-- Shell -->
<div class="shell">
    <!-- Header -->
    <#include "../templates/header.ftl">
    <!-- End Header -->

    <!-- Main -->
    <div id="main">
        <div class="cl">&nbsp;</div>

        <!-- Content -->
        <div id="content">
            <!-- Products -->
            <@body/>
            <!-- End Products -->
        </div>
        <!-- End Content -->

        <!-- Sidebar -->
        <div id="sidebar">
            <!-- Search -->
            <#include "../templates/search.ftl">
            <!-- End Search -->
            <!-- Categories -->
            <#include "../templates/categories.ftl">
            <!-- End Categories -->
        </div>
        <!-- End Sidebar -->

        <div class="cl">&nbsp;</div>
    </div>
    <!-- End Main -->

    <!-- Side Full -->

    <div class="side-full">
        <!-- More Products -->
        <#include "../templates/moreProduction.ftl">
        <!-- End More Products -->

        <!-- Text Cols -->
        <#include "../templates/textCols.ftl">
        <!-- End Text Cols -->

    </div>
    <!-- End Side Full -->

    <!-- Footer -->
    <#include "../templates/footer.ftl">
    <!-- End Footer -->

</div>
<!-- End Shell -->
<div id="overlay"></div>
</body>
</html>
</#macro>