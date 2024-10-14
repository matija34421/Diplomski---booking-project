import ReviewCreateModel from "../models/ReviewModels/ReviewCreate";

async function createReviewFetch(reviewParameter:string,userId:number,propertId:number,star_number:number){
    try {

        const review:ReviewCreateModel = {
            userId:userId,
            propertyId:propertId,
            review:reviewParameter,
            star_number:star_number
        }

        const response = await fetch('http://localhost:8080/api/properties/reviews', {
            method: 'POST',
            headers: { 
                'Content-Type': 'application/json' ,
                'Authorization': 'Bearer '+localStorage.getItem('jwt')
            },
            body: JSON.stringify(review),
        });

        const data = await response.json();

        if (!response.ok) {
            return { success: false, message: data.message || 'Failed to create property' };
        }

        return { success: true };
    } catch (err) {
        return { success: false, message: 'An unexpected error occurred' };
    }
}

export default createReviewFetch;