import { useState } from 'react';
import styled from 'styled-components';
import { Outlet, Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const ClientePage = () => {
  const navigate = useNavigate();
  const clientes = [
    { id: 1, nombre: 'Pedro Picapiedra', fecha: '24/02/2021', entrego: 'Juan', ciudad: 'Ocaña', confirmado: true },
    { id: 2, nombre: 'Marta López', fecha: '22/02/2021', entrego: 'Ana', ciudad: 'Ocaña', confirmado: false },
  ];
  const [darkMode, setDarkMode] = useState(false);
  const theme = darkMode ? darkTheme : lightTheme;
  const toggleTheme = () => {
    setDarkMode(!darkMode);
  };
  return (
    <MainContent theme={theme}>
      <div className="inventory-dashboard">

        <header onClick={() => navigate('/PageTwo')} style={{ cursor: 'pointer' }}>
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
            <li style={{ backgroundColor: '#eaeded' }}><Link to="/ClientePage">Clientes</Link></li>
            <li><Link to="/ProveedorPage">Proveedores</Link></li>
            <li><Link to="/ProductoPage">Productos</Link></li>
            <li><Link to="/ServicioPage">Servicios</Link></li>
            <li><Link to="/FacturaPage">Facturas</Link></li>
            <li><Link to="/SuministroPage">Suministros</Link></li>
          </ul>
        </nav>
        <main className="dashboard-content">
          <HeaderSection>
            <h1>Clientes</h1>
            <ActionButtons>
              <button>Nuevo Cliente</button>
              <button>Filtrar</button>
              <button>Imprimir</button>
              <Dropdown>
                <button>Estadísticas ▾</button>
                <div className="dropdown-content">
                  <a href="#">Clientes frecuentes</a>
                  <a href="#">Por ciudad</a>
                  <a href="#">Por fecha</a>
                </div>
              </Dropdown>
              <Dropdown>
                <button>Plantillas ▾</button>
                <div className="dropdown-content">
                  <a href="#">Exportar Excel</a>
                  <a href="#">Exportar PDF</a>
                </div>
              </Dropdown>
            </ActionButtons>
          </HeaderSection>
          <TableContainer>
            <Card>
              <div className="card-header">
                <h2>Clientes</h2>
                <button className="add-button">Agregar cliente</button>
              </div>
              <table>
                <thead>
                  <tr>
                    <th>Nombre</th>
                    <th>Fecha</th>
                    <th>Ciudad</th>
                    <th>Confirmado</th>
                    <th>Acciones</th>
                  </tr>
                </thead>
                <tbody>
                  {clientes.map((cliente) => (
                    <tr key={cliente.id}>
                      <td>{cliente.nombre}</td>
                      <td>{cliente.fecha}</td>
                      <td>{cliente.ciudad}</td>
                      <td>{cliente.confirmado ? 'Sí' : 'No'}</td>
                      <td>
                        <button className="edit-btn">Editar</button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </Card>
          </TableContainer>
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
    </MainContent>
  );
};
export default ClientePage;

// Estilos
const MainContent = styled.div`
  min-height: 100vh; /* Asegura que ocupe toda la altura de la ventana */
  display: flex;
  flex-direction: column; /* Distribuye el contenido verticalmente */
  background: ${(props) => props.theme.background};
  color: ${(props) => props.theme.text};
  font-family: Arial, sans-serif;
  .inventory-dashboard {
    display: flex;
    flex: 1; /* Hace que este elemento ocupe el espacio restante */
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
    box-shadow: 0 4px rgba(0,0,0,0.1);
  }
  header h1 {
    margin: 0;
    font-size: 1.8rem;
    cursor: default;
  }
  .control-panel {
    width: 220px;
    background-color: ${(props) => props.theme.cardBackground};
    height: 100vh; /* Asegura que el panel lateral ocupe toda la altura */
    padding: 20px 15px;
    position: fixed;
    top: 0;
    left: 0;
    overflow-y: auto;
    box-shadow: 2px 0 5px rgba(0,0,0,0.1);
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
    padding: 80px 500px;
    width: calc(100% - 220px);
    box-sizing: border-box;
    min-height: 100vh; /* Asegura que ocupe toda la altura */
    background: ${(props) => props.theme.background};
    display: flex;
    flex-direction: column; /* Distribuye el contenido verticalmente */
    justify-content: start; /* Alinea el contenido al inicio */
    align-items: center; /* Centra horizontalmente */
  }
  .stats-container {
    display: flex;
    grid-template-columns: repeat(2, 1fr); /* Cuadrícula de 2x2 como OSWA */
    gap: 20px;
    justify-content: center;
  }
  .stat-card {
    background-color: ${(props) => props.theme.cardBackground};
    padding: 20px;
    text-align: center;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  }
  .stat-value {
    font-size: 2rem;
    font-weight: bold;
  }
  .data-section {
    margin-bottom: 30px;
    background-color: rgba(255, 255, 255, 0.05); /* un toque sutil */
    padding: 20px;
    border-radius: 12px;
    box-shadow: 0 2px 6px rgba(0,0,0,0.1);
    margin: 60px;
  }
  .data-section h3 {
    border-bottom: 1px solid #ddd;
    padding-bottom: 5px;
  }
  table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 10px;
    background-color: white; /* Ensure the table has a white background */
    border: 1px solid #ccc; /* Add border to the table */
  }
  th, td {
    padding: 10px;
    text-align: left;
    border: 1px solid #ccc; /* Add border to cells */
  }
  th {
    background-color: #f0f0f0; /* Light gray for header cells */
    font-weight: bold; /* Make header text bold */
    text-align: center; /* Center-align header text */
    color: #0066cc; /* Header text color */
  }
  tr:nth-child(even) {
    background-color: #f9f9f9; /* Alternate row background color */
  }
  tr:hover {
    background-color: rgba(255,255,255,0.05);
  }
  .nombre-cell { /* Target the "Nombre" column cells */
    background-color: white; /* Set background to white */
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

const HeaderSection = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
`;

const ActionButtons = styled.div`
  display: flex;
  gap: 10px;
  button {
    padding: 8px 12px;
    background: #0066cc;
    color: white;
    border: none;
    cursor: pointer;
    border-radius: 4px;
  }
  button:hover {
    background: #004c99;
  }
`;

const Dropdown = styled.div`
  position: relative;
  .dropdown-content {
    display: none;
    position: absolute;
    background-color: white;
    box-shadow: 0px 8px 16px rgba(0,0,0,0.2);
    padding: 10px;
    top: 100%;
    z-index: 1;
    min-width: 180px;
  }
  &:hover .dropdown-content {
    display: block;
  }
  a {
    text-decoration: none;
    display: block;
    padding: 5px 10px;
    color: black;
  }
  a:hover {
    background-color: #f0f0f0;
  }
`;

const TableContainer = styled.div`
  overflow-x: auto;
  max-width: 100%; /* Limitar el ancho máximo */
`;

const Card = styled.div`
  background-color: white; /* Ensure the card has a white background */
  border-radius: 8px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  padding: 20px;
  margin-bottom: 20px;
`;

const CardHeader = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
`;

const AddButton = styled.button`
  background-color: #0066cc;
  color: white;
  border: none;
  padding: 8px 12px;
  border-radius: 4px;
  cursor: pointer;
  &:hover {
    background-color: #004c99;
  }
`;

const EditButton = styled.button`
  background-color: #000;
  color: white;
  border: none;
  padding: 8px 12px;
  border-radius: 4px;
  cursor: pointer;
  &:hover {
    background-color: #333;
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