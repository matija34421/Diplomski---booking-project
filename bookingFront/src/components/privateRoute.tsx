import React from 'react';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Index from './index';
import { jwtDecode } from "jwt-decode";

type PrivateRouteProps = {
  Component: React.ComponentType;
  Permission:string;
};

interface DecodedToken {
  exp?: number;
  roles?: string[];
  sub?: string;
}

const PrivateRoute: React.FC<PrivateRouteProps> = ({ Component,Permission }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {

    if (localStorage.getItem('jwt')) {
      const token = localStorage.getItem('jwt')
      const tokenDecoded:DecodedToken = jwtDecode(token ? token : "");
      console.log(tokenDecoded);
      if(tokenDecoded.exp && tokenDecoded.exp * 1000 >= Date.now() && tokenDecoded.roles){
        if(tokenDecoded.roles.includes(Permission)){
          setIsAuthenticated(true);
        }
        else{
          alert("Nemate dozvolu za pristupanje ovom endpoint-u!");
          navigate("/");
        }
      }
      else{
        setIsAuthenticated(false);
      }
    } else {
      setIsAuthenticated(false);
    }
  }, [navigate]);

  return isAuthenticated ? <Component /> : <Index/>;
};

export default PrivateRoute;
