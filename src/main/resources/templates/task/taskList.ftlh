<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="mb-4 text-center">
        <h5 class="mb-3">Список задач к упражнениям</h5>
        <a class="btn btn-outline-primary" href="/task/add" role="button">Создать задачу</a>
    </div>
    <table class="table table-hover col-sm-5 mx-auto">
        <thead>
        <tr class="text-center">
            <th scope="col">Задача</th>
            <th scope="col">Правильный ответ</th>
            <th scope="col">Упражнение</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list tasks as task>
            <tr class="text-center">
                <td class="align-middle">${task.content}</td>
                <td class="align-middle">${task.rightanswer}</td>
                <td class="align-middle">${task.topic.title}</td>
                <td class="align-middle"><a class="btn btn-outline-primary" href="/task/update/${task.id}">редактировать</a></td>
                <td class="align-middle">
                    <form action="/task" method="post">
                        <input type="hidden" name="taskId" value="<#if task.id??>${task.id!}</#if>"/>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button class="btn btn-outline-primary" type="submit">удалить</button>
                    </form>
                </td>
            </tr>
        <#else>
            <tr>
                <td class="align-middle">Пока нет задач</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>