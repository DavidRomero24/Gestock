// import React, { useState } from "react";

// const Login = () => {
//   const [isFlipped, setIsFlipped] = useState(false);

//   return (
//     <div
//       style={{
//         display: "flex",
//         justifyContent: "center",
//         alignItems: "center",
//         height: "100vh",
//         width: "100vw",
//         fontFamily: "sans-serif",
//         fontWeight: "bold",
//         background: "#142c3d",
//         backgroundImage:
//           "linear-gradient(0deg, rgba(20, 44, 61, 1) 0%, rgba(78, 144, 230, 1) 100%)",
//       }}
//     >
//       <div
//         style={{
//           display: "flex",
//           flexDirection: "column",
//           alignItems: "center",
//           justifyContent: "center",
//           gap: "30px",
//         }}
//       >
//         {/* Contenedor centrado del switch y etiquetas */}
//         <div style={{ display: "flex", alignItems: "center", gap: "20px" }}>
//           <span
//             style={{
//               textDecoration: isFlipped ? "none" : "underline",
//               color: "#323232",
//               fontWeight: 600,
//               fontSize: 20,
//               width: "100px",
//               textAlign: "right",
//             }}
//           >
//             Log in
//           </span>

//           <label
//             style={{
//               position: "relative",
//               width: "50px",
//               height: "24px",
//               display: "inline-block",
//             }}
//           >
//             <input
//               type="checkbox"
//               checked={isFlipped}
//               onChange={() => setIsFlipped(!isFlipped)}
//               style={{ opacity: 0, width: 0, height: 0 }}
//             />
//             <span
//               style={{
//                 position: "absolute",
//                 cursor: "pointer",
//                 top: 0,
//                 left: 0,
//                 right: 0,
//                 bottom: 0,
//                 backgroundColor: isFlipped ? "#2d8cf0" : "#fff",
//                 border: "2px solid #323232",
//                 borderRadius: "5px",
//                 // boxShadow: "4px 4px #323232",
//                 transition: "0.3s",
//               }}
//             >
//               <span
//                 style={{
//                   position: "absolute",
//                   height: "20px",
//                   width: "20px",
//                   left: isFlipped ? "26px" : "2px",
//                   bottom: "2px",
//                   backgroundColor: "#fff",
//                   border: "2px solid #323232",
//                   borderRadius: "50%",
//                   boxShadow: "0 3px 0 #323232",
//                   transition: "0.3s",
//                 }}
//               ></span>
//             </span>
//           </label>

//           <span
//             style={{
//               textDecoration: isFlipped ? "underline" : "none",
//               color: "#323232",
//               fontWeight: 600,
//               fontSize: 20,
//               width: "100px",
//               textAlign: "left",
//             }}
//           >
//             Sign up
//           </span>
//         </div>

//         {/* Tarjeta giratoria */}
//         <div
//           style={{
//             width: "300px",
//             height: "350px",
//             backgroundColor: "transparent",
//             perspective: "1000px",
//             textAlign: "center",
//             transition: "transform 0.8s",
//             transformStyle: "preserve-3d",
//             transform: isFlipped ? "rotateY(180deg)" : "none",
//             position: "relative",
//           }}
//         >
//           {/* Front */}
//           <div
//             style={{
//               padding: "20px",
//               position: "absolute",
//               display: "flex",
//               flexDirection: "column",
//               justifyContent: "center",
//               background: "lightgrey",
//               gap: "20px",
//               borderRadius: "5px",
//               border: "2px solid #323232",
//               boxShadow: isFlipped ? "none" : "4px 4px #323232",
//               backfaceVisibility: "hidden",
//               width: "100%",
//               height: "100%",
//             }}
//           >
//             <div
//               style={{
//                 fontSize: "25px",
//                 fontWeight: 900,
//                 color: "#323232",
//               }}
//             >
//               Log in
//             </div>

//             <form
//               style={{
//                 display: "flex",
//                 flexDirection: "column",
//                 alignItems: "center",
//                 gap: "20px",
//               }}
//             >
//               <input placeholder="Email" type="email" style={inputStyle} />
//               <input
//                 placeholder="Password"
//                 type="password"
//                 style={inputStyle}
//               />
//               <button type="submit" style={buttonStyle}>
//                 Log in
//               </button>
//             </form>
//           </div>

//           {/* Back */}
//           <div
//             style={{
//               padding: "20px",
//               position: "absolute",
//               display: "flex",
//               flexDirection: "column",
//               justifyContent: "center",
//               background: "lightgrey",
//               gap: "20px",
//               borderRadius: "5px",
//               border: "2px solid #323232",
//               boxShadow: "4px 4px #323232",
//               backfaceVisibility: "hidden",
//               transform: "rotateY(180deg)",
//               width: "100%",
//               height: "100%",
//             }}
//           >
//             <div
//               style={{
//                 fontSize: "25px",
//                 fontWeight: 900,
//                 color: "#323232",
//               }}
//             >
//               Sign up
//             </div>

//             <form
//               style={{
//                 display: "flex",
//                 flexDirection: "column",
//                 alignItems: "center",
//                 gap: "20px",
//               }}
//             >
//               <input placeholder="Name" type="text" style={inputStyle} />
//               <input placeholder="Email" type="email" style={inputStyle} />
//               <input
//                 placeholder="Password"
//                 type="password"
//                 style={inputStyle}
//               />
//               <button type="submit" style={buttonStyle}>
//                 Sign up
//               </button>
//             </form>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// };

