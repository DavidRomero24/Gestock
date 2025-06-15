import { useState } from 'react';
import styled, { ThemeProvider } from "styled-components";
import { Outlet, Link } from 'react-router-dom';

const EmpleadoPage = () => {
    // Estado inicial de los empleados
    const [employees, setEmployees] = useState([
        {
            name: "Adriana Santiago",
            role: "HR technician - Human resources",
            manager: "Alvaro De Las Cuevas",
            start: "May 20, 2020",
            end: "",
        },
        {
            name: "Arturo Zamora",
            role: "Developer - Development and Operations",
            manager: "Pedro Rodríguez",
            start: "Jan 14, 2018",
            end: "",
        },
        {
            name: "Aida Barrera",
            role: "Developer - Development and Operations",
            manager: "Pedro Rodríguez",
            start: "Nov 04, 2016",
            end: "",
        },
        {
            name: "Ana María Dominguín",
            role: "Recruiter - HR Recruitment",
            manager: "Ainara Sánchez",
            start: "May 25, 2018",
            end: "",
        },
    ]);

    // Estado para el modo oscuro
    const [darkMode, setDarkMode] = useState(false);
    const theme = darkMode ? darkTheme : lightTheme;
    const toggleTheme = () => {
        setDarkMode(!darkMode);
    };

    // Estado para el modal de agregar empleado
    const [showAddModal, setShowAddModal] = useState(false);
    const [newEmployee, setNewEmployee] = useState({
        name: "",
        role: "",
        manager: "",
        start: "",
        end: "",
    });

    // Estado para el modal de editar empleado
    const [showEditModal, setShowEditModal] = useState(false);
    const [editingEmployee, setEditingEmployee] = useState(null);

    // Estado para el modal de ver detalles del empleado
    const [showViewModal, setShowViewModal] = useState(false);
    const [selectedEmployee, setSelectedEmployee] = useState(null);

    // Manejar cambios en el formulario de nuevo empleado
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewEmployee({ ...newEmployee, [name]: value });
    };

    // Agregar un nuevo empleado
    const handleAddEmployee = () => {
        if (!newEmployee.name || !newEmployee.role || !newEmployee.manager || !newEmployee.start) {
            alert("Por favor, complete todos los campos obligatorios.");
            return;
        }
        setEmployees([...employees, newEmployee]);
        setShowAddModal(false); // Cerrar el modal
        setNewEmployee({ name: "", role: "", manager: "", start: "", end: "" }); // Reiniciar el formulario
    };

    // Editar un empleado existente
    const handleEditEmployee = (employee) => {
        setEditingEmployee(employee);
        setShowEditModal(true);
        setNewEmployee({ ...employee }); // Prellenar el formulario con los datos actuales
    };

    // Guardar cambios al editar un empleado
    const handleUpdateEmployee = () => {
        if (!newEmployee.name || !newEmployee.role || !newEmployee.manager || !newEmployee.start) {
            alert("Por favor, complete todos los campos obligatorios.");
            return;
        }
        const updatedEmployees = employees.map((emp) =>
            emp === editingEmployee ? { ...emp, ...newEmployee } : emp
        );
        setEmployees(updatedEmployees);
        setShowEditModal(false);
        setEditingEmployee(null);
        setNewEmployee({ name: "", role: "", manager: "", start: "", end: "" });
    };

    // Ver detalles de un empleado
    const handleViewEmployee = (employee) => {
        setSelectedEmployee(employee);
        setShowViewModal(true);
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
                            <li style={{ backgroundColor: '#eaeded' }}><Link to="/EmpleadoPage">Empleados</Link></li>
                            <li><Link to="/ClientePage">Clientes</Link></li>
                            <li><Link to="/ProveedorPage">Proveedores</Link></li>
                            <li><Link to="/ProductoPage">Productos</Link></li>
                            <li><Link to="/ServicioPage">Servicios</Link></li>
                            <li><Link to="/FacturaPage">Facturas</Link></li>
                            <li><Link to="/SuministroPage">Suministros</Link></li>
                        </ul>
                    </nav>
                    <main className="dashboard-content">
                        <EmployeeManagement>
                            <div className="topbar">
                                <span style={{ fontWeight: "bold" }}>GESTOCK</span>
                            </div>
                            <div className="toolbar">
                                <input type="text" placeholder="Buscar por filtro" className="search-input" />
                                <div className="action-buttons">
                                    <button className="btn-primary">Exportar datos</button>
                                    <button className="btn-primary" onClick={() => setShowAddModal(true)}>Agregar empleado</button>
                                </div>
                            </div>
                            <div className="employee-table">
                                <table>
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Nombre</th>
                                            <th>Categoría</th>
                                            <th>Jefe directo</th>
                                            <th>Fecha de inicio</th>
                                            <th>Fecha de fin</th>
                                            <th>Acceso</th>
                                            <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {employees.map((emp, idx) => (
                                            <tr key={idx}>
                                                <td><input type="checkbox" /></td>
                                                <td><span className="name-link" onClick={() => handleViewEmployee(emp)}>{emp.name}</span></td>
                                                <td>{emp.role}</td>
                                                <td>
                                                    <div className="manager">
                                                        <div className="avatar"></div>
                                                        {emp.manager}
                                                    </div>
                                                </td>
                                                <td>{emp.start}</td>
                                                <td>{emp.end || ""}</td>
                                                <td><div className="status-dot"></div></td>
                                                <td>
                                                    <button className="edit-btn" onClick={() => handleEditEmployee(emp)}>Editar</button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </EmployeeManagement>
                    </main>
                </div>

                {/* Modal para agregar empleado */}
                {showAddModal && (
                    <div className="modal-overlay">
                        <div className="modal">
                            <h2>Agregar Nuevo Empleado</h2>
                            <form>
                                <label htmlFor="name">Nombre:</label>
                                <input
                                    type="text"
                                    id="name"
                                    name="name"
                                    value={newEmployee.name}
                                    onChange={handleInputChange}
                                    required
                                />

                                <label htmlFor="role">Categoría:</label>
                                <input
                                    type="text"
                                    id="role"
                                    name="role"
                                    value={newEmployee.role}
                                    onChange={handleInputChange}
                                    required
                                />

                                <label htmlFor="manager">Jefe Directo:</label>
                                <input
                                    type="text"
                                    id="manager"
                                    name="manager"
                                    value={newEmployee.manager}
                                    onChange={handleInputChange}
                                    required
                                />

                                <label htmlFor="start">Fecha de Inicio:</label>
                                <input
                                    type="date"
                                    id="start"
                                    name="start"
                                    value={newEmployee.start}
                                    onChange={handleInputChange}
                                    required
                                />

                                <label htmlFor="end">Fecha de Fin:</label>
                                <input
                                    type="date"
                                    id="end"
                                    name="end"
                                    value={newEmployee.end}
                                    onChange={handleInputChange}
                                />

                                <button type="button" onClick={handleAddEmployee}>Guardar</button>
                                <button type="button" onClick={() => setShowAddModal(false)}>Cancelar</button>
                            </form>
                        </div>
                    </div>
                )}

                {/* Modal para editar empleado */}
                {showEditModal && (
                    <div className="modal-overlay">
                        <div className="modal">
                            <h2>Editar Empleado</h2>
                            <form>
                                <label htmlFor="name">Nombre:</label>
                                <input
                                    type="text"
                                    id="name"
                                    name="name"
                                    value={newEmployee.name}
                                    onChange={handleInputChange}
                                    required
                                />

                                <label htmlFor="role">Categoría:</label>
                                <input
                                    type="text"
                                    id="role"
                                    name="role"
                                    value={newEmployee.role}
                                    onChange={handleInputChange}
                                    required
                                />

                                <label htmlFor="manager">Jefe Directo:</label>
                                <input
                                    type="text"
                                    id="manager"
                                    name="manager"
                                    value={newEmployee.manager}
                                    onChange={handleInputChange}
                                    required
                                />

                                <label htmlFor="start">Fecha de Inicio:</label>
                                <input
                                    type="date"
                                    id="start"
                                    name="start"
                                    value={newEmployee.start}
                                    onChange={handleInputChange}
                                    required
                                />

                                <label htmlFor="end">Fecha de Fin:</label>
                                <input
                                    type="date"
                                    id="end"
                                    name="end"
                                    value={newEmployee.end}
                                    onChange={handleInputChange}
                                />

                                <button type="button" onClick={handleUpdateEmployee}>Guardar Cambios</button>
                                <button type="button" onClick={() => setShowEditModal(false)}>Cancelar</button>
                            </form>
                        </div>
                    </div>
                )}

                {/* Modal para ver detalles del empleado */}
                {showViewModal && (
                    <div className="modal-overlay">
                        <div className="modal">
                            <h2>Detalles del Empleado</h2>
                            <p><strong>Nombre:</strong> {selectedEmployee?.name}</p>
                            <p><strong>Categoría:</strong> {selectedEmployee?.role}</p>
                            <p><strong>Jefe Directo:</strong> {selectedEmployee?.manager}</p>
                            <p><strong>Fecha de Inicio:</strong> {selectedEmployee?.start}</p>
                            <p><strong>Fecha de Fin:</strong> {selectedEmployee?.end || "N/A"}</p>
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
            </StyledWrapper>
        </ThemeProvider>
    );
};

// Estilos globales
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

  a {
    color: black;
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
    padding: 80px 640px;
    width: calc(100% - 220px);
    box-sizing: border-box;
    min-height: 100vh;
    background: ${(props) => props.theme.background};
    display: flex;
    justify-content: center;
    align-items: start;
  }

  .stats-container {
    display: flex;
    grid-template-columns: repeat(2, 1fr);
    gap: 20px;
    justify-content: center;
  }

  .stat-card {
    background-color: ${(props) => props.theme.cardBackground};
    padding: 20px;
    text-align: center;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }

  .stat-value {
    font-size: 2rem;
    font-weight: bold;
  }

  .data-section {
    margin-bottom: 30px;
    background-color: rgba(255, 255, 255, 0.05);
    padding: 20px;
    border-radius: 12px;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
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

  .control-paneling {
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

  .control-panel li:hover {
    background-color: ${(props) => props.theme.hoverBackground};
    color: ${(props) => props.theme.text};
  }

  .container-one {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  .book-one {
    position: relative;
    border-radius: 10px;
    width: 420px;
    height: 500px;
    background-color: white;
    box-shadow: 1px 1px 12px #000;
    transform: preserve-3d;
    perspective: 2000px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #000;
  }

  .info-one {
    display: block;
  }

  .img_LogoGestock-one {
    width: 35%;
    height: auto;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-left: auto;
    margin-right: auto;
  }

  .cover-one {
    top: 0;
    position: absolute;
    background-color: white;
    width: 100%;
    height: 100%;
    border-radius: 10px;
    cursor: pointer;
    transition: all 0.5s;
    transform-origin: 0;
    box-shadow: 1px 1px 12px #000;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  .img_gestock-one {
    width: auto;
    height: 28%;
    margin-top: 20px;
    margin-left: auto;
    margin-right: auto;
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

// Estilos específicos para EmployeeManagement
const EmployeeManagement = styled.div`
  min-height: 100vh;
  background: #f9fafb;
  font-family: Arial, sans-serif;
  display: flex;
  flex-direction: column;

  .topbar {
    background: white;
    border-bottom: 1px solid #e5e7eb;
    padding: 0.75rem 1.5rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .toolbar {
    background: white;
    border-bottom: 1px solid #e5e7eb;
    padding: 0.75rem 1.5rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .employee-table {
    flex: 1;
    padding: 1rem 1.5rem;
    background: white;
  }

  table {
    width: 100%;
    border-collapse: collapse;
    font-size: 0.9rem;
  }

  th,
  td {
    padding: 0.75rem 0.5rem;
    border-bottom: 1px solid #e5e7eb;
    text-align: left;
  }

  th {
    background: #f3f4f6;
  }

  .name-link {
    color: #2563eb;
    font-weight: bold;
    cursor: pointer;
  }

  .manager {
    display: flex;
    align-items: center;
    gap: 0.5rem;
  }

  .avatar {
    width: 32px;
    height: 32px;
    border-radius: 50%;
    background: #cbd5e1;
  }

  .status-dot {
    width: 10px;
    height: 10px;
    background: #22c55e;
    border-radius: 50%;
    margin: auto;
  }

  .action-buttons {
    display: flex;
    gap: 0.5rem;
  }

  .search-input {
    padding: 0.5rem;
    border: 1px solid #d1d5db;
    border-radius: 0.25rem;
    width: 250px;
  }

  .btn-primary {
    background: #3b82f6;
    color: white;
    padding: 0.5rem 1rem;
    border: none;
    border-radius: 0.25rem;
    cursor: pointer;
  }

  .edit-btn {
    background: #ff9f43;
    color: white;
    padding: 0.5rem 1rem;
    border: none;
    border-radius: 0.25rem;
    cursor: pointer;
  }
`;

export default EmpleadoPage;