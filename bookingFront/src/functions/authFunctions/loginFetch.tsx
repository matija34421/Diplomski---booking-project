import UserLogin from "../../models/usermodels/UserLogin";

async function loginFetch(user: UserLogin) {
    try {
        const response = await fetch('http://localhost:8080/api/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(user),
        });

        const data = await response.json();

        if (!response.ok) {
            return { success: false, message: data.message || 'Login failed' };
        }

        console.log(data);
        localStorage.setItem('jwt', data.token);
        localStorage.setItem('user', data.username);
        localStorage.setItem('id' , data.id);
        localStorage.setItem('email', data.email);

        return { success: true };
    } catch (err) {
        return { success: false, message: 'An unexpected error occurred' };
    }
}

export default loginFetch;
