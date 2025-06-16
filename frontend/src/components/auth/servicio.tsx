import { useState } from 'react';
import styled, { ThemeProvider } from "styled-components";
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const ServicioPage = () => {
  const navigate = useNavigate();
  const [darkMode, setDarkMode] = useState(false);
  const theme = darkMode ? darkTheme : lightTheme;
  const toggleTheme = () => {
    setDarkMode(!darkMode);
  };

  // Datos iniciales de servicios
  const [servicios, setServicios] = useState([
    { id: 1, nombre: "Reparación de teléfonos", estado: "Activo" },
    { id: 2, nombre: "Instalación de sistemas operativos", estado: "Inactivo" },
    { id: 3, nombre: "Configuración de redes Wi-Fi", estado: "Activo" },
  ]);

  // Funciones para las acciones
  const crearServicio = () => {
    console.log("Creando nuevo servicio...");
  };

  const consultarServicio = (id) => {
    console.log(`Consultando servicio con ID: ${id}`);
  };

  const eliminarServicio = (id) => {
    setServicios(servicios.filter((s) => s.id !== id));
  };

  const modificarServicio = (id) => {
    console.log(`Modificando servicio con ID: ${id}`);
  };

  return (
    <ThemeProvider theme={theme}>
      <StyledWrapper>
        <div className="inventory-dashboard">
          <header>
            <img className="img_LogoGestockpagone" src="../src/assets/LogoGestock.png" alt="Logo de la empresa" />
            <h1>GESTOCK</h1>
          </header>
          <nav className="control-panel">
            <div onClick={() => navigate('/PageTwo')} style={{ cursor: 'pointer' }}>
          <img className="img_gestockpagone" src="../src/assets/Gestock.png" alt="Logo de la empresa" />
          </div>
            <ul>
              <li><Link to="/PanelDeControlPage">Panel de control</Link></li>
              <li><Link to="/EmpleadoPage">Empleados</Link></li>
              <li><Link to="/ClientePage">Clientes</Link></li>
              <li><Link to="/ProveedorPage">Proveedores</Link></li>
              <li><Link to="/ProductoPage">Productos</Link></li>
              <li style={{backgroundColor: '#eaeded'}}><Link to="/ServicioPage">Servicios</Link></li>
              <li><Link to="/FacturaPage">Facturas</Link></li>
              <li><Link to="/SuministroPage">Suministros</Link></li>
            </ul>
          </nav>
          <main className="dashboard-content">
            <div style={{
              display: 'flex',
              height: '100vh',
              fontFamily: 'sans-serif',
              backgroundColor: '#f8fafc',
              maxWidth: '1400px', // Limitar el ancho máximo
              margin: '0 auto', // Centrar horizontalmente
            }}>
              {/* Barra lateral izquierda */}
              <aside style={{
                width: '60px',
                backgroundColor: '#1e293b',
                color: 'white',
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                paddingTop: '1rem',
                gap: '1rem',
              }}>
              </aside>

              {/* Contenido principal */}
              <div style={{
                flex: 1,
                display: 'flex',
                flexDirection: 'column',
                gap: '1rem',
                padding: '1rem',
              }}>
                {/* Encabezado */}
                <div style={{
                  borderBottom: '1px solid #e5e7eb',
                  backgroundColor: 'white',
                  display: 'flex',
                  justifyContent: 'space-between',
                  alignItems: 'center',
                  padding: '1rem',
                }}>
                  <span style={{ fontWeight: 'bold', fontSize: '2rem' }}>Servicios</span>
                  <div>
                    <button
                      style={{
                        backgroundColor: '#6366f1',
                        color: 'white',
                        border: 'none',
                        padding: '0.5rem 1rem',
                        borderRadius: '0.25rem',
                      }}
                      onClick={crearServicio}
                    >
                      Crear servicio
                    </button>
                  </div>
                </div>

                {/* Lista de servicios */}
                <div style={{
                  display: 'grid',
                  gridTemplateColumns: 'repeat(auto-fill, minmax(300px, 1fr))', // Distribuir tarjetas en columnas
                  gap: '1rem',
                  padding: '1rem',
                }}>
                  {servicios.map((servicio) => (
                    <Card key={servicio.id} servicio={servicio} onConsultar={() => consultarServicio(servicio.id)} onDelete={() => eliminarServicio(servicio.id)} onModificar={() => modificarServicio(servicio.id)} />
                  ))}
                </div>
              </div>
            </div>
          </main>
        </div>
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
              <path fill="#fff" d="m391.84 540.91c-.421-.329-.949-.524-1.523-.524-1.351 0-2.451 1.084-2.485 2.435-1.395.526-2.388 1.88-2.388 3.466 0 1.874 1.385 3.423 3.182 3.667v.034h12.73v-.006c1.775-.104 3.182-1.584 3.182-3.395 0-1.747-1.309-3.186-2.994-3.379.007-.106.011-.214.011-.322 0-2.707-2.271-4.901-5.072-4.901-2.073 0-3.856 1.202-4.643 2.925" />
            </svg>
          </span>
        </label>
      </StyledWrapper>
    </ThemeProvider>
  );
};

