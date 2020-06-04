<ul class="nav flex-column">    <#-- col-sm-2 -->
    <#if subjects??>
        <#list subjects as subject>
            <li class="nav-item">
                <a class="nav-link active" href="#">${subject.subjectname}</a>
            </li>
        </#list>
    </#if>

    <#--  <li class="nav-item">
          <a class="nav-link" href="#">Русский язык</a>
      </li>
      <li class="nav-item">
          <a class="nav-link" href="#">Окружающий мир</a>
      </li>
      <li class="nav-item">
          <a class="nav-link" href="#">Дополнительно</a>
      </li>-->
</ul>