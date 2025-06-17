import { useState } from "react";
import styled, { ThemeProvider } from 'styled-components';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { AxiosError } from "axios";



// Configuración de Axios
const API_URL = "http://localhost:8080/api/auth";

const Login = () => {
  const [isFlipped, setIsFlipped] = useState(false);
  const [darkMode, setDarkMode] = useState(false);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  // Estados para Login
  const [loginData, setLoginData] = useState({
    username: "",
    password: ""
  });

  // Estados para Register
  const [registerData, setRegisterData] = useState({
    username: "",
    firstName: "",
    secondName: "",
    lastName: "",
    email: "",
    phone: "",
    password: "",
    confirmPassword: "",
    role: "USER" // Valor por defecto
  });

  const toggleTheme = () => {
    setDarkMode(!darkMode);
  };

  const theme = {
    background: darkMode 
      ? 'linear-gradient(0deg, rgba(20, 44, 61, 1) 0%, rgb(16, 63, 125) 100%)' 
      : 'radial-gradient(circle, rgba(137, 186, 250, 1) 0%, rgba(79, 108, 255, 1) 100%)'
  };

  // Manejadores de cambio para Login
  const handleLoginChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setLoginData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  // Manejadores de cambio para Register
  const handleRegisterChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setRegisterData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  // Manejador de envío para Login
  const handleLoginSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");
    
    try {
      const response = await axios.post(`${API_URL}/login`, loginData);
      console.log("Login exitoso:", response.data);
      
      // Guardar token en localStorage
      localStorage.setItem("token", response.data.token);
      localStorage.setItem("refreshToken", response.data.refreshToken);
      localStorage.setItem("username", response.data.username);
      localStorage.setItem("role", response.data.role);
      
      // Redirigir al dashboard
      navigate("/PageTwo");
    } catch (err) {
      setError("Usuario o contraseña incorrectos");
      console.error("Error en login:", err);
    }
  };

  // Manejador de envío para Register
  const handleRegisterSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");
    
    try {
      if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(registerData.email)) {
      throw new Error("Por favor ingresa un email válido");
    }
      // Validar que las contraseñas coincidan
      if (registerData.password !== registerData.confirmPassword) {
        throw new Error("Las contraseñas no coinciden");
      }

      const response = await axios.post(`${API_URL}/register`, {
        username: registerData.username,
        password: registerData.password,
        confirmPassword: registerData.confirmPassword,
        firstName: registerData.firstName,
        secondName: registerData.secondName,
        lastName: registerData.lastName,
        phone: registerData.phone,
        role: registerData.role
      });

      console.log("Registro exitoso:", response.data);
      
      // Mostrar mensaje de éxito y cambiar a login
      setError("¡Registro exitoso! Por favor inicia sesión");
      setIsFlipped(false);
      
      // Limpiar formulario
      setRegisterData({
        username: "",
        firstName: "",
        secondName: "",
        lastName: "",
        email: "",
        phone: "",
        password: "",
        confirmPassword: "",
        role: "USER"
      });
    } 
    // catch (err) {
  // if (err instanceof AxiosError && err.response?.data?.message) {
  //   setError(err.response.data.message);
  // } else {
  //   setError("Error en el registro");
  // }
  // console.error("Error en registro:", err);
   catch (err) {
  if (err instanceof AxiosError) {
    if (err.response?.status === 409) {
      setError("El nombre de usuario o email ya está registrado");
    } else {
      setError(err.message || "Error en el registro. Por favor intenta nuevamente.");
    }
  } else {
    setError("Error inesperado en el registro.");
  }

  console.error("Error en registro:", err);
}
  };

  return (
    <ThemeProvider theme={theme}>
      <StyledWrapper>
        <div className='social'>
          <div className="main">
            <div className="up">
              <a href="/">
                <button className="card1">
                  <svg xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" viewBox="0,0,256,256" width="30px" height="30px" fillRule="nonzero" className="instagram"><g fillRule="nonzero" stroke="none" strokeWidth={1} strokeLinecap="butt" strokeLinejoin="miter" strokeMiterlimit={10}  strokeDashoffset={0} fontFamily="none" fontWeight="none" fontSize="none" textAnchor="none" style={{mixBlendMode: 'normal'}}><g transform="scale(8,8)"><path d="M11.46875,5c-3.55078,0 -6.46875,2.91406 -6.46875,6.46875v9.0625c0,3.55078 2.91406,6.46875 6.46875,6.46875h9.0625c3.55078,0 6.46875,-2.91406 6.46875,-6.46875v-9.0625c0,-3.55078 -2.91406,-6.46875 -6.46875,-6.46875zM11.46875,7h9.0625c2.47266,0 4.46875,1.99609 4.46875,4.46875v9.0625c0,2.47266 -1.99609,4.46875 -4.46875,4.46875h-9.0625c-2.47266,0 -4.46875,-1.99609 -4.46875,-4.46875v-9.0625c0,-2.47266 1.99609,-4.46875 4.46875,-4.46875zM21.90625,9.1875c-0.50391,0 -0.90625,0.40234 -0.90625,0.90625c0,0.50391 0.40234,0.90625 0.90625,0.90625c0.50391,0 0.90625,-0.40234 0.90625,-0.90625c0,-0.50391 -0.40234,-0.90625 -0.90625,-0.90625zM16,10c-3.30078,0 -6,2.69922 -6,6c0,3.30078 2.69922,6 6,6c3.30078,0 6,-2.69922 6,-6c0,-3.30078 -2.69922,-6 -6,-6zM16,12c2.22266,0 4,1.77734 4,4c0,2.22266 -1.77734,4 -4,4c-2.22266,0 -4,-1.77734 -4,-4c0,-2.22266 1.77734,-4 4,-4z" /></g></g></svg>
                </button>
              </a>
              <a href="/">
                <button className="card2">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 48 48" width="30px" height="30px" className="twitter"><path d="M42,12.429c-1.323,0.586-2.746,0.977-4.247,1.162c1.526-0.906,2.7-2.351,3.251-4.058c-1.428,0.837-3.01,1.452-4.693,1.776C34.967,9.884,33.05,9,30.926,9c-4.08,0-7.387,3.278-7.387,7.32c0,0.572,0.067,1.129,0.193,1.67c-6.138-0.308-11.582-3.226-15.224-7.654c-0.64,1.082-1,2.349-1,3.686c0,2.541,1.301,4.778,3.285,6.096c-1.211-0.037-2.351-0.374-3.349-0.914c0,0.022,0,0.055,0,0.086c0,3.551,2.547,6.508,5.923,7.181c-0.617,0.169-1.269,0.263-1.941,0.263c-0.477,0-0.942-0.054-1.392-0.135c0.94,2.902,3.667,5.023,6.898,5.086c-2.528,1.96-5.712,3.134-9.174,3.134c-0.598,0-1.183-0.034-1.761-0.104C9.268,36.786,13.152,38,17.321,38c13.585,0,21.017-11.156,21.017-20.834c0-0.317-0.01-0.633-0.025-0.945C39.763,15.197,41.013,13.905,42,12.429" /></svg>
                </button>
              </a>
            </div>
            <div className="down">
              <a href="/">
                <button className="card3">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 30 30" width="30px" height="30px" className="github">    <path d="M15,3C8.373,3,3,8.373,3,15c0,5.623,3.872,10.328,9.092,11.63C12.036,26.468,12,26.28,12,26.047v-2.051 c-0.487,0-1.303,0-1.508,0c-0.821,0-1.551-0.353-1.905-1.009c-0.393-0.729-0.461-1.844-1.435-2.526 c-0.289-0.227-0.069-0.486,0.264-0.451c0.615,0.174,1.125,0.596,1.605,1.222c0.478,0.627,0.703,0.769,1.596,0.769 c0.433,0,1.081-0.025,1.691-0.121c0.328-0.833,0.895-1.6,1.588-1.962c-3.996-0.411-5.903-2.399-5.903-5.098 c0-1.162,0.495-2.286,1.336-3.233C9.053,10.647,8.706,8.73,9.435,8c1.798,0,2.885,1.166,3.146,1.481C13.477,9.174,14.461,9,15.495,9 c1.036,0,2.024,0.174,2.922,0.483C18.675,9.17,19.763,8,21.565,8c0.732,0.731,0.381,2.656,0.102,3.594 c0.836,0.945,1.328,2.066,1.328,3.226c0,2.697-1.904,4.684-5.894,5.097C18.199,20.49,19,22.1,19,23.313v2.734 c0,0.104-0.023,0.179-0.035,0.268C23.641,24.676,27,20.236,27,15C27,8.373,21.627,3,15,3z" /></svg>
                </button>
              </a>
              <a href="/">
                <button className="card4">
                  <svg height="30px" width="30px" viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg" className="discord"><path d="M40,12c0,0-4.585-3.588-10-4l-0.488,0.976C34.408,10.174,36.654,11.891,39,14c-4.045-2.065-8.039-4-15-4s-10.955,1.935-15,4c2.346-2.109,5.018-4.015,9.488-5.024L18,8c-5.681,0.537-10,4-10,4s-5.121,7.425-6,22c5.162,5.953,13,6,13,6l1.639-2.185C13.857,36.848,10.715,35.121,8,32c3.238,2.45,8.125,5,16,5s12.762-2.55,16-5c-2.715,3.121-5.857,4.848-8.639,5.815L33,40c0,0,7.838-0.047,13-6C45.121,19.425,40,12,40,12z M17.5,30c-1.933,0-3.5-1.791-3.5-4c0-2.209,1.567-4,3.5-4s3.5,1.791,3.5,4C21,28.209,19.433,30,17.5,30z M30.5,30c-1.933,0-3.5-1.791-3.5-4c0-2.209,1.567-4,3.5-4s3.5,1.791,3.5,4C34,28.209,32.433,30,30.5,30z" /></svg>
                </button>
              </a>
            </div>
          </div>
        </div>

        {/* Main login container */}
        <div className="container">
          {/* Flip card */}
          <div className={`flip-card ${isFlipped ? 'flipped' : ''}`}>
            <div className="flip-card-inner">
              {/* Front - Login */}
              <div className="flip-card-front">
                <h2>Iniciar Sesión</h2>
                {error && !isFlipped && <div className="error-message">{error}</div>}
                <form className="auth-form" onSubmit={handleLoginSubmit}>
                  <input 
                    type="text" 
                    name="username"
                    placeholder="Nombre de usuario" 
                    value={loginData.username}
                    onChange={(e) => setLoginData({ ...loginData, username: e.target.value })}
                    // {handleLoginChange}
                    required 
                  />
                  <input 
                    type="password" 
                    name="password"
                    placeholder="Contraseña" 
                    value={loginData.password}
                    onChange={(e) => setLoginData({ ...loginData, password: e.target.value })}
                    // {handleLoginChange}
                    required 
                  />
                  <button type="submit">Ingresar</button>
                </form>
                <div className="forgot-password">
                  <Link to="/forgot-password">¿Olvidaste tu contraseña?</Link>
                </div>
              </div>
              
              {/* Back - Register */}
              <div className="flip-card-back">
                <h2>Registrarse</h2>
                {error && isFlipped && <div className="error-message">{error}</div>}
                <form className="auth-form" onSubmit={handleRegisterSubmit}>
                  <div className="name-fields">
                    <input 
                      type="text" 
                      name="firstName"
                      placeholder="Primer Nombre" 
                      value={registerData.firstName}
                      onChange={handleRegisterChange}
                      required 
                    />
                    <input 
                      type="text" 
                      name="secondName"
                      placeholder="Segundo Nombre" 
                      value={registerData.secondName}
                      onChange={handleRegisterChange}
                    />
                  </div>
                  <input 
                    type="text" 
                    name="lastName"
                    placeholder="Apellidos" 
                    value={registerData.lastName}
                    onChange={handleRegisterChange}
                    required 
                  />
                  <input 
                    type="text" 
                    name="username"
                    placeholder="Nombre de usuario" 
                    value={registerData.username}
                    onChange={handleRegisterChange}
                    required 
                  />
                  <input 
                    type="email" 
                    name="email"
                    placeholder="Email" 
                    value={registerData.email}
                    onChange={handleRegisterChange}
                    required 
                  />
                  <input 
                    type="tel" 
                    name="phone"
                    placeholder="Teléfono" 
                    value={registerData.phone}
                    onChange={handleRegisterChange}
                    required 
                  />
                  <select
                    name="role"
                    value={registerData.role}
                    onChange={handleRegisterChange}
                    className="role-select"
                  >
                    <option value="USER">Usuario Normal</option>
                    <option value="STAFF">Staff</option>
                    <option value="ADMIN">Administrador</option>
                  </select>
                  <div className="password-fields">
                    <input 
                      type="password" 
                      name="password"
                      placeholder="Contraseña" 
                      value={registerData.password}
                      onChange={handleRegisterChange}
                      required 
                    />
                    <input 
                      type="password" 
                      name="confirmPassword"
                      placeholder="Confirmar contraseña" 
                      value={registerData.confirmPassword}
                      onChange={handleRegisterChange}
                      required 
                    />
                  </div>
                  <button type="submit">Registrarse</button>
                </form>
              </div>
            </div>
          </div>

          {/* Toggle switch */}
          <div className="toggle-switch">
            <span className={!isFlipped ? 'active' : ''}>Iniciar Sesión</span>
            <div 
              className={`switch ${isFlipped ? 'flipped' : ''}`} 
              onClick={() => {
                setIsFlipped(!isFlipped);
                setError("");
              }}
            >
              <div className="switch-handle" />
            </div>
            <span className={isFlipped ? 'active' : ''}>Registrarse</span>
          </div>
        </div>

        {/* Theme toggle */}
        <label className="switch">
          <input 
            type="checkbox" 
            checked={darkMode}
            onChange={toggleTheme}
          />
          <span className="slider">
            <div className="star star_1" />
            <div className="star star_2" />
            <div className="star star_3" />
            <svg className="cloud" viewBox="0 0 16 16">
              <path fill="#323232" d="m391.84 540.91c-.421-.329-.949-.524-1.523-.524-1.351 0-2.451 1.084-2.485 2.435-1.395.526-2.388 1.88-2.388 3.466 0 1.874 1.385 3.423 3.182 3.667v.034h12.73v-.006c1.775-.104 3.182-1.584 3.182-3.395 0-1.747-1.309-3.186-2.994-3.379.007-.106.011-.214.011-.322 0-2.707-2.271-4.901-5.072-4.901-2.073 0-3.856 1.202-4.643 2.925"/>
            </svg>
          </span>
        </label>
      </StyledWrapper>
    </ThemeProvider>
  );
};

