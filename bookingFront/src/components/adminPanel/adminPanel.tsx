import { useState } from 'react'
import AdminReservationManipulation from './adminReservationManipulation';
import AdminUserManipulation from './adminUserManipulation';
import AdminPropertyManipulation from './adminPropertyManipulation';
import '../../assets/adminpanel.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faUser } from '@fortawesome/free-regular-svg-icons';
import { useNavigate } from 'react-router-dom';

const AdminPanel = () => {
  const [component,setComponent] = useState<string>('user');

  const navigate = useNavigate();

  function handleSubmit(name:string){
    setComponent(name);

    var url = '';

    switch (name) {
      case 'user':
        url = 'http://localhost:8080/api/users';
        break;
      case 'property':
        url = 'http://localhost:8080/api/properties/all';
        break;
      case 'reservation':
        url = 'http://localhost:8080/api/reservations';
        break;
      default:
        break;
    }
    
  }

  var username = localStorage.getItem('user');
  var email = localStorage.getItem('email');


  return (
    <>
      <div>
        <div className='dashboardProfile'>
          <div onClick={()=> navigate('/')} className='dashboardProfileIcon'>
            <FontAwesomeIcon icon={faUser}/>
          </div>
          <div className='dashboardProfileData'>
            <h5>{username}</h5>
            <h6>{email}</h6>
          </div>
        </div>
        <div className='main-container-adminpanel'>
          <div className='dashboard'>
            <button onClick={() => handleSubmit('user')}>User manipulation</button>
            <button onClick={() => handleSubmit('property')}>Property manipulation</button>
            <button onClick={() => handleSubmit('reservation')}>Reservation manipulation</button>
          </div>
          <div className='admin-container'>
            <div className='dataContainer'>
              {component === 'user' && <AdminUserManipulation/>}
              {component === 'property' && <AdminPropertyManipulation/>}
              {component === 'reservation' && <AdminReservationManipulation />}
            </div>
          </div>
      </div>
      </div>
    </>
  )
}

export default AdminPanel
