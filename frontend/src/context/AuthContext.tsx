import { createContext, useState, useEffect } from "react";
import { login, refreshToken } from "../services/AuthService";
import { ReactNode } from "react";

// Definimos la interfaz del usuario
interface User {
  username: string;
  role: string;
}

// Definimos lo que contendrá el contexto
interface AuthContextType {
  user: User | null;
  signIn: (username: string, password: string) => Promise<void>;
  signOut: () => void;
  loading: boolean;
}

// Creamos el contexto con un valor inicial seguro
const AuthContext = createContext<AuthContextType | null>(null);

// Proveedor del contexto
export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const checkAuth = async () => {
      const token = localStorage.getItem("token");
      if (token) {
        try {
          const data = await refreshToken(token);
          setUser({ username: data.username, role: data.role });
        } catch {
          // Ignoramos el error si no lo usamos
          localStorage.removeItem("token");
        }
      }
      setLoading(false);
    };

    checkAuth();
  }, []);

  const signIn = async (username: string, password: string) => {
    const data = await login({username, password});
    setUser({ username: data.username, role: data.role });
    localStorage.setItem("token", data.token);
  };

  const signOut = () => {
    setUser(null);
    localStorage.removeItem("token");
  };

  return (
    <AuthContext.Provider value={{ user, signIn, signOut, loading }}>
      {!loading && children}
    </AuthContext.Provider>
  );
};

export { AuthContext };

// // Hook personalizado para usar el contexto fácilmente
// export const useAuth = () => {
//   const context = useContext(AuthContext);
//   if (!context) {
//     throw new Error("useAuth debe usarse dentro de un AuthProvider");
//   }
//   return context;
// };