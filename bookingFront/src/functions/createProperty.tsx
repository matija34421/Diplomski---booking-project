import CreateFullProperty from "../models/propertyModels/CreateFullProperty";

async function createPropertyFetch(property:CreateFullProperty){
    try {
        console.log(property);
        const response = await fetch('http://localhost:8080/api/properties', {
            method: 'POST',
            headers: { 
                'Content-Type': 'application/json' ,
                'Authorization': 'Bearer '+localStorage.getItem('jwt')
            },
            body: JSON.stringify(property),
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

export default createPropertyFetch;