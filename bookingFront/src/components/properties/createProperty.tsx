import { faL } from '@fortawesome/free-solid-svg-icons';
import React from 'react'
import {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import createPropertyFetch from '../../functions/createProperty';
import CreateFullProperty from '../../models/propertyModels/CreateFullProperty';
import Navbar from '../navbar';

const CreateProperty = () => {

    const navigate = useNavigate();
    const [property,setProperty] = useState<CreateFullProperty>({
        id:0,
        title:'',
        description:'',
        type:'Apartman',
        landlord:localStorage.getItem('id') ? parseInt(localStorage.getItem('id') as string) : 0,
        picture_path:'',
        price:0,
        location:'',
        addres:'',
        petFriendly:false,
        wiFi:false,
        kitchen:false,
        breakfast:false,
        lunch:false,
        dinner:false,
        ac:false,
        smokingAllowed:false,
        tv:false,
        freeCancelation:false,
        guestNumber:0,
        coordinateLat:"1",
        coordinateLng:"1"
    });
    const [descriptionError,setDescriptionError] = useState<string>('');
    const [titleError,setTitleError] = useState<string>('');
    const [typeError,setTypeError] = useState<string>('');
    const [locationError,setLocationError] = useState<string>('');
    const [addressError,setAddressError] = useState<string>('');
    const [priceError,setPriceError] = useState<string>('');
    const [guestNumberError,setGuestNumberError] = useState<string>('');
    const [isValid,setIsValid] = useState<boolean> (false);

    function handleChange(e:React.ChangeEvent<HTMLInputElement>){
        const { name, value } = e.target;
        setProperty((property) => ({
            ...property,
            [name]: value
        }));
    }

    function handleSelectChange(e: React.ChangeEvent<HTMLSelectElement>) {
        const { name, value } = e.target;
        setProperty((prevProperty) => ({
            ...prevProperty,
            [name]: value,
        }));
    }

    function handlePictureChange(e: React.ChangeEvent<HTMLInputElement>) {
        const { name, files } = e.target;
    
        if (files && files.length > 0) {
            const selectedFile = files[0]; 
            
            setProperty((property) => ({
                ...property,
                [name]: selectedFile.name
            }));
    
            const formData = new FormData();
            formData.append('picture', selectedFile);
    
            fetch('http://localhost:8080/api/pictures/uploadPicture', {
                method: 'POST',
                body: formData,
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    console.log('Upload successful:', data);
                } else {
                    console.error('Upload failed:', data.message);
                }
            })
            .catch(error => {
                console.error('Error during file upload:', error);
            });
        }
    }

    function handleSubmit(e: React.FormEvent){
        e.preventDefault();

        if (!validateForm(property)) {
            return;
        }

        createPropertyFetch(property).then(result => {
            if (!result.success) {
                alert("Greska");
            } else {
                alert("Uspesno ste kreirali objekat!");
                navigate("/property/update");
            }
        });
    }

    function handleCheck(e:React.ChangeEvent<HTMLInputElement>){
        const { name, checked } = e.target;
        setProperty((property) => ({
            ...property,
            [name]: checked
        }));
    }

    function validateForm(property: CreateFullProperty) {
        let isValid = true;
    
        if (!property.title) {
            setTitleError("Property title is required field.");
            isValid = false;
        } else {
            setTitleError('');
        }
    
        if (!property.description) {
            setDescriptionError("Description is required field.");
            isValid = false;
        } else {
            setDescriptionError('');
        }
    
        if (!property.type) {
            setTypeError("Type is required field.");
            isValid = false;
        } else {
            setTypeError('');
        }
    
        if (!property.addres) {
            setAddressError("Address is required field.");
            isValid = false;
        } else {
            setAddressError('');
        }
    
        if (!property.location) {
            setLocationError("Location is required field.");
            isValid = false;
        } else {
            setLocationError('');
        }
    
        if (!property.guestNumber || property.guestNumber <= 0) {
            setGuestNumberError("Guest number is required field and must be above 0.");
            isValid = false;
        } else {
            setGuestNumberError('');
        }
    
        if (!property.price || property.price <= 0) {
            setPriceError("Price is required field and must be above 0.");
            isValid = false;
        } else {
            setPriceError('');
        }
    
        return isValid;
    }
    

  return (
    <div>
        <Navbar/>
        <div className='main-container'>
        <h2>Kreiranje novog objekta</h2>
      <div className='container'>
        <form action="" onSubmit={handleSubmit}>
            <div className='formData'>
                <label htmlFor="title">Naziv: </label>
                <input type="text" name='title' id='title' value={property.title} onChange={handleChange}/>
                {titleError ? <p style={{color:"red",textAlign:"left",margin:"0"}}>{titleError}</p> : <></>}
            </div>
            <div className='formData'>
                <label htmlFor="description">Opis: </label>
                <input type="text" name='description' id='description' value={property.description} onChange={handleChange}/>
                {descriptionError ? <p style={{color:"red",textAlign:"left",margin:"0"}}>{descriptionError}</p> : <></>}
            </div>
            <div className='formData'>
                <label htmlFor="type">Izaberite tip prostora:</label>
                <select name="type" id="type" onChange={handleSelectChange}>
                    <option value="Apartman">Apartman</option>
                    <option value="Vila">Vila</option>
                    <option value="Hotel">Hotel</option>
                    <option value="Vikendica">Vikendica</option>
                </select>
            </div>
            <div className='formData'>
                <label htmlFor="location">Drzava: </label>
                <input type="text" name='location' id='location' value={property.location} onChange={handleChange}/>
                {locationError ? <p style={{color:"red",textAlign:"left",margin:"0"}}>{locationError}</p> : <></>}
            </div>
            <div className='formData'>
                <label htmlFor="addres">Adresa: </label>
                <input type="text" name='addres' id='addres' value={property.addres} onChange={handleChange}/>
                {addressError ? <p style={{color:"red",textAlign:"left",margin:"0"}}>{addressError}</p> : <></>}
            </div>
            <div className='formData'>
                <label htmlFor="price">Cena objekta: </label>
                <input type="number" name='price' id='price' value={property.price} onChange={handleChange}/>
                {priceError ? <p style={{color:"red",textAlign:"left",margin:"0"}}>{priceError}</p> : <></>}
            </div>
            <div className='formData'>
                <label htmlFor="guestNumber">Broj gostiju: </label>
                <input type="text" name='guestNumber' id='guestNumber' value={property.guestNumber} onChange={handleChange}/>
                {guestNumberError ? <p style={{color:"red",textAlign:"left",margin:"0"}}>{guestNumberError}</p> : <></>}
            </div>
            <div className='formDataCheck'>
                <label htmlFor="petFriendly">Pet friendly? </label>
                <input type="checkbox" name='petFriendly' id='petFriendly' onChange={handleCheck}/>
            </div>
            <div className='formDataCheck'>
                <label htmlFor="wiFi">Wi fi? </label>
                <input type="checkbox" name='wiFi' id='wiFi' onChange={handleCheck}/>
            </div>
            <div className='formDataCheck'>
                <label htmlFor="kitchen">Kuhinja? </label>
                <input type="checkbox" name='kitchen' id='kitchen' onChange={handleCheck}/>
            </div>
            <div className='formDataCheck'>
                <label htmlFor="breakfast">Dorucak? </label>
                <input type="checkbox" name='breakfast' id='breakfast' onChange={handleCheck}/>
            </div>
            <div className='formDataCheck'>
                <label htmlFor="lunch">Rucak? </label>
                <input type="checkbox" name='lunch' id='lunch' onChange={handleCheck}/>
            </div>
            <div className='formDataCheck'>
                <label htmlFor="dinner">Vecera? </label>
                <input type="checkbox" name='dinner' id='dinner' onChange={handleCheck}/>
            </div>
            <div className='formDataCheck'>
                <label htmlFor="ac">Klima? </label>
                <input type="checkbox" name='ac' id='ac' onChange={handleCheck}/>
            </div>
            <div className='formDataCheck'>
                <label htmlFor="smokingAllowed">Dozvoljeno pusenje? </label>
                <input type="checkbox" name='smokingAllowed' id='smokingAllowed' onChange={handleCheck}/>
            </div>
            <div className='formDataCheck'>
                <label htmlFor="tv">TV? </label>
                <input type="checkbox" name='tv' id='tv' onChange={handleCheck}/>
            </div>
            <div className='formDataCheck'>
                <label htmlFor="freeCancelation">Besplatno otkazivanje? </label>
                <input type="checkbox" name='freeCancelation' id='freeCancelation' onChange={handleCheck}/>
            </div>
            <div className='formData'>
                <label htmlFor="picture_path">Pocetna slika: </label>
                <input type="file" name='picture_path' id='picture_path' onChange={handlePictureChange}/>
            </div>
            <div className='formSubmit'>
                <button className='registerbutton'>Kreiraj</button>
            </div>
        </form>
      </div>
    </div>
    </div>
  )
}

export default CreateProperty
