import  { useEffect, useState } from 'react'
import fetchPropertiesForUser from '../../functions/fetchPropertiesForUser';
import Property from '../../models/propertyModels/Properties';
import Navbar from '../navbar';
import '../../assets/PropertiesForUserCss.css'
import CreateFullProperty from '../../models/propertyModels/CreateFullProperty';
import updatePropertyFetch from '../../functions/updatePropertyFetch';
import { useNavigate } from 'react-router-dom';
import fetchProperty from '../../functions/fetchSingleProperty';
import FullProperty from '../../models/propertyModels/FullProperty';
import fetchDeleteProperties from '../../functions/deletePropertyFetch';

const PropertiesForUser = () => {

    const [properties, setProperties] = useState<Property[]>([]);
    const [property,setProperty] = useState<CreateFullProperty>({
        id: 0,
        title:'',
        description:'',
        type:'',
        landlord:0,
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
        coordinateLat:'',
        coordinateLng:''
    });

    const [descriptionError,setDescriptionError] = useState<string>('');
    const [titleError,setTitleError] = useState<string>('');
    const [typeError,setTypeError] = useState<string>('');
    const [locationError,setLocationError] = useState<string>('');
    const [addressError,setAddressError] = useState<string>('');
    const [priceError,setPriceError] = useState<string>('');
    const [guestNumberError,setGuestNumberError] = useState<string>('');
    const [propertyIdToEnableDelete, setPropertyIdToEnableDelete] = useState<number | null>(null);

    const [isValid,setIsValid] = useState<boolean>(false);

    const navigate = useNavigate();

    function handleChange(e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>){
        const { name, value } = e.target;
        setProperty((property) => ({
            ...property,
            [name]: value
        }));
        console.log(property);
    }

    function handleSelectChange(e: React.ChangeEvent<HTMLSelectElement>) {
        const { name, value } = e.target;
        setProperty((prevProperty) => ({
            ...prevProperty,
            [name]: value,
        }));

        console.log(property);
    }

    function handleSubmit(e: React.FormEvent){
        e.preventDefault();

        validateForm(property);

        if (!validateForm(property)) {
            return;
        }

        updatePropertyFetch(property).then(result => {
            if (!result.success) {
                alert("Greska");
            } else {
                alert("Uspesno ste ažurirali objekat!");
            }
        });
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
            setTypeError("Property type is required field.");
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
    

    function handleCheck(e:React.ChangeEvent<HTMLInputElement>){
        const { name, checked } = e.target;
        setProperty((property) => ({
            ...property,
            [name]: checked
        }));

        console.log(property);
    }

    function addPicture(e: React.ChangeEvent<HTMLInputElement>) {
        const { name, files } = e.target;

        if (files && files.length > 0) {
            const selectedFile = files[0]; 
            
            setProperty((property) => ({
                ...property,
                [name]: selectedFile.name
            }));
    
            const formData = new FormData();
            formData.append('picture', selectedFile);
            formData.append('picturePath', selectedFile.name);
            formData.append('property_id', property.id.toString());

            console.log(formData);
    
            fetch('http://localhost:8080/api/pictures', {
                method: 'POST',
                body: formData,
                headers: {
                    'Authorization': localStorage.getItem('jwt') || '',
                  },
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

    useEffect(() => {
        console.log('useEffect')
        fetchPropertiesForUser(localStorage.getItem('id') ? parseInt(localStorage.getItem('id') as string) : 0,)
        .then(fetchedData => {
            setProperties(fetchedData);
        })
        .catch(err => console.error("Error fetching data:", err));
      }, [property]);

      const showUpdate = (id: number) => {
        fetchProperty(id)
            .then((fetchedProperty: FullProperty | null) => {
                if (fetchedProperty) {
                    const propertyToEdit: CreateFullProperty = {
                        id: fetchedProperty.id,
                        title: fetchedProperty.title,
                        description: fetchedProperty.description,
                        type: fetchedProperty.type,
                        landlord: fetchedProperty.landlord.id,
                        picture_path: fetchedProperty.picture_path,
                        price: fetchedProperty.price,
                        location: fetchedProperty.location,
                        addres: fetchedProperty.addres, 
                        petFriendly: fetchedProperty.petFriendly,
                        wiFi: fetchedProperty.wiFi,
                        kitchen: fetchedProperty.kitchen,
                        breakfast: fetchedProperty.breakfast,
                        lunch: fetchedProperty.lunch,
                        dinner: fetchedProperty.dinner,
                        ac: fetchedProperty.ac,
                        smokingAllowed: fetchedProperty.smokingAllowed,
                        tv: fetchedProperty.tv,
                        freeCancelation: fetchedProperty.freeCancelation,
                        guestNumber: fetchedProperty.guestNumber,
                        coordinateLat: fetchedProperty.coordinateLat,
                        coordinateLng: fetchedProperty.coordinateLng,
                    };
                    setProperty(propertyToEdit);
                    setPropertyIdToEnableDelete(id);
                } else {
                    console.error("Error fetching property");
                }
            })
            .catch(err => {
                console.error("Error fetching property:", err);
            });
    };

      const handleUpdate = () => {
        updatePropertyFetch(property);
    }

      const handleDelete = (id:number) => {
        fetchDeleteProperties(id);
        alert('Uspesno ste obrisali stambenu jedinicu');
    }

    const handleClick = (id:number) =>{
        navigate('/property/'+id)
    }


  return (
    <div>
    <Navbar />
    <div style={{display:"flex", justifyContent:"center",alignItems:"center"}}>
        <div className='properties-for-user-container' style={{width:"60%"}}>
            <h2 id='propertyh2'>Objekti u vasem vlasnistvu: </h2>
            <div className='propertiesContainer'>
                {properties && properties.length > 0 ? (
                    properties.map((propertyData) => (
                        <div className='property-Container' key={propertyData.id}>
                            <div className='propertyPic' onClick={() => {handleClick(propertyData.id)}} style={{ backgroundImage: `url('/src/assets/${propertyData.picture_path}')` }}></div>
                            <div className='propertyInfo' onClick={() => {handleClick(propertyData.id)}}>
                                <div className='propertyTitle'>{propertyData.title}</div>
                                <div className='propertyLocation'>{propertyData.address}</div>
                                <div className='propertyType'>{propertyData.type}</div>
                                <div className='propertyPrice'>RSD {propertyData.price * 117}.00</div>
                            </div>
                            <button className='propertyButton' onClick={() => { showUpdate(propertyData.id) }}>Izmeni</button>
                                <button className='propertyButton' id={`property-delete-${propertyData.id}`} disabled={propertyData.id !== propertyIdToEnableDelete} onClick={() => handleDelete(propertyData.id)}>Obrisi</button>
                        </div>

                    ))
                    ) : (
                    <p>Jos uvek nemate ni jedan objekat! Klikom na dugme "kreirajte objekat" mozete da ih kreirate</p> 
                )}
            </div>
            <div>
                <form className='form' action="" onSubmit={() => {handleUpdate}}>
                <div className='formData'>
                    <label htmlFor="title">Naziv: </label>
                    <input type="text" name='title' id='title' value={property.title} onChange={handleChange}/>
                    {titleError ? <p style={{color:"red",textAlign:"left",margin:"0"}}>{titleError}</p> : <></>}
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
                    <label htmlFor="location">Grad: </label>
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
                <div className='second-div'>
                    <div className='formDataCheck'>
                        <label htmlFor="petFriendly">Pet friendly? </label>
                        <input type="checkbox" name='petFriendly' id='petFriendly' checked={property.petFriendly} onChange={handleCheck}/>
                    </div>
                    <div className='formDataCheck'>
                        <label htmlFor="wiFi">Wi fi? </label>
                        <input type="checkbox" name='wiFi' id='wiFi' checked={property.wiFi} onChange={handleCheck}/>
                    </div>
                    <div className='formDataCheck'>
                        <label htmlFor="kitchen">Kuhinja? </label>
                        <input type="checkbox" name='kitchen' id='kitchen' checked={property.kitchen} onChange={handleCheck}/>
                    </div>
                    <div className='formDataCheck'>
                        <label htmlFor="breakfast">Dorucak? </label>
                        <input type="checkbox" name='breakfast' id='breakfast' checked={property.breakfast} onChange={handleCheck}/>
                    </div>
                    <div className='formDataCheck'>
                        <label htmlFor="lunch">Rucak? </label>
                        <input type="checkbox" name='lunch' id='lunch' checked={property.lunch} onChange={handleCheck}/>
                    </div>
                    <div className='formDataCheck'>
                        <label htmlFor="dinner">Vecera? </label>
                        <input type="checkbox" name='dinner' id='dinner' checked={property.dinner} onChange={handleCheck}/>
                    </div>
                    <div className='formDataCheck'>
                        <label htmlFor="ac">Klima? </label>
                        <input type="checkbox" name='ac' id='ac' checked={property.ac} onChange={handleCheck}/>
                    </div>
                    <div className='formDataCheck'>
                        <label htmlFor="smokingAllowed">Dozvoljeno pusenje? </label>
                        <input type="checkbox" name='smokingAllowed' id='smokingAllowed' checked={property.smokingAllowed} onChange={handleCheck}/>
                    </div>
                    <div className='formDataCheck'>
                        <label htmlFor="tv">TV? </label>
                        <input type="checkbox" name='tv' id='tv' checked={property.tv} onChange={handleCheck}/>
                    </div>
                    <div className='formDataCheck'>
                        <label htmlFor="freeCancelation">Besplatno otkazivanje? </label>
                        <input type="checkbox" name='freeCancelation' id='freeCancelation' checked={property.freeCancelation} onChange={handleCheck}/>
                    </div>
                </div>
                <div className='formData'>
                    <label htmlFor="description">Opis: </label>
                    <textarea
                        style={{width: "100%", minHeight: "100px"}}
                        name="description"
                        id="description"
                        value={property.description}
                        onChange={handleChange}
                    ></textarea>
                        {descriptionError ? <p style={{color:"red",textAlign:"left",margin:"0"}}>{descriptionError}</p> : <></>}
                </div>
                <div className='formData'>
                    <label htmlFor="picture_path">Dodaj sliku objketu: </label>
                    <input type="file" name='picture_path' id='picture_path' onChange={addPicture}/>
                </div>
                <div className='formSubmit'>
                    <button className='registerbutton' onClick={handleSubmit}>Izmeni</button>
                </div>
            </form>
            </div>
        </div>
    </div>
</div>
  )
}

export default PropertiesForUser
