<#import "parts/common.ftlh" as c>
<#import "parts/filter.ftlh" as fl>

<@c.page>
    <div class="text-center">
        <h5>Список упражнений по предметам</h5>
    </div>
    <div class="d-flex justify-content-end">
        <div class="mr-2"><@fl.filterlist "Поиск по упражнению" "/topic" /></div>
    </div>
    <div class="text-center">
        <a class="btn btn-outline-primary" href="/topic/add" role="button">Создать упражнение</a>
    </div>
    <table class="table table-hover col-sm-5 mx-auto">
        <thead>
        <tr>
            <th scope="col">Упражнение</th>
            <th scope="col">Предмет</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list topics as topic>
            <tr>
                <td class="align-middle">${topic.title}</td>
                <td class="align-middle">${topic.subject.subjectname}</td>
                <td class="align-middle"><a class="btn btn-outline-primary" href="/topic/update/${topic.id}">редактировать</a></td>
                <td class="align-middle">
                    <form action="/topic" method="post">
                        <input type="hidden" name="topicId" value="<#if topic.id??>${topic.id!}</#if>"/>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button class="btn btn-outline-primary" type="submit">удалить</button>
                    </form>
                </td>
                <#--<td>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#messageConfirm">
                        удалить
                    </button>
                </td> -->
            </tr>

        <#--<div class="modal fade" id="messageConfirm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Подтверждение удаления</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Подтвердите удаление темы ${topic.title}
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                            <form action="/topic" method="post">
                                <input type="hidden" name="topicId" value="<#if topic.id??>${topic.id!}</#if>"/>
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button class="btn btn-primary" type="submit">удалить</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>-->

        <#else>
            <tr>
                <td class="align-middle">Пока нет упражнений</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>