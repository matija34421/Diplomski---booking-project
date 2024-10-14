import React from 'react'
import { useState } from 'react'
import UserForCreating from '../../models/usermodels/UserForCreating';
import registerFetch from '../../functions/authFunctions/registerFetch';
import Navbar from '../navbar';
import { useNavigate } from 'react-router-dom';

export default function Register(){
    const [user,setUser] = useState<UserForCreating>({
        username: '',
        password: '',
        email: '',
        address: '',
        phone: '',
        confirmPassword:'',
        role:'KORISNIK'
    });

    const [usernameError,setUsernameError] = useState<string>('');
    const [emailError,setEmailError] = useState<string>('');
    const [emailExistingError,setEmailExistingError] = useState<string>('');
    const [emailNotValidError,setEmailNotValidError] = useState<string>('');
    const [phoneError,setPhoneError] = useState<string>('');
    const [addressError,setAddressError] = useState<string>('');
    const [passwordError,setPasswordError] = useState<string>('');
    const [confirmPasswordError,setConfirmPasswordError] = useState<string>('');
    const [passwordMismatchError,setPasswordMismatchError] = useState<string>('');
    

    function handleChange(e:React.ChangeEvent<HTMLInputElement>){
        const { name, value } = e.target;
        setUser((user) => ({
            ...user,
            [name]: value
        }));
    }

    function handleSelectChange(e: React.ChangeEvent<HTMLSelectElement>) {
        const { name, value } = e.target;
        setUser((prevUser) => ({
            ...prevUser,
            [name]: value,
        }));
    }

    const navigate = useNavigate();

    function handleSubmit(e: React.FormEvent) {
        e.preventDefault();
    
        if(!validateForm(user)){
            return;
        }
    
        registerFetch(user).then(result => {
            if (!result.success) {
                setEmailExistingError(result.message);
            } else {
                alert("Uspesno ste se registrovali!");
                navigate("/");
            }
        });
    }

    function validateForm(user: UserForCreating): boolean {
        let isValid = true;
    
        if (!user.username) {
            setUsernameError("Username is required.");
            isValid = false;
        } else {
            setUsernameError('');
        }
    
        if (!user.email) {
            setEmailError("Email is required.");
            isValid = false;
        } else {
            setEmailError('');
        }
    
        if (!user.phone) {
            setPhoneError("Phone number is required.");
            isValid = false;
        } else {
            setPhoneError('');
        }
    
        if (!user.address) {
            setAddressError("Address is required.");
            isValid = false;
        } else {
            setAddressError('');
        }
    
        if (!user.password) {
            setPasswordError("Password is required.");
            isValid = false;
        } else {
            setPasswordError('');
        }
    
        if (!user.confirmPassword) {
            setConfirmPasswordError("Confirm password is required.");
            isValid = false;
        } else {
            setConfirmPasswordError('');
        }
    
        if (user.password !== user.confirmPassword) {
            setPasswordMismatchError("Passwords do not match.");
            isValid = false;
        } else {
            setPasswordMismatchError('');
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
                <label htmlFor="username">Username</label>
                <input type="text" name="username" id="username" value={user.username} onChange={handleChange}/>
            </div>
            {usernameError ? 
                <div className='errormsg'>
                    {usernameError}
                </div> : (null)
            }
            <div className='formData'>
                <label htmlFor="email">Email</label>
                <input type="text" name="email" id="email" value={user.email} onChange={handleChange}/>
            </div>
            {emailError ? 
                <div className='errormsg'>
                    {emailError}
                </div> : (null)
            }
            {emailNotValidError ? 
                <div className='errormsg'>
                    {emailNotValidError}
                </div> : (null)
            }
            {emailExistingError ? 
                <div className='errormsg'>
                    {emailExistingError}
                </div> : (null)
            }
            <div className='formData'>
                <label htmlFor="phone">Phone</label>
                <input type="text" name="phone" id="phone" value={user.phone} onChange={handleChange}/>
            </div>
            {phoneError ? 
                <div className='errormsg'>
                    {phoneError}
                </div> : (null)
            }
            <div className='formData'>
                <label htmlFor="address">Address</label>
                <input type="text" name="address" id="address" value={user.address} onChange={handleChange}/>
            </div>
            {addressError ? 
                <div className='errormsg'>
                    {addressError}
                </div> : (null)
            }
            <div className='formData'>
                <label htmlFor="password">Password</label>
                <input type="password" name="password" id="password" value={user.password} onChange={handleChange}/>
            </div>
            {passwordError ? 
                <div className='errormsg'>
                    {passwordError}
                </div> : (null)
            }
            <div className='formData'>
                <label htmlFor="confirmPassword">Confirm password</label>
                <input type="password" name="confirmPassword" id="confirmPassword" value={user.confirmPassword} onChange={handleChange}/>
            </div>
            {confirmPasswordError ? 
                <div className='errormsg'>
                    {confirmPasswordError}
                </div> : (null)
            }
            {passwordMismatchError ? 
                <div className='errormsg'>
                    {passwordMismatchError}
                </div> : (null)
            }
            <div className='formData'>
                <label htmlFor="role">Choose role</label>
                <select name="role" value={user.role} id="role" onChange={handleSelectChange}>
                    <option value="KORISNIK">KORISNIK</option>
                    <option value="IZDAVAC">IZDAVAC</option>
                </select>
            </div>
            <div className='formSubmit'>
                <button className='registerbutton'>Register</button>
            </div>
        </form>
    </div>
    </>
  )
}
