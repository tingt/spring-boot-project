<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.parameterName}"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1,user-scalable=no">
    <!-- 当前页面css -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
    <script type="text/javascript" src="/libs/jquery.js"></script>
</head>
<script>
    function ajaxDeleteUser(id){
        var csrfParameter = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var paraJson = {};
        paraJson[csrfParameter] = csrfToken;
        $.ajax({
            type:"POST",
            dataType:"json",
            url:"/user/"+id+"/delete",
            data:paraJson,
            success:
                    function(data){
                        if(data.error==0){
                            $('#'+'user_'+id).remove();  //删除表格行
                        }else{
                            alert(data.message);
                        }
                    }
        });
    }
    function updateUser(id){
        $('#listForm').attr("action","/user/"+id+"/update").submit();
    }
    function viewUser(id){
        $('#listForm').attr("action","/user/"+id).submit();
    }
</script>
<body>
<br />
<div>
    <form id="listForm" name="myform" action="/user/add" method="post" class="form-inline">
    <input id="crsf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <#if currentUser.roleName=='ADMIN'><button type="submit" class="btn btn-success" onclick="">新增用户</button></#if>
        <div class="table-responsive" style="margin-top:15px">
            <table class="table table-hover table-bordered"  style="width:50%">
                <thead>
                <tr>
                    <th>用户名</th>
                    <th>邮箱</th>
                    <th>性别</th>
                    <th>角色</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>
                <#list userList as user>
                <tr id="user_${user.id}">
                    <td>${user.userName}</td>
                    <td>${user.email}</td>
                    <td>${user.sex!""}</td>
                    <td>${user.roleName!""}</td>
                    <td><#if currentUser.roleName=='ADMIN'><a href="javascript:void(0);" onclick="ajaxDeleteUser('${user.id}')">删除</a></#if>
                        <a href="javascript:void(0);" onclick="updateUser('${user.id}')">修改</a>
                        <a href="javascript:void(0);" onclick="viewUser('${user.id}')">查看</a>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </form>
    </div>
</div>

</body>
</html>