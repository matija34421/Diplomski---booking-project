import ReservationPost from "../models/reservationModels/ReservationPost";

async function fetchPostReservation(reservation:ReservationPost) {
    try {
        console.log(reservation);
        const response = await fetch('http://localhost:8080/api/reservations', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(reservation),
        });

        if (!response.ok) {
            return { success: false, message:'Objekat je vec rezervisan tog datuma!' };
        }
        return { success: true };
    } catch (err) {
        return { success: false, message: 'An unexpected error occurred' };
    }
}

export default fetchPostReservation;