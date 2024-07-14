import './App.css';
import Header from './komponente/Header';
import FiguricaLista from './komponente/FiguricaLista';
import { useEffect, useRef, useState } from 'react';
import { sveFigurice } from './api/figuriceServise';
import { Navigate, Route, Routes } from 'react-router-dom';



function App() {

  const modelRef = useRef();

  //use statovi
  const [data, setData] = useState({});
  const [trenutnaStrana, setTrenutnaStrana] = useState(0);

  const dobijSveFigurice = async (strana=0, velicina=10) => {
    try {
      setTrenutnaStrana(strana);
      const {data} =  await sveFigurice(strana, velicina);
      setData(data);
      console.log(data);
    } catch (err) {
      console.log(err)
    }
  }

  useEffect(() => {
    dobijSveFigurice();
  }, [])

  const toggleModel = (show) => show ? modelRef.current.showModal() : modelRef.current.close();

  return (
    <>
      <Header toggleModel={toggleModel} brojFigurica={data.totalElements}></Header>
      <main className='main'>
        <div className='container'>
          <Routes>
            <Route path="/" element={<Navigate to="/figurice" />} />
            <Route path="/figurice" element={<FiguricaLista data={data} trenutnaStrana={trenutnaStrana} dobijSveFigurice={dobijSveFigurice} />} />
          </Routes>
        </div>
      </main>

      <dialog ref={modelRef}  className="modal" id="modal">
        <div className="modal__header">
          <h3>Nova figurica</h3>
          <i onClick={() => toggleModel(false)} className='bi bi-x-lg'></i> {/* Ikona za zatvaranje */}
        </div>
        <div className="divider"></div>
        <div className="modal__body">
          <form>
            <div className="user-details">
              <div className="input-box">
                <span className="details">Naziv Figurice</span>
                <input type="text" name='naziv' required />
              </div>
              <div className="input-box">
                <span className="details">Kategorija Figurice</span>
                <input type="text" name='kategorija' required />
              </div>
              <div className="input-box">
                <span className="details">Materijal figurice</span>
                <input type="text" name='materijal' required />
              </div>
              <div className="file-input">
                <span className="details">Slika figurice</span>
                <input type="file" name='photo' required />
              </div>
            </div>
            <div className="form_footer">
              <button type='button' className="btn btn-danger" onClick={() => toggleModel(false)}>Odustani</button>
              <button type='submit' className="btn btn-primary">Sacuvaj</button>
            </div>
          </form>
        </div>
      </dialog>
    </>
  );
}

export default App;
