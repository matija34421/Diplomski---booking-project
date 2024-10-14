async function fetchDeleteReservation(id:number): Promise<any> {
    try {
        const response = await fetch('http://localhost:8080/api/reservations/'+id, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ localStorage.getItem('jwt'),
          },
        });
  
        if (!response.ok) {
          throw new Error('Error: ' + response.status);
        }

        const data = await response.json();
        console.log(data);
        return data;
      }catch (err) {
        console.error("Fetch error:", err);
        return null; 
      }
     
    } 
  
  export default fetchDeleteReservation;