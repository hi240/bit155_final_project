//개인페이지에서는 채팅버튼 숨기기
if(window.location.pathname == '/teamMain.do') {
	$("#fixedBtn").hide();
} else {
	$("#fixedBtn").show();
}

//팝오버 띄우기
$("#fixedBtn").popover({
/*  title: "Notifiche",*/
	html: true,
	sanitize: false,
	placement: "bottom",
	container: "body",
	content: function() {
	  return $("#alert_list").html();
	}
});

///////////////////////////////////////////////////////////웹소켓

var ws = null;
var loginId = null;

$(function () {
    ws = new WebSocket('ws://192.168.0.56:8090/chatting');
	ws.onopen = function() {
   	 /*   console.log('웹소켓 서버 접속 성공-chat.js');*/
   	    ws.send('connent∥'+teamNo+"∥"+currUserNickname+"∥"+"님 접속∥"+currUserImage);
    };
    // 메세지 받기
    ws.onmessage = function(evt) {
    	makeChatBox(evt.data);
    };
    ws.onerror = function(evt) {
    	console.log('웹소켓 에러 발생-chat.js : ' + evt.data);
    };
    ws.onclose = function() {
    	/*console.log("웹소켓 연결이 종료됨-chat.js");*/
    };
});

$('#fixedBtn').on({
    "shown.bs.popover": function(){
        var input = $(".popover input#message");
        input.focus();
        $(document).on('keydown', input, function(key) {
        	if (key.keyCode == 13) {
        		if(input.val() != "") {
        			// 웹소켓 서버에 데이터 전송하기
        			ws.send('send∥'+teamNo+"∥"+currUserNickname+"∥"+input.val()+"∥"+currUserImage);
        			input.val("");
        		}
        	}
    	});
		$(document).on('click', '#sendBtn', function() {
			if(input.val() != "") {
				// 웹소켓 서버에 데이터 전송하기
				ws.send('send∥'+teamNo+"∥"+currUserNickname+"∥"+input.val()+"∥"+currUserImage);
				input.val("");
			}
		});
    }
});

/**
* @함수명 : makeChatBox(data)
* @작성일 : 2020. 7. 28.
* @작성자 : 김혜린
* @설명 : 채팅박스 그리는 함수
**/
function makeChatBox(data) {
	var nickAndMsg = data.split("∥");
	var notice = nickAndMsg[0];
	var nick = nickAndMsg[2];
	var msg = nickAndMsg[3];
	var image = nickAndMsg[4];
	var time = nickAndMsg[6];
	/*console.log("에코닉네임:"+nick);
	console.log("에코메세지:"+msg);
	console.log("에코타임:"+time);
	console.log("에코image:"+image);*/
	let html = "";
	if(notice.trim() == "notice") {
		html += '<li class="chat-item odd list-style-none mt-3">';
		html += 	'<div class="chat-content text-center d-inline-block">';
		html += 		'<div class="box msg p-2 d-inline-block mb-1 box" style="background-color:#ffffcf;">'+ nick+msg +'</div>';
		html += 		'<br>';
		html += 	'</div>';
		html += '</li>';
	} else {
		if(nick.trim() == currUserNickname) {
			html += '<li class="chat-item odd list-style-none mt-3">';
			html += 	'<div class="chat-content text-right d-inline-block">';
			html += 		'<div class="box msg p-2 d-inline-block mb-1 box">'+ msg +'</div>';
			html += 		'<br>';
			html += 	'</div>';
			html += 	'<div class="chat-time text-right d-block font-10 mt-1 mr-0 mb-3 time">'+ time +'</div>';
			html += '</li>';
		} else {
			html += '<li class="chat-item list-style-none mt-3">';
			html += 	'<div class="chat-img d-inline-block">';
			html +=				'<div class="user-img rounded-circle" style="float: left; background-color: white; overflow: hidden; height: 40px; width: 40px;">'
			html +=				'<div style="top: 0; left: 0; right: 0; bottom: 0; transform: translate(50%, 50%);">'
			html += 				'<img src="assets/images/userImage/'+ image +'" alt="user"';
			html += 					'style="width: auto; height: 40px; transform: translate(-50%, -50%); display:block;"';
			html += 					'data-toggle="tooltip" data-placement="top" title="'+currUser+'">';
			html +=					'</div>'
			html +=				'</div>'
			html += 	'</div>';
			html += 	'<div class="chat-content d-inline-block" style="margin-left:5px;">';
			html += 		'<h6 class="font-weight-medium">'+ nick +'</h6>';
			html += 		'<div class="msg p-2 d-inline-block mb-1">'+ msg +'</div>';
			html += 	'</div>';
			html += 	'<div class="chat-time d-block font-10 mt-1 mr-0 mb-3">'+ time +'</div>';
			html += '</li>';
		}
	}

	$(".popover #msgUl").append(html);
	$(".popover .chat-box").scrollTop($(".popover #msgUl")[0].scrollHeight);
}
