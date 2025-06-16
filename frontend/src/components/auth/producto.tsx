import { useState } from 'react';
import styled, { ThemeProvider } from "styled-components";
import { Outlet, Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const ProductoPage = () => {
  const navigate = useNavigate();
  const [darkMode, setDarkMode] = useState(false);
  const theme = darkMode ? darkTheme : lightTheme;
  const toggleTheme = () => {
    setDarkMode(!darkMode);
  };

  // Datos iniciales de productos
  const [productos, setProductos] = useState([
    { id: 1, codigo: "1001", nombre: "Mesa de noche redonda", precio: 938000 },
    { id: 2, codigo: "1002", nombre: "Adorno en cerámica rosa", precio: 368000 },
    { id: 3, codigo: "1003", nombre: "Planta decorativa", precio: 210000 },
    { id: 4, codigo: "1004", nombre: "Adorno en cerámica blanca", precio: 398000 },
    { id: 5, codigo: "1005", nombre: "Planta Kokedama", precio: 230000 },
    { id: 6, codigo: "1006", nombre: "Canasta tejida", precio: 238000 },
  ]);

  // Estado para el producto seleccionado
  const [productoSeleccionado, setProductoSeleccionado] = useState(null);

  // Función para agregar un producto a la factura
  const agregarProducto = (producto) => {
    console.log(`Agregando producto: ${producto.nombre}`);
  };

  // Función para eliminar un producto
  const eliminarProducto = (id) => {
    setProductos(productos.filter((p) => p.id !== id));
  };

  // Función para editar un producto
  const editarProducto = (id) => {
    console.log(`Editando producto con ID: ${id}`);
  };

  // Función para mostrar detalles de un producto
  const verDetalles = (producto) => {
    setProductoSeleccionado(producto);
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
              <li style={{backgroundColor: '#eaeded'}}><Link to="/ProductoPage">Productos</Link></li>
              <li><Link to="/ServicioPage">Servicios</Link></li>
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
                  <span style={{ fontWeight: 'bold' }}>Facturar</span>
                  <input
                    type="text"
                    placeholder="Buscar productos"
                    style={{
                      width: '300px',
                      padding: '0.5rem',
                      borderRadius: '0.25rem',
                      border: '1px solid #d1d5db',
                    }}
                  />
                </div>
                {/* Sección de productos */}
                <div style={{
                  display: 'flex',
                  flex: 1,
                  gap: '1rem',
                  overflowY: 'auto', // Permitir scroll vertical si hay muchos productos
                }}>
                  {/* Lista de productos */}
                  <section style={{
                    flex: 2,
                    padding: '1rem',
                    display: 'grid',
                    gridTemplateColumns: 'repeat(auto-fill, minmax(200px, 1fr))', // Distribuir productos en columnas
                    gap: '1rem',
                    backgroundColor: '#f1f5f9',
                  }}>
                    {productos.map((p) => (
                      <div
                        key={p.id}
                        style={{
                          backgroundColor: 'white',
                          borderRadius: '0.5rem',
                          padding: '1rem',
                          boxShadow: '0 1px 3px rgba(0,0,0,0.1)',
                          display: 'flex',
                          flexDirection: 'column',
                          alignItems: 'center',
                          gap: '0.5rem',
                        }}
                      >
                        <div style={{ fontSize: '0.75rem', fontWeight: 'bold', color: '#475569' }}>{p.codigo}</div>
                        <div
                          style={{
                            height: '80px',
                            width: '80px',
                            backgroundColor: '#e2e8f0',
                            borderRadius: '0.5rem',
                          }}
                        />
                        <div style={{ textAlign: 'center', fontWeight: 'bold' }}>{p.nombre}</div>
                        <div style={{ color: '#0f766e', fontWeight: 'bold' }}>${p.precio.toLocaleString('es-CO')}</div>
                        <button
                          style={{
                            backgroundColor: '#0f766e',
                            color: 'white',
                            border: 'none',
                            padding: '0.25rem 0.75rem',
                            borderRadius: '0.25rem',
                          }}
                          onClick={() => agregarProducto(p)}
                        >
                          +
                        </button>
                        <button
                          style={{
                            backgroundColor: '#ff4d4d',
                            color: 'white',
                            border: 'none',
                            padding: '0.25rem 0.75rem',
                            borderRadius: '0.25rem',
                            marginTop: '0.5rem',
                          }}
                          onClick={() => eliminarProducto(p.id)}
                        >
                          Eliminar
                        </button>
                        <button
                          style={{
                            backgroundColor: '#28a745',
                            color: 'white',
                            border: 'none',
                            padding: '0.25rem 0.75rem',
                            borderRadius: '0.25rem',
                            marginTop: '0.5rem',
                          }}
                          onClick={() => editarProducto(p.id)}
                        >
                          Editar
                        </button>
                        <button
                          style={{
                            backgroundColor: '#6c757d',
                            color: 'white',
                            border: 'none',
                            padding: '0.25rem 0.75rem',
                            borderRadius: '0.25rem',
                            marginTop: '0.5rem',
                          }}
                          onClick={() => verDetalles(p)}
                        >
                          Ver detalles
                        </button>
                      </div>
                    ))}
                  </section>
                  {/* Panel de facturación */}
                  <aside style={{
                    width: '400px',
                    backgroundColor: 'white',
                    borderLeft: '1px solid #e5e7eb',
                    padding: '1rem',
                    display: 'flex',
                    flexDirection: 'column',
                    gap: '1rem',
                  }}>
                    <div>
                      <div style={{ marginBottom: '0.5rem', fontWeight: 'bold' }}>Factura de venta</div>
                      <select style={selectStyle}>
                        <option>Lista de precio General</option>
                      </select>
                      <select style={{ ...selectStyle, marginTop: '0.5rem' }}>
                        <option>Cliente: INDU S.A.S.</option>
                      </select>
                    </div>
                    <div style={{ flex: 1, display: 'flex', alignItems: 'center', justifyContent: 'center', color: '#94a3b8' }}>
                      Aquí verás los productos que elijas para la venta
                    </div>
                    <div>
                      <select style={{ ...selectStyle, marginBottom: '0.5rem' }}>
                        <option>Vendedor</option>
                      </select>
                      <button
                        style={{
                          width: '100%',
                          backgroundColor: '#0f766e',
                          color: 'white',
                          padding: '0.5rem',
                          borderRadius: '0.25rem',
                          border: 'none',
                        }}
                      >
                        Finalizar
                      </button>
                    </div>
                  </aside>
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

export default ProductoPage;

const selectStyle = {
  width: '100%',
  padding: '0.5rem',
  borderRadius: '0.25rem',
  border: '1px solid #d1d5db',
};

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
    box-shadow: 0 4px rgba(0, 0, 0, 0.1);
  }
  header h1 {
    margin: 0;
    font-size: 1.8rem;
    cursor: default;
  }
    a{
    color:black
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
    padding: 80px 440px;
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