import './App.css'
import Register from './components/auth/registration'
import Login from './components/auth/login';
import UpdateUser from './components/adminPanel/updateUser';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AdminPanel from './components/adminPanel/adminPanel';
import Index from './components/index';
import ShowProperty from './components/properties/showProperty';
import PrivateRoute from './components/privateRoute';
import CreateProperty from './components/properties/createProperty';
import PropertiesForUser from './components/properties/PropertiesForUser';
import ReservationsForUser from './components/ReservationsForUser';



function App() {
  return (
    <>
      <BrowserRouter>
      <Routes>
        <Route path='/' element={<Index/>}></Route>
        <Route path='/login' element={<Login/>}></Route>
        <Route path='/register'  element={<Register/>}></Route>
        <Route path='/adminpanel'  element={<PrivateRoute Component={AdminPanel} Permission={"ROLE_ADMIN"} />}></Route>
        <Route path='/adminpanel/updateUser'  element={<UpdateUser/>}></Route>
        <Route path='/property/:id' element={<ShowProperty/>}></Route>
        <Route path='/property/create' element={<PrivateRoute Component={CreateProperty} Permission={"izdavac:create"} />}></Route>
        <Route path='/property/update' element={<PrivateRoute Component={PropertiesForUser} Permission={"izdavac:update"} />}></Route>
        <Route path='/user/reservations' element={<PrivateRoute Component={ReservationsForUser} Permission={"korisnik:read"} />}></Route>
      </Routes>
    </BrowserRouter>
    </>
  )
}

export default App
