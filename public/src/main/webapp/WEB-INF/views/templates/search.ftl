<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<div class="box search">
    <h2>Search by <span></span></h2>

    <div class="box-content">
        <form action="<@spring.url value="/search"/>" method="post">

            <label>Поиск</label>
            <input type="text" class="field" name="name" value="Преступление" title="Search"/>

            <label>Категория</label>
            <select class="field" name="categoryId">
            <#if menuList??>
                <#list menuList as category>
                    <option value="${category.id}">${category.name}</option>
                </#list>
            </#if>
            </select>

            <div class="inline-field">
                <label>Цена</label>
                <select class="field small-field" name="min">
                    <option value="0">0руб</option>
                    <option value="10">10руб</option>
                    <option value="100">100руб</option>
                    <option value="200">200руб</option>
                    <option value="250">250руб</option>
                    <option value="500">500руб</option>
                    <option value="1000">1000руб</option>
                    <option value="10000">10000руб</option>
                    <option value="100000">100000руб</option>
                    <option value="1000000">1000000руб</option>
                </select>
                <label>to:</label>
                <select class="field small-field" name="max">
                    <option value="1000000">1000000руб</option>
                    <option value="100000">100000руб</option>
                    <option value="10000">10000руб</option>
                    <option value="1000">1000руб</option>
                    <option value="500">500руб</option>
                    <option value="250">250руб</option>
                    <option value="200">200руб</option>
                    <option value="100">100руб</option>
                    <option value="10">10руб</option>
                    <option value="0">0руб</option>
                </select>
            </div>

            <input type="submit" class="search-submit" value="Search"/>

            <p>
                <a href="#" class="bul">Расширенный поиск</a><br/>
                <a href="#" class="bul">Служба поддержки</a>
            </p>

        </form>
    </div>
</div>