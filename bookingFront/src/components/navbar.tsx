import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../assets/navbar.css';
import {jwtDecode} from "jwt-decode";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from '@fortawesome/free-solid-svg-icons';

interface DecodedToken {
  exp?: number;
  roles?: string[];
  sub?: string;
}

const Navbar = () => {
  const [isLogged, setIsLogged] = useState<boolean>(false);
  const [isIzdavac, setIsIzdavac] = useState<boolean>(false);
  const [isAdmin, setIsAdmin] = useState<boolean>(false);
  const [menuOpen, setMenuOpen] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const jwt = localStorage.getItem('jwt');
    
    if (jwt) {
      try {
        const tokenDecoded: DecodedToken = jwtDecode(jwt);
        if (tokenDecoded.exp && tokenDecoded.exp * 1000 >= Date.now()) {
          setIsLogged(true);
          if (tokenDecoded.roles?.includes('izdavac:create')) {
            setIsIzdavac(true);
          }
          if (tokenDecoded.roles?.includes('ROLE_ADMIN')) {
            setIsAdmin(true);
          }
        } else {
          setIsLogged(false);
          setIsIzdavac(false);
          localStorage.clear();
        }
      } catch (err) {
        setIsLogged(false);
        setIsIzdavac(false);
        localStorage.clear();
      }
    } else {
      setIsLogged(false);
      setIsIzdavac(false);
    }
  }, [isLogged]);

  const handleLogOut = () => {
    localStorage.removeItem('jwt');
    localStorage.removeItem('user');
    localStorage.removeItem('email');
    setIsLogged(false);
    setIsIzdavac(false);
    toggleMenu();
    navigate('/');
  };

  const handleRegister = () => {
    navigate('/register');
  };

  const handleLogin = () => {
    navigate('/login');
  };

  const handleLogo = () => {
    navigate('/');
  };

  const toggleMenu = () => {
    setMenuOpen(!menuOpen);
  };

  return (
    <>
      <div className='nav-main'>
        <div className='nav-container'>
          <div className='logo-title'>
            <div onClick={handleLogo} className='nav-logo'></div>
            <div className='title'>MyTrips</div>
          </div>
          <div className='nav-buttons'>
            {isLogged ? (
              <>
                <button className='hamburger-icon' style={{ backgroundColor: "#386180" }} onClick={toggleMenu}>
                  <FontAwesomeIcon style={{ color: "white" }} icon={faBars} />
                </button>
              </>
            ) : (
              <>
                <button className='navbar-button' onClick={handleRegister}>Registruj se</button>
                <button className='navbar-button' id='nav-button' onClick={handleLogin}>Prijavi se</button>
              </>
            )}
          </div>
        </div>
      </div>

      {menuOpen && (
        <div className={`menu-dropdown ${menuOpen ? 'open' : ''}`}>
          <button className='menu-button' onClick={handleLogOut}>Odjavi se</button>
          <button className='menu-button' onClick={() => { navigate('/user/reservations') }}>Vaše rezervacije</button>
          {isIzdavac && (
            <div style={{ display: "flex", flexDirection: "column", width: "100%" }}>
              <button className='menu-button' onClick={() => { navigate("/property/create") }}>Kreirajte objekat</button>
              <button className='menu-button' onClick={() => { navigate("/property/update") }}>Rad sa objektima</button>
              {isAdmin && (
                <button className='menu-button' onClick={() => { navigate("/adminpanel") }}>Admin panel</button>
              )}
            </div>
          )}
          {
          }
        </div>
      )}
    </>
  );
};

export default Navbar;
