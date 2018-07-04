<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        #progress:after {
            content: '%';
        }
    </style>
</head>
<body>
<h3>File upload demo</h3>
<form action="TestServlet" method="post" enctype="multipart/form-data" id="dataForm">
    <input type="file" name="file" id="fileInput"> <br>
    <input type="submit" value="submit" id="submit">
</form>
<div id="progress"></div>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    (function () {
        var form = document.getElementById("dataForm");
        var progress = document.getElementById("progress");

        $("#submit").click(function (event) {
            //阻止默认事件
            event.preventDefault();
            //循环查看状态
            var t = setInterval(function () {
                $.ajax({
                    url: 'getProgress',
                    type: 'POST',
                    dataType: 'text',
                    data: {
                        filename: fileInput.files[0].name,
                    },
                    success: function (responseText) {
                        var data = JSON.parse(responseText);
                        //前台更新进度
                        progress.innerText = parseInt((data.progress / data.size) * 100);
                    },
                    error: function () {
                        console.log("error");
                    }
                });
            }, 500);
            //上传文件
            $.ajax({
                url: 'upFile',
                type: 'POST',
                dataType: 'text',
                data: new FormData(form),
                processData: false,
                contentType: false,
                success: function () {
                    //上传完成，清除循环事件
                    clearInterval(t);
                    //将进度更新至100%
                    progress.innerText = 100;
                },
                error: function () {
                    console.log("error");
                }
            });
            return false;
        });
    })();
</script>
</body>
</html>