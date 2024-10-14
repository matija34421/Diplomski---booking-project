import FullProperty from "../models/propertyModels/FullProperty";

async function fetchProperty(id:number): Promise<FullProperty | null> {
    try {
        const response = await fetch('http://localhost:8080/api/properties/'+id, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          },
        });
  
        if (!response.ok) {
          throw new Error('Error: ' + response.status);
        }

        const data = await response.json();
        return data as FullProperty;
      }catch (err) {
        console.error("Fetch error:", err);
        return null; 
      }
     
    } 
  
  export default fetchProperty;