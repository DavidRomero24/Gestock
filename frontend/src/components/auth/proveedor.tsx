import { useState } from 'react';
import styled, { ThemeProvider } from "styled-components";
import { Link } from 'react-router-dom';

const ProveedorPage = () => {
    // Estado inicial de los proveedores
    const [proveedores, setProveedores] = useState([
        { id: 1, nombre: 'Empresa A', fecha: '2023-09-01', ciudad: 'Bogotá', confirmado: true },
        { id: 2, nombre: 'Empresa B', fecha: '2023-08-15', ciudad: 'Medellín', confirmado: false },
        { id: 3, nombre: 'Empresa C', fecha: '2023-07-20', ciudad: 'Cali', confirmado: true },
    ]);

    // Estado para el modo oscuro
    const [darkMode, setDarkMode] = useState(false);
    const theme = darkMode ? darkTheme : lightTheme;
    const toggleTheme = () => {
        setDarkMode(!darkMode);
    };

    // Estado para el modal de agregar proveedor
    const [showAddModal, setShowAddModal] = useState(false);
    const [newProveedor, setNewProveedor] = useState({
        nombre: "",
        fecha: "",
        ciudad: "",
        confirmado: false,
    });

    // Estado para el modal de editar proveedor
    const [showEditModal, setShowEditModal] = useState(false);
    const [editingProveedor, setEditingProveedor] = useState(null);

    // Estado para el modal de ver detalles del proveedor
    const [showViewModal, setShowViewModal] = useState(false);
    const [selectedProveedor, setSelectedProveedor] = useState(null);

    // Manejar cambios en el formulario de nuevo proveedor
    const handleInputChange = (e) => {
        const { name, value, type, checked } = e.target;
        if (type === "checkbox") {
            setNewProveedor({ ...newProveedor, [name]: checked });
        } else {
            setNewProveedor({ ...newProveedor, [name]: value });
        }
    };

    // Agregar un nuevo proveedor
    const handleAddProveedor = () => {
        if (!newProveedor.nombre || !newProveedor.fecha || !newProveedor.ciudad) {
            alert("Por favor, complete todos los campos obligatorios.");
            return;
        }
        const newId = proveedores.length > 0 ? proveedores[proveedores.length - 1].id + 1 : 1;
        const updatedProveedores = [...proveedores, { ...newProveedor, id: newId }];
        setProveedores(updatedProveedores);
        setShowAddModal(false); // Cerrar el modal
        setNewProveedor({ nombre: "", fecha: "", ciudad: "", confirmado: false }); // Reiniciar el formulario
    };

    // Editar un proveedor existente
    const handleEditProveedor = (proveedor) => {
        setEditingProveedor(proveedor);
        setShowEditModal(true);
        setNewProveedor({ ...proveedor }); // Prellenar el formulario con los datos actuales
    };

    // Guardar cambios al editar un proveedor
    const handleUpdateProveedor = () => {
        if (!newProveedor.nombre || !newProveedor.fecha || !newProveedor.ciudad) {
            alert("Por favor, complete todos los campos obligatorios.");
            return;
        }
        const updatedProveedores = proveedores.map((prov) =>
            prov.id === editingProveedor.id ? { ...prov, ...newProveedor } : prov
        );
        setProveedores(updatedProveedores);
        setShowEditModal(false);
        setEditingProveedor(null);
        setNewProveedor({ nombre: "", fecha: "", ciudad: "", confirmado: false });
    };

    // Ver detalles de un proveedor
    const handleViewProveedor = (proveedor) => {
        setSelectedProveedor(proveedor);
        setShowViewModal(true);
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
                            <li><Link to="/EmpleadoPage">Empleados</Link></li>
                            <li><Link to="/ClientePage">Clientes</Link></li>
                            <li style={{ backgroundColor: '#eaeded' }}><Link to="/ProveedorPage">Proveedores</Link></li>
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
                                        <h2 style={{ fontSize: '1.25rem', fontWeight: 'bold' }}>Proveedores</h2>
                                        <div style={{ display: 'flex', gap: '0.5rem' }}>
                                            <button style={{ border: '1px solid #ccc', padding: '0.5rem 1rem', borderRadius: '0.25rem', display: 'flex', alignItems: 'center', gap: '0.5rem' }} onClick={() => setShowAddModal(true)}>Agregar proveedor</button>
                                        </div>
                                    </div>
                                    <table style={{ width: '100%', borderCollapse: 'collapse' }}>
                                        <thead>
                                            <tr>
                                                <th style={thStyle}>Nombre</th>
                                                <th style={thStyle}>Fecha</th>
                                                <th style={thStyle}>Ciudad</th>
                                                <th style={thStyle}>Confirmado</th>
                                                <th style={thStyle}>Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {proveedores.map((prov, idx) => (
                                                <tr key={idx} style={{ borderTop: '1px solid #ccc' }}>
                                                    <td style={tdStyle}><span className="name-link" onClick={() => handleViewProveedor(prov)}>{prov.nombre}</span></td>
                                                    <td style={tdStyle}>{prov.fecha}</td>
                                                    <td style={tdStyle}>{prov.ciudad}</td>
                                                    <td style={tdStyle}>{prov.confirmado ? "Sí" : "No"}</td>
                                                    <td style={tdStyle}>
                                                        <button className="edit-btn" onClick={() => handleEditProveedor(prov)}>Editar</button>
                                                    </td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </main>
                </div>

                {/* Modal para agregar proveedor */}
                {showAddModal && (
                    <div className="modal-overlay">
                        <div className="modal">
                            <h2>Agregar Nuevo Proveedor</h2>
                            <form>
                                <label htmlFor="nombre">Nombre:</label>
                                <input
                                    type="text"
                                    id="nombre"
                                    name="nombre"
                                    value={newProveedor.nombre}
                                    onChange={handleInputChange}
                                    required
                                />

                                <label htmlFor="fecha">Fecha:</label>
                                <input
                                    type="date"
                                    id="fecha"
                                    name="fecha"
                                    value={newProveedor.fecha}
                                    onChange={handleInputChange}
                                    required
                                />

                                <label htmlFor="ciudad">Ciudad:</label>
                                <input
                                    type="text"
                                    id="ciudad"
                                    name="ciudad"
                                    value={newProveedor.ciudad}
                                    onChange={handleInputChange}
                                    required
                                />

                                <label htmlFor="confirmado">Confirmado:</label>
                                <input
                                    type="checkbox"
                                    id="confirmado"
                                    name="confirmado"
                                    checked={newProveedor.confirmado}
                                    onChange={handleInputChange}
                                />

                                <button type="button" onClick={handleAddProveedor}>Guardar</button>
                                <button type="button" onClick={() => setShowAddModal(false)}>Cancelar</button>
                            </form>
                        </div>
                    </div>
                )}

                {/* Modal para editar proveedor */}
                {showEditModal && (
                    <div className="modal-overlay">
                        <div className="modal">
                            <h2>Editar Proveedor</h2>
                            <form>
                                <label htmlFor="nombre">Nombre:</label>
                                <input
                                    type="text"
                                    id="nombre"
                                    name="nombre"
                                    value={newProveedor.nombre}
                                    onChange={handleInputChange}
                                    required
                                />

                                <label htmlFor="fecha">Fecha:</label>
                                <input
                                    type="date"
                                    id="fecha"
                                    name="fecha"
                                    value={newProveedor.fecha}
                                    onChange={handleInputChange}
                                    required
                                />

                                <label htmlFor="ciudad">Ciudad:</label>
                                <input
                                    type="text"
                                    id="ciudad"
                                    name="ciudad"
                                    value={newProveedor.ciudad}
                                    onChange={handleInputChange}
                                    required
                                />

                                <label htmlFor="confirmado">Confirmado:</label>
                                <input
                                    type="checkbox"
                                    id="confirmado"
                                    name="confirmado"
                                    checked={newProveedor.confirmado}
                                    onChange={handleInputChange}
                                />

                                <button type="button" onClick={handleUpdateProveedor}>Guardar Cambios</button>
                                <button type="button" onClick={() => setShowEditModal(false)}>Cancelar</button>
                            </form>
                        </div>
                    </div>
                )}

                {/* Modal para ver detalles del proveedor */}
                {showViewModal && (
                    <div className="modal-overlay">
                        <div className="modal">
                            <h2>Detalles del Proveedor</h2>
                            <p><strong>Nombre:</strong> {selectedProveedor?.nombre}</p>
                            <p><strong>Fecha:</strong> {selectedProveedor?.fecha}</p>
                            <p><strong>Ciudad:</strong> {selectedProveedor?.ciudad}</p>
                            <p><strong>Confirmado:</strong> {selectedProveedor?.confirmado ? "Sí" : "No"}</p>
                            <button type="button" onClick={() => setShowViewModal(false)}>Cerrar</button>
                        </div>
                    </div>
                )}

                {/* Interruptor de tema claro/oscuro */}
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

// Estilos globales
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

  a {
    color: black;
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

  th,
  td {
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

  /* Estilos para los modales */
  .modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .modal {
    background: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
    width: 400px;
    text-align: center;
  }

  .modal h2 {
    margin-bottom: 20px;
  }

  .modal label {
    display: block;
    margin-bottom: 5px;
  }

  .modal input {
    width: 100%;
    padding: 8px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }

  .modal button {
    padding: 8px 16px;
    margin: 10px 5px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
`;

// Temas
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

// Estilos específicos para TableContainer
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

// Estilos para las celdas de la tabla
const thStyle = {
    textAlign: 'left',
    padding: '0.75rem',
    fontWeight: 'bold',
    backgroundColor: '#f3f4f6',
    fontSize: '0.875rem',
};

const tdStyle = {
    padding: '0.75rem',
    fontSize: '0.875rem',
};

export default ProveedorPage;