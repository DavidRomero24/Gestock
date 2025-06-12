
import { useState } from 'react';
import { Link } from 'react-router-dom';
import styled, { ThemeProvider } from "styled-components";

const ProveedorPage = () => {
    const cuentas = [
        { cuenta: "10000000", nombre: "Capital Social", grupo: "Financiación básica", grupoColor: "green", debe: 0, haber: 3000, saldo: -3000 },
        { cuenta: "21600000", nombre: "Mobiliario", grupo: "Activo no corriente", grupoColor: "purple", debe: 10000, haber: 0, saldo: 10000 },
        { cuenta: "40000000", nombre: "Proveedores", grupo: "Proveedores", grupoColor: "orange", debe: 0, haber: 3200, saldo: -3200 },
        { cuenta: "43000000", nombre: "Clientes", grupo: "Clientes", grupoColor: "yellow", debe: 3000, haber: 0, saldo: 3000 },
        { cuenta: "57200000", nombre: "Bancos", grupo: "Cuentas financieras", grupoColor: "blue", debe: 4300, haber: 0, saldo: 4300 },
        { cuenta: "62800000", nombre: "Suministros", grupo: "Gastos", grupoColor: "gray", debe: 6000, haber: 0, saldo: 6000 },
    ];
    const proveedores = [
        { id: 1, nombre: 'Empresa A', fecha: '2023-09-01', ciudad: 'Bogotá', confirmado: true },
        { id: 2, nombre: 'Empresa B', fecha: '2023-08-15', ciudad: 'Medellín', confirmado: false },
        { id: 3, nombre: 'Empresa C', fecha: '2023-07-20', ciudad: 'Cali', confirmado: true },
    ];

    const [darkMode, setDarkMode] = useState(false);
    const theme = darkMode ? darkTheme : lightTheme;
    const toggleTheme = () => {
        setDarkMode(!darkMode);
    };

    return (
        <ThemeProvider theme={theme}>
            <MainContent>
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
                        <div style={{ padding: '1.5rem', minHeight: '100vh', color: 'white' }}>
                            <div style={{ backgroundColor: 'white', color: 'black', borderRadius: '0.5rem', overflow: 'hidden' }}>
                                <div style={{ padding: '1.5rem' }}>
                                    <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1rem' }}>
                                        <h2 style={{ fontSize: '1.25rem', fontWeight: 'bold' }}>Cuadro de cuentas</h2>
                                        <div style={{ display: 'flex', gap: '0.5rem' }}>
                                            <button style={{ border: '1px solid #ccc', padding: '0.5rem 1rem', borderRadius: '0.25rem', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>Nuevo asiento</button>
                                            <button style={{ backgroundColor: '#2563eb', color: 'white', padding: '0.5rem 1rem', borderRadius: '0.25rem', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>Nueva cuenta</button>
                                        </div>
                                    </div>
                                    <table style={{ width: '100%', borderCollapse: 'collapse' }}>
                                        <thead>
                                            <tr>
                                                <th style={thStyle}>Cuenta</th>
                                                <th style={thStyle}>Nombre</th>
                                                <th style={thStyle}>Grupo</th>
                                                <th style={thStyle}>Debe</th>
                                                <th style={thStyle}>Haber</th>
                                                <th style={thStyle}>Saldo</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {cuentas.map((c, i) => (
                                                <tr key={i} style={{ borderTop: '1px solid #ccc' }}>
                                                    <td style={tdStyle}>{c.cuenta}</td>
                                                    <td style={tdStyle}>{c.nombre}</td>
                                                    <td style={tdStyle}>
                                                        <span style={{ backgroundColor: getBadgeColor(c.grupoColor), color: 'white', borderRadius: '0.25rem', padding: '0.2rem 0.5rem', fontSize: '0.75rem' }}>{c.grupo}</span>
                                                    </td>
                                                    <td style={tdStyle}>{c.debe}</td>
                                                    <td style={tdStyle}>{c.haber}</td>
                                                    <td style={tdStyle}>{c.saldo}</td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>
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
            </MainContent>
        </ThemeProvider>
    );
};

export default ProveedorPage;
const thStyle = {
  textAlign: 'left',
  padding: '0.75rem',
  fontWeight: 'bold',
  backgroundColor: '#f3f4f6',
  fontSize: '0.875rem'
};

const tdStyle = {
  padding: '0.75rem',
  fontSize: '0.875rem'
};

function getBadgeColor(colorName) {
  const colors = {
    green: '#10b981',
    purple: '#8b5cf6',
    orange: '#f97316',
    yellow: '#facc15',
    blue: '#3b82f6',
    gray: '#6b7280'
  };
  return colors[colorName] || '#000';
}
// Estilos
const MainContent = styled.div`
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
    padding: 80px 580px;
    width: calc(100% - 220px);
    box-sizing: border-box;
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
    background-color: rgba(255,255,255,0.05);
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
  table {
    width: 100%;
    border-collapse: collapse;
    th, td {
      padding: 10px;
      border: 1px solid #ccc;
      text-align: left;
    }
    .edit-btn {
      background: #28a745;
      color: white;
      border: none;
      padding: 6px 10px;
      border-radius: 4px;
      cursor: pointer;
    }
    .edit-btn:hover {
      background: #218838;
    }
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