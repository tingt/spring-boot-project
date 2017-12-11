<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1,user-scalable=no">
    <!-- 当前页面css -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
    <script type="text/javascript" src="/libs/jquery.js"></script>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
    <ul>
        <li><a href="/registration">Register</a></li>
    </ul>
</nav>

<div class="container">
    <h3>Please login</h3>
    <form action="/login" method="POST" role="form" class="form-horizontal">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
            <label  class="col-md-2 control-label" for="userName">用户名</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="userName" placeholder="用户名" name="userName" required autofocus />
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label" for="password">密码</label>
            <div class="col-md-4">
            <input type="password" name="password" id="password" class="form-control"  required/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-2 col-md-2 checkbox">
                <label>
                    <input type="checkbox" name="remember-me" id="remember-me">     Remember me
                </label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-offset-2 col-md-2">
                <button type="submit" class="btn btn-success">Login in</button>
            </div>
        </div>

        <#if error.isPresent()>
            <div class="col-md-offset-2">
                <p style="color:red">The email or password you have entered is invalid, try again.</p>
            </div>

        </#if>
    </form>
</div>
</body>

</html>