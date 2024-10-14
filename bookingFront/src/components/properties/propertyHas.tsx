import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import FullProperty from '../../models/propertyModels/FullProperty'
import { faWifi,faFan,faEgg,faBurger,faBowlFood,faUtensils,faUser,faDog,faSmoking,faTv } from '@fortawesome/free-solid-svg-icons'

interface PropertyHasProps {
    property:FullProperty | null
  }

const PropertyHas = ({property}:PropertyHasProps) => {
  return (
    <>
    {property && property?.guestNumber > 0 ? (
        <div className='property-has'>
            <FontAwesomeIcon icon={faUser}/><p>broj gostiju ({property?.guestNumber})</p>
        </div>
    ) : (null)} 

      {property?.wiFi === true ? (
        <div className='property-has'>
            <FontAwesomeIcon icon={faWifi}/><p>besplatan Wi-Fi</p>
        </div>
    ) : (null)}

      {property?.ac === true ? (
        <div className='property-has'>
            <FontAwesomeIcon icon={faFan}/><p>klima uređaj</p>
        </div>
    ) : (null)}

    {property?.breakfast === true ? (
        <div className='property-has'>
            <FontAwesomeIcon icon={faEgg}/><p>doručak</p>
        </div>
    ) : (null)}

    {property?.lunch === true ? (
        <div className='property-has'>
            <FontAwesomeIcon icon={faBurger}/><p>ručak</p>
        </div>
    ) : (null)}

    {property?.dinner === true ? (
        <div className='property-has'>
            <FontAwesomeIcon icon={faBowlFood}/><p>večera</p>
        </div>
    ) : (null)}

    {property?.kitchen === true ? (
        <div className='property-has'>
            <FontAwesomeIcon icon={faUtensils}/><p>kuhinja</p>
        </div>
    ) : (null)}

    {property?.petFriendly === true ? (
        <div className='property-has'>
            <FontAwesomeIcon icon={faDog}/><p>pet friendly</p>
        </div>
    ) : (null)}

    {property?.smokingAllowed === true ? (
        <div className='property-has'>
            <FontAwesomeIcon icon={faSmoking}/><p>dozvoljeno pušenje</p>
        </div>
    ) : (null)}

    {property?.tv === true ? (
        <div className='property-has'>
            <FontAwesomeIcon icon={faTv}/><p>TV</p>
        </div>
    ) : (null)}
    </>
  )
}

export default PropertyHas
