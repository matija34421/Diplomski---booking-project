import Review from "../models/ReviewModels/Review";

async function fetchReviews(id:number): Promise<Review[]> {
    try {
        const response = await fetch('http://localhost:8080/api/properties/reviews/'+id, {
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
        return data as Review[];
      }catch (err) {
        console.error("Fetch error:", err);
        return []; 
      }
     
    } 
  
  export default fetchReviews;