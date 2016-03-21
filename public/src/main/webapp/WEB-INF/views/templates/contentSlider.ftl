<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<@spring.url value="/resources/image/" var="img_res"/>
<div id="slider" class="box">
    <div id="slider-holder">
        <ul>
            <li><a href="#"><img src="${img_res}slide1.jpg" alt=""/></a></li>
            <li><a href="#"><img src="${img_res}slide1.jpg" alt=""/></a></li>
            <li><a href="#"><img src="${img_res}slide1.jpg" alt=""/></a></li>
            <li><a href="#"><img src="${img_res}slide1.jpg" alt=""/></a></li>
        </ul>
    </div>
    <div id="slider-nav">
        <a href="#" class="active">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
    </div>
</div>