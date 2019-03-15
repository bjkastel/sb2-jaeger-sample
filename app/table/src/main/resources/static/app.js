Vue.config.debug = true

let stompClient = null;

const app = new Vue({
    el: "#app",
    methods: {
        getReservationsFast() {
            axios({ method: "GET", "url": "http://localhost:8081/reservations/fast" }).then(result => {
                app.reservationList = result.data;
            }, error => {
                console.error(error);
            });
        },
        getReservationsSlow() {
            axios({ method: "GET", "url": "http://localhost:8081/reservations/slow" }).then(result => {
                app.reservationList = result.data;
            }, error => {
                console.error(error);
            });
        }
    },
    data: function() {
        return {
            reservationList: []
        };
    }
});
