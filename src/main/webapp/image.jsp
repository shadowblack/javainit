<html>
<head>

</head>

<body>
<form method="post" action="upload" enctype="multipart/form-data">
    <input type="text" name="ext" size="30"/>
    <input type="text" name="name" id="name" size="30"/>
    <input type="file" accept="image/*" name="file" id="file" />
    <input type="submit" value='Save' onclick="return validateForm()"/>
</form>
</body>
</html>