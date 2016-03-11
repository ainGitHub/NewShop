<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>
<#macro mainTemplate title="MainTemplate">

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>Book Store</title>
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css" media="all"/>
    <!--[if lte IE 6]>
    <link rel="stylesheet" href="/resources/css/ie6.css" type="text/css" media="all"/><![endif]-->

    <!-- JS -->
    <script src="/resources/js/jquery-1.4.1.min.js" type="text/javascript"></script>
    <script src="/resources/js/jquery.jcarousel.pack.js" type="text/javascript"></script>
    <script src="/resources/js/jquery-func.js" type="text/javascript"></script>
    <!-- End JS -->


    <link rel="shortcut icon" href="/resources/image/favicon.ico" type="image/ico">
</head>
<body>

<!-- Shell -->
<div class="shell">

    <!-- Header -->
    <#include "header.ftl">
    <!-- End Header -->

    <!-- Main -->
    <div id="main">
        <div class="cl">&nbsp;</div>

        <!-- Content -->
        <div id="content">

            <!-- Content Slider -->

            <!-- End Content Slider -->

            <!-- Products -->
            <@body/>
            <!-- End Products -->

        </div>
        <!-- End Content -->

        <!-- Sidebar -->
        <div id="sidebar">
            <!-- Search -->
            <#include "search.ftl">
            <!-- End Search -->
            <!-- Categories -->
            <#include "categories.ftl">
            <!-- End Categories -->
        </div>
        <!-- End Sidebar -->

        <div class="cl">&nbsp;</div>
    </div>
    <!-- End Main -->

    <!-- Side Full -->

    <div class="side-full">
        <!-- More Products -->
        <#include "moreProduction.ftl">
        <!-- End More Products -->

        <!-- Text Cols -->
        <#include "textCols.ftl">
        <!-- End Text Cols -->

    </div>
    <!-- End Side Full -->

    <!-- Footer -->
    <#include "footer.ftl">
    <!-- End Footer -->

</div>
<!-- End Shell -->


</body>
</html>
</#macro>