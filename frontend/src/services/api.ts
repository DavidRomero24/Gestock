import axios from "axios";

const API_URL = "http://localhost:8080/api/auth"; // Ajusta el puerto si es necesario

const api = axios.create({
  baseURL: API_URL,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true, // Para enviar cookies/tokens (si usas JWT en cookies)
});

// Interceptor para manejar errores globales
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response) {
      console.error("Error de API:", error.response.data);
    } else {
      console.error("Error de conexión:", error.message);
    }
    return Promise.reject(error);
  }
);

export default api;