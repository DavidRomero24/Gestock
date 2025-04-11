import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "../pages/auth/LoginPage";
import WelcomePage from "../pages/auth/WelcomePage";
// import RegisterPage from "../pages/auth/RegisterPage";

export default function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
      {/* <Route path="/" element={<RegisterPage />}/> */}

      <Route path="/" element={<WelcomePage />}/>
        <Route path="/LoginPage" element={<LoginPage />} />
        
        {/* <Route><div className="flex justify-center items-center min-h-screen bg-gray-950">
     
        // {/* <Route path="/register" element={<RegisterPage />} /> */}
      </Routes>
    </BrowserRouter>
  );
}
