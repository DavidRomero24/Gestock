// import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
// import WelcomePage from '../auth/WelcomePage';
// import LoginPage from "../auth/LoginPage";
// import PageTwo from '../auth/PageTwo';
// import InventoryDashboard from '../auth/PageTwo'; // Importa el dashboard
// import PanelDeControl from '../auth/PanelDeControlPage';
// import ClientePage from '../auth/ClientePage';
// import ProveedorPage from '../auth/ProveedorPage';
// import ProductoPage from '../auth/ProductoPage';
// import ServicioPage from '../auth/ServicioPage';
// import FacturaPage from '../auth/FacturaPage';
// import SuministroPage from '../auth/SuministroPage';

// function App() {
//   return (
//     <Router>
//       <Routes>
//         {/* Rutas públicas */}
//         <Route path="/" element={<WelcomePage />} />
//         <Route path="/LoginPage" element={<LoginPage />} />
//       </Routes>
//       <Route path="/PageTwo" element={<PageTwo />} />


//       <Route path="/dashboard" element={<InventoryDashboard />}>
//         <Route index element={<PanelDeControl />} /> // Ruta por defecto
//         <Route path="paneldecontrol" element={<PanelDeControl />} />
//         <Route path="cliente" element={<ClientePage />} />
//         <Route path="proveedor" element={<ProveedorPage />} />
//         <Route path="producto" element={<ProductoPage />} />
//         <Route path="servicio" element={<ServicioPage />} />
//         <Route path="factura" element={<FacturaPage />} />
//         <Route path="suministro" element={<SuministroPage />} />
//       </Route>
//     </Routes>
//     </Router >
//   );
// }

// export default App;