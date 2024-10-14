import { useState, useEffect } from 'react';
import fetchReviews from '../../functions/fetchReviews';
import Review from '../../models/ReviewModels/Review';
import { faUser } from '@fortawesome/free-regular-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import StarRating from './StarRating';
import fetchCreateReview from '../../functions/fetchCreateReview';

interface ReviewProps {
  id: number;
}

const PropertyReviews = ({ id }: ReviewProps) => {
  const [reviews, setReviews] = useState<Review[]>([]);
  const [showReviewForm, setShowReviewForm] = useState(false);
  const [currentReviewIndex, setCurrentReviewIndex] = useState(0);
  const [newReviewText, setNewReviewText] = useState('');
  const [newReviewRating, setNewReviewRating] = useState<number>(5);
  const [errorMessage,setErrorMessage]= useState<string>('');
  const [isLogged,setIsLogged] = useState<boolean>(false);

  useEffect(() => {
    const fetchAndSetReviews = async () => {
      if (id) {
        const fetchedReviews = await fetchReviews(id);
        setReviews(fetchedReviews);
      }
    };
    fetchAndSetReviews();

    const token = localStorage.getItem('jwt');
    const userId = localStorage.getItem('id') ? parseInt(localStorage.getItem('id') as string) : 0;

    if (token && userId !== 0) {
      setIsLogged(true); 
    } else {
      setIsLogged(false); 
    }
  }, [id,errorMessage]);


  const handleNext = () => {
    setCurrentReviewIndex((prevIndex) =>
      prevIndex === reviews.length - 1 ? 0 : prevIndex + 1
    );
  };

  const handlePrev = () => {
    setCurrentReviewIndex((prevIndex) =>
      prevIndex === 0 ? reviews.length - 1 : prevIndex - 1
    );
  };

  const handleCreateReviewClick = () => {

    setShowReviewForm(true);
  };

  const handleFormSubmit = () => {
    if(newReviewText.length<5){
      setErrorMessage('Vasa recenzija mora biti duza od 5 karaktera!');
      return;
    }

    console.log('Recenzija kreirana:', newReviewText, newReviewRating);
    setNewReviewText('');
    setNewReviewRating(5);
    setShowReviewForm(false); 

    const userId = localStorage.getItem('id') ? parseInt(localStorage.getItem('id') as string) : 0;

    const propertyId = id;

    alert("Uspesno ste kreirali recenziju objekta!");
    fetchCreateReview(newReviewText,userId,propertyId,newReviewRating);
  };

  if (showReviewForm) {
    return (
      <div style={{margin:"10px",textAlign:"left",borderRadius:"12px",backgroundColor:"#386180",padding:"10px",display:"flex",justifyContent:"center",flexDirection:"column",textWrap:"wrap"}}>
        <p style={{marginBottom:"0px",color:"white",fontWeight:"bold"}}>Vaša recenzija:</p>
        <textarea placeholder='Unesite recenzciju...' value={newReviewText} onChange={(e) => setNewReviewText(e.target.value)} rows={4} style={{ width: '100%',maxWidth:"150px",maxHeight:"65px" }}/>
        <br />
        <p style={{marginBottom:"0px",color:"white",fontWeight:"bold"}}>Vaša ocena:</p>
        <select value={newReviewRating} onChange={(e) => setNewReviewRating(parseInt(e.target.value))}>
          {[1, 2, 3, 4, 5].map((num) => (
            <option key={num} value={num}>
              {num}
            </option>
          ))}
        </select>
        <br />
        <button onClick={handleFormSubmit}>Potvrdi</button>
        {errorMessage ? (<p style={{color:"red",maxWidth:"170px"}}>{errorMessage}</p>) : (<></>)}
      </div>
    );
  }

  if (reviews.length === 0) {
    return (
      <div>
        <div>No reviews available</div>
        {isLogged ?(<div style={{ marginTop: '10px' }} onClick={handleCreateReviewClick} className='property-reservation-button'>
          Ostavite Recenziju
        </div>):(<p>Ulogujte se kako bi ste kreirali recenziju</p>)}
      </div>
    );
  }

  const currentReview = reviews[currentReviewIndex];

  return (
    <div>
      <div className='propertyReview'>
        <button onClick={handlePrev}>{"<"}</button>
        <div className='propertyReview-1'>
          <div className='propertyReview-1-1'>
            <FontAwesomeIcon className='userIcon' icon={faUser} />
            <h5>{currentReview.user}</h5>
          </div>
          <p>{currentReview.review}</p>
          <StarRating star_number={currentReview.star_number} />
        </div>
        <button onClick={handleNext}>{">"}</button>
      </div>
      {isLogged ?(<div style={{ marginTop: '10px' }} onClick={handleCreateReviewClick} className='property-reservation-button'>
          Ostavite Recenziju
        </div>):(<p>Ulogujte se kako bi ste kreirali recenziju</p>)}
    </div>
  );
};

export default PropertyReviews;
