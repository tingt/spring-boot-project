<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1,user-scalable=no">
    <!-- 当前页面css -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/2.9.4/jquery.fullpage.min.css"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
</head>
<body>
<br />
<div>
    <form id="listForm" name="myform" action="/user/doUpdate" method="post" class="form-horizontal">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
            <label  class="col-md-2 control-label" for="userName">用户名</label>
            <div class="col-md-4">
            <input type="text" class="form-control" id="userName" placeholder="用户名" name="userName" value="${user.userName}" />
            <input type="hidden" class="form-control" id="userId"  name="id" value="${user.id}" readonly/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label" for="passWord">密码</label>
            <div class="col-md-4">
            <input type="password" class="form-control" id="password" placeholder="Password" name="password" value="${user.password}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label" for="passWord">确认密码</label>
            <div class="col-md-4">
                <input type="password" class="form-control" id="passwordRepeated" placeholder="Password" name="passwordRepeated" required/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label" for="exampleInputEmail1">邮箱</label>
            <div class="col-md-4">
                <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email" name="email" value="${user.email}">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label" for="sex">性别</label>
            <div class="col-md-4">
            <label class="radio-inline">
                <input type="radio" name="sex" id="inlineRadio1" value="M" <#if (user.sex)?? && (user.sex=='M')!> checked="checked" </#if>> 男
            </label>
            <label class="radio-inline">
                <input type="radio" name="sex" id="inlineRadio2" value="F" <#if (user.sex)?? && (user.sex=='F')!> checked="checked" </#if>> 女
            </label>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label" for="role">角色</label>
            <div class="col-md-2">
                <select class="col-md-4 form-control" name="roleId" required>
                <#list roleList as role>
                    <option value="${role.id}" <#if user.roleId==role.id> selected="selected"</#if>>${role.roleName}</option>
                </#list>
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-offset-2 col-md-4">
                <button type="submit" class="btn btn-success">更新</button>
                <button type="submit" class="btn btn-default" onclick="window.location.href = '/user/list';">返回</button>
            </div>
        </div>
    </form>
</div>
</div>

</body>
</html>