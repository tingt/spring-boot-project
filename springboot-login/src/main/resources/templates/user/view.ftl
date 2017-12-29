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
    <form id="listForm" name="myform" action="" method="POST" class="form-horizontal">
        <div class="form-group">
            <div class="col-md-offset-2">
                <h3>Your info:</h3>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-md-2 control-label" for="userName">用户名</label>
            <div class="col-md-4">
                <p class="form-control-static">${user.userName}</p>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label" for="exampleInputEmail1">邮箱</label>
            <div class="col-md-4">
                <p class="form-control-static">${user.email}</p>
            </div>
        </div>


        <div class="form-group">
            <label class="col-md-2 control-label" for="role">角色</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="role" placeholder="role" name="role" value="${user.roleName}" readonly>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label" for="sex">性别</label>
            <div class="col-md-4">
                <label class="radio-inline">
                    <input type="radio" name="sex" id="inlineRadio1" value="M" <#if (user.sex)?? && (user.sex=='M')!> checked="checked" </#if> disabled> 男
                </label>
                <label class="radio-inline">
                    <input type="radio" name="sex" id="inlineRadio2" value="F" <#if (user.sex)?? && (user.sex=='F')!> checked="checked" </#if> disabled> 女
                </label>
            </div>
        </div>


        <div class="form-group">
            <div class="col-md-offset-2 col-md-4">
                <button type="submit" class="btn btn-default" onclick="window.location.href = window.history.back(1);">返回</button>
            </div>
        </div>
    </form>
</div>
</div>

</body>
</html>