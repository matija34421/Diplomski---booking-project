async function fetchProperties(): Promise<any> {
    try {
        const response = await fetch('http://localhost:8080/api/properties/all', {
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
        return data;
      }catch (err) {
        console.error("Fetch error:", err);
        return null; 
      }
     
    } 
  
  export default fetchProperties;