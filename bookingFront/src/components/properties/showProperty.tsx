import { useEffect, useRef } from 'react'
import fetchProperty from '../../functions/fetchSingleProperty'
import { useNavigate, useParams } from 'react-router-dom';
import { useState } from 'react';
import FullProperty from '../../models/propertyModels/FullProperty';
import Navbar from '../navbar';
import '../../assets/showproperty.css'
import fetchPictures from '../../functions/fetchPictures';
import Picture from '../../models/pictureModels/Picture';
import {  } from '@fortawesome/free-regular-svg-icons';
import PropertyHas from './propertyHas';
import PropertyDetails from './propertyDetails';
import Review from '../../components/properties/Review';
import fetchPostReservation from '../../functions/fetchPostReservation';
import ReservationPost from '../../models/reservationModels/ReservationPost';
import Reservation from '../../models/reservationModels/Reservation';
import fetchReservationsForProperty from '../../functions/fetchReservations';


const ShowProperty = () => {

  const { id } = useParams();
  const parsedId = id ? parseInt(id) : 0;

  const [property,setProperty] = useState<FullProperty | null>({
    id: 0,
    title:'',
    description:'',
    type:'',
    landlord:{
      id: 0,
      username: '',
      email: '',
      address: '',
      phone: ''
    },
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
  })
  const [pictures,setPictures] = useState<Picture[] | null>([])

  const getTodayDate = (): string => {
    const today = new Date();
    const year = today.getFullYear();
    const month = (today.getMonth() + 1).toString().padStart(2, '0');
    const day = today.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
  };

  const getTomorrowDate = () => {
    const today = new Date();
    today.setDate(today.getDate() + 1);
  
    const year = today.getFullYear();
    const month = (today.getMonth() + 1).toString().padStart(2, '0');
    const day = today.getDate().toString().padStart(2, '0');
  
    return `${year}-${month}-${day}`;
  };

  const [startDate,setStartDate] = useState<string>(getTodayDate())
  const [endDate,setEndDate] = useState<string>(getTomorrowDate())
  const [dateDifference,setDateDifference] = useState<number>(1)
  const [error,setError] = useState<string>('');
  const errorDivRef = useRef<HTMLDivElement | null>(null);
  const pregledDivRef = useRef<HTMLDivElement | null>(null);
  const podaciDivRef = useRef<HTMLDivElement | null>(null);
  const sadrzajDivRef = useRef<HTMLDivElement | null>(null);
  const kontaktDivRef = useRef<HTMLDivElement | null>(null);
  const rezervacijaDivRef = useRef<HTMLDivElement | null>(null);
  const [reservations, setReservations] = useState<Reservation[]>([]);
  const navigate = useNavigate();

  const getDateDifference = (start: string, end: string) => {
    const startDate = new Date(start);
    const endDate = new Date(end);


    const diffTime = endDate.getTime() - startDate.getTime();
    const diffDays = diffTime / (1000 * 60 * 60 * 24);

    setDateDifference(diffDays);
    setTotalPrice(property?.price ? (property.price*diffDays):(0))
  };

  const [totalPrice,setTotalPrice] = useState<number>(property?.price ? property.price*117 : 0);
  var dateErrorMsg = "";

  useEffect(() => {
    const fetchAndSetProperty = async () => {
      const propertyId = id ? parseInt(id) : null;

      if (propertyId) {
        const fetchedProperty = await fetchProperty(propertyId);
        setProperty(fetchedProperty);
      }
    };

    const fetchAndSetPictures = async () => {
      const propertyId = id ? parseInt(id) : null;

      if (propertyId) {
        const fetchedPictures = await fetchPictures(propertyId);
        setPictures(fetchedPictures);
      }
    };

    const startDateObj = new Date(startDate);
    const endDateObj = new Date(endDate);

    if (endDateObj < startDateObj) {
        setEndDate(startDate);
        dateErrorMsg = "Krajnji datum ne sme da bude manji od pocetnog , vec barem isti ili veci!";
    }
    else{
      dateErrorMsg = "";
    }

    fetchAndSetPictures();
    fetchAndSetProperty();

    const fetchReservationsForPropertie = async () =>{
      const propertyId = id ? parseInt(id) : 0;

      const data:Reservation[] = await fetchReservationsForProperty(propertyId)
      setReservations(data);
    }

    fetchReservationsForPropertie();

  }, [id,startDate,endDate,dateErrorMsg,error]);

  const isDateDisabled = (date: Date) => {
    return reservations.some(reservation => {
      const startDate = new Date(reservation.start_date);
      const endDate = new Date(reservation.end_date);
      return date >= startDate && date <= endDate;
    });
  };

  const handleStartDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setStartDate(e.target.value);
  };

  const handleEndDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEndDate(e.target.value);
  };

  const handleReservationClick = () => {

    const userIdFromStorage = localStorage.getItem('id');
    const userId = userIdFromStorage ? parseInt(userIdFromStorage, 10) : 0;

    const reservation:ReservationPost = {
      propertyId:property?.id ? property.id : 0,
      userId: userId,
      start_date:startDate,
      end_date:endDate
    }

    fetchPostReservation(reservation).then(result => {
      if (!result.success) {
          setError(result.message ? result.message : "");
          if (errorDivRef.current) {
            errorDivRef.current.scrollIntoView({ behavior: 'smooth' });
          }
      } else {
        alert("Uspesno ste rezervisali smestaj!");
        navigate('/');
      }
  });
  }


  const handleScrollViews = (name:string)=>{
    switch(name){
      case 'Pregled':
        if (pregledDivRef.current) {
          pregledDivRef.current.scrollIntoView({ behavior: 'smooth' });
        }
        break;
      case 'Podaci':
        if (podaciDivRef.current) {
          podaciDivRef.current.scrollIntoView({ behavior: 'smooth' });
        }
        break;
      case 'Sadrzaj':
        if (sadrzajDivRef.current) {
          sadrzajDivRef.current.scrollIntoView({ behavior: 'smooth' });
        }
        break;
      case 'Rezervacija':
        if (rezervacijaDivRef.current) {
          rezervacijaDivRef.current.scrollIntoView({ behavior: 'smooth' });
        }
        break;
      case 'Kontakt':
        if (kontaktDivRef.current) {
          kontaktDivRef.current.scrollIntoView({ behavior: 'smooth' });
        }
        break;
    }
  }

  return (
    <div>
      <Navbar/>
      <div className='property-container'>
        <div className='property-wrapper'>
          <div className='property-navbar'>
            <ul>
              <li onClick={() => handleScrollViews('Pregled')}>Pregled</li>
              <li onClick={() => handleScrollViews('Podaci')}>Podaci o apartmanu i cena</li>
              <li onClick={() => handleScrollViews('Sadrzaj')}>Sadržaji</li>
            </ul>
          </div>
          <div className='property-first-part'>
            <div>
              <div className='property-first-part-1'>
                <div className='property-first-part-1-1'>
                  <div ref={pregledDivRef} className='property-title'>{property?.title}</div>
                  <div className='property-address'>{property?.addres} {property?.location}</div>
                </div>

                <div className='property-first-part-1-2'>
                  <div onClick={() => handleScrollViews('Rezervacija')} className='property-reservation-button'>Rezervišite smeštaj u prostoru</div>
                  <br />
                  <div className='property-reservation-button' onClick={() => {handleScrollViews('Kontakt')}}>Kontaktirajte zaposlenog</div>
                </div>
              </div>
              <div className='property-first-part-2'>
                <div className='property-pictures'>
                  <div className='property-pic' style={{backgroundImage: `url('/src/assets/${property?.picture_path}')`}}></div>
                    {pictures && pictures.length > 0 ? (
                      pictures.map((picture) => (
                        <div className='property-pic' style={{backgroundImage: `url('/src/assets/${picture.picturePath}')`}}></div>
                      ))
                    ) : (
                      <p>No pictures available</p>
                    )}
                </div>
                <div style={{display:"flex",flexDirection:"column"}}>
                  <Review id={parsedId}/>
                </div>
              </div>
            </div>
          </div>
          <div ref={podaciDivRef} className='property-second-part'>
            <PropertyHas property={property}/>
            <div className='property-description'>
              <h2>Opis apartmana</h2>
              {property?.description}
              <h2>Detalji apartmana</h2>
              <PropertyDetails property={property}/>
            </div>
          </div>
          <div className='property-third-part'>
            <h2 ref={rezervacijaDivRef}>Rezervacija</h2>
            <div className='property-dates'>
                <div className='property-date-input'><p>Start date:</p><input type="date" value={startDate} onChange={handleStartDateChange} /></div>
                <div className='property-date-input'><p>End date:</p><input type="date" value={endDate} onChange={handleEndDateChange} /></div>
                <div className='property-reservation-button-2' onClick={() => {getDateDifference(startDate,endDate)}}>Primeni</div>
            </div>
            {dateErrorMsg !=="" ? (<p style={{color:"red"}}>dateErrorMsg</p>):(<></>)}
            {error ? <p ref={errorDivRef} style={{color:"red"}}> {error} </p> :  <></>}
            <table>
              <tr>
                <th>Title and address</th>
                <th>Your email and username</th>
                <th>Total nights</th>
                <th>Price</th>
                <th>Cancelation options</th>
                <th></th>
              </tr>
              <tr>
                <td>{property?.title} <br /> {property?.addres}</td>
                <td>{localStorage.getItem('email') ? (localStorage.getItem('email')) : (<p style={{color:"red"}}>Ulogujte se kako bi rezervisali prostor!</p>)} <br />{localStorage.getItem('user') ? (localStorage.getItem('user')) : (<></>)}</td>
                <td>{dateDifference}</td>
                <td>{property?.price && dateDifference ? property.price * 117 * dateDifference : 0}.00 RSD</td>
                <td>{property?.freeCancelation ? (<p style={{color:"green"}}>Free cancelation until 7 days eariler!</p>) : (<p>No free cancelation!</p>)}</td>
                <td>
                  {localStorage.getItem('email') ? (<button className='property-reservation-button' onClick={handleReservationClick} style={{borderRadius:"0"}}>Rezervišite smeštaj u prostoru</button>) : (<></>)}
                </td>
              </tr>
            </table>
            <div className='property-contact' ref={kontaktDivRef}>
              <h3>Kontaktirajte vlasnika:</h3>
              <p>telefon : {property?.landlord.phone}</p>
              <p>email : {property?.landlord.email}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ShowProperty;
