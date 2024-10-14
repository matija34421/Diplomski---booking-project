import { useState } from 'react'
import { Navigate, useNavigate } from 'react-router-dom';
import UserLogin from '../../models/usermodels/UserLogin';
import loginFetch from '../../functions/authFunctions/loginFetch';
import Navbar from '../navbar';
import '../../assets/auth.css';

export default function Login(){
    const [user,setUser] = useState<UserLogin>({
        password: '',
        email: '',
    });

    const [emailError,setEmailError] = useState<string>('');
    const [passwordError,setPasswordError] = useState<string>('');
    const [isValid,setIsValid] = useState<boolean>(false);
    const [emailNotValid,setEmailNotValidError] = useState<string>('');

    const [error,setError] = useState<string>('');

    function handleChange(e:React.ChangeEvent<HTMLInputElement>){
        const { name, value } = e.target;
        setUser((user) => ({
            ...user,
            [name]: value
        }));
    }

    const navigate = useNavigate();

    function handleSubmit(e: React.FormEvent){
        e.preventDefault();

        if(!validateForm (user)){
            return;
        }
        else{
            loginFetch(user).then(result => {
                if (!result.success) {
                    setError(result.message);
                } else {
                    alert('Uspesno ste se ulogovali!');
                    navigate("/");
                }
            });
        }
    }

    function validateForm(user: UserLogin): boolean {
        let isValid = true;
    
        if (!user.email) {
            setEmailError("Email is required.");
            isValid = false;
        } else {
            setEmailError('');
        }
    
        if (!user.password) {
            setPasswordError("Password is required.");
            isValid = false;
        } else {
            setPasswordError('');
        }
    
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (user.email && !emailRegex.test(user.email)) {
            setEmailNotValidError("Email is not valid.");
            isValid = false;
        } else {
            setEmailNotValidError('');
        }
    
        return isValid;
    }

  return (
    <>
    <Navbar/>
    <div className='container'>
        <form action="" onSubmit={handleSubmit}>
            <div className='formData'>
                <label htmlFor="email">Email</label>
                <input type="text" name="email" id="email" value={user.email} onChange={handleChange}/>
            </div>
            {emailError ? 
                <div className='errormsg'>
                    {emailError}
                </div> : (null)
            }
            {emailNotValid ? 
                <div className='errormsg'>
                    {emailNotValid}
                </div> : (null)
            }
            <div className='formData'>
                <label htmlFor="password">Password</label>
                <input type="password" name="password" id="password" value={user.password} onChange={handleChange}/>
            </div>
            {error ? 
                <div className='errormsg'>
                    {error}
                </div> : (null)
            }
            {passwordError ? 
                <div className='errormsg'>
                    {passwordError}
                </div> : (null)
            }
            <div className='formSubmit'>
                <button className='loginbutton'>Login</button>
            </div>
        </form>
    </div>
    </>
  )
}
