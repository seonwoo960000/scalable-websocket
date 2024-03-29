const stompClient = new StompJs.Client({
  brokerURL: 'ws://localhost:8080/devvy-ws'
});

stompClient.onConnect = (frame) => {
  setConnected(true);
  console.log('Connected: ' + frame);
  const channelName = $("#channelName").val();
  stompClient.subscribe(`/chat/in/${channelName}`, (chat) => {
    sendChatMessage(JSON.parse(chat.body).content);
  });
};

stompClient.onWebSocketError = (error) => {
  console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
  console.error('Broker reported error: ' + frame.headers['message']);
  console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  } else {
    $("#conversation").hide();
  }
  $("#chatting").html("");
}

function connect() {
  stompClient.activate();
}

function disconnect() {
  stompClient.deactivate();
  setConnected(false);
  console.log("Disconnected");
}

function sendName() {
  const channelName = $("#channelName").val();
  const name = $("#name").val();
  stompClient.publish({
    destination: `/chat/in/${channelName}`,
    body: JSON.stringify({'content': name})
  });
}

function sendChatMessage(message) {
  $("#chatting").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
  $("form").on('submit', (e) => e.preventDefault());
  $("#connect").click(() => connect());
  $("#disconnect").click(() => disconnect());
  $("#send").click(() => sendName());
});
