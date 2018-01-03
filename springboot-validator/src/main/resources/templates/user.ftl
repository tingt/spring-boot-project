<#import "spring.ftl" as spring />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1,user-scalable=no">
    <!-- 当前页面css -->
  <!--  <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />  -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
    <script type="text/javascript" src="/libs/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="/libs/jquery.validate.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2">
            <div class="page-header">
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">User Info</h3>
                </div>
                <div class="panel-body">
                    <form id="signupForm" method="post" class="form-horizontal" action="/user/save">
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="name">Username</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="name" name="name" placeholder="username"/>
                                <p style="color:red"><@spring.bind "userVO.name" /><@spring.showErrors  ";" /></p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="email">Email</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="email" name="email" placeholder="Email"/>
                                <p style="color:red"><@spring.bind "userVO.email" /><@spring.showErrors  ";" /></p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="birth">Birth</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="birth" name="birth" placeholder="birth" />
                                <p style="color:red"><@spring.bind "userVO.birth" /><@spring.showErrors  ";" /></p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="password">Password</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="password" name="password" placeholder="Password" />
                                <p style="color:red"><@spring.bind "userVO.password" /><@spring.showErrors  ";" /></p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="confirm_password">Confirm password</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="confirm_password" name="confirmPassword" placeholder="Confirm password" />
                                <p style="color:red"><@spring.bind "userVO.confirmPassword" /><@spring.showErrors  ";" /></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-3">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" id="agree" name="agree" value="agree" />Please agree to our policy
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-9 col-sm-offset-3">
                                <button type="submit" class="btn btn-primary" name="signup" value="Sign up">Sign up</button>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        <div class="col-sm-8 col-sm-offset-2">
        <@spring.bind "userVO" />
        <#if spring.status.error>
            <div class="form-group">
                <div class="col-sm-5">
                    <ul style="color:red">
                        <#list spring.status.errorMessages as error>
                            <b><li>${error}</li></b>
                        </#list>
                    </ul>
                </div>
            </div>
        </#if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $( document ).ready( function () {
        $( "#signupForm" ).validate( {
            rules: {
                name: {
                    required: true,
                    minlength: 2
                },
                email: {
                    required: true,
                    email: true
                },
                password: "required",

                confirm_password: {
                    required: true,
                    equalTo: "#password"
                },

                agree: "required"
            },
            messages: {
                name: {
                    required: "Please enter a username",
                    minlength: "Your username must consist of at least 2 characters"
                },
                password: {
                    required: "Please provide a password",
                },
                confirm_password: {
                    required: "Please provide a password",
                    equalTo: "Please enter the same password as above"
                },
                email: "Please enter a valid email address",
                agree: "Please accept our policy"
            },
            errorElement: "em",
            errorPlacement: function ( error, element ) {
                error.addClass( "help-block" );

                element.parents( ".col-sm-6" ).addClass( "has-feedback" );

                if ( element.prop( "type" ) === "checkbox" ) {
                    error.insertAfter( element.parent( "label" ) );
                } else {
                    error.insertAfter( element );
                }
                if ( !element.next( "span" )[ 0 ] ) {
                    $( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element );
                }
            },
            success: function ( label, element ) {
                // Add the span element, if doesn't exists, and apply the icon classes to it.
                if ( !$( element ).next( "span" )[ 0 ] ) {
                    $( "<span class='glyphicon glyphicon-ok form-control-feedback'></span>" ).insertAfter( $( element ) );
                }
            },
            highlight: function ( element, errorClass, validClass ) {
                $( element ).parents( ".col-sm-6" ).addClass( "has-error" ).removeClass( "has-success" );
                $( element ).next( "span" ).addClass( "glyphicon-remove" ).removeClass( "glyphicon-ok" );
            },
            unhighlight: function ( element, errorClass, validClass ) {
                $( element ).parents( ".col-sm-6" ).addClass( "has-success" ).removeClass( "has-error" );
                $( element ).next( "span" ).addClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" );
            }
        } );
    } );
</script>
</body>
</html>
