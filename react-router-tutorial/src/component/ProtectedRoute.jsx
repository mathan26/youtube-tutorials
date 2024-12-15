import React, { useEffect } from "react";
import { useNavigate } from "react-router";

const ProtectedRoute = ({ children }) => {
  const navigate = useNavigate();

  const [authenticated, setAuthenticated] = React.useState(true);
  useEffect(() => {
    if (!authenticated) {
      navigate("/login");
    }
  }, [authenticated]);

  return children;
};

export default ProtectedRoute;
