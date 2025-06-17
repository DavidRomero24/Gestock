import { useAuth } from "../context/useAuth";
import { Navigate } from "react-router-dom";
import { ReactNode } from "react";


const PrivateRoute = ({ children }: { children: ReactNode }) => {
  const { user, loading } = useAuth();

  if (loading) return <div>Cargando...</div>;
  return user ? children : <Navigate to="/LoginPage" />;
};

export default PrivateRoute;