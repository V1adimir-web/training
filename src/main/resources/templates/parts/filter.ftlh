<#macro filterlist text path>
 <#--   <div class="row col-sm-5 justify-content-end border border-primary">
        <div class="col-md-auto border border-primary">-->
            <form method="get" action="${path}" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter!}"
                       placeholder="${text}" />
                <button type="submit" class="btn btn-primary ml-2">Найти</button>
            </form>
  <#--      </div>
    </div>-->
</#macro>