import Navbar from './navbar'
import '../assets/index.css'
import { useRef, useState } from 'react'
import Properties from './properties/showProperties'

const Index = () => {
  const [propertyType,setPropertyType] = useState<string>('');
  const [propertyLocation,setPropertyLocation] = useState<string>('');
  const [propertyName,setPropertyName] = useState<string>('');

  const searchDivRef = useRef<HTMLDivElement | null>(null);

  function handleTypeChange(name:string){
    setPropertyType(name);

    var type = '';

    switch (name) {
      case 'Apartman':
        type = 'Apartman';
        break;
      case 'Hotel':
        type = 'Hotel';
        break;
      case 'Vila':
        type = 'Vila';
        break;
      case 'Vikendica':
        type = 'Vikendica';
        break;
      default:
        break;
    }

    if (searchDivRef.current) {
      searchDivRef.current.scrollIntoView({ behavior: 'smooth' });
    }
    
  }

  function handleLocationChange(name:string){
    setPropertyLocation(name);

    var location = '';

    switch (name) {
      case 'Srbija':
        location = 'Srbija';
        break;
      case 'Francuska':
        location = 'Francuska';
        break;
      case 'Italija':
        location = 'Italija';
        break;
      case 'Španija':
        location = 'Španija';
        break;
      case 'Amerika':
        location = 'Amerika';
        break;
      case 'Tajland':
      location = 'Tajland';
      break;
      default:
        break;
    }

    if (searchDivRef.current) {
      searchDivRef.current.scrollIntoView({ behavior: 'smooth' });
    }
    
  }

  function handleSort(e: React.ChangeEvent<HTMLInputElement>) {
    const value = e.target.value;
    setPropertyName(value);

    if (searchDivRef.current) {
      searchDivRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  }

  return (
    <>
    <Navbar/>
      <div className='index-main'>
        <div className='index-container'>
          <div className='first-part'>
            <h1>Pronađite svoj sledeći smeštaj</h1>
            <h2>Pretražite ponude za hotele i smeštaje na svojim omiljenim lokacijama</h2>
          </div>
        </div>
      </div>
      <div className='second-part'>
          <div className='search-by-type'>
            <h2>Pretražujte po vrsti objekta</h2>
            <div className='search-by-type-types'>
                <div className='search-type' onClick={() => handleTypeChange('Hotel')}>
                  <div className='type-pic' id='hotel-pic'></div>
                  <h3>Hoteli</h3>
                </div>
                <div className='search-type' onClick={() => handleTypeChange('Apartman')}>
                  <div className='type-pic' id='apartman-pic'></div>
                  <h3>Apartmani</h3>
                </div>
                <div className='search-type' onClick={() => handleTypeChange('Vila')}>
                  <div className='type-pic' id='vila-pic'></div>
                  <h3>Vile</h3>
                </div>
                <div className='search-type' onClick={() => handleTypeChange('Vikendica')}>
                  <div className='type-pic' id='vikendica-pic'></div>
                  <h3>Vikendice</h3>
                </div>
            </div>
            <button onClick={() => handleTypeChange('')}>ponisti pretragu</button>
          </div>
          <div className='search-by-location'>
            <h2>Pretražujte po lokaciji</h2>
                <div className='locations-1'>
                  <div className='location1-child' onClick={() => handleLocationChange('Srbija')}>
                    <div className='locations1-pic' id='serb-pic'></div>
                    <h3>Srbija</h3>
                  </div>
                  <div className='location1-child' onClick={() => handleLocationChange('Španija')}>
                    <div className='locations1-pic' id='spain-pic'></div>
                    <h3>Španija</h3>
                  </div>
                </div>
                <div className='locations-2'>
                  <div className='locations2-child' onClick={() => handleLocationChange('Francuska')}>
                    <div className='locations2-pic' id='francuksa-pic'></div>
                    <h3>Francuska</h3>
                  </div>
                  <div className='locations2-child' onClick={() => handleLocationChange('Italija')}>
                    <div className='locations2-pic' id='italija-pic'></div>
                    <h3>Italija</h3>
                  </div>
                  <div className='locations2-child' onClick={() => handleLocationChange('Tajland')}>
                    <div className='locations2-pic' id='tajland-pic'></div>
                    <h3>Tajland</h3>
                  </div>
                  <div className='locations2-child' onClick={() => handleLocationChange('Amerika')}>
                    <div className='locations2-pic' id='amerika-pic'></div>
                    <h3>Amerika</h3>
                  </div>
                </div>
                <button onClick={() => handleLocationChange('')}>ponisti pretragu</button>
            </div>
            <div className='properties'>
            <div className='searchDiv' ref={searchDivRef}>
              <label htmlFor="sortByName">Sort by title:</label>
              <input type="text" name="sortByName" id="sortByName" value={propertyName} onChange={handleSort} />
            </div>
              <Properties searchType={propertyType} searchLocation={propertyLocation} searchName={propertyName}/>
            </div>
        </div>
    </>
  )
}

export default Index
