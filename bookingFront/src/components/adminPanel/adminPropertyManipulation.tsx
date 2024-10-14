import React from 'react'
import { useState,useEffect } from 'react';
import Property from '../../models/propertyModels/Properties';
import universalFetch from '../../functions/universalFetch';


  const AdminPropertyManipulation = () => {
    const [filteredData, setFilteredData] = useState<Property[]>([]);
    const [nameSort, setNameSort] = useState<string>('');
    const [properties, setProperties] = useState<Property[]>([]);
    const [selectedPropertyId, setselectedPropertyId] = useState<number | null>(null);
    const [property, setProperty] = useState<Property>({
      id: 0,
      title: '',
      description: '',
      type: '',
      landlord: {
        id: 0,
        username: '',
        email: '',
        address: '',
        phone: ''
      },
      picture_path:'',
      price:0,
      location:'',
      address:''
    });
  
    function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
      const { name, value } = e.target;
  
      setProperty((prevProperty) => ({
          ...prevProperty,
          [name]: value,
        }));
    }
  
    function handleUpdate() {
      const jwt = localStorage.getItem('jwt');
      if (jwt) {
        universalFetch('http://localhost:8080/api/properties', 'PUT', jwt, property)
          .catch(err => console.error("Error fetching data:", err));
      }
    }
  
    function handleDelete() {
      const jwt = localStorage.getItem('jwt');
      if (jwt) {
        universalFetch('http://localhost:8080/api/properties/' + selectedPropertyId, 'DELETE', jwt, property)
        .then(() => {
            universalFetch('http://localhost:8080/api/properties/all', 'GET', jwt)
              .then(updatedProperties => {
                setProperties(updatedProperties); 
                setFilteredData(updatedProperties); 
              })
              .catch(err => console.error("Error fetching updated data:", err));
          })
        .catch(err => console.error("Error fetching data:", err));
      }
    }
  
    function handleSort(e: React.ChangeEvent<HTMLInputElement>) {
      const value = e.target.value;
      setNameSort(value);
  
      if (value === '') {
        setFilteredData(properties);
      } else {
        const filtered = properties.filter(property => 
          property.title.toLowerCase().includes(value.toLowerCase())
        );
        setFilteredData(filtered);
      }
    }

    useEffect(() => {
      const jwt = localStorage.getItem('jwt');
      if (jwt) {
        universalFetch('http://localhost:8080/api/properties/all', 'GET', jwt, undefined)
          .then(fetchedData => {
            setProperties(fetchedData);
            setFilteredData(fetchedData);
          })
          .catch(err => console.error("Error fetching data:", err));
      }
    }, []);
  
    function handleEdit(propertyData: Property) {
      setselectedPropertyId(propertyData.id);
      setProperty(propertyData);
    }
  
    return (
      <>
        <h2>Property List</h2>
        <label htmlFor="sortByName">Sort by username:</label>
        <input type="text" name="sortByName" id="sortByName" value={nameSort} onChange={handleSort} />
        <table>
          <tr>
            <th>ID</th>
            <th>TITLE</th>
            <th>DESCRIPTION</th>
            <th>TYPE</th>
            <th>OWNER</th>
            <th>OWNERS PHONE</th>
          </tr>
        {filteredData.map((propertyData) => (
    propertyData && propertyData.landlord ? (
      <tr key={propertyData.id}>
        <td>
            <input 
              type="text"
              name="id"
              id={`id-${propertyData.id}`}
              value={propertyData.id === selectedPropertyId ? property.id : propertyData.id} 
              onChange={handleChange}
              disabled={propertyData.id !== selectedPropertyId}
            />
            </td>
        <td>
        <input
          type="text"
          name="title"
          id={`title-${propertyData.title}`}
          value={propertyData.id === selectedPropertyId ? property.title : propertyData.title}
          onChange={handleChange}
          disabled={propertyData.id !== selectedPropertyId}
        /></td>

        <td><input
          type="text"
          name="description"
          id={`desc-${propertyData.description}`}
          value={propertyData.id === selectedPropertyId ? property.description : propertyData.description}
          onChange={handleChange}
          disabled={propertyData.id !== selectedPropertyId}
        /></td>

        <td><input
          type="text"
          name="type"
          id={`type-${propertyData.type}`}
          value={propertyData.id === selectedPropertyId ? property.type : propertyData.type}
          onChange={handleChange}
          disabled={propertyData.id !== selectedPropertyId}
        /></td>

        <td><input
          type="text"
          name="owner"
          id={`owner-${propertyData.landlord.id}`}
          value={propertyData.id === selectedPropertyId ? property.landlord.username : propertyData.landlord.username}
          onChange={handleChange}
          disabled={true}
        /></td>

        <td><input
          type="text"
          name="contact"
          id={`contact-${propertyData.landlord.phone}`}
          value={propertyData.id === selectedPropertyId ? property.landlord.phone : propertyData.landlord.phone}
          onChange={handleChange}
          disabled={true}
        /></td>

        {propertyData.id === selectedPropertyId ? (
          <td><button onClick={handleUpdate}>Update</button></td>
        ) : (
          <td><button onClick={() => handleEdit(propertyData)}>Edit</button></td>
        )}
        <td><button
          onClick={handleDelete}
          disabled={propertyData.id !== selectedPropertyId}>
          Delete
        </button></td>
      </tr>
    ) : null
  ))}
        </table>
      </>
    );
  };
  export default AdminPropertyManipulation;
  
