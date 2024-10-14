import Picture from "../models/pictureModels/Picture";

async function fetchPictures(id:number): Promise<Picture[] | null> {
    try {
        const response = await fetch('http://localhost:8080/api/pictures/'+id, {
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
        return data as Picture[];
      }catch (err) {
        console.error("Fetch error:", err);
        return []; 
      }
     
    } 
  
  export default fetchPictures;