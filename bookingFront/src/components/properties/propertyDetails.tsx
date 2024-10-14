import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import FullProperty from '../../models/propertyModels/FullProperty' 
import { faCheck,faX } from '@fortawesome/free-solid-svg-icons'
import '../../assets/propertyDetails.css'

interface PropertyDetailsProps {
    property:FullProperty | null
  }

const PropertyDetails = ({property}:PropertyDetailsProps) => {
  return (
    <ul>
      {property && property?.guestNumber > 0 ? (
        <div className='property-details'>
            <FontAwesomeIcon icon={faCheck}/>
            <p><strong>Kapacitet:</strong> ({property?.guestNumber}) osobe/a</p>
        </div>
    ) : (null)} 
      {property?.wiFi === true ? (
        <div className='property-details'>
            <FontAwesomeIcon icon={faCheck}/><p><strong>Besplatan WiFi:</strong> Ostanite povezani tokom boravka s besplatnim internetom.</p>
        </div>
    ) : (
        <div className='property-details'>
            <FontAwesomeIcon icon={faX}/>
            <p><strong>Besplatan WiFi:</strong> Bez besplatnog interneta</p>
        </div>
    )}

      {property?.ac === true ? (
        <div className='property-details'>
            <FontAwesomeIcon icon={faCheck}/><p><strong>Klimatizacija:</strong> Održavajte savršenu temperaturu tokom cele godine.</p>
        </div>
    ) : (
        <div className='property-details'>
            <FontAwesomeIcon icon={faX}/><p><strong>Klimatizacija:</strong> Bez klima uređaja</p>
        </div>
    )}

    {property?.breakfast === true ? (
        <div className='property-details'>
            <FontAwesomeIcon icon={faCheck}/><p><strong>Doručak</strong> obezbeđen</p>
        </div>
    ) : (
        <div className='property-details'>
            <FontAwesomeIcon icon={faX}/><p><strong>Doručak</strong> nije obezbeđen u ovom objektu</p>
        </div>
    )}

    {property?.lunch === true ? (
        <div className='property-details'>
            <FontAwesomeIcon icon={faCheck}/><p><strong>Ručak</strong> je obezbeđen</p>
        </div>
    ) : (
        <div className='property-details'>
            <FontAwesomeIcon icon={faX}/><p><strong>Ručak</strong> nije obezbeđen u ovom objektu</p>
        </div>
    )}

    {property?.dinner === true ? (
        <div className='property-details'>
            <FontAwesomeIcon icon={faCheck}/><p><strong>Večera</strong> je obezbeđena</p>
        </div>
    ) : (
        <div className='property-details'>
            <FontAwesomeIcon icon={faX}/><p><strong>Večera</strong> nije obezbeđena u ovom objektu</p>
        </div>
    )}

    {property?.kitchen === true ? (
        <div className='property-details'>
            <FontAwesomeIcon icon={faCheck}/><p><strong>Kuhinja:</strong> Potpuno opremljena kuhinja sa svim potrebnim aparatima i posuđem, savršena za pripremu vaših omiljenih jela.</p>
        </div>
    ) : (
        <div className='property-details'>
        <FontAwesomeIcon icon={faX}/><p><strong>Kuhinja:</strong> bez kuhinje</p>
    </div>
    )}

    {property?.petFriendly === true ? (
        <div className='property-details'>
            <FontAwesomeIcon icon={faCheck}/><p><strong>Pet friendly:</strong> Da, slobodno dovedite svog ljubimca!</p>
        </div>
    ) : (
        <div className='property-details'>
            <FontAwesomeIcon icon={faX}/><p><strong>Pet friendly:</strong> Ljubimci nisu dozvoljeni</p>
        </div>
    )}

    {property?.smokingAllowed === true ? (
        <div className='property-details'>
            <FontAwesomeIcon icon={faCheck}/><p><strong>Dozvoljeno pušenje:</strong> Da, uživajte u dimu svog omiljenog duvana u udobnosti vlastitog prostora.</p>
        </div>
    ) : (
        <div className='property-details'>
            <FontAwesomeIcon icon={faX}/><p><strong>Dozvoljeno pušenje:</strong> Ne , pušenje nije dozvoljeno u ovom objektu</p>
        </div>
    )}

    {property?.tv === true ? (
        <div className='property-details'>
            <FontAwesomeIcon icon={faCheck}/><p><strong>TV:</strong> Uživajte u omiljenim serijama i filmovima na velikom TV-u</p>
        </div>
    ) : (
        <div className='property-details'>
            <FontAwesomeIcon icon={faX}/><p><strong>TV:</strong> Televizor nije obezbeđen u ovom objektu</p>
        </div>
    )}
    <br />
    
    </ul>
  )
}

export default PropertyDetails