// const inputStyle: React.CSSProperties = {
//   width: "250px",
//   height: "40px",
//   borderRadius: "5px",
//   border: "2px solid #323232",
//   backgroundColor: "#fff",
//   boxShadow: "4px 4px #323232",
//   fontSize: "15px",
//   fontWeight: 600,
//   color: "#323232",
//   padding: "5px 10px",
//   outline: "none",
// };

// const buttonStyle: React.CSSProperties = {
//   marginTop: "20px",
//   width: "120px",
//   height: "40px",
//   borderRadius: "5px",
//   border: "2px solid #323232",
//   backgroundColor: "#fff",
//   boxShadow: "4px 4px #323232",
//   fontSize: "17px",
//   fontWeight: 600,
//   color: "#323232",
//   cursor: "pointer",
// };

// export default Login;

import React, { useState } from "react";

const Login = () => {
  const [isFlipped, setIsFlipped] = useState(false);

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
        width: "100vw",
        fontFamily: "sans-serif",
        fontWeight: "bold",
        background: "#142c3d",
        backgroundImage:
          "linear-gradient(0deg, rgba(20, 44, 61, 1) 0%, rgba(78, 144, 230, 1) 100%)",
      }}
    >
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
          gap: "30px",
        }}
      >
        {/* Switch con etiquetas */}
        <div style={{ display: "flex", alignItems: "center", gap: "20px" }}>
          <span
            style={{
              textDecoration: isFlipped ? "none" : "underline",
              color: "#323232",
              fontWeight: 600,
              fontSize: 20,
              width: "150px",
              textAlign: "right",
            }}
          >
            Iniciar Sesión
          </span>

          {/* Toggle switch custom */}
          <div
            onClick={() => setIsFlipped(!isFlipped)}
            style={{
              width: "50px",
              height: "24px",
              borderRadius: "30px",
              backgroundColor: isFlipped ? "#2d8cf0" : "#fff",
              border: "2px solid #323232",
              position: "relative",
              cursor: "pointer",
              transition: "background-color 0.3s",
            }}
          >
            <div
              style={{
                width: "30px",
                height: "30px",
                borderRadius: "50%",
                backgroundColor: "#fff",
                position: "absolute",
                top: "-4px",
                left: isFlipped ? "24px" : "-4px",
                transition: "left 0.3s",
                border: "2px solid #323232",
                boxShadow: "0 3px 0 #323232",
              }}
            />
          </div>

          <span
            style={{
              textDecoration: isFlipped ? "underline" : "none",
              color: "#323232",
              fontWeight: 600,
              fontSize: 20,
              width: "100px",
              textAlign: "left",
            }}
          >
            Registrarse
          </span>
        </div>

        {/* Tarjeta giratoria */}
        <div
          style={{
            width: "420px",
            height: "500px",
            backgroundColor: "transparent",
            perspective: "1000px",
            textAlign: "center",
            transition: "transform 0.8s",
            transformStyle: "preserve-3d",
            transform: isFlipped ? "rotateY(180deg)" : "none",
            position: "relative",
          }}
        >
          {/* Front */}
          <div
            style={{
              padding: "20px",
              position: "absolute",
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              background: "white",
              gap: "20px",
              borderRadius: "5px",
              border: "2px solid #323232",
              boxShadow: isFlipped ? "none" : "1px 1px 12px #000",
              backfaceVisibility: "hidden",
              width: "100%",
              height: "100%",
            }}
          >
            <div
              style={{
                fontSize: "25px",
                fontWeight: 1000,
                color: "#000",
              }}
            >
              Iniciar Sesión
            </div>

            <form
              style={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                gap: "20px",
              }}
            >
              <input placeholder="Email" type="email" style={inputStyle} />
              <input
                placeholder="Password"
                type="password"
                style={inputStyle}
              />
              <button type="submit" style={buttonStyle}>
              Ingresar
              </button>
            </form>
          </div>

          {/* Back */}
          <div
            style={{
              padding: "20px",
              position: "absolute",
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              background: "lightgrey",
              gap: "20px",
              borderRadius: "5px",
              border: "2px solid #323232",
              boxShadow: "1px 1px 12px #000",
              backfaceVisibility: "hidden",
              transform: "rotateY(180deg)",
              width: "100%",
              height: "100%",
            }}
          >
            <div
              style={{
                fontSize: "25px",
                fontWeight: 900,
                color: "#323232",
              }}
            >
              Sign up
            </div>

            <form
              style={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                gap: "20px",
              }}
            >
              <input placeholder="Name" type="text" style={inputStyle} />
              <input placeholder="Email" type="email" style={inputStyle} />
              <input
                placeholder="Password"
                type="password"
                style={inputStyle}
              />
              <button type="submit" style={buttonStyle}>
                Sign up
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

const inputStyle: React.CSSProperties = {
  width: "310px",
  height: "38px",
  borderRadius: "12px",
  border: "2px solid #323232",
  backgroundColor: "#fff",
  // boxShadow: "4px 4px #323232",
  fontSize: "15px",
  fontWeight: 600,
  color: "#323232",
  padding: "5px 10px",
  outline: "none",
};

const buttonStyle: React.CSSProperties = {
  marginTop: "20px",
  width: "120px",
  height: "40px",
  borderRadius: "5px",
  border: "2px solid #323232",
  backgroundColor: "#fff",
  boxShadow: "1px 1px 12px #000",
  fontSize: "17px",
  fontWeight: 600,
  color: "#323232",
  cursor: "pointer",
};

export default Login;
