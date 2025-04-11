import styled from 'styled-components';
import { Link } from 'react-router-dom';
// Tarjeta de info de la empresa
const Welcome = () => {
  return (
    <StyledWrapper>
      <div className="container">
      <div  className="book " >
        <p>La info de nuestra empresa</p>
        <div className="cover">
          <h1>Vive una experiencia unica con</h1>
          <img className="img_gestock" src="../src/assets/Gestock.png" alt="Logo de la empresa" />
          <h3>¡El mejor sistema para tu empresa!</h3>
          
        </div>
      </div>

        <div aria-label="User Login Button" tabIndex={0} role="button" className="user-profile">
          <Link to="/LoginPage" className="user">
          <div className="user-profile-inner">
            <svg aria-hidden="true" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
              <g data-name="Layer 2" id="Layer_2">
                <path d="m15.626 11.769a6 6 0 1 0 -7.252 0 9.008 9.008 0 0 0 -5.374 8.231 3 3 0 0 0 3 3h12a3 3 0 0 0 3-3 9.008 9.008 0 0 0 -5.374-8.231zm-7.626-4.769a4 4 0 1 1 4 4 4 4 0 0 1 -4-4zm10 14h-12a1 1 0 0 1 -1-1 7 7 0 0 1 14 0 1 1 0 0 1 -1 1z" />
              </g>
            </svg>
            <p>Access</p>
          </div>
          </Link>
          
        </div>
      </div>
    </StyledWrapper>
  );
};



const StyledWrapper = styled.div`
    background: #142c3d;
    background: linear-gradient(0deg, rgba(20, 44, 61, 1) 0%, rgba(78, 144, 230, 1) 100%);
    // background: #89bafa;
    // background: radial-gradient(circle, rgba(137, 186, 250, 1) 0%, rgba(79, 108, 255, 1) 100%);
    height: 100vh;
    width: 100vw;
    display: flex;
    justify-content: center;
    align-items: center;
    

    .container{
    display: flex;
    flex-direction: column; 
    justify-content: center;
    align-items: center; 
    
    }

// <-------------------Imagen y texto de la tarjeta----------------------------->
    h1{
      font-size: 20px;
    }

    .img_gestock{
      width: auto;
      height: 28%;
      margin-top: 20px;
      margin-left: auto;
      margin-right: auto;
      

    }


  .book {
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

  .cover {
    
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

  .book:hover .cover {
    transition: all 0.5s;
    transform: rotateY(-80deg);
  }

  p {
    font-size: 20px;
    font-weight: bolder;
  }

  // <--------------------------------Boton de acceso--------------------------------------->

  .user-profile {
    width: 131px;
    height: 51px;
    border-radius: 15px;
    cursor: pointer;
    transition: 0.3s ease;
    background: linear-gradient(
      to bottom right,
rgb(46, 255, 161) 0%,
      rgba(46, 142, 255, 0) 30%
    );
    background-color: rgba(46, 142, 255, 0.2);
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 20px auto;
  }

  .user-profile:hover,
  .user-profile:focus {
    background-color: rgba(2, 89, 64, 1);
    box-shadow: 0 0 10px rgba(2, 89, 64, 1);
    outline: none;
  }

  .user-profile-inner {
    width: 127px;
    height: 47px;
    border-radius: 13px;
    background-color: #1a1a1a;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 15px;
    color: #fff;
    font-weight: 600;
  }

  .user-profile-inner svg {
    width: 27px;
    height: 27px;
    fill: #fff;
  }
`;


export default Welcome;
