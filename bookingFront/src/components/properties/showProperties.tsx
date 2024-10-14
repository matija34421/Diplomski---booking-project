import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import fetchProperties from '../../functions/fetchProperties';
import Property from '../../models/propertyModels/Properties';
import '../../assets/showproperties.css';

interface PropertiesProps {
    searchType: string;
    searchLocation: string;
    searchName:string;
  }

const Properties = ({ searchType, searchLocation,searchName }: PropertiesProps) => {
    const [filteredData, setFilteredData] = useState<Property[]>([]);
    const [properties, setProperties] = useState<Property[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        console.log('useEffect')
        fetchProperties()
        .then(fetchedData => {
            setProperties(fetchedData);
            setFilteredData(fetchedData);
        })
        .catch(err => console.error("Error fetching data:", err));
      }, []);

      useEffect(() => {
        if (searchType === '' && searchLocation === '' && searchName === '') {
          setFilteredData(properties);
        } else {
          const filtered = properties.filter(property => {
            const matchesType = searchType ? property.type === searchType : true;
            const matchesLocation = searchLocation ? property.location === searchLocation : true;
            const matchesName = searchName ? property.title.toLowerCase().includes(searchName) : true;
            return matchesType && matchesLocation && matchesName;
          });
          setFilteredData(filtered);
        }
      }, [searchType, searchLocation,searchName, properties]);

      function handleClick(id:number){
        navigate('/property/'+id)
      }

  return (
    <div className='property-main'>
        <h2 id='propertyh2'>Predlog objekata</h2>
         <div className='propertiesContainer'>
            {filteredData.map((propertyData) => ( (
            <div className='propertyContainer' onClick={() => {handleClick(propertyData.id)}} key={propertyData.id}>
            <div className='propertyPic' style={{backgroundImage: `url('src/assets/${propertyData.picture_path}')`}}></div>
                <div className='propertyInfo'>
                    <div className='propertyTitle'>{propertyData.title}</div>
                    <div className='propertyLocation'>{propertyData.address}</div>
                    <div className='propertyType'>{propertyData.type}</div>
                    <div className='propertyPrice'>RSD {propertyData.price*117}.00    </div>
                </div>
            </div>
            )
            ))}
            </div>
        </div>
  )
}

export default Properties
