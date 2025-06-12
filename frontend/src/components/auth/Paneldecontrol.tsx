import { useState } from 'react';
import styled, { ThemeProvider } from "styled-components";
import { Link } from 'react-router-dom';

const PanelControl = () => {

  const stats = [
    { label: "Usuarios", value: 3 },
    { label: "Categorías", value: 1 },
    { label: "Productos", value: 3 },
    { label: "Ventas", value: 7 }
  ];

  const topProducts = [{ title: "Espada MS250", totalSold: 3, totalQuantity: 4 },
  { title: "Filtro de gasolina", totalSold: 2, totalQuantity: 2 },
  { title: "Carburador", totalSold: 2, totalQuantity: 2 }];
  const recentSales = [{ id: 1, product: "Espada MS250", date: "2017-06-16", total: "$100.00" },
  { id: 2, product: "Carburador", date: "2017-06-16", total: "$80.00" },
  { id: 3, product: "Espada MS250", date: "2017-06-16", total: "$200.00" },
  { id: 4, product: "Filtro de gasolina", date: "2017-06-16", total: "$10.00" },
  { id: 5, product: "Carburador", date: "2017-06-16", total: "$80.00" }];
  const recentlyAdded = [{ id: 1, name: "Espada MS250", price: "$100", category: "Repuestos" },
  { id: 2, name: "", price: "$20", category: "Repuestos" },
  { id: 3, name: "", price: "$10", category: "Repuestos" }];



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
            <div className="control-paneling">
              <div className="stats-container">
                {stats.map((stat, index) => (
                  <div key={index} className="stat-card">
                    <div className="stat-value">{stat.value}</div>
                    <div className="stat-label">{stat.label}</div>
                  </div>
                ))}
              </div>
              <div className="card-section">
                <div className="data-section">
                  <div className="card-one">
                    <h3>III PRODUCTOS MÁS VENDIDOS</h3>
                    <table>
                      <thead>
                        <tr>
                          <th>Título</th>
                          <th>Total vendido</th>
                          <th>Cantidad total</th>
                        </tr>
                      </thead>
                      <tbody>
                        {topProducts.map((product, index) => (
                          <tr key={index}>
                            <td>{product.title}</td>
                            <td>{product.totalSold}</td>
                            <td>{product.totalQuantity}</td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                </div>

                <div className="data-section">
                  <div className="card-two">
                    <h3>III ÚLTIMAS VENTAS</h3>
                    <table>
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Producto</th>
                          <th>Fecha</th>
                          <th>Venta total</th>
                        </tr>
                      </thead>
                      <tbody>
                        {recentSales.map((sale) => (
                          <tr key={sale.id}>
                            <td>{sale.id}</td>
                            <td>{sale.product}</td>
                            <td>{sale.date}</td>
                            <td>{sale.total}</td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                </div>

                <div className="data-section">
                  <div className="card-three">
                    <h3>III PRODUCTOS RECIENTEMENTE AÑADIDOS</h3>
                    <table>
                      <tbody>
                        {recentlyAdded.map((item) => (
                          <tr key={item.id}>
                            <td>{item.id}</td>
                            <td>{item.name}</td>
                            <td>{item.price}</td>
                            <td>{item.category}</td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
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

export default PanelControl;

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
  padding: 80px 30px 30px 30px;
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
