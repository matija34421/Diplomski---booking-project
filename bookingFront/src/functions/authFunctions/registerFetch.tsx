import UserForCreating from "../../models/usermodels/UserForCreating";

async function registerFetch(user:UserForCreating){
    try {
        const response = await fetch('http://localhost:8080/api/auth/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(user),
        });

        const data = await response.json();

        if (!response.ok) {
            return { success: false, message: data.message || 'Email already existing' };
        }

        return { success: true };
    } catch (err) {
        return { success: false, message: 'An unexpected error occurred' };
    }
}

export default registerFetch;