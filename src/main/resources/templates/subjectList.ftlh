<#import "parts/common.ftlh" as c>
<#import "parts/filter.ftlh" as fl>

<@c.page>
    <div class="text-center">
        <h5>Список предметов</h5>
    </div>
    <div class="d-flex justify-content-end">
        <div class="mr-2"><@fl.filterlist "Поиск по предмету" "/subject" /></div>
    </div>
    <div class="text-center">
        <a class="btn btn-outline-primary" href="/subject/add" role="button">Создать предмет</a>
    </div>
    <table class="table table-hover col-sm-5 mx-auto">
        <thead>
        <tr>
            <th scope="col">Предмет</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list subjects as subject>
            <tr>
                <td class="align-middle">${subject.subjectname}</td>
                <td class="align-middle"><a class="btn btn-outline-primary" href="/subject/update/${subject.id}">редактировать</a></td>
                <td class="align-middle">
                    <form action="/subject" method="post">
                        <input type="hidden" name="subjectId" value="<#if subject.id??>${subject.id!}</#if>"/>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button class="btn btn-outline-primary" type="submit">удалить</button>
                    </form>
                </td>
            </tr
        <#else>
            <tr>
                <td class="align-middle">Пока нет предметов</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>