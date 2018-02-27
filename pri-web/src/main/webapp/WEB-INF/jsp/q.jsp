<html>
<head>
    <title></title>
    <style type="text/css">
        .div1 {
            position: absolute;/*绝对定位，将DIV1外面的大框用绝对定位进行div的固定*/
            margin-left: 15%;/*距左15%的距离*/
            margin-top: 20px;/*距上20px的距离*/
            width: 1000px;/*宽设定1000px*/
            height: 400px;/*高设定400px*/
            background-color: yellow;
        }
        .div2 {
           position: relative;
            float: left;
            height: 400px;
            width: 300px;
            background-color: red;
        }
        .div3 {
           position: relative;
            float: left;
            height: 200px;
            width: 400px;
            background-color: blue;
        }
       .div4 {
            position: relative;
            float: left;
            height: 200px;
            width: 300px;
           background-color: pink;
        }
    </style>
</head>
<body>
    <div class="div1">
        <div class="div2">2</div>
        <div class="div3">3</div>
        <div class="div4">4</div>
    </div>
</body>
</html>