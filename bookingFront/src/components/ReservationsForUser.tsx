import Navbar from './navbar';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Reservation from '../models/reservationModels/Reservation';
import fetchReservationsForUser from '../functions/fetchReservationsForUser';
import '../assets/reservationsForUser.css';
import fetchDeleteReservation from '../functions/deleteReservationFetch';

type ReservationWithPrice = Reservation & {
    totalPrice: number;
    dateDifference: number;
};

const ReservationsForUser = () => {
    const [reservations, setReservations] = useState<ReservationWithPrice[]>([]);
    const [deleteReservationId, setDeleteReservationId] = useState<number | null>(null); // State to track which reservation is toggled for deletion

    const navigate = useNavigate();

    const calculateDateDifference = (start: string, end: string) => {
        const startDate = new Date(start);
        const endDate = new Date(end);
    
        const diffTime = endDate.getTime() - startDate.getTime();
        const diffDays = diffTime / (1000 * 60 * 60 * 24);
    
        return diffDays;
    };

    useEffect(() => {
        const userId = localStorage.getItem('id') ? parseInt(localStorage.getItem('id') as string) : 0;

        fetchReservationsForUser(userId)
            .then(fetchedData => {
                const updatedReservations = fetchedData.map((reservation: Reservation) => {
                    const diffDays = calculateDateDifference(reservation.start_date, reservation.end_date);
                    const totalPrice = reservation.property.price * diffDays;

                    return {
                        ...reservation,
                        dateDifference: diffDays,
                        totalPrice: totalPrice
                    };
                });

                setReservations(updatedReservations);
            })
            .catch(err => console.error("Error fetching data:", err));
    }, []);

    const navigateToProperty = (id: number) => {
        navigate('/property/' + id);
    }

    const toggleDeleteButton = (id: number) => {
        // If the clicked reservation is already open, close it. Otherwise, open it.
        setDeleteReservationId(deleteReservationId === id ? null : id);
    }

    const handleDelete = (id: number) => {
        fetchDeleteReservation(id);
        alert("Uspesno ste obrisali rezervaciju!");
        setDeleteReservationId(null); // Reset after deletion
    }

    return (
        <div>
            <Navbar />
            <h2 style={{ color: "#386180", marginLeft: "20%" }}>Vaše rezervacije: </h2>
            <div className='mainContainer'>
                    {reservations.length > 0 ? 
                        (reservations.map((reservationData, index) => (
                            <div key={index} className='reservationContainer'>
                                <div className='reservationPic' style={{ backgroundImage: `url('/src/assets/${reservationData.property.picture_path}')` }}></div>
                                <div className='reservation-td'>
                                    <div className='reservation-title'>{reservationData.property.title}</div>
                                    <textarea className='reservation-desc'>{reservationData.property.description}</textarea>
                                </div>
                                <div className='reservation-pd'>
                                    <div className='reservation-price'>
                                        Total Price: {(reservationData.totalPrice * 117).toFixed(2)}
                                    </div>
                                    <div className='reservation-dates'>Od: <strong>{reservationData.start_date}</strong> <br />Do: <strong>{reservationData.end_date}</strong></div>
                                    <div className='reservation-click' onClick={() => { navigateToProperty(reservationData.property.id) }}>Pogledajte objekat</div>
                                    
                                    {deleteReservationId === reservationData.id ? (
                                        <div className='button-div'>
                                            <div className='reservation-delete' onClick={() => handleDelete(reservationData.id)}>Da li ste sigurni?</div>
                                            <div className='reservation-click' onClick={() => toggleDeleteButton(reservationData.id)}>Odustanite</div>
                                        </div>
                                    ) : (
                                        <div className='reservation-delete' onClick={() => toggleDeleteButton(reservationData.id)}>Obrišite rezervaciju</div>
                                    )}
                                </div>
                            </div>
                        )))
                        : <div>No reservations found</div>}
            </div>
        </div>
    );
}

export default ReservationsForUser;
