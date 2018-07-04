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
<form action="eTest" method="post" enctype="multipart/form-data" id="dataForm">
    <input type="file" name="file" id="fileInput"> <br>
    <input type="submit" value="submit" id="submit">
</form>
<div id="progress"></div>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
//    (function () {
//        var form = document.getElementById("dataForm");
//        var progress = document.getElementById("progress");
//
//        $("#submit").click(function (event) {
//            $.ajax({
//                url: 'eTest',
//                type: 'POST',
//                dataType: 'text',
//                data: new FormData(form),
//                processData: false,
//                contentType: false,
//                success: function () {
//                    //将进度更新至100%
//                    progress.innerText = 100;
//                },
//                error: function () {
//                    console.log("error");
//                }
//            });
//            return false;
//        });
//    })();
</script>
</body>
</html>