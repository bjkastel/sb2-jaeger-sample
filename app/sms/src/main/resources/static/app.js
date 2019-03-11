Vue.config.debug = true

let stompClient = null;

const app = new Vue({
    el: "#app",
    mounted: function() {
        this.$nextTick(function() {
            let socket = new SockJS("/data-info");
            stompClient = Stomp.over(socket);
            stompClient.connect(
                {},
                function(frame) {
                    console.log("Connected: " + frame);

                    stompClient.subscribe("/sms", function(val) {
                        console.log(val);
                        console.log(JSON.parse(val.body));
                        let sms = JSON.parse(val.body);
                        app.smsList.push(sms);
                        app.$notification.show(sms.number, {
                            body: sms.text,
                            requireInteraction: true
                        }, {})
                    });
                }
            );
            app.$notification.requestPermission().then(console.log);
        });
    },
    data: function() {
        return {
            smsList: []
        };
    }
});
