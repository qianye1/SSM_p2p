<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Eloan-P2P平台-></title>
    <link rel="stylesheet" href="/js/bootstrap-3.3.2-dist/css/bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="/css/core.css" type="text/css"/>
    <script type="text/javascript" src="/js/jquery/jquery-2.1.3.js"></script>
    <script type="text/javascript" src="/js/bootstrap-3.3.2-dist/js/bootstrap.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
    <script type="text/javascript" src="/js/jquery.bootstrap.min.js"></script>

    <style type="text/css">
        .el-register-form {
            width: 600px;
            margin-left: auto;
            margin-right: auto;
            margin-top: 20px;
        }

        .el-register-form .form-control {
            width: 220px;
            display: inline;
        }
    </style>

    <script>

        $(function () {
            $("#reChargeForm").validate({
                rules: {
                    amount: {
                        required: true,
                        number: true,
                        rangelength: [1, 5]
                    }
                },
                    messages: {
                        amount: {
                            required: "请输入金额!",
                            number: "必须为数字",
                            rangelength: "充值额度必须在{0}-{10000}之间!"
                        }
                    },
                    errorClass: "text-danger",
                    highlight: function (element) {
                        $(element).closest("div.form-group").addClass("has-error");
                    },
                    unhighlight: function (element) {
                        $(element).closest("div.form-group").removeClass("has-error");
                    }
            });
            })
        </script>
        </head>
<body>
<!-- 网页顶部导航 -->
<#include "common/head-tpl.ftl" />
<!-- 网页导航 -->
<!-- 在当前的freemarker的上下文中,添加一个变量,变量的名字叫nav,变量的值叫personal -->
<#assign currentNav="personal" />
<#include "common/navbar-tpl.ftl" />


        <!-- 网页导航 -->
        <div class="navbar navbar-default el-navbar">
            <div class="container">
                <div class="navbar-header">
                    <span class="el-page-title">充值中心</span>
                </div>
            </div>
        </div>

    <!-- 网页内容 -->
    <div class="container">
                <form id="reChargeForm" class="form-horizontal el-login-form" action="/alipay.do" method="post" >
                    <p class="h4" style="margin: 10px 10px 10px;color:#999;" >请输入充值金额</p>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <input type="text" autocomplete="off" name="money" class="form-control" id="money" style="width: 150px;"/>
                        </div>
                    </div>
                    <div class="form-gorup">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-success" style="width: 100px;">
                                充值
                            </button>
                        </div>
                    </div>
                </form>
            </div>

</body>
</html>