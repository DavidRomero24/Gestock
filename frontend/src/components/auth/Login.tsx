import React, { useState } from "react";

const Login = () => {
  const [isFlipped, setIsFlipped] = useState(false);

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "flex-start",
        paddingTop: "100px",
        height: "100vh",
        width: "100vw",
        fontFamily: "system-ui, Avenir, Helvetica, Arial, sans-serif",
        // fontWeight: "bold",
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
            width: "750px",
            height: "650px",
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
              <input placeholder="Password" type="password" style={inputStyle} />
              <button type="submit" style={buttonStyle}>
                Ingresar
              </button>
            </form>
            <div style={{
              backgroundColor: "transparent",
              color: "black",
              fontFamily: "sans-serif",
              width: "100%",
              textAlign: "left",
              paddingLeft: "20px",
              marginTop: "-1px",
              fontSize: "14px",
              cursor: "pointer",
            }}>
              ¿Olvidaste tu contraseña?
            </div>

          </div>

          {/* Back */}
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
              Registrarse
            </div>
            
            <form
              style={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                gap: "20px",
              }}
            >
              <div style={{
                display: "flex",
                flexWrap: "wrap",
                justifyContent: "space-between",
                gap: "10px",
              }}>
                <input placeholder="Primer Nombre" type="text" style={{ ...inputStyle, width: "340px" }} />
                <input placeholder="Segundo Nombre" type="text" style={{ ...inputStyle, width: "340px" }} />
              </div>

              <div style={{ width: "715px", 
                marginRight: "31px",
                paddingRight:"1px",
                marginLeft:"9px",
                display: "flex", 
                flexDirection: "column", 
                gap: "18px" 
                }}>
                <input placeholder="Apellidos" type="text" style={inputStyle} />
                <input placeholder="Email" type="email" style={inputStyle} />
                <input placeholder="Teléfono" type="text" style={inputStyle} />
              </div>

              <div style={{
                display: "flex",
                flexWrap: "wrap",
                justifyContent: "space-between",
                gap: "10px",
              }}>
                <input placeholder="Contraseña" type="password" style={{ ...inputStyle, width: "340px" }} />
                <input placeholder="Confirmar contraseña" type="password" style={{ ...inputStyle, width: "340px" }} />
              </div>
              <button type="submit" style={buttonStyle}>
                Registrarse
              </button>
            </form>

          </div>
        </div>
      </div>
    </div>
  );
};

const inputStyle: React.CSSProperties = {
  // width: "340px",
  width: "100%",
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
  paddingBottom: "35px",
  width: "140px",
  height: "40px",
  borderRadius: "5px",
  border: "2px solid #323232",
  backgroundColor: "#000",
  boxShadow: "1px 1px 12px #000",
  fontSize: "17px",
  fontWeight: 600,
  color: "#fff",
  cursor: "pointer",
};

export default Login;
