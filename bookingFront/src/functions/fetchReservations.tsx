import Reservation from "../models/reservationModels/Reservation";

async function fetchReservationsForProperty(id:number): Promise<Reservation[]> {
    try {
        const response = await fetch('http://localhost:8080/api/reservations/propertyId/'+id, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          },
        });
  
        if (!response.ok) {
          throw new Error('Error: ' + response.status);
        }

        const data = await response.json();
        console.log(data);
        return data as Reservation[];
      }catch (err) {
        console.error("Fetch error:", err);
        return []; 
      }
     
    } 
  
  export default fetchReservationsForProperty;