import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "../pages/auth/LoginPage";
import WelcomePage from "../pages/auth/WelcomePage";
import PageTwo from "../pages/auth/PageTwo";
import ClientePage from "../pages/auth/ClientePage";
import PanelDeControlPage from "../pages/auth/PanelDeControlPage";
import ProveedorPage from "../pages/auth/ProveedorPage";
import ProductoPage from "../pages/auth/ProductoPage";
import ServicioPage from "../pages/auth/ServicioPage";
import FacturaPage from "../pages/auth/FacturaPage";
import SuministroPage from "../pages/auth/SuministroPage";
import EmpleadoPage from "../pages/auth/EmpleadoPage";

export default function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path="/" element={<WelcomePage />} />
        <Route path="/LoginPage" element={<LoginPage />} />
        <Route path="/EmpleadoPage" element={<EmpleadoPage/>}/>
        <Route path="/PageTwo" element={<PageTwo/>}/>
        <Route path="/ClientePage" element={<ClientePage/>}/>
        <Route path="/PanelDeControlPage" element={<PanelDeControlPage/>}></Route>
        <Route path="/ProveedorPage" element={<ProveedorPage />} />
        <Route path="/ProductoPage" element={<ProductoPage />} />
        <Route path="/ServicioPage" element={<ServicioPage />} />
        <Route path="/FacturaPage" element={<FacturaPage />} />
        <Route path="/SuministroPage" element={<SuministroPage />} />
      </Routes>
    </BrowserRouter>
  );
}