const StyledWrapper = styled.div`
  background: ${props => props.theme.background};
  height: 100vh;
  width: 100vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
  transition: background 0.5s ease;

  .error-message {
    color: #ff3333;
    background-color: #ffeeee;
    padding: 10px;
    border-radius: 5px;
    margin-bottom: 20px;
    text-align: center;
    font-size: 14px;
    max-width: 100%;
    border: 1px solid #ffaaaa;
  }

  .input-error {
    border-color: #ff3333 !important;
  }

  /* Social media styles (same as Welcome) */
  .social {
    display: flex;
    align-items: flex-end;
    align-self: flex-end;
    margin-bottom: 30px;
    margin-left: 30px;
  }

  .main {
    display: flex;
    flex-direction: column;
    gap: 0.5em;
    place-content: end;
    place-items: end;
  }

  .up, .down {
    display: flex;
    flex-direction: row;
    gap: 0.5em;
  }

  .down {
    padding: 5px 0;
  }

  .card1, .card2, .card3, .card4 {
    width: 90px;
    height: 90px;
    outline: none;
    border: none;
    background: white;
    border-radius: 90px 5px 5px 5px;
    box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px, rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
    transition: .2s ease-in-out;
  }

  .card2 {
    border-radius: 5px 90px 5px 5px;
  }

  .card3 {
    border-radius: 5px 5px 5px 90px;
  }

  .card4 {
    border-radius: 5px 5px 90px 5px;
  }

  .instagram {
    margin-top: 1.5em;
    margin-left: 1.2em;
    fill: #cc39a4;
  }

  .twitter {
    margin-top: 1.5em;
    margin-left: -.9em;
    fill: #03A9F4;
  }

  .github {
    margin-top: -.6em;
    margin-left: 1.2em;
  }

  .discord {
    margin-top: -.9em;
    margin-left: -1.2em;
    fill: #8c9eff;
  }

  .card1:hover {
    cursor: pointer;
    scale: 1.1;
    background-color: #cc39a4;
  }

  .card1:hover .instagram {
    fill: white;
  }

  .card2:hover {
    cursor: pointer;
    scale: 1.1;
    background-color: #03A9F4;
  }

  .card2:hover .twitter {
    fill: white;
  }

  .card3:hover {
    cursor: pointer;
    scale: 1.1;
    background-color: black;
  }

  .card3:hover .github {
    fill: white;
  }

  .card4:hover {
    cursor: pointer;
    scale: 1.1;
    background-color: #8c9eff;
  }

  .card4:hover .discord {
    fill: white;
  }

  /* Login container styles */
  .container {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 30px;
  }

  /* Flip card styles */
  .flip-card {
    width: 450px;
    height: 550px;
    perspective: 1000px;
  }

  .flip-card-inner {
    position: relative;
    width: 100%;
    height: 100%;
    transition: transform 0.8s;
    transform-style: preserve-3d;
  }

  .flip-card.flipped .flip-card-inner {
    transform: rotateY(180deg);
  }

  .flip-card-front, .flip-card-back {
    position: absolute;
    width: 100%;
    height: 100%;
    backface-visibility: hidden;
    background: white;
    border-radius: 10px;
    border: 2px solid #323232;
    box-shadow: 1px 1px 12px #000;
    padding: 40px;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .flip-card-back {
    transform: rotateY(180deg);
  }

  .flip-card h2 {
    font-size: 25px;
    font-weight: 900;
    color: #323232;
    margin-bottom: 20px;
  }

  /* Error message */
  .error-message {
    color: #ff3333;
    margin-bottom: 20px;
    text-align: center;
    font-size: 14px;
    max-width: 100%;
  }

  /* Form styles */
  .auth-form {
    display: flex;
    flex-direction: column;
    gap: 15px;
    width: 100%;
  }

  .name-fields, .password-fields {
    display: flex;
    gap: 15px;
    width: 100%;
  }

  .auth-form input, .role-select {
    width: 100%;
    height: 45px;
    border-radius: 8px;
    border: 2px solid #323232;
    
    padding: 0 15px;
    font-size: 15px;
    outline: none;
  }

  .role-select {
    background-color: #323232;
    cursor: pointer;
  }

  .auth-form button {
    margin-top: 15px;
    width: 140px;
    height: 45px;
    border-radius: 8px;
    border: 2px solid #323232;
    background-color: #000;
    color: white;
    font-size: 16px;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.3s;
    align-self: center;
  }

  .auth-form button:hover {
    background-color: #333;
  }

  .forgot-password {
    margin-top: 10px;
    width: 100%;
    text-align: left;
  }

  .forgot-password a {
    color: #323232;
    text-decoration: none;
    font-size: 14px;
    transition: color 0.3s;
  }

  .forgot-password a:hover {
    color: #2d8cf0;
    text-decoration: underline;
  }

  /* Toggle switch styles */
  .toggle-switch {
    display: flex;
    align-items: center;
    gap: 20px;
    margin-top: 50px;
  }

  .toggle-switch span {
    font-size: 18px;
    font-weight: 600;
    color: #323232;
    width: 120px;
    text-align: center;
  }

  .toggle-switch span.active {
    text-decoration: underline;
  }

  .switch {
    width: 60px;
    height: 30px;
    border-radius: 30px;
    background-color: #fff;
    border: 2px solid #323232;
    position: relative;
    cursor: pointer;
    transition: background-color 0.3s;
    align-self: flex-start;
    margin: 10px;
  }

  .switch.flipped {
    background-color: #2d8cf0;
  }

  .switch-handle {
    width: 26px;
    height: 26px;
    background-color: white;
    border-radius: 50%;
    position: absolute;
    top: 0;
    left: 0;
    border: 2px solid #323232;
    box-shadow: 0 3px 0 #323232;
    transition: left 0.3s;
  }

  .switch.flipped .switch-handle {
    left: calc(100% - 26px);
  }

  /* Theme toggle switch (same as Welcome) */
  .switch {
    align-self: flex-start;
    margin-top: 10px;
    margin-right: 10px;
    font-size: 17px;
    position: relative;
    display: inline-block;
    width: 4em;
    height: 2.2em;
    border-radius: 30px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }

  .switch input {
    opacity: 0;
    width: 0;
    height: 0;
  }

  .slider {
    position: absolute;
    cursor: pointer;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: #2a2a2a;
    transition: 0.4s;
    border-radius: 30px;
    overflow: hidden;
  }

  .slider:before {
    position: absolute;
    content: "";
    height: 1.2em;
    width: 1.2em;
    border-radius: 20px;
    left: 0.5em;
    bottom: 0.5em;
    transition: 0.4s;
    transition-timing-function: cubic-bezier(0.81, -0.04, 0.38, 1.5);
    box-shadow: inset 8px -4px 0px 0px #fff;
    
  }

  .switch input:checked + .slider {
    background-color: #00a6ff;
  }

  .switch input:checked + .slider:before {
    transform: translateX(1.8em);
    box-shadow: inset 15px -4px 0px 15px #ffcf48;
  }

  .star {
    background-color: #323232;
    border-radius: 50%;
    position: absolute;
    width: 5px;
    transition: all 0.4s;
    height: 5px;
  }

  .star_1 {
    left: 2.5em;
    top: 0.5em;
  }

  .star_2 {
    left: 2.2em;
    top: 1.2em;
  }

  .star_3 {
    left: 3em;
    top: 0.9em;
  }

  .switch input:checked ~ .slider .star {
    opacity: 0;
  }

  .cloud {
    width: 3.5em;
    position: absolute;
    bottom: -1.4em;
    left: -1.1em;
    opacity: 0;
    transition: all 0.4s;
  }

  .switch input:checked ~ .slider .cloud {
    opacity: 1;
  }
`;

export default Login;