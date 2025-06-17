import api from "./api";

interface LoginDTO {
  username: string;
  password: string;
}

interface RegisterDTO {
  username: string;
  password: string;
  confirmPassword: string;
  firstName: string;
  secondName: string;
  lastName: string;
  phone: string;
  role: string;
}

interface AuthResponse {
  token: string;
  refreshToken: string;
  username: string;
  role: string;
  expirationDate: string; // Usa `Date` si ya estás convirtiendo fechas correctamente
}

export const login = async ({ username, password }: LoginDTO): Promise<AuthResponse> => {
  const response = await api.post<AuthResponse>("/login", { username, password });
  return response.data;
};

export const register = async (userData: RegisterDTO): Promise<AuthResponse> => {
  const response = await api.post<AuthResponse>("/register", userData);
  return response.data;
};

export const refreshToken = async (refreshToken: string): Promise<AuthResponse> => {
  const response = await api.post<AuthResponse>("/refresh", { refreshToken });
  return response.data;
};
