Vue.config.debug = true

let stompClient = null;

const app = new Vue({
    el: "#app",
    methods: {
        reserve() {
            axios({ method: "PUT", "url": "http://localhost:8080/reservation", "data": this.reservation }).then(result => {
                app.reservationList = result.data;
            }, error => {
                console.error(error);
            });
        },
        reserveSimple() {
            axios({ method: "GET", "url": "http://localhost:8080/reservation" }).then(result => {
                app.reservationList = result.data;
            }, error => {
                console.error(error);
            });
        }
    },
    data: function() {
        return {
            reservation: {
                firstName: "James",
                lastName: "Bond",
                reservedSeats: 2,
                mobileNumber: "+49 1234 123456"
            }
        };
    }
});
