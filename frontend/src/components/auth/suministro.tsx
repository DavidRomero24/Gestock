import { useState } from 'react';
import styled, { ThemeProvider } from "styled-components";
import { Outlet, Link } from 'react-router-dom';

const InventoryDashboard = () => {

    const [darkMode, setDarkMode] = useState(false);
    const theme = darkMode ? darkTheme : lightTheme;

    const toggleTheme = () => {
        setDarkMode(!darkMode);
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
                        <img className="img_gestockpagone" src="../src/assets/Gestock.png" alt="Logo de la empresa" />
                        <ul>
                            <li><Link to="/PanelDeControlPage">Panel de control</Link></li>
                            <li><Link to="/EmpleadoPage">Empleados</Link></li>
                            <li><Link to="/ClientePage">Clientes</Link></li>
                            <li><Link to="/ProveedorPage">Proveedores</Link></li>
                            <li><Link to="/ProductoPage">Productos</Link></li>
                            <li><Link to="/ServicioPage">Servicios</Link></li>
                            <li><Link to="/FacturaPage">Facturas</Link></li>
                            <li style={{backgroundColor: '#eaeded'}}><Link to="/SuministroPage">Suministros</Link></li>
                        </ul>
                    </nav>
                    <main className="dashboard-content">
                        <PurchaseScreen>
                            <div className="topbar">
                                <span>GESTOCK</span>
                                <span>GESTION INVENTARIO MICROEMPRESAS</span>
                            </div>
                            <div className="content">
                                <div className="menu-bar">
                                </div>
                                <div className="main-section">
                                    <div className="table-section">
                                        <div className="table-actions">
                                            <button>🔍</button>
                                            <button>➕</button>
                                            <button>✏️</button>
                                            <button>🗑️</button>
                                        </div>
                                        <table>
                                            <thead>
                                                <tr>
                                                    <th>Tipo</th>
                                                    <th>Número</th>
                                                    <th>Fecha</th>
                                                    <th>Proveedor</th>
                                                    <th>NIF/CIF</th>
                                                    <th>Base</th>
                                                    <th>Impuesto</th>
                                                    <th>Total</th>
                                                    <th>Retención</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>📄</td>
                                                    <td>1/2016</td>
                                                    <td>06/01/2020</td>
                                                    <td>Inmobiliaria Locales SA</td>
                                                    <td>B48478131</td>
                                                    <td>600,00 €</td>
                                                    <td>126,00 €</td>
                                                    <td>726,00 €</td>
                                                    <td>-</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div className="details-panel">
                                        <h3>1/2016 - <span className="highlight">Inmobiliaria Locales SA</span></h3>
                                        <div className="status">PENDIENTE DE PAGO</div>
                                        <p>Total: <strong>726,00 €</strong></p>
                                        <p>Vencimiento: 08/01/2020</p>
                                        <p>Pagado: 08/01/2020</p>
                                    </div>
                                </div>
                            </div>
                        </PurchaseScreen>
                    </main>
                </div>
                <label className="switch">
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
                </label>
            </StyledWrapper>
        </ThemeProvider>
    );
};

export default InventoryDashboard;
const PurchaseScreen = styled.div`
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  font-family: Arial, sans-serif;
  background: #f3f4f6;

  .topbar {
    background: #0066cc;
    color: white;
    padding: 0.75rem 1rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 0.9rem;
  }

  .content {
    display: flex;
    flex: 1;
  }

  .menu-bar {
    width: 60px;
    background: #1e293b;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 1rem;
    color: white;
  }

  .main-section {
    flex: 1;
    display: flex;
    padding: 1rem;
    gap: 1rem;
  }

  .table-section {
    flex: 2;
    background: white;
    border-radius: 0.25rem;
    padding: 1rem;
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.05);
  }

  table {
    width: 100%;
    border-collapse: collapse;
    font-size: 0.85rem;
  }

  th, td {
    border-bottom: 1px solid #e5e7eb;
    padding: 0.5rem;
    text-align: left;
  }

  .table-actions {
    display: flex;
    gap: 0.5rem;
    margin: 0.5rem 0;
  }

  .table-actions button {
    background: none;
    border: none;
    cursor: pointer;
    font-size: 1rem;
  }

  .details-panel {
    width: 260px;
    background: white;
    border-radius: 0.25rem;
    padding: 1rem;
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.05);
  }

  .details-panel h3 {
    margin-top: 0;
    font-size: 1rem;
  }

  .highlight {
    color: #007bff;
    font-weight: bold;
  }

  .status {
    background: #fcd34d;
    padding: 0.25rem 0.5rem;
    border-radius: 0.25rem;
    font-size: 0.75rem;
    font-weight: bold;
    margin: 0.5rem 0;
  }
`;
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
    box-shadow: 0 4px rgba(0,0,0,0.1);
  }
    header h1{
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
    background-color: ${(props) => props.theme.background};
    color: ${(props) => props.theme.text};
  }

  .dashboard-content {
    margin-left: 220px;
  padding: 80px 250px;
  width: calc(100% - 220px);
  box-sizing: border-box;
  min-height: 100vh; /* Asegura altura completa */
  background: ${(props) => props.theme.background};
  display: flex;
  justify-content: center;
  align-items: start;
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
  margin: 60px
  }

  .data-section h3 {
    border-bottom: 1px solid #ddd;
    padding-bottom: 5px;
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
    background-color: rgba(255,255,255,0.05);
  }

  .switch {
    position: fixed;
    top: 20px;
    right: 20px;
    z-index: 100;
  }

  .control-paneling{
    
  }

  .card-section{
    display: flex;
  }
  
  .img_gestockpagone{
    width: 200px;
    height: 70px;
    cursor: pointer;
  }
  
  
  .img_LogoGestockpagone{
    width: 60px;
    height: 60px
  }
    
  
  .control-panel li:hover {
    background-color: ${(props) => props.theme.hoverBackground}; 
    color: ${(props) => props.theme.text}; 
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
