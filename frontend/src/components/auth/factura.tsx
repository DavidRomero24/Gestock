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
                            <li><Link to="/ClientePage">Clientes</Link></li>
                            <li><Link to="/ProveedorPage">Proveedores</Link></li>
                            <li><Link to="/ProductoPage">Productos</Link></li>
                            <li><Link to="/ServicioPage">Servicios</Link></li>
                            <li><Link to="/FacturaPage">Facturas</Link></li>
                            <li><Link to="/SuministroPage">Suministros</Link></li>
                        </ul>
                    </nav>
                    <main className="dashboard-content">
                        <InvoiceLayout>
                            <div className="sidebar">
                                <div>🏠</div>
                                <div>📄</div>
                                <div>💼</div>
                                <div>📊</div>
                            </div>
                            <div className="main-content">
                                <div className="invoice-box">
                                    <h2>Solar Tech S.A</h2>
                                    <p>Dirección: Avenida Diagonal, 505</p>
                                    <p>Barcelona, España</p>
                                    <p>CIF: 12345678T</p>

                                    <hr style={{ margin: '1rem 0' }} />

                                    <p><strong>Cliente:</strong> Proyecto Volta S.L.</p>
                                    <p><strong>NIF:</strong> 12345678A</p>

                                    <table style={{ width: '100%', marginTop: '1rem', borderCollapse: 'collapse' }}>
                                        <thead>
                                            <tr style={{ borderBottom: '1px solid #e5e7eb' }}>
                                                <th style={{ textAlign: 'left' }}>Descripción</th>
                                                <th>Cantidad</th>
                                                <th>Precio</th>
                                                <th>Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>Servicio 1 - mantenimiento</td>
                                                <td>2</td>
                                                <td>750 €</td>
                                                <td>1.500 €</td>
                                            </tr>
                                            <tr>
                                                <td colSpan="3" style={{ textAlign: 'right' }}><strong>Total:</strong></td>
                                                <td><strong>1.500 €</strong></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div className="info-box">
                                    <div className="tabs">
                                        <button className="active">General</button>
                                        <button>Mensajes</button>
                                        <button>Historial</button>
                                    </div>

                                    <div className="info-content">
                                        <div><span>Total</span><span>1.500 €</span></div>
                                        <div><span>Número de documento</span><span>F250001</span></div>
                                        <div><span>Cliente</span><span>Proyecto Volta S.L.</span></div>
                                        <div><span>Fecha</span><span>22/01/2025</span></div>
                                        <div><span>Almacén</span><span>Barcelona</span></div>
                                    </div>

                                    <div style={{ marginTop: '1rem' }}>
                                        <p><strong>Estado:</strong> Borrador</p>
                                        <button style={{ backgroundColor: '#10b981', color: 'white', padding: '0.3rem 1rem', border: 'none', borderRadius: '0.25rem' }}>Aprobar</button>
                                    </div>

                                    <div style={{ marginTop: '1rem' }}>
                                        <p><strong>Categorización:</strong></p>
                                        <p>Cuenta contable: <span style={{ fontWeight: 'bold' }}>Prestación de servicios</span></p>
                                    </div>

                                    <button className="send-button">📤 Enviar</button>
                                </div>
                            </div>
                        </InvoiceLayout>
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
const InvoiceLayout = styled.div`
  min-height: 100vh;
  display: flex;
  background: #f8fafc;
  color: #1f2937;
  font-family: Arial, sans-serif;

  .sidebar {
    width: 60px;
    background: #1e293b;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 1rem;
    color: white;
  }

  .main-content {
    flex: 1;
    display: flex;
    padding: 2rem;
    gap: 2rem;
  }

  .invoice-box {
    flex: 2;
    background: white;
    border-radius: 0.5rem;
    padding: 2rem;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
  }

  .info-box {
    flex: 1;
    background: white;
    border-radius: 0.5rem;
    padding: 1.5rem;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    gap: 1rem;
  }

  .info-box h3 {
    margin-top: 0;
  }

  .tabs {
    display: flex;
    gap: 1rem;
    border-bottom: 1px solid #e5e7eb;
  }

  .tabs button {
    background: none;
    border: none;
    font-weight: bold;
    padding-bottom: 0.5rem;
    border-bottom: 2px solid transparent;
    cursor: pointer;
  }

  .tabs button.active {
    border-color: #3b82f6;
    color: #3b82f6;
  }

  .info-content {
    display: flex;
    flex-direction: column;
    gap: 0.5rem;
  }

  .info-content div {
    display: flex;
    justify-content: space-between;
  }

  .send-button {
    background: #3b82f6;
    color: white;
    padding: 0.5rem 1rem;
    border: none;
    border-radius: 0.25rem;
    cursor: pointer;
    align-self: flex-start;
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
  padding: 80px 320px;
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
