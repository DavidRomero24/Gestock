import { useState } from 'react';
import styled, { ThemeProvider } from 'styled-components';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const EmpleadoPage = () => {
  const navigate = useNavigate();

  // Estado inicial de empleados con ID
  const [employees, setEmployees] = useState([
    {
      id: 1,
      firstName: "Adriana",
      secondName: "",
      lastName: "Santiago",
      secondLastName: "",
      role: "HR technician - Human resources",
      manager: "Alvaro De Las Cuevas",
      startDate: "May 20, 2020",
      endDate: "",
      birthDate: "1990-05-15",
      email: "adriana@example.com",
      phone: "123-456-7890",
      salary: 50000,
      employeeType: "Full-time",
    },
    {
      id: 2,
      firstName: "Arturo",
      secondName: "",
      lastName: "Zamora",
      secondLastName: "",
      role: "Developer - Development and Operations",
      manager: "Pedro Rodríguez",
      startDate: "Jan 14, 2018",
      endDate: "",
      birthDate: "1988-02-10",
      email: "arturo@example.com",
      phone: "987-654-3210",
      salary: 60000,
      employeeType: "Full-time",
    },
    {
      id: 3,
      firstName: "Aida",
      secondName: "",
      lastName: "Barrera",
      secondLastName: "",
      role: "Developer - Development and Operations",
      manager: "Pedro Rodríguez",
      startDate: "Nov 04, 2016",
      endDate: "",
      birthDate: "1985-07-20",
      email: "aida@example.com",
      phone: "555-555-5555",
      salary: 55000,
      employeeType: "Full-time",
    },
    {
      id: 4,
      firstName: "Ana María",
      secondName: "",
      lastName: "Domingún",
      secondLastName: "",
      role: "Recruiter - HR Recruitment",
      manager: "Ainara Sánchez",
      startDate: "May 25, 2018",
      endDate: "",
      birthDate: "1992-03-15",
      email: "anamaria@example.com",
      phone: "444-444-4444",
      salary: 52000,
      employeeType: "Full-time",
    },
  ]);

  // Estado para modo oscuro
  const [darkMode, setDarkMode] = useState(false);
  const theme = darkMode ? darkTheme : lightTheme;
  const toggleTheme = () => {
    setDarkMode(!darkMode);
  };

  // Estados para modales
  const [showAddModal, setShowAddModal] = useState(false);
  const [newEmployee, setNewEmployee] = useState({
    id: null,
    firstName: "",
    secondName: "",
    lastName: "",
    secondLastName: "",
    role: "",
    manager: "",
    startDate: "",
    endDate: "",
    birthDate: "",
    email: "",
    phone: "",
    salary: "",
    employeeType: "",
  });
  const [showEditModal, setShowEditModal] = useState(false);
  const [editingEmployee, setEditingEmployee] = useState(null);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewEmployee({ ...newEmployee, [name]: value });
  };

  const handleAddEmployee = () => {
    if (
      !newEmployee.firstName ||
      !newEmployee.lastName ||
      !newEmployee.role ||
      !newEmployee.manager ||
      !newEmployee.startDate ||
      !newEmployee.birthDate ||
      !newEmployee.email ||
      !newEmployee.phone ||
      !newEmployee.salary ||
      !newEmployee.employeeType
    ) {
      alert("Por favor, complete todos los campos obligatorios.");
      return;
    }

    const newId = employees.length > 0 ? Math.max(...employees.map(e => e.id)) + 1 : 1;

    const newEmp = {
      id: newId,
      ...newEmployee
    };

    setEmployees([...employees, newEmp]);
    setShowAddModal(false);
    setNewEmployee({
      id: null,
      firstName: "",
      secondName: "",
      lastName: "",
      secondLastName: "",
      role: "",
      manager: "",
      startDate: "",
      endDate: "",
      birthDate: "",
      email: "",
      phone: "",
      salary: "",
      employeeType: "",
    });
  };

  const handleEditEmployee = (employee) => {
    setEditingEmployee(employee);
    setShowEditModal(true);
    setNewEmployee({ ...employee });
  };

  const handleUpdateEmployee = () => {
    if (
      !newEmployee.firstName ||
      !newEmployee.lastName ||
      !newEmployee.role ||
      !newEmployee.manager ||
      !newEmployee.startDate ||
      !newEmployee.birthDate ||
      !newEmployee.email ||
      !newEmployee.phone ||
      !newEmployee.salary ||
      !newEmployee.employeeType
    ) {
      alert("Por favor, complete todos los campos obligatorios.");
      return;
    }
    const updatedEmployees = employees.map((emp) =>
      emp === editingEmployee ? { ...emp, ...newEmployee } : emp
    );
    setEmployees(updatedEmployees);
    setShowEditModal(false);
    setEditingEmployee(null);
    setNewEmployee({
      id: null,
      firstName: "",
      secondName: "",
      lastName: "",
      secondLastName: "",
      role: "",
      manager: "",
      startDate: "",
      endDate: "",
      birthDate: "",
      email: "",
      phone: "",
      salary: "",
      employeeType: "",
    });
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
                      <th>ID</th>
                      <th>Primer Nombre</th>
                      <th>Segundo Nombre</th>
                      <th>Primer Apellido</th>
                      <th>Segundo Apellido</th>
                      <th>Fecha de Nacimiento</th>
                      <th>Email</th>
                      <th>Número de Teléfono</th>
                      <th>Salario</th>
                      <th>Tipo de Empleado</th>
                      <th>Acciones</th>
                    </tr>
                  </thead>
                  <tbody>
                    {employees.map((emp, idx) => (
                      <tr key={idx}>
                        <td>{emp.id}</td>
                        <td>{emp.firstName}</td>
                        <td>{emp.secondName || "-"}</td>
                        <td>{emp.lastName}</td>
                        <td>{emp.secondLastName || "-"}</td>
                        <td>{emp.birthDate}</td>
                        <td>{emp.email}</td>
                        <td>{emp.phone}</td>
                        <td>${emp.salary}</td>
                        <td>{emp.employeeType}</td>
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
                <label htmlFor="id">ID:</label>
                <input type="text" id="id" value={newEmployee.id || ""} disabled />

                <label htmlFor="firstName">Primer Nombre:</label>
                <input
                  type="text"
                  id="firstName"
                  name="firstName"
                  value={newEmployee.firstName}
                  onChange={handleInputChange}
                  required
                />
                <label htmlFor="secondName">Segundo Nombre:</label>
                <input
                  type="text"
                  id="secondName"
                  name="secondName"
                  value={newEmployee.secondName}
                  onChange={handleInputChange}
                />
                <label htmlFor="lastName">Primer Apellido:</label>
                <input
                  type="text"
                  id="lastName"
                  name="lastName"
                  value={newEmployee.lastName}
                  onChange={handleInputChange}
                  required
                />
                <label htmlFor="secondLastName">Segundo Apellido:</label>
                <input
                  type="text"
                  id="secondLastName"
                  name="secondLastName"
                  value={newEmployee.secondLastName}
                  onChange={handleInputChange}
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
                <label htmlFor="startDate">Fecha de Inicio:</label>
                <input
                  type="date"
                  id="startDate"
                  name="startDate"
                  value={newEmployee.startDate}
                  onChange={handleInputChange}
                  required
                />
                <label htmlFor="endDate">Fecha de Fin:</label>
                <input
                  type="date"
                  id="endDate"
                  name="endDate"
                  value={newEmployee.endDate}
                  onChange={handleInputChange}
                />
                <label htmlFor="birthDate">Fecha de Nacimiento:</label>
                <input
                  type="date"
                  id="birthDate"
                  name="birthDate"
                  value={newEmployee.birthDate}
                  onChange={handleInputChange}
                  required
                />
                <label htmlFor="email">Email:</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={newEmployee.email}
                  onChange={handleInputChange}
                  required
                />
                <label htmlFor="phone">Número de Teléfono:</label>
                <input
                  type="tel"
                  id="phone"
                  name="phone"
                  value={newEmployee.phone}
                  onChange={handleInputChange}
                  required
                />
                <label htmlFor="salary">Salario:</label>
                <input
                  type="number"
                  id="salary"
                  name="salary"
                  value={newEmployee.salary}
                  onChange={handleInputChange}
                  required
                />
                <label htmlFor="employeeType">Tipo de Empleado:</label>
                <input
                  type="text"
                  id="employeeType"
                  name="employeeType"
                  value={newEmployee.employeeType}
                  onChange={handleInputChange}
                  required
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
                <label htmlFor="id">ID:</label>
                <input type="text" id="id" value={newEmployee.id || ""} disabled />

                <label htmlFor="firstName">Primer Nombre:</label>
                <input
                  type="text"
                  id="firstName"
                  name="firstName"
                  value={newEmployee.firstName}
                  onChange={handleInputChange}
                  required
                />
                <label htmlFor="secondName">Segundo Nombre:</label>
                <input
                  type="text"
                  id="secondName"
                  name="secondName"
                  value={newEmployee.secondName}
                  onChange={handleInputChange}
                />
                <label htmlFor="lastName">Primer Apellido:</label>
                <input
                  type="text"
                  id="lastName"
                  name="lastName"
                  value={newEmployee.lastName}
                  onChange={handleInputChange}
                  required
                />
                <label htmlFor="secondLastName">Segundo Apellido:</label>
                <input
                  type="text"
                  id="secondLastName"
                  name="secondLastName"
                  value={newEmployee.secondLastName}
                  onChange={handleInputChange}
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
                <label htmlFor="startDate">Fecha de Inicio:</label>
                <input
                  type="date"
                  id="startDate"
                  name="startDate"
                  value={newEmployee.startDate}
                  onChange={handleInputChange}
                  required
                />
                <label htmlFor="endDate">Fecha de Fin:</label>
                <input
                  type="date"
                  id="endDate"
                  name="endDate"
                  value={newEmployee.endDate}
                  onChange={handleInputChange}
                />
                <label htmlFor="birthDate">Fecha de Nacimiento:</label>
                <input
                  type="date"
                  id="birthDate"
                  name="birthDate"
                  value={newEmployee.birthDate}
                  onChange={handleInputChange}
                  required
                />
                <label htmlFor="email">Email:</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={newEmployee.email}
                  onChange={handleInputChange}
                  required
                />
                <label htmlFor="phone">Número de Teléfono:</label>
                <input
                  type="tel"
                  id="phone"
                  name="phone"
                  value={newEmployee.phone}
                  onChange={handleInputChange}
                  required
                />
                <label htmlFor="salary">Salario:</label>
                <input
                  type="number"
                  id="salary"
                  name="salary"
                  value={newEmployee.salary}
                  onChange={handleInputChange}
                  required
                />
                <label htmlFor="employeeType">Tipo de Empleado:</label>
                <input
                  type="text"
                  id="employeeType"
                  name="employeeType"
                  value={newEmployee.employeeType}
                  onChange={handleInputChange}
                  required
                />
                <button type="button" onClick={handleUpdateEmployee}>Guardar Cambios</button>
                <button type="button" onClick={() => setShowEditModal(false)}>Cancelar</button>
              </form>
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
    padding: 80px 300px;
    width: calc(100% - 220px);
    box-sizing: border-box;
    min-height: 100vh;
    background: ${(props) => props.theme.background};
    display: flex;
    justify-content: center;
    align-items: start;
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
    z-index: 9999;
  }

  .modal {
    background: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
    width: 400px;
    max-height: 90vh;
    overflow-y: auto;
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