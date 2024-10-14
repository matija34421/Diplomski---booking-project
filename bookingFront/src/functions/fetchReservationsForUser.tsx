import Reservation from "../models/reservationModels/Reservation";

async function fetchPropertiesForUser(id:number): Promise<any> {
    try {
        const response = await fetch('http://localhost:8080/api/reservations/userId/'+id, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+localStorage.getItem('jwt')
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
        return null; 
      }
     
    } 
  
  export default fetchPropertiesForUser;