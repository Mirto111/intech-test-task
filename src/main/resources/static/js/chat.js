$(document).on("click", ".button", function () {
    sendMessage();
});

var stompClient = null;

window.addEventListener("load", function () {
    connect();
});


function connect() {
    let socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected);
}

function onConnected() {
    stompClient.subscribe('/topic/chat', onMessageReceived);
}

function sendMessage() {
    let messageContent = $("#text-message").val();
    if (messageContent && stompClient) {
        stompClient.send("/app/send/message", {}, JSON.stringify({text: messageContent}));
    }
}

function onMessageReceived(payload) {
    let message = JSON.parse(payload.body);
    $(".chat-messages")
        .append('<div id=' + message.id + ' class="container">' +
            '<span class="name-left">' + message.from + '</span>' +
            '<p class="mess">' + message.text + '</p>' +
            '<span class="time-right">' + message.dateTime + '</span></div>');
}

