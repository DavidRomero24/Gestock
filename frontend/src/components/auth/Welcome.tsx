import styled from 'styled-components';

const Welcome = () => {
  return (
    <StyledWrapper>
      <div  className="book " >
        <p>Hello</p>
        <div className="cover">
          <p>Hover Me</p>
        </div>
      </div>
    </StyledWrapper>
  );
};



const StyledWrapper = styled.div`
    height: 100vh;
    width: 100vw;
    display: flex;
    justify-content: center;
    align-items: center;

  .book {
    position: relative;
    border-radius: 10px;
    width: 220px;
    height: 300px;
    background-color: whitesmoke;
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
    background-color: lightgray;
    width: 100%;
    height: 100%;
    border-radius: 10px;
    cursor: pointer;
    transition: all 0.5s;
    transform-origin: 0;
    box-shadow: 1px 1px 12px #000;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .book:hover .cover {
    transition: all 0.5s;
    transform: rotateY(-80deg);
  }

  p {
    font-size: 20px;
    font-weight: bolder;
  }
`;


export default Welcome;
