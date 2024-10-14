

import { useState,useEffect } from "react";
import universalFetch from "../../functions/universalFetch";
import Reservation from "../../models/reservationModels/Reservation";

const AdminReservationManipulation = () => {

  const [filteredData, setFilteredData] = useState<Reservation[]>([]);
  const [nameSort, setNameSort] = useState<string>('');
  const [reservations, setReservations] = useState<Reservation[]>([]);
  const [selectedReservationId, setselectedReservationId] = useState<number | null>(null);
  const [reservation,setReservation] = useState<Reservation>({
    id:0,
    start_date:'',
    end_date:'',
    user:{
      id: 0,
      username: '',
      email: '',
      address: '',
      phone: ''
    },
    property:{
      id:0,
      type:'',
      description:'',
      title:'',
      picture_path:'',
      price:0,
      location:''
    }
  });



  function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
    const { name, value } = e.target;

    setReservation((prevReservation) => ({
        ...prevReservation,
        [name]: value,
      }));
  }

  function handleUpdate() {
    const jwt = localStorage.getItem('jwt');
    if (jwt) {
      universalFetch('http://localhost:8080/api/reservations', 'PUT', jwt, reservation)
        .catch(err => console.error("Error fetching data:", err));
    }
  }

  function handleDelete() {
    const jwt = localStorage.getItem('jwt');
    if (jwt) {
      universalFetch('http://localhost:8080/api/reservations/' + selectedReservationId, 'DELETE', jwt, reservation)
      .then(() => {
          universalFetch('http://localhost:8080/api/reservations', 'GET', jwt)
            .then(updatedReservations => {
              setReservation(updatedReservations); 
              setFilteredData(updatedReservations); 
            })
            .catch(err => console.error("Error fetching updated data:", err));
        })
      .catch(err => console.error("Error fetching data:", err));
    }
  }

  function handleSort(e: React.ChangeEvent<HTMLInputElement>) {
    const value = e.target.value;
    setNameSort(value);

    if (value === '') {
      setFilteredData(reservations);
    } else {
      const filtered = reservations.filter(reservation => 
        reservation.property.title.toLowerCase().includes(value.toLowerCase())
      );
      setFilteredData(filtered);
    }
  }

  useEffect(() => {
    const jwt = localStorage.getItem('jwt');
    if (jwt) {
      universalFetch('http://localhost:8080/api/reservations', 'GET', jwt, undefined)
        .then(fetchedData => {
          setReservations(fetchedData);
          setFilteredData(fetchedData);
        })
        .catch(err => console.error("Error fetching data:", err));
    }
  }, []);

  function handleEdit(reservationData: Reservation) {
    setselectedReservationId(reservationData.id);
    setReservation(reservationData);
  }

  return (
    <>
      <h2>Reservation List</h2>
        <label htmlFor="sortByName">Sort by assets name:</label>
        <input type="text" name="sortByName" id="sortByName" value={nameSort} onChange={handleSort} />
        <table>
        <tr>
          <th>Id</th>
          <th>Client</th>
          <th>First Day</th>
          <th>Last Day</th>
          <th>Asset</th>
        </tr>
        {filteredData.map((reservationData) => (
         reservationData && reservationData.user ? (
          <tr key={reservationData.id}>
            <td>
            <input 
              type="text"
              name="id"
              id={`id-${reservationData.id}`}
              value={reservationData.id === selectedReservationId ? reservation.id : reservationData.id} 
              onChange={handleChange}
              disabled={reservationData.id !== selectedReservationId}
            />
            </td>
          <td>
            <input
              type="text"
              name="user"
              id={`user-${reservationData.user.id}`}
              value={reservationData.id === selectedReservationId ? reservation.user.username : reservationData.user.username}
              onChange={handleChange}
              disabled={true}  // Keep this field read-only
            />
          </td>

          <td>
            <input
              type="date"
              name="start_date"
              id={`start_date-${reservationData.start_date}`}
              value={reservationData.id === selectedReservationId ? reservation.start_date : reservationData.start_date}
              onChange={handleChange}
              disabled={reservationData.id !== selectedReservationId}
            />
          </td>

          <td>
            <input
              type="date"
              name="end_date"
              id={`end_date-${reservationData.end_date}`}
              value={reservationData.id === selectedReservationId ? reservation.end_date : reservationData.end_date}
              onChange={handleChange}
              disabled={reservationData.id !== selectedReservationId}
            />
          </td>

          <td>
            <input
              type="text"
              name="property"
              id={`property-${reservationData.property.id}`}
              value={reservationData.id === selectedReservationId ? reservation.property.title : reservationData.property.title}
              onChange={handleChange}
              disabled={true}  // Keep this field read-only
            />
          </td>


            {reservationData.id === selectedReservationId ? (
              <td><button onClick={handleUpdate}>Update</button></td>
            ) : (
              <td><button onClick={() => handleEdit(reservationData)}>Edit</button></td>
            )}
            <td><button
              onClick={handleDelete}
              disabled={reservationData.id !== selectedReservationId}>
              Delete
            </button></td>
        </tr>
    ) : null
  ))}
        </table>
    </>
  )
}

export default AdminReservationManipulation
