<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>hello spring boot</title>
    <script type="text/javascript" src="/libs/jquery.js"></script>
</head>

<body>
<li id="sc"><a href="javascript:switchLanguage('zh_CN');"><@spring.message "hello.chinese" /></a></li>
<li id="en"><a href="javascript:switchLanguage('en_US')"><@spring.message "hello.english" /></a></li>

<br />
<a href="user"><@spring.message "hello.viewUser" /></a>

</body>
<script>
    $(function(){
        var locale = getCookie("locale");
        $('#sc').removeClass('active');
        $('#en').removeClass('active');
        if(locale=="zh_CN"){
            $('#sc').addClass('active');
        }else if(locale=="en_US"){
            $('#en').addClass('active');
        }
    });

    function switchLanguage(locale){
        $.ajax({
            type:"POST",
            dataType:"json",
            url:"/switchLanguage",
            data:{lang:locale},
            success:
                    function(data){
                        if(data.error==0){
                            window.location.reload();
                        }
                    }
        });

    }

    function getCookie(name) {
        var bikky = document.cookie;
        name += "=";
        var i = 0;
        while (i < bikky.length) {
            var offset = i + name.length;
            if (bikky.substring(i, offset) == name) {
                var endstr = bikky.indexOf(";", offset);
                if (endstr == -1) endstr = bikky.length;
                return unescape(bikky.substring(offset, endstr));
            }
            i = bikky.indexOf(" ", i) + 1;
            if (i == 0) break;
        }
        return null;
    }
</script>
</html>