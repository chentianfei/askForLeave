<%--
  Created by IntelliJ IDEA.
  User: CTF
  Date: 2021/12/6
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--基础引入--%>
    <%@include file="/pages/common/baseinfo.jsp"%>
    <style>

        /*! normalize.css v3.0.2 | MIT License | git.io/normalize */
        html{
            font-family:sans-serif;
            -ms-text-size-adjust:100%;
            -webkit-text-size-adjust:100%
        }

        body{
            margin:0
        }

        article,aside,details,figcaption,
        figure,footer,header,hgroup,main,
        menu,nav,section,summary{
            display:block
        }

        audio,canvas,progress,video{
            display:inline-block;
            vertical-align:baseline
        }

        audio:not([controls]){
            display:none;height:0
        }

        [hidden],template{
            display:none
        }

        a{
            background-color:transparent
        }

        a:active,a:hover{
            outline:0
        }
        abbr[title]{
            border-bottom:1px dotted
        }

        b,strong{
            font-weight:bold
        }

        dfn{
            font-style:italic
        }

        h1{
            font-size:2em;
            margin:0.67em 0
        }

        mark{
            background:#ff0;
            color:#000
        }

        small{
            font-size:80%
        }

        sub,sup{
            font-size:75%;
            line-height:0;
            position:relative;
            vertical-align:baseline}

        sup{top:-0.5em}

        sub{bottom:-0.25em}

        img{border:0}

        svg:not(:root){
            overflow:hidden
        }

        figure{margin:1em 40px}

        hr{-moz-box-sizing:content-box;
            -webkit-box-sizing:content-box;
            box-sizing:content-box;
            height:0
        }

        pre{overflow:auto}
        code,kbd,pre,samp{
            font-family:monospace, monospace;
            font-size:1em
        }
        button,input,optgroup,select,textarea{
            color:inherit;
            font:inherit;
            margin:0
        }

        button{
            overflow:visible
        }

        button,select{
            text-transform:none
        }

        button,html input[type="button"],input[type="reset"],input[type="submit"]{
            -webkit-appearance:button;
            cursor:pointer}

        button[disabled],html input[disabled]{
            cursor:default
        }

        button::-moz-focus-inner,input::-moz-focus-inner{
            border:0;
            padding:0
        }

        input{line-height:normal}input[type="checkbox"],input[type="radio"]{
                                     -webkit-box-sizing:border-box;
                                     -moz-box-sizing:border-box;
                                     box-sizing:border-box;
                                     padding:0}

        input[type="number"]::-webkit-inner-spin-button,input[type="number"]::-webkit-outer-spin-button{
            height:auto
        }

        input[type="search"]{
            -webkit-appearance:textfield;
            -moz-box-sizing:content-box;
            -webkit-box-sizing:content-box;
            box-sizing:content-box
        }

        input[type="search"]::-webkit-search-cancel-button,input[type="search"]::-webkit-search-decoration{
            -webkit-appearance:none
        }

        fieldset{
            border:1px solid #c0c0c0;
            margin:0 2px;
            padding:0.35em 0.625em 0.75em
        }

        legend{
            border:0;
            padding:0
        }

        textarea{
            overflow:auto
        }

        optgroup{
            font-weight:bold
        }

        table{
            border-collapse:collapse;
            border-spacing:0
        }

        td,th{
            padding:0
        }

        .btn {
            display: inline-block;
            *display: inline;
            *zoom: 1;
            padding: 4px 10px 4px;
            margin-bottom: 0;
            font-size: 13px;
            line-height: 18px;
            color: #333333;
            text-align: center;
            text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
            vertical-align: middle;
            background-color: #f5f5f5;
            background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -ms-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6));
            background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: linear-gradient(top, #ffffff, #e6e6e6);
            background-repeat: repeat-x;
            filter: progid:dximagetransform.microsoft.gradient(startColorstr=#ffffff, endColorstr=#e6e6e6, GradientType=0);
            border-color: #e6e6e6 #e6e6e6 #e6e6e6;
            border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
            border: 1px solid #e6e6e6;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            cursor: pointer;
            *margin-left: .3em; }

        .btn:hover, .btn:active, .btn.active, .btn.disabled, .btn[disabled] {
            background-color: #e6e6e6;
        }

        .btn-large {
            padding: 9px 14px;
            font-size: 15px;
            line-height: normal;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
        }

        .btn:hover {
            color: #333333;
            text-decoration: none;
            background-color: #e6e6e6;
            background-position: 0 -15px;
            -webkit-transition: background-position 0.1s linear;
            -moz-transition: background-position 0.1s linear;
            -ms-transition: background-position 0.1s linear;
            -o-transition: background-position 0.1s linear;
            transition: background-position 0.1s linear;
        }

        .btn-primary, .btn-primary:hover {
            text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
            color: #ffffff;
        }

        .btn-primary.active {
            color: rgba(255, 255, 255, 0.75);
        }

        .btn-primary {
            background-color: #4a77d4;
            background-image: -moz-linear-gradient(top, #6eb6de, #4a77d4);
            background-image: -ms-linear-gradient(top, #6eb6de, #4a77d4);
            background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#6eb6de), to(#4a77d4));
            background-image: -webkit-linear-gradient(top, #6eb6de, #4a77d4);
            background-image: -o-linear-gradient(top, #6eb6de, #4a77d4);
            background-image: linear-gradient(top, #6eb6de, #4a77d4);
            background-repeat: repeat-x;
            filter: progid:dximagetransform.microsoft.gradient(startColorstr=#6eb6de, endColorstr=#4a77d4, GradientType=0);
            border: 1px solid #3762bc;
            text-shadow: 1px 1px 1px rgba(0,0,0,0.4);
            box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.5);
        }

        .btn-primary:hover, .btn-primary:active, .btn-primary.active, .btn-primary.disabled, .btn-primary[disabled] {
            filter: none;
            background-color: #4a77d4;
        }

        .btn-block {
            width: 50%;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            margin: auto;
            display:block;
        }

        * { -webkit-box-sizing:border-box; -moz-box-sizing:border-box; -ms-box-sizing:border-box; -o-box-sizing:border-box; box-sizing:border-box; }

        html { width: 100%; height:100%; overflow:hidden; }

        body {
            width: 100%;
            height:100%;
            font-family: 'Open Sans', sans-serif;
            background: #092756;
            background: -moz-radial-gradient(0% 100%,  rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%),-moz-linear-gradient(top,  rgba(57,173,219,.25) 0%, rgba(42,60,87,.4) 100%), -moz-linear-gradient(-45deg,  #670d10 0%, #092756 100%);
            background: -webkit-radial-gradient(0% 100%, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -webkit-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -webkit-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
            background: -o-radial-gradient(0% 100%,  rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -o-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -o-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
            background: -ms-radial-gradient(0% 100%, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -ms-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -ms-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
            background: -webkit-radial-gradient(0% 100%,  rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), linear-gradient(to bottom,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), linear-gradient(135deg,  #670d10 0%,#092756 100%);
            filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#3E1D6D', endColorstr='#092756',GradientType=1 );
        }

        .login {
            position: absolute;
            top: 0;
            bottom: 20%;
            left: 0;
            right: 0;
            margin: auto;
            width:500px;
            height:300px;
        }

        .login h1 {
            color: #fff;
            text-shadow: 0 0 10px rgba(0,0,0,0.3);
            letter-spacing:1px;
            text-align:center;
        }

        input {
            display: block;
            width: 50%;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            margin:10px auto;
            /*margin-bottom: 10px;*/
            background: rgba(0,0,0,0.3);
            border: none;
            outline: none;
            padding: 10px;

            font-size: 13px;
            color: #fff;
            text-shadow: 1px 1px 1px rgba(0,0,0,0.3);

            border: 1px solid rgba(0,0,0,0.3);
            border-radius: 4px;

            box-shadow: inset 0 -5px 45px rgba(100,100,100,0.2), 0 1px 1px rgba(255,255,255,0.2);
            -webkit-transition: box-shadow .5s ease;
            -moz-transition: box-shadow .5s ease;
            -o-transition: box-shadow .5s ease;
            -ms-transition: box-shadow .5s ease;
            transition: box-shadow .5s ease;
        }

        input:focus {
            box-shadow: inset 0 -5px 45px rgba(100,100,100,0.4), 0 1px 1px rgba(255,255,255,0.2);
        }

    </style>
</head>

<body>
    <div  class="login">

       <div>
            <h1>仁布县人社局请销假管理系统</h1>
            <form method="post" action="loginServlet?action=login">
                <input type="text" name="username" id="username" placeholder="用户名" autocomplete="off"
                       required="required" />

                <input type="password" name="password" id="password" placeholder="密码"  autocomplete="off"
                       required="required" />

                <button type="submit"  class="btn btn-primary btn-block btn-large">登录</button>
            </form>
        </div>

        <div style="position: absolute;margin: auto;left:26%">
           <span style="
           display: block;
           top: 0;
           bottom: 0;
           left: 0;
           right: 0;
           margin: 10px auto;
           color:#FF0000;
           letter-spacing:3px">
               ${requestScope.message}
           </span>
        </div>

    </div>

</script>

</body>
</html>
