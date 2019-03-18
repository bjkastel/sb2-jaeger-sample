Vue.config.debug = true

let stompClient = null;

const app = new Vue({
    el: "#app",
    methods: {
        getReservationsFast() {
            app.startTime = performance.now();
            axios({ method: "GET", "url": "http://localhost:8081/reservations/fast" }).then(result => {
                app.reservationList = result.data;
                app.duration = Number(performance.now() - app.startTime).toFixed(2);
            }, error => {
                console.error(error);
            });
        },
        getReservationsSlow() {
            app.startTime = performance.now();
            axios({ method: "GET", "url": "http://localhost:8081/reservations/slow" }).then(result => {
                app.reservationList = result.data;
                app.duration = Number(performance.now() - app.startTime).toFixed(2);
            }, error => {
                console.error(error);
            });
        }
    },
    data: function() {
        return {
            reservationList: [],
            startTime: null,
            duration: 0
        };
    }
});
