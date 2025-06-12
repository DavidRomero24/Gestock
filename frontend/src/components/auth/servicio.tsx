import { useState } from 'react';
import styled, { ThemeProvider } from "styled-components";
import { Outlet, Link } from 'react-router-dom';

const InventoryDashboard = () => {
    const data = [
        { title: "Receipts", location: "Main Street WH", process: 4, late: 1 },
        { title: "Internal Transfers", location: "Main Street WH", process: 2 },
        { title: "Delivery Orders", location: "Main Street WH", process: 5, waiting: 3 },
        { title: "Manufacturing", location: "Main Street WH", process: 4, late: 2 },
        { title: "Receipts", location: "Central Avenue WH", process: 1 },
        { title: "Internal Transfers", location: "Central Avenue WH", process: 1, late: 1 },
        { title: "Delivery Orders", location: "Central Avenue WH", process: 3, late: 1 },
        { title: "Repairs", location: "Central Avenue WH", process: 2 },
        { title: "Manufacturing", location: "Central Avenue WH", late: 2 },
        { title: "Receipts", location: "Cedar Lane Warehouse", process: 3 },
        { title: "Internal Transfers", location: "Cedar Lane Warehouse", process: 1 },
        { title: "Pick", location: "Cedar Lane Warehouse", process: 3 },
        { title: "Pick", location: "", process: 2 },
        { title: "Delivery Orders", location: "", waiting: 1 },
        { title: "Repairs", location: "", process: 1 },
    ];
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
                        <StyledWrapper>
                            <div className="inventory-dashboard">
                                <div className="header-service">
                                    <h1>Inventory Overview</h1>
                                    <input type="text" placeholder="Search..." style={{ padding: '0.5rem', borderRadius: '0.25rem', border: '1px solid #d1d5db' }} />
                                </div>
                                <div className="card-grid">
                                    {data.map((item, index) => (
                                        <div className="card" key={index}>
                                            <h3>{item.title}</h3>
                                            <small>{item.location}</small>
                                            <div className="status">
                                                {item.process && <div className="to-process">{item.process} To Process</div>}
                                                {item.late && <div className="late">{item.late} Late</div>}
                                                {item.waiting && <div className="waiting">{item.waiting} Waiting</div>}
                                            </div>
                                        </div>
                                    ))}
                                </div>
                            </div>
                        </StyledWrapper>
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
  padding: 80px 610px;
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
min-height: 100vh;
  display: flex;
  background: #f8fafc;
  color: #111827;
  font-family: Arial, sans-serif;

  .inventory-dashboard {
    display: flex;
    flex: 1;
    flex-direction: column;
    width: 100%;
  }

  .header-service {
    padding: 1rem;
    background: white;
    border-bottom: 1px solid #e5e7eb;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .card-grid {
    padding: 1rem;
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
    gap: 1rem;
    flex: 1;
    background: #f1f5f9;
  }

  .card {
    background: white;
    border-radius: 0.5rem;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
    padding: 1rem;
    display: flex;
    flex-direction: column;
    gap: 0.5rem;
  }

  .card h3 {
    margin: 0;
    font-size: 1rem;
  }

  .card small {
    color: #6b7280;
  }

  .card .status {
    display: flex;
    justify-content: space-between;
    font-size: 0.75rem;
  }

  .status div {
    padding: 0.2rem 0.5rem;
    border-radius: 0.25rem;
    font-weight: bold;
    color: white;
  }

  .to-process {
    background: #7c3aed;
  }

  .late {
    background: #ef4444;
  }

  .waiting {
    background: #10b981;
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
}  
