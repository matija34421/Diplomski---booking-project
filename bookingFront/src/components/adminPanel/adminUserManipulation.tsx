import React, { useState, useEffect } from 'react';
import User from '../../models/usermodels/Users';
import universalFetch from '../../functions/universalFetch';




const AdminUserManipulation = () => {
  const [filteredData, setFilteredData] = useState<User[]>([]);
  const [usernameSort, setUsernameSort] = useState<string>('');
  const [users, setUsers] = useState<User[]>([]);
  const [selectedUserId, setSelectedUserId] = useState<number | null>(null);
  const [user, setUser] = useState<User>({
    id: 0,
    username: '',
    password: '',
    email: '',
    address: '',
    phone: ''
  });

  function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
    const { name, value } = e.target;
    setUser((prevUser) => ({
      ...prevUser,
      [name]: value
    }));
  }

  function handleUpdate() {
    const jwt = localStorage.getItem('jwt');
    if (jwt) {
      universalFetch('http://localhost:8080/api/users', 'PUT', jwt, user)
        .catch(err => console.error("Error fetching data:", err));
    }
  }

  function handleDelete() {
    const jwt = localStorage.getItem('jwt');
    if (jwt) {
      universalFetch('http://localhost:8080/api/users/' + selectedUserId, 'DELETE', jwt, user)
        .catch(err => console.error("Error fetching data:", err));
    }
  }

  function handleSort(e: React.ChangeEvent<HTMLInputElement>) {
    const value = e.target.value;
    setUsernameSort(value);

    if (value == '') {
      setFilteredData(users);
    } else {
      const filtered = users.filter(user => 
        user.username.toLowerCase().includes(value.toLowerCase())
      );
      setFilteredData(filtered);
    }
  }

  useEffect(() => {
    const jwt = localStorage.getItem('jwt');
    if (jwt) {
      universalFetch('http://localhost:8080/api/users', 'GET', jwt, undefined)
        .then(fetchedData => {
          setUsers(fetchedData);
          setFilteredData(fetchedData); // Update filteredData with fetched data
        })
        .catch(err => console.error("Error fetching data:", err));
    }
  }, []);

  function handleEdit(userData: User) {
    setSelectedUserId(userData.id);
    setUser(userData);
  }

  return (
    <>
      <h2>User List</h2>
      <label htmlFor="sortByName">Sort by username:</label>
      <input type="text" name="sortByName" id="sortByName" value={usernameSort} onChange={handleSort} />
      <table>
        <tr><th>ID</th><th>USERNAME</th><th>EMAIL</th><th>ADDRESS</th><th>PHONE</th></tr>
        {filteredData.map((userData) => (
          <tr key={userData.id}>
            <td>
            <input 
              type="text"
              name="id"
              id={`id-${userData.id}`}
              value={userData.id === selectedUserId ? user.id : userData.id} 
              onChange={handleChange}
              disabled={userData.id !== selectedUserId}
            />
            </td>
          <td>
            <input 
              type="text"
              name="username"
              id={`username-${userData.id}`}
              value={userData.id === selectedUserId ? user.username : userData.username} 
              onChange={handleChange}
              disabled={userData.id !== selectedUserId}
            />
          </td>
  
          <td>
            <input
              type="text"
              name="email"
              id={`email-${userData.id}`}
              value={userData.id === selectedUserId ? user.email : userData.email}
              onChange={handleChange}
              disabled={userData.id !== selectedUserId}
            />
          </td>
  
          <td>
            <input
              type="text"
              name="address"
              id={`address-${userData.id}`}
              value={userData.id === selectedUserId ? user.address : userData.address}
              onChange={handleChange}
              disabled={userData.id !== selectedUserId}
            />
          </td>
  
          <td>
            <input
              type="text"
              name="phone"
              id={`phone-${userData.id}`}
              value={userData.id === selectedUserId ? user.phone : userData.phone}
              onChange={handleChange}
              disabled={userData.id !== selectedUserId}
            />
          </td>
  
            {userData.id === selectedUserId ? (
              <td><button onClick={handleUpdate}>Update</button></td>
            ) : (
              <td><button onClick={() => handleEdit(userData)}>Edit</button></td>
            )}
            <td><button 
              onClick={handleDelete} 
              disabled={userData.id !== selectedUserId}>
              Delete
            </button>
            </td>
        </tr>
        ))}
      </table>
    </>
  );
};

export default AdminUserManipulation;