// Componente Card
const Card = ({ servicio, onConsultar, onDelete, onModificar }) => {
  return (
    <div
      style={{
        backgroundColor: 'white',
        borderRadius: '0.5rem',
        padding: '1rem',
        boxShadow: '0 1px 3px rgba(0,0,0,0.1)',
        display: 'flex',
        flexDirection: 'column',
        gap: '0.5rem',
      }}
    >
      <div style={{ fontSize: '1.2rem', fontWeight: 'bold' }}>{servicio.nombre}</div>
      <div>{servicio.estado}</div>
      <div style={{ display: 'flex', justifyContent: 'space-between' }}>
        <button
          style={{
            backgroundColor: '#6366f1',
            color: 'white',
            border: 'none',
            padding: '0.5rem 1rem',
            borderRadius: '0.25rem',
          }}
          onClick={onConsultar}
        >
          Consultar
        </button>
        <button
          style={{
            backgroundColor: '#ef4444',
            color: 'white',
            border: 'none',
            padding: '0.5rem 1rem',
            borderRadius: '0.25rem',
          }}
          onClick={onDelete}
        >
          Eliminar
        </button>
        <button
          style={{
            backgroundColor: '#10b981',
            color: 'white',
            border: 'none',
            padding: '0.5rem 1rem',
            borderRadius: '0.25rem',
          }}
          onClick={onModificar}
        >
          Modificar
        </button>
      </div>
    </div>
  );
};

export default ServicioPage;

// Estilos
const StyledWrapper = styled.div`
  min-height: 100vh;
  display: flex;
  background: ${(props) => props.theme.background};
  color: ${(props) => props.theme.text};
  font-family: Arial, sans-serif;

  .inventory-dashboard {
    display: flex;
    flex: 1;
  }
a{
    color:black
    }
  header {
    position: fixed;
    top: 0;
    left: 250px;
    right: 0;
    height: 60px;
    background: ${(props) => props.theme.cardBackground};
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 10;
    box-shadow: 0 4px rgba(0, 0, 0, 0.1);
  }
  header h1 {
    margin: 0;
    font-size: 1.8rem;
    cursor: default;
  }
  .control-panel {
    width: 220px;
    background-color: ${(props) => props.theme.cardBackground};
    height: 100vh;
    padding: 20px 15px;
    position: fixed;
    top: 0;
    left: 0;
    overflow-y: auto;
    box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  }
  .control-panel h2 {
    font-size: 1.2rem;
    margin-bottom: 10px;
  }
  .control-panel ul {
    list-style: none;
    padding: 0;
  }
  .control-panel li {
    margin: 10px 0;
    cursor: pointer;
    padding: 5px 10px;
    border-radius: 5px;
  }
  .control-panel li:hover {
    background-color: ${(props) => props.theme.hoverBackground};
    color: ${(props) => props.theme.text};
  }
  .dashboard-content {
    margin-left: 220px;
    padding: 80px 620px;
    width: calc(100% - 220px);
    box-sizing: border-box;
    min-height: 100vh; /* Asegura altura completa */
    background: ${(props) => props.theme.background};
    display: flex;
    justify-content: center;
    align-items: start;
  }
  table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 10px;
  }
  th, td {
    padding: 10px;
    text-align: left;
    border-bottom: 1px solid #ddd;
  }
  th {
    background-color: ${(props) => props.theme.cardBackground};
  }
  tr:hover {
    background-color: rgba(255, 255, 255, 0.05);
  }
  .switch {
    position: fixed;
    top: 20px;
    right: 20px;
    z-index: 100;
  }
  .card-section {
    display: flex;
  }
  .img_gestockpagone {
    width: 200px;
    height: 70px;
    cursor: pointer;
  }
  .img_LogoGestockpagone {
    width: 60px;
    height: 60px;
  }
`;

const lightTheme = {
  background: 'radial-gradient(circle, rgba(137, 186, 250, 1) 0%, rgba(79, 108, 255, 1) 100%)',
  text: '#000000',
  cardBackground: '#ffffff',
  hoverBackground: '#e0e0e0',
};

const darkTheme = {
  background: 'linear-gradient(0deg, rgba(20, 44, 61, 1) 0%, rgb(16, 63, 125) 100%)',
  text: '#ffffff',
  cardBackground: '#1a1a1a',
  hoverBackground: '#333333',
};