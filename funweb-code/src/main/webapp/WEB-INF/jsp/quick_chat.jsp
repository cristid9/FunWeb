<!DOCTYPE html>
<html>
<head>
    <title>Chat</title>
    <link rel = "stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/2.0.2/socket.io.js"></script>
    <link rel="stylesheet" href="/resources/css/qchat_style.css">
</head>
<body>

<center><h4><b>Share your Web Technologies knowledge</b></h4></center>
<br>
<div class = "container">

    <div class = "row" id = "messageArea">
        <div class = "col-md-4">
            <div class = "well">
                <h4><b> Online users </b></h4>
                <ul class = "list-group" id = "users"> </ul>

            </div>

        </div>
        <div class = "col-md-8">
            <div class="chat" id = "chat">

            </div>

            <form id = "messageForm">
                <div class = "form-group">
                    <label>Enter Message</label>
                    <textarea class = "form-control" id = "message" name="styled-textarea" autofocus> </textarea>
                    <br/>
                    <input type="submit" class = "btn btn-primary" value = "Send Message"/>
                </div>
            </form>
        </div>

    </div>

</div>
<script>
    // TODO: refactor this to external file
    $(function(){
        var socket = io.connect("http://localhost:3000");
        var $messageForm = $('#messageForm');
        var $message = $('#message');
        var $chat = $('#chat');
        var $userFormArea = $('#userFormArea');
        var $userForm = $('#userForm');
        var $messageArea = $('#messageArea');
        var $users = $('#users');
        var $username = '${username}';// This value is provided by spring

        setTimeout(function () {
            socket.emit('new user', $username, function (data) {
                console.log("dfsdf");
            });
        }, 1000);

        $messageForm.submit(function(e){
            e.preventDefault();
            if($message.val() != "" ){
                socket.emit('send message', $message.val());
                $message.val('');
            }
        });

        socket.on('new message', function(data){
            var currentdate = new Date();
            var datetime =
                currentdate.getHours() + ":"
                + currentdate.getMinutes();

            $chat.append('<div class = "well"><strong> '+ data.user +'</strong> (<b>' + datetime + '</b>) : ' + data.msg + '<div>');
            //$chat.scrollTop($chat.height())
            var objDiv = document.getElementById("chat");
            objDiv.scrollTop = objDiv.scrollHeight;

        });

//        $userForm.submit(function(e){
//            e.preventDefault();
//            socket.emit('new user', $username.val(),function(data){
//                if(data){
//                    $userFormArea.hide();
//                    $messageArea.show();
//                }
//            });
//            $username.val('');
//        });
        socket.on('get users', function(data){
            var html = '';
            for (var i = 0; i < data.length; i++){
                html +='<li ><span>' + data[i]+ '</span></li>';
            }
            $users.html(html);
        });

        $('#message').on('keypress', function (e) {
            if(e.which === 13){

                $(this).attr("disabled", "disabled");

                socket.emit('send message', $message.val());
                $message.val('');
                $(this).removeAttr("disabled");
                $("#message").focus();
            }
        });

        $('#message').keydown(function(e) {
            if(e.keyCode == 13) {
                e.preventDefault(); // Makes no difference
                $(this).parent().submit(); // Submit form it belongs to
            }
        });

    });


</script>


</body>
</html>