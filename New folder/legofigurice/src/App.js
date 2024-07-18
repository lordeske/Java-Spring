import './App.css';
import Header from './komponente/Header';
import FiguricaLista from './komponente/FiguricaLista';
import { useEffect, useRef, useState } from 'react';
import { sveFigurice, kreirajFiguricu, azurirajSliku, obrisiFiguricu } from './api/figuriceServise';
import { Navigate, Route, Routes, useNavigate, useLocation } from 'react-router-dom';
import FiguricaDetalji from './komponente/FiguricaDetalji';

function App() {
  const modelRef = useRef();
  const slikaRef = useRef();
  const fajlRef = useRef();
  const navigacija = useNavigate();
  const location = useLocation();

  const [data, setData] = useState({});
  const [fajl, setFajl] = useState(undefined);
  const [trenutnaStrana, setTrenutnaStrana] = useState(0);
  const [objekatFigurice, setObjekatFigurice] = useState({
    "nazivFigurice": "",
    "urlSlike": "",
    "kategorijaFigurice": "",
    "materijalFigurice": ""
  });

  const dobijSveFigurice = async (strana = 0, velicina = 10) => {
    try {
      setTrenutnaStrana(strana);
      const { data } = await sveFigurice(strana, velicina);
      setData(data);
      console.log(data);
    } catch (err) {
      console.log(err);
    }
  }

  const fetchObrisiFiguricu = async (id) => {
    try {
      await obrisiFiguricu(id);
      console.log(`Obrisana figurica sa ID: ${id}`);
      dobijSveFigurice();
      navigacija("/");
    } catch (err) {
      console.log(err);
    }
  }

  const kreirajNovuFiguricu = async (e) => {
    e.preventDefault();

    try {
      const { data } = await kreirajFiguricu(objekatFigurice);
      const formData = new FormData();
      formData.append('file', fajl, fajl.name);
      formData.append("idFigurice", data.idFigurice);
      const { data: urlSlike } = await azurirajSliku(formData);
      console.log(urlSlike);
      toggleModel(false)
      dobijSveFigurice();

      setObjekatFigurice({
        "nazivFigurice": "",
        "urlSlike": "",
        "kategorijaFigurice": "",
        "materijalFigurice": ""
      })
      setFajl(undefined);
      fajlRef.current.value = null;
    } catch (err) {
      console.log(err);
    }
  }

  const azurirajFiguricu = async (figurica) => {
    try {
      const { data } = await kreirajFiguricu(figurica);
      console.log(data);
    } catch (err) {
      console.log(err);
    }
  }

  const unesi = (event) => {
    setObjekatFigurice({ ...objekatFigurice, [event.target.name]: event.target.value });
    console.log(objekatFigurice);
  }

  useEffect(() => {
    dobijSveFigurice();
  }, []);

  useEffect(() => {
    if (location.pathname === '/figurice') {
      dobijSveFigurice(trenutnaStrana);
    }
  }, [location]);

  const toggleModel = (show) => show ? modelRef.current.showModal() : modelRef.current.close();

  return (
    <>
      <Header toggleModel={toggleModel} brojFigurica={data.totalElements}></Header>
      <main className='main'>
        <div className='container'>
          <Routes>
            <Route path="/" element={<Navigate to="/figurice" />} />
            <Route path="/figurice" element={<FiguricaLista data={data} trenutnaStrana={trenutnaStrana} dobijSveFigurice={dobijSveFigurice} />} />
            <Route path='/figurice/:id' element={<FiguricaDetalji fetchObrisiFiguricu={fetchObrisiFiguricu} azurirajFiguricu={azurirajFiguricu} />} />
          </Routes>
        </div>
      </main>

      <dialog ref={modelRef} className="modal" id="modal">
        <div className="modal__header">
          <h3>Nova figurica</h3>
          <i onClick={() => toggleModel(false)} className='bi bi-x-lg'></i>
        </div>
        <div className="divider"></div>
        <div className="modal__body">
          <form onSubmit={kreirajNovuFiguricu}>
            <div className="user-details">
              <div className="input-box">
                <span className="details">Naziv Figurice</span>
                <input type="text" name='nazivFigurice' required value={objekatFigurice.nazivFigurice} onChange={unesi} />
              </div>
              <div className="input-box">
                <span className="details">Kategorija Figurice</span>
                <input type="text" name='kategorijaFigurice' required value={objekatFigurice.kategorijaFigurice} onChange={unesi} />
              </div>
              <div className="input-box">
                <span className="details">Materijal figurice</span>
                <input type="text" name='materijalFigurice' required value={objekatFigurice.materijalFigurice} onChange={unesi} />
              </div>
              <div className="file-input">
                <span className="details">Slika figurice</span>
                <input type="file" name='urlSlike' required ref={fajlRef} onChange={(e) => setFajl(e.target.files[0])} />
              </div>
            </div>
            <div className="form_footer">
              <button type='button' className="btn btn-danger" ref={slikaRef} onClick={() => toggleModel(false)}>Odustani</button>
              <button type='submit' className="btn btn-primary">Sacuvaj</button>
            </div>
          </form>
        </div>
      </dialog>
    </>
  );
}

export default App;
