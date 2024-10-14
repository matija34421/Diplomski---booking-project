import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faStar } from '@fortawesome/free-solid-svg-icons';

interface ReviewProps {
  star_number: number;
}

const StarRating = ({ star_number }: ReviewProps) => {
  const stars = Array.from({ length: 5 }, (_, index) => {
    return (
      <FontAwesomeIcon
        key={index}
        icon={faStar}
        style={{
          color: index < star_number ? 'gold' : 'gray',
        }}
        className='userIcon'
      />
    );
  });

  return <div>{stars}</div>;
};

export default StarRating;